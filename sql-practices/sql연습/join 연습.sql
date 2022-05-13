-- inner join

-- 예제1: 현재 근무하고 있는 직원의 이름과 직책을 출력하세요
select a.emp_no, a.first_name, b.title
	from employees as a, titles as b 
    where a.emp_no = b.emp_no           -- join 조건(n-1)
    and b.to_date like '9999%';			-- row 선택 조건
    
-- 예제2: 현재 직원의 이름과 직책을 출력하되 여성 엔지니어만 출력 
select a.emp_no, a.first_name, a.gender, b.title
	from employees as a, titles as b 
    where a.emp_no = b.emp_no		-- join 조건(n-1)
    AND a.gender = 'f'				-- row 선택 조건 1
    AND b.title = 'engineer'		-- row 선택 조건 2
	AND b.to_date like '9999%';		-- row 선택 조건 3
    
--
-- ANSI/ISO SQL1999 JOIN 표준문법
--
-- 1) natural join
-- 두 테이블에 이름이 같은 공통 컬럼이 있으면 조인 조건을 명시하지 않아도 암묵적으로 조인이 됨
	select a.emp_no, a.first_name, b.title
	from employees a natural join titles b
    where b.to_date like '9999%';			-- row 선택 조건

-- 2) join ~ using
-- natural join 문제점
select count(*)
	from salaries a natural join titles b
    where a.to_date like '9999%'
    and b.to_date like '9999%';
    
select count(*)
	from salaries a join titles b using (emp_no)
    where a.to_date like '9999%'
    and b.to_date like '9999%';
    

-- 3) join ~ on
select b.title, avg(a.salary)
	from salaries a join titles b on a.emp_no = b.emp_no
    where a.to_date like '9999%' 
    and b.to_date like '9999%'
    group by b.title
    order by avg(a.salary) desc;