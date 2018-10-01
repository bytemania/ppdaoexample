create table address(
  id int not null,
  address varchar(75) not null
);

alter table address add primary key (id);


create table customer(
  id int not null,
  name varchar(30) not null,
  address_id int not null,
  referrer_id int
);

alter table customer add primary key (id);
alter table customer add foreign key (address_id) references address(id);
alter table customer add foreign key (referrer_id) references customer(id);


create table orders(
  id int not null,
  customer_id int not null,
  date date not null,
  amount double not null
);

alter table orders add primary key(id);
alter table orders add foreign key(customer_id) references customer(id);


create table item(
  id int not null,
  name varchar(30) not null,
  description varchar(255)
);

alter table item add primary key(id);


create table item_orders(
  order_id int not null,
  item_id int not null
);

alter table item_orders add primary key (order_id, item_id);
alter table item_orders add foreign key(order_id) references orders(id);
alter table item_orders add foreign key (item_id) references item(id);
