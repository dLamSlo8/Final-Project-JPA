-- Final Project Part 2 JPA

-- Clear tables
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

-- Delete data in tables
delete from safety_training;
delete from purchase_details;
delete from quantity;
delete from recipe;
delete from inventory;
delete from stock;
delete from product;
delete from transaction;
delete from work_details;
delete from payment_method;
delete from loyalty_card;
delete from customer;
delete from store_workers;
delete from store_owners;
delete from store_managers;
delete from employee;
delete from orders;
delete from store_suppliers;
delete from supplier;
delete from store;
delete from corporate_location;

-- Insert statements into tables
insert into stock (stock_id, name) values
   (1, 'Coffee Bean Pack'),
   (2, 'Sugar Packet'),
   (3, 'Cup')
;
insert into store (store_id, city, state, street_address, zip_code) values
   (1, "San Diego", "CA", "1234 Black Mountain Road", "92129"),
   (2, "San Diego", "CA", "1532 Canyonside Lane", "92153"),
   (3, "Los Angeles", "CA", "111 Park Village", "90193")
;
insert into quantity (stock_id, store_id, quantity) values
   (1, 1, 100), -- store 1 has 100 coffee packs
   (2, 1, 10), -- store 1 has 10 sugar packets
   (3, 1, 50) -- store 1 has 50 cups
;
insert into supplier (supplier_id, city, email, name, state, street_address, zip_code) values
   (1, "New York City", "supplier1@gmail.com", "S1", "NY", "73rd Street", "19422"),
   (2, "Los Angeles", "supplier2@gmail.com", "S2", "CA", "183 Arrowhead", "10211")
;
insert into orders(order_id, deliver_date, total_cost, store_id, supplier_id) values
   (1, '2020-03-07', 300, 1, 1), 
   (2, '2020-03-08', 500, 2, 2),
   (3, '2020-03-11', 100, 2, 2)
;
insert into inventory (order_id, stock_id, price_per_unit, quantity) values
   (1, 2, 1.5, 150), -- order 1 is for 150 sugar packets from supplier 1
   (1, 3, 1, 200), -- order 1 is also for 200 cups from supplier 1
   (2, 1, 5, 300), -- order 2 is for 300 coffee packets from supplier 2
   (3, 3, 0.75, 200) -- order 3 is for 200 cups from supplier 2
;
insert into employee (employee_id, access_level, email, hire_date, name, salary) values
   (1, '0', 'steven@yahoo.com', '2020-02-26', 'Steven Pineda', 12),
   (2, '0', 'derek@gmail.com', '2020-03-04', 'Derek Lam', 14),
   (3, '1', 'grace@san.rr.com', '2018-04-05', 'Grace McLeod', 44),
   (4, '0', 'devin@calpoly.edu', '2020-03-07', 'Devin Rosario', 15)
;
insert into safety_training(employee_id, training_date, teacher) values
   (3, '2018-04-07', 3),
   (2, '2020-03-07', 3)
;
insert into store(store_id, street_address, city, state, zip_code) values
   (31, "1 Grand Ave.", "San Luis Obispo", "CA", 93405),
   (32, "2 Grand Ave.", "San Luis Obispo", "CA", 93405)
;
insert into employee(employee_id, access_level, email, name, phone, salary) values
   (31, '0', "dlam@mail.com", "Derek", "1234567890", 100),
   (32, '2', "boss@mail.com", "Boss", "2345678901", 1000)
;
insert into store_workers(work_stores_store_id, workers_employee_id) values
   (31, 31),
   (32, 31)
;
insert into store_owners(own_stores_store_id, owners_employee_id) values 
   (31, 32),
   (32, 32)
;
insert into stock(stock_id, name) values
   (31, "bread"),
   (32, "peanut butter"),
   (33, "jelly"),
   (34, "coffee"),
   (35, "milk"),
   (36, "cup");
insert into quantity(stock_id, store_id, quantity) values
   (31, 31, 1),
   (32, 31, 2),
   (33, 31, 1),
   (34, 31, 2),
   (35, 31, 1), 
   (36, 31, 1),
   (31, 32, 3),
   (32, 32, 2),
   (33, 32, 1),
   (34, 32, 2),
   (35, 32, 1),
   (36, 32, 1)
;
insert into product(product_id, name, price) values
   (31, "pb&j", 5.00),
   (32, "latte", 4.00)
;
insert into recipe(product_id, stock_id, quantity) values
   (31, 31, 2),
   (31, 32, 1),
   (31, 33, 1),
   (32, 34, 2),
   (32, 35, 1),
   (32, 36, 1)
;
insert into customer (id) values
   (31),
   (32),
   (33),
   (34)
;
insert into payment_method(type) values
   ("card"),
   ("cash")
;
insert into transaction(transaction_id, customer_id, employee_id, store_id, payment_method,
 total_price, sales_tax, date) values
   (31, 31, 31, 31, "cash", 5.50, 10, "2020-01-01 10:00:00"),
   (32, 32, 31, 31, "card", 8.80, 10, "2020-01-01 09:00:00"),
   (33, 33, 31, 32, "cash", 10.10, 10, "2020-01-02 10:00:00"),
   (34, 34, 31, 32, "card", 5.50, 10, "2020-01-02 09:00:00"),
   (35, 31, 31, 32, "cash", 15.15, 10, "2020-01-01 11:00:00"),
   (36, 33, 31, 31, "card", 4.40, 10, "2020-01-02 04:00:00")
