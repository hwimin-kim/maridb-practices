show tables;
desc member;
desc category;
desc book;
desc cart;
desc orders;
desc orders_book;
-- -------------- 순서 -----------------
delete from orders_book;
alter table orders_book auto_increment =1;
delete from orders;
alter table orders auto_increment =1;
delete from cart;
alter table cart auto_increment =1;
delete from member;
alter table member auto_increment =1;
delete from book;
alter table book auto_increment =1;
delete from category;
alter table category auto_increment =1;

 select * from member;
 select * from category;
 select * from book;
 select * from cart;
 select * from orders;
 select * from orders_book;
-- -------------- 끝 -----------------

-- member: insert
-- member: findAll
select * from member;
select no, name, password, phone_number, email from member;

-- category: insert
-- category: findAll
select * from category;
select no, name from category;

-- book: insert
-- book: findAll
select * from book;
select a.no, a.title, a.price, b.name
from book a, category b
where a.category_no = b.no
order by a.no;

-- cart: insert
-- cart: findAll
select * from cart;
select a.name, b.title, c.count
 from member a, book b, cart c
 where a.no = c.member_no
 and b.no = c.book_no;
 -- orders: insert
 -- orders: findAll
select b.orders_number, a.name, a.email, b.pay, b.receive
from member a, orders b
where a.no = b.member_no;
-- orders: findByBook
select a.no, a.title, b.count, a.price * b.count as total_pay
from book a, orders_book b
where a.no = b.book_no;

