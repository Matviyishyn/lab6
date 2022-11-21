insert into user_metadata (email, password, is_verified, access_level)
values ('user@gmail.com', 'yutguy$4', true, 'CUSTOMER');

insert into customer (status, user_metadata_id) values
('ACTIVE', (select id from user_metadata order by id desc limit 1));

insert into customer_identity (name, surname, phone, address)
values ('Elon', 'Musk', '+380923454656', 'Lviv');

select * from customer
inner join customer_identity ci on customer.id = ci.customer_id
inner join user_metadata um on um.id = customer.user_metadata_id;

