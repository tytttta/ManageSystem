create  table customer(
id bigint(20) not null auto_increment,
name varchar(255) default  null,
contact varchar(255) default  null,
telephone varchar(255)default  null,
email varchar(255) default  null,
remark text,
primary key(id)
)engine=InnoDB default charset=utf8;

truncate customer;

insert into customer values (1, 'customer1','ty1','11111','ty1@qq.com', null);


insert into customer values (2, 'customer2','ty2','22222','ty2@qq.com', null);