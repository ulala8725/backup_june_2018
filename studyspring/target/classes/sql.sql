--create table
create table userst
(
	id varchar2(5) primary key,
	pass varchar2(8),
	name varchar2(40),
	kana varchar2(40)
);

create table userdetail
(
	no number(5) primary key,
	id varchar2(5),
	birth date,
	club varchar2(40)
);

create table board
(
	no number(5) primary key,
	title varchar2(100) not null,
	contents varchar2(2000),
	id varchar2(5),
	write_date date,
	hit number,
	save_file varchar2(100),
	save_file_sys varchar2(100),
	foreign key(id) references userst(id)
);

create table reply
(
	no number(5) primary key,
	boardNo number(5),
	comments varchar2(500),
	id varchar2(5),
	write_date date,
	foreign key(id) references userst(id),
	foreign key(boardNo) references board(no)
);


--create sequence
create sequence board_seq
	nocache
;

create sequence reply_seq
	nocache
;


--insert dummy data
insert all
	into userst values ('id1', '1111', 'あああ', 'aaa')
	into userdetail values (10001, 'id1', '2002-02-02', 'ああ')
select * from dual
;

insert into board values
(
	board_seq.nextVal, 'dummy title', 'dummy contents', 'id1', sysdate, 0
);

insert into reply values
(
	reply_seq.nextVal, 51, 'commentsを書きます。', 'id1', sysdate
);

