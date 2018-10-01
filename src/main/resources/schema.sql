create table public.client
(
   ppsn varchar(16) not null,
   name varchar(255) not null
);

alter table public.client add primary key (ppsn);