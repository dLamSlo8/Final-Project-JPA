
USE `dlam12`;

-- drop table if exists safety_training;
-- drop table if exists purchase_details;
-- drop table if exists quantity;
-- drop table if exists recipe;
-- drop table if exists inventory;
-- drop table if exists stock;
-- drop table if exists product;
-- drop table if exists transaction;
-- drop table if exists work_details;
-- drop table if exists payment_method;
-- drop table if exists loyalty_card;
-- drop table if exists customer;
-- drop table if exists store_workers;
-- drop table if exists store_owners;
-- drop table if exists store_managers;
-- drop table if exists employee;
-- drop table if exists orders;
-- drop table if exists store_suppliers;
-- drop table if exists supplier;
-- drop table if exists store;
-- drop table if exists corporate_location;

-- insert store -> employee -> store_workers -> stock -> stock quantity ->
-- product -> recipe -> customer -> payment_method -> transaction -> purchase_details

insert into store(street_address, city, state, zip_code)
values
("1 Grand Ave.", "San Luis Obispo", "CA", 93405),
("2 Grand Ave.", "San Luis Obispo", "CA", 93405);

insert into employee(access_level, email, name, phone, salary)
values('0', "dlam@mail.com", "Derek", "1234567890", 100),
('2', "boss@mail.com", "Boss", "2345678901", 1000);

insert into store_workers(work_stores_store_id, workers_employee_id)
values
(1, 1),
(2, 1);

insert into store_owners(own_stores_store_id, owners_employee_id)
values 
(1, 2),
(2, 2);

insert into stock(name)
values("bread"),
("peanut butter"),
("jelly"),
("coffee"),
("milk"),
("cup");

insert into quantity(stock_id, store_id, quantity)
values
(1, 1, 1),
(2, 1, 2),
(3, 1, 1),
(4, 1, 2),
(5, 1, 1), 
(6, 1, 1),
(1, 2, 3),
(2, 2, 2),
(3, 2, 1),
(4, 2, 2),
(5, 2, 1),
(6, 2, 1);

insert into product(name, price)
values
("pb&j", 5.00),
("latte", 4.00);

insert into recipe(product_id, stock_id, quantity)
values
(1, 1, 2),
(1, 2, 1),
(1, 3, 1),
(2, 4, 2),
(2, 5, 1),
(2, 6, 1);

insert into customer
values
(), (), (), ();

insert into payment_method(type)
values
("card"),
("cash");

insert into transaction(customer_id, employee_id, store_id, payment_method,
 total_price, sales_tax, date)
values
(1, 1, 1, "cash", 5.50, 10, "2020-01-01 10:00:00"),
(2, 1, 1, "card", 8.80, 10, "2020-01-01 09:00:00"),
(3, 1, 2, "cash", 10.10, 10, "2020-01-02 10:00:00"),
(4, 1, 2, "card", 5.50, 10, "2020-01-02 09:00:00"),
(1, 1, 2, "cash", 15.15, 10, "2020-01-01 11:00:00"),
(3, 1, 1, "card", 4.40, 10, "2020-01-02 04:00:00");

insert into purchase_details(product_id, transaction_id, quantity)
values
(1, 1, 1),
(2, 2, 2),
(1, 3, 2),
(1, 4, 1),
(1, 5, 3),
(2, 6, 1);
-- day 1
--  store 1: 1 pbj 2 latte, $14.30
--  store 2: 3 pbj 0 latte, $15.15
-- day 2
--  store 1: 1 latte, $4.40
--  store 2: 3 pbj 0 latte, $15.60

-- employee
-- 1. Find out what items are available to be sold.
with 
    product_requirements(product_id, name, stock_id, quantity) as
        (select product.product_id as product_id, name, stock_id, quantity
        from product
        join recipe
        on recipe.product_id = product.product_id),
    product_recipe_count(product_id, name, recipe_count) as
        (select product_id, name, count(name)
        from product_requirements
        group by name, product_id),
    store_available_stock(employee_id, store_id, stock_id, quantity) as
        (select employee_id, quantity.store_id, stock_id, quantity
        from quantity
        join 
        (select employee.employee_id as employee_id, work_stores_store_id as store_id
        from employee
        join store_workers
        on store_workers.workers_employee_id = employee.employee_id) as employees_store
        on quantity.store_id = employees_store.store_id),
    product_stock(employee_id, store_id, product_id, name, stock_id, product_quantity, stock_quantity) as
        (select employee_id, store_id, product_id, name, product_requirements.stock_id, 
        product_requirements.quantity as product_quantity,
        store_available_stock.quantity as stock_quantity
        from product_requirements
        join store_available_stock
        on product_requirements.stock_id = store_available_stock.stock_id)   
select employee_id, store_id, product_recipe_count.name, product_recipe_count.product_id 
from product_recipe_count
join
(select employee_id, store_id, product_id, name, sum(if(product_quantity <= stock_quantity, 1, 0)) as satisfied
from product_stock
group by employee_id, store_id, product_id, name) as num_satisfied
on product_recipe_count.recipe_count = num_satisfied.satisfied and
  product_recipe_count.product_id = num_satisfied.product_id
order by store_id, product_id;

-- 2. Find out how much they sold of any given product on any 
--    given day for inventory purposes.
with 
    employees_stores as
        (select employee.employee_id as employee_id, work_stores_store_id as store_id
        from employee
        join store_workers
        on store_workers.workers_employee_id = employee.employee_id),
    store_transactions as
        (select employees_stores.employee_id as employee_id, employees_stores.store_id
        as store_id,
        transaction.transaction_id as transaction_id, transaction.date as date
        from employees_stores
        join transaction
        on employees_stores.employee_id = transaction.employee_id
        and employees_stores.store_id = transaction.store_id),
    transactions_products as
        (select employee_id, store_id, store_transactions.transaction_id
        as transaction_id, date, product_id, quantity
        from store_transactions
        join purchase_details
        on store_transactions.transaction_id = purchase_details.transaction_id)
    