;
insert into purchase_details(product_id, transaction_id, quantity) values
   (31, 31, 1),
   (32, 32, 2),
   (31, 33, 2),
   (31, 34, 1),
   (31, 35, 3),
   (32, 36, 1)
;
insert into customer values (11, "Joshua");
insert into employee (employee_id, access_level, email, name, phone) values
   (11, '0', 'jsohua@gmail.com', "Jake Sohua", "6666666666"),
   (12, '1', 'lily@gmail.com', "Lily Padilla", "1029366"),
   (13, '2', 'winston@gmail.com', "Winston Rock", "61231666"),
   (14, '0', 'phil@gmail.com', "Phillip Rock", "90211266")
;
insert into store values
   (11, "San Luis Obispo", "CA", "111 Main St.", "91801")
;
insert into store_workers values
   (11, 11),
   (11, 14)
;
insert into store_managers (manage_stores_store_id, managers_employee_id) values
   (11, 1)
;
insert into store_owners values(11, 13);
insert into transaction(transaction_id, sales_tax, total_price, customer_id, employee_id, payment_method, store_id)
values (11, 7.25, 25.25, 11, 11, "cash", 11);
insert into transaction(transaction_id, sales_tax, total_price, customer_id, employee_id, payment_method, store_id)
values (12, 7.25, 87.25, 11, 11, "cash", 11);
insert into product values (11, "Tomato", 20);
insert into product values (12, "Corn", 30);
insert into purchase_details values(11, 11, 2);
insert into purchase_details values(11, 12, 4);

-- Queries (for each of the 10-15 identified in Part 1)

-- Customer Queries
-- 1. Find your most commonly purchased item
with tmp1(product_id, c_product_id) as
    (select
        product_id, count(product_id) as times
        from purchase_details
        inner join transaction on transaction.transaction_id = purchase_details.transaction_id
        where customer_id = 11
        group by product_id),
     tmp2 (max) as
     (select max(c_product_id) from tmp1),
     tmp3 (id, max) as
     (select
        tmp1.product_id, tmp2.max
        from tmp1, tmp2
        where tmp1.c_product_id = tmp2.max
        )
select product.name
from tmp3, product
where product.product_id = tmp3.id;
      
-- 2. Find your most expensive transaction in the past month
SELECT max(total_price) as max_last_month
FROM (select total_price, date
      from transaction
      where customer_id = 11) as this_customer
WHERE date (CURRENT_DATE() - INTERVAL 1 MONTH);

-- Employee Queries
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

-- 2. Find out how much they sold of any given product on any given day for inventory purposes.
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

-- Location Manager / Regional Manager Queries --
-- 1. Find out the number of employees per location
with worker_counts as (
select sw.work_stores_store_id as sw_id, count(*) as workers
from store_workers sw
group by sw_id),
store_worker_counts as (
select s.*, (case when workers is null then 0 else workers end) as workers
from store s
left outer join worker_counts wc on (s.store_id = wc.sw_id)),
owner_counts as (
select so.own_stores_store_id as so_id, count(*) as owners
from store_owners so
group by so_id),
store_owner_counts as (
select s.*, (case when owners is null then 0 else owners end) as owners
from store s
left outer join owner_counts oc on (s.store_id = oc.so_id)),
manager_counts as (
select sm.manage_stores_store_id as sm_id, count(*) as managers
from store_managers sm
group by sm_id),
store_manager_count as (
select s.*, (case when managers is null then 0 else managers end) as managers
from store s
left outer join manager_counts mc on (s.store_id = mc.sm_id)
)
select worker.street_address, worker.city, worker.state, (workers+owners+managers) as employees
from store_worker_counts worker
join store_manager_count m on (worker.store_id = m.store_id)
join store_owner_counts o on (worker.store_id = o.store_id)
where (workers+owners+managers) > 0
;

-- 2. Find out how many locations are in an area
select state, count(store_id) as stores_in_state
from store
where state = "CA";

select city, count(store_id) as stores_in_city
from store
where city = "San Luis Obispo";

-- Supplier Queries --
-- 1. Find out when a stock is delivered to a specific store.
select name as item, quantity, store_id, deliver_date from orders
   join inventory on (orders.order_id = inventory.order_id)
   join stock on (inventory.stock_id = stock.stock_id)
; 
-- 2. Find out which supplier provides a stock with the cheapest price per unit
WITH stock_supplier_price AS (
select stock.name as item, supplier.name as supplier_name, price_per_unit as price
   from orders
   join inventory on (orders.order_id = inventory.order_id)
   join stock on (inventory.stock_id = stock.stock_id)
   join supplier on (orders.supplier_id = supplier.supplier_id)
)
select item, supplier_name, price as cheapest_price_per_unit from stock_supplier_price ssp1
   WHERE price =
   (select MIN(price) from stock_supplier_price ssp2
    where ssp1.item = ssp2.item)
;   

-- Owner / Board of Director Queries -- 
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

-- Regualtor, Auditor, Tax Authority Queries -- 
-- 1. Find any employee with a salary below $12/hr (should be an empty set)
select name, salary from employee
   where salary < 12
;

-- 2. Find all employees who have not received safety training
select e.name, e.hire_date from employee e
   left outer join safety_training st on (e.employee_id = st.employee_id)
   where st.employee_id is null
;