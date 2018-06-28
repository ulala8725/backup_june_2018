create table dog
(
	id number primary key,
	kind varchar2(20),
	price number,
	image varchar2(20),
	country varchar2(20),
	height number,
	weight number,
	content varchar2(400),
	readcount number
);

create sequence dog_seq
	start with 5
	nocache
;

insert into dog values (1, 'poodle', 1000, 'poodle', 'france', 1, 20, '可愛いプードル', 0);
insert into dog values (2, 'Yorkshire terrier', 2000, 'Yorkshire_terrier', 'england', 1, 10, 'ヨクヨク ヨークシャーテリア', 0);
insert into dog values (3, 'siba', 1500, 'siba', 'japan', 1, 150, 'シバシバ芝犬', 0);
insert into dog values (4, 'Golden Retriever', 3000, 'Golden_Retriever', 'germany', 2, 30, 'ゴルゴル ゴールデン・レトリバー', 0);