select employee_id, store_id, date(date) as date, product_id, name, sum(quantity) as quantity_sold
from 
    (select employee_id, store_id, date, product.product_id, quantity, product.name
    from transactions_products
    join product
    on transactions_products.product_id = product.product_id) as product_w_name
group by employee_id, store_id, date(date), product_id
order by date(date), product_id;
    
-- owner
-- 1. Calculate daily profit 
with
    owners_stores(owner_id, store_id) as 
        (select employee_id, own_stores_store_id
        from employee
        join store_owners
        on employee.employee_id = store_owners.owners_employee_id),
    store_transactions(owner_id, store_id, date, total_price) as
        (select owner_id, transaction.store_id, date, total_price
        from owners_stores
        join transaction
        on owners_stores.store_id = transaction.store_id)
select owner_id, store_id, date(date), sum(total_price) as daily_profit
from store_transactions
group by owner_id, store_id, date(date)
order by owner_id, store_id, date(date);
-- 2. Find the most and least popular item per day.
with
    owners_stores(owner_id, store_id) as 
        (select employee_id, own_stores_store_id
        from employee
        join store_owners
        on employee.employee_id = store_owners.owners_employee_id),
    store_transactions(owner_id, store_id, transaction_id, date) as
        (select owner_id, transaction.store_id, transaction_id, date
        from owners_stores
        join transaction
        on owners_stores.store_id = transaction.store_id),
    transaction_products(owner_id, store_id, transaction_id, date, 
     product_id, quantity) as
        (select owner_id, store_id, store_transactions.transaction_id, 
         date, product_id, quantity
        from store_transactions
        join purchase_details
        on store_transactions.transaction_id = purchase_details.transaction_id),
    product_quantities(owner_id, store_id, date, product_id,
     quantity) as 
        (select owner_id, store_id, date(date), product_id,
         sum(quantity) as quantity
        from transaction_products
        group by owner_id, store_id, date(date), product_id),
    max_quantities(owner_id, store_id, date, quantity) as
        (select owner_id, store_id, date, max(quantity) as quantity 
        from product_quantities
        group by owner_id, store_id, date),
    min_quantities(owner_id, store_id, date, quantity) as
        (select owner_id, store_id, date, min(quantity) as quantity
        from product_quantities
        group by owner_id, store_id, date),
    max_products(owner_id, store_id, date, max_product_id, max_quantity) as
        (select prodq.owner_id, prodq.store_id, prodq.date, prodq.product_id, prodq.quantity
        from product_quantities as prodq
        join max_quantities as maxq
        on prodq.owner_id = maxq.owner_id and prodq.store_id = maxq.store_id and
         prodq.date = maxq.date and prodq.quantity = maxq.quantity),
    min_products(owner_id, store_id, date, min_product_id, min_quantity) as
        (select prodq.owner_id, prodq.store_id, prodq.date, prodq.product_id, prodq.quantity
        from product_quantities as prodq
        join min_quantities as minq
        on prodq.owner_id = minq.owner_id and prodq.store_id = minq.store_id and
         prodq.date = minq.date and prodq.quantity = minq.quantity)     

select maxp.owner_id, maxp.store_id, maxp.date, maxp.max_product_id, maxp.max_quantity,
 minp.min_product_id, minp.min_quantity
from max_products as maxp
join min_products as minp
on maxp.owner_id = minp.owner_id and maxp.store_id = minp.store_id and 
 maxp.date = minp.date;
-- 3. Find location with best/worst sales per day.
with
    owners_stores(owner_id, store_id) as 
        (select employee_id, own_stores_store_id
        from employee
        join store_owners
        on employee.employee_id = store_owners.owners_employee_id),
    store_transactions(owner_id, store_id, date, total_price) as
        (select owner_id, transaction.store_id, date, total_price
        from owners_stores
        join transaction
        on owners_stores.store_id = transaction.store_id),
    daily_profit(owner_id, store_id, date, profit) as
        (select owner_id, store_id, date(date) as date, sum(total_price) as profit
        from store_transactions
        group by owner_id, store_id, date(date)
        order by owner_id, store_id, date(date)),
    min_profits(owner_id, date, min_profit) as 
        (select owner_id, date, min(profit)
        from daily_profit
        group by owner_id, date),
    max_profits(owner_id, date, max_profit) as 
        (select owner_id, date, max(profit)
        from daily_profit
        group by owner_id, date),
    min_stores(owner_id, date, min_store_id, min_profit) as
        (select p.owner_id, p.date, p.store_id, min_profit
        from daily_profit as p
        join min_profits as minp
        on p.owner_id = minp.owner_id and p.date = minp.date
        and p.profit = minp.min_profit),
    max_stores(owner_id, date, max_store_id, max_profit) as
        (select p.owner_id, p.date, p.store_id, max_profit
        from daily_profit as p
        join max_profits as maxp
        on p.owner_id = maxp.owner_id and p.date = maxp.date
        and p.profit = maxp.max_profit)
select mins.owner_id, mins.date, mins.min_store_id, mins.min_profit, 
 maxs.max_store_id, maxs.max_profit
from min_stores as mins
join max_stores as maxs
on mins.owner_id = maxs.owner_id and mins.date = maxs.date;
