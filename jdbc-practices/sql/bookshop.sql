-- bookshop

-- schema 확인
desc book;
desc author;

-- auto_increment reset
delete from book;
alter table book auto_increment 1;

-- AuthorDao:insert
insert into author values(null, '원수연');

-- select author
select * from book;
select * from author;

-- BookDao:insert
insert into book values(null, '...', '재고있음', 1);

-- BookDao:findAll
select a.no, a.title, b.name, a.state_code
	from book a, author b
    where a.author_no = b.no
    order by no asc;