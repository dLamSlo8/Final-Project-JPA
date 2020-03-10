-- JPA Queries
-- smpineda
-- Mar 7, 2020

delete from inventory;
delete from quantity;
delete from stock;
delete from orders;
delete from supplier;
delete from store;
delete from safety_training;
delete from employee;

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

-- Find out when a stock is delivered to a specific store.
select name as item, quantity, store_id, deliver_date from orders
   join inventory on (orders.order_id = inventory.order_id)
   join stock on (inventory.stock_id = stock.stock_id)
   
-- Find out which supplier provides a stock with the cheapest price per unit
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
   

-- Regulator, Auditor, Tax Authority (2-3)
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

-- Find any employee with a salary below $12/hr (should be an empty set)
select name, salary from employee
   where salary < 12
   
-- Find all employees who have not received safety training
select e.name, e.hire_date from employee e
   left outer join safety_training st on (e.employee_id = st.employee_id)
   where st.employee_id is null
-- Show the employee name, hire date;

