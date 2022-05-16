-- Outer Join

-- insert into department values(null, '총무');
-- insert into department values(null, '영업');
-- insert into department values(null, '개발');
-- insert into department values(null, '기획');
select * from department;
-- insert into employee values(null, '둘리', 1);
-- insert into employee values(null, '마이콜', 2);
-- insert into employee values(null, '또치', 3);
-- insert into employee values(null, '길동이', null);
select * from employee;

select a.name as '이름', b.name as '부서'
	from employee a , department b
    where a.department_no = b.no;

-- left join    
select a.name as '이름', ifnull(b.name, '---') as '부서'
	from employee a left join department b on a.department_no = b.no;

-- right join
select a.name as '이름', b.name as '부서'
	from employee a right join department b on a.department_no = b.no;