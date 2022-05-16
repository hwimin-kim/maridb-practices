drop table member;
create table member(
	no int(11) not null auto_increment,
    email varchar(200) not null,
    password varchar(64) not null,
    name varchar(100) not null,
    department varchar(100), 
    primary key(no)
);
desc member;

-- alter
alter table member add juminbunho char(13) not null;
desc member;

alter table member drop juminbunho;
desc member;

alter table member add juminbunho char(13) not null after email;
desc member;

alter table member change department department char(13) not null;
desc member;

alter table member modify department  varchar(100) not null;
desc member;

alter table member add self_intro text;
desc member;

alter table member drop juminbunho;
desc member;

-- insert
insert into member values(null, 'kickscar@gmail.com', password('1234'), '안대혁', '개발팀', null);

insert into member(no, email, name, password, department) values(null, 'kickscar2@gmail.com', '안대혁2', password('1234'),  '개발팀');

select * from member;

-- update
update member
	set email = 'kickscar3', password = password('0123')
    where no = 2;
select * from member;
    
-- delete
delete
	from member
    where no = 2;
select * from member;

-- transaction
select @@autocommit;
set autocommit = 0;
insert into member(no, email, name, password, department) values(null, 'kickscar2@gmail.com', '안대혁5', password('1234'),  '개발팀');
commit;
select * from member;