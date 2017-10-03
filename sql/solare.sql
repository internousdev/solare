drop database if exists solare;

create database if not exists solare;
use solare;

drop table if exists customer_info;

create table customer_info(
account_number int(10) primary key auto_increment,
customer_id varchar(30) unique,
acc_password varchar(30),
user_uuid varchar(100),
last_name varchar(255),
first_name varchar(255),
customer_mailaddress varchar(255),
customer_gender int(1),
phone_number varchar(30),
register_date timestamp DEFAULT 0,
update_date timestamp DEFAULT current_timestamp on update current_timestamp
);

drop table if exists customerID_info;

create table customerID_info(
customerID_number int(10) primary key auto_increment,
customer_id varchar(30),
user_uuid varchar(100),
insert_date timestamp,
delete_date timestamp
);

drop table if exists reservation_info;

create table reservation_info(
reservation_number int(10) primary key auto_increment,
customer_reservation_number int(1),
account_number int(10),
course_number int(20),
number_of_ppl int(10),
full_name varchar(50),
phone_number varchar(30),
reservation_date timestamp default 0,
reservation_dayofweek int(5),
reservation_time int(5),
register_date timestamp default 0,
update_date timestamp default current_timestamp on update current_timestamp,
delete_date timestamp
);

drop table if exists seat_info;

create table seat_info(
date date,
dayofweek int(1),
max_number_of_ppl int(5),
remaining_seats int(5)
);