--create table
create table user
(
	id varchar(5) primary key,
	pass varchar(8),
	name varchar(40),
	kana varchar(40)
);


create table userdetail
(
	no int(5) primary key, --oracleではNUMBER
	id varchar(5),
	birth date,
	club varchar(40)
);

insert into user values ("id1", "1111", "あああ", "aaa");
insert into userdetail values (10001, "id1", "2018-01-01", "あああ");