insert into payment_method values ("cash");
insert into payment_method values("card");
insert into customer values (11, "Joshua");
insert into employee(employee_id, access_level, email, name, phone)
values (11, '0', 'jsohua@gmail.com', "Jake Sohua", "6666666666");
insert into store 
values(11, "San Luis Obispo", "CA", "111 Main St.", "91801");
insert into store_workers values(11, 11);
insert into store_managers values(11, 11);
insert into store_owners values(11, 11);
insert into transaction(transaction_id, sales_tax, total_price, customer_id, employee_id, payment_method, store_id)
values (11, 7.25, 25.25, 11, 11, "cash", 11);
insert into transaction(transaction_id, sales_tax, total_price, customer_id, employee_id, payment_method, store_id)
values (12, 7.25, 87.25, 11, 11, "cash", 11);
insert into product values (11, "Tomato", 20);
insert into product values (12, "Corn", 30);
insert into purchase_details values(11, 11, 2);
insert into purchase_details values(11, 12, 4);

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
      


SELECT max(total_price) as max_last_month
FROM (select total_price, date
      from transaction
      where customer_id = 11) as this_customer
WHERE date (CURRENT_DATE() - INTERVAL 1 MONTH);

With tmp1(street, city, state, workers) as
(
select store.street_address, store.city, store.state, count(workers_employee_id) as emp
from store_workers
inner join store on store_workers.work_stores_store_id = store.store_id
group by store.street_address, store.city, store.state),
tmp2 (street, city, state, managers) as
(
select store.street_address, store.city, store.state, count(managers_employee_id) as emp
from store_managers
inner join store on store_managers.manage_stores_store_id = store.store_id
group by store.street_address, store.city, store.state),
tmp3 (street, city, state, owners) as
(
select store.street_address, store.city, store.state, count(owners_employee_id) as emp
from store_owners
inner join store on store_owners.own_stores_store_id = store.store_id
group by store.street_address, store.city, store.state),
tmp4 (street, city, state, emp) as
(select tmp1.street, tmp1.city, tmp1.state, (workers+managers+owners)
from tmp1, tmp2, tmp3)
select * from tmp4;


select state, count(store_id) as stores_in_state
from store
where state = "CA";

select city, count(store_id) as stores_in_city
from store
where city = "San Luis Obispo";
