drop database if exists openconnect;
create database openconnect;
use openconnect;

create table user(
id int not null auto_increment,
user_id varchar(25) unique,
user_name varchar(255),
password varchar(255),
user_kana varchar(255),
gender enum('male','female'),
birthday date,
email varchar(255),
credit_number varchar(4),
credit_token varchar(255),
oauth_id varchar(50),
oauth_name varchar(50),
registration_date datetime,
updated_date datetime,
postal_code varchar(8),
address varchar(100),
cell_number varchar(15),
primary key(id)
);
insert into user values
(1,"kuruyama1@docomo.ne.jp","来山1","kuruyama1","クルヤマ1",'male',"2000-01-01 01:00:00","kuruyama1@docomo.ne.jp","0000","","","","2016-03-14 01:00:00","2016-03-14 01:00:00","1111111","東京都文京区湯島1-1-1","08011111111"),
(2,"kuruyama2@docomo.ne.jp","来山2","kuruyama2","クルヤマ2",'female',"2000-01-02 01:00:00","kuruyama2@docomo.ne.jp","0001","","","","2016-03-14 01:00:01","2016-03-14 01:00:01","1111112","東京都文京区湯島1-1-2","08011111112"),
(3,"kuruyama3@docomo.ne.jp","来山3","kuruyama3","クルヤマ3",'male',"2000-01-03 01:00:00","kuruyama3@docomo.ne.jp","0002","","","","2016-03-14 01:00:02","2016-03-14 01:00:02","1111113","東京都文京区湯島1-1-3","08011111113"),
(4,"kuruyama4@docomo.ne.jp","来山4","kuruyama4","クルヤマ4",'female',"2000-01-04 01:00:00","kuruyama4@docomo.ne.jp","0003","","","","2016-03-14 01:00:03","2016-03-14 01:00:03","1111114","東京都文京区湯島1-1-4","08011111114"),
(5,"kuruyama5@docomo.ne.jp","来山5","kuruyama5","クルヤマ5",'female',"2000-01-05 01:00:00","kuruyama5@docomo.ne.jp","0004","","","","2016-03-14 01:00:04","2016-03-14 01:00:04","1111115","東京都文京区湯島1-1-5","08011111115"),
(6,"kuruyama6@docomo.ne.jp","来山6","kuruyama6","クルヤマ6",'male',"2000-01-06 01:00:00","kuruyama6@docomo.ne.jp","0005","","","","2016-03-14 01:00:05","2016-03-14 01:00:05","1111116","東京都文京区湯島1-1-6","08011111116");

create table if not exists site(
id int(10) NOT NULL AUTO_INCREMENT,
name varchar(50),
url varchar(255),
PRIMARY KEY (id)
);

create table if not exists admin(
id int(11) not null auto_increment,
admin_id varchar(25) unique,
password varchar(255) not null,
primary key(id)
);

insert into admin values(1,"1","admin01");


insert into site(name,url)
values('YouJustJewelry','http://www.internousdev.com:8080/YouJustJewelry/'),
('solare','http://www.internousdev.com:8080/solare/'),
('UESTO','http://www.internousdev.com:8080/UESTO/'),
('WorldTravel','http://www.internousdev.com:8080/WorldTravel/'),
('gpscoffee','http://www.internousdev.com:8080/gpscoffee/'),
('la-poupee','http://www.internousdev.com:8080/la-poupee/');