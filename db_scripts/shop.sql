insert into user_metadata (email, password, is_verified, access_level)
values ('example@gmail.com', 'FDGD4GF', true, 'SHOP');

select * from user_metadata where email = '' limit 1;

insert into country (id, label)
VALUES (1, 'Europe');
insert into statistic (id)
values (1);

insert into shop (status,
                  country_id,
                  statistic_id,
                  brand,
                  shop_description,
                  user_metadata_id)
VALUES ('ACTIVE', 1, 1, 'ROSHEN', 'Very cool shop', (select id from user_metadata order by id desc limit 1));

select *
from shop
         inner join user_metadata um on shop.user_metadata_id = um.id