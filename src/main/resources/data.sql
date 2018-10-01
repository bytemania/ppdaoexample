insert into address(id, address) values(301, '12 Main St., Houston TX 77001');
insert into address(id, address) values(302, '1007 Mountain, Dr., Gotham NY 10286');
insert into address(id, address) values(303, '145 Grand Canal Dublin 1');

insert into customer(id, name, address_id, referrer_id) values(101, 'John Doe', 301, null);
insert into customer(id, name, address_id, referrer_id) values(102, 'Bruce Wayne', 302, 101);
insert into customer(id, name, address_id, referrer_id) values(103, 'James Smith', 303, 101);

insert into orders(id, customer_id, date, amount) values(555, 101, '2018-10-01', 156.78);
insert into orders(id, customer_id, date, amount) values(556, 102, '2018-10-01', 99.99);
insert into orders(id, customer_id, date, amount) values(557, 101, '2018-10-01', 75.00);

insert into item(id, name, description) values(201, 'Tickle Me Elmo', 'It wants to be tickled');
insert into item(id, name, description) values(202, 'District 9 DVD', 'Awesome sci-fi movie');
insert into item(id, name, description) values(203, 'Batarang', 'It is very sharp');

insert into item_orders(order_id, item_id) values (555, 201);
insert into item_orders(order_id, item_id) values (555, 202);
insert into item_orders(order_id, item_id) values (556, 202);
insert into item_orders(order_id, item_id) values (556, 203);
