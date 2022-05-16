-- sub query

-- 1) select 절

-- 2) from 절의 서브 쿼리
select now() as a, sysdate() as b, 3+1 as c;
select s.a, s.b
	from (select now() as a, sysdate() as b, 3+1 as c) s ;
    
-- 3) where 절의 서브쿼리
-- 예제
-- 현재, Fai Bale이 근무하는 부서에서 근무하는 직원의 사번, 전체 이름을 출력해보세요.
select dept_no
	from dept_emp a, employees b
    where a.emp_no = b.emp_no
    and a.to_date like '9999%'
    and concat(b.first_name, ' ', b.last_name) = 'Fai Bale';
    
select b.emp_no, b.first_name
	from dept_emp a, employees b
    where a.emp_no = b.emp_no
    and a.to_date like '9999%'
    and dept_no = 'd004';
    
select b.emp_no, b.first_name
	from dept_emp a, employees b
    where a.emp_no = b.emp_no
    and a.to_date like '9999%'
    and dept_no = (select dept_no
				   from dept_emp a, employees b
				   where a.emp_no = b.emp_no
				   and a.to_date like '9999%'
				   and concat(b.first_name, ' ', b.last_name) = 'Fai Bale');

-- 3-1) 단일행 연산자: =, >, <, >=, <=, <>, !=
-- 실습문제 1:   현재 전체사원의 평균 연봉보다 적은 급여를 받는 사원의 이름, 급여를 나타내세요.
select avg(salary)
	from salaries
    where to_date like '9999%';
    
select concat(a.first_name, ' ', a.last_name), b.salary
	from employees a, salaries b
    where a.emp_no = b.emp_no
    and b.to_date like '9999%'
    and b.salary < (select avg(salary)
					from salaries
					where to_date like '9999%')
	order by b.salary desc ;


-- 실습문제 2:   현재 가장적은 평균 급여를 받고 있는 직책에대해서  평균 급여를 구하세요
-- 1) 현재 가장 적은 직책의 평균급여
-- 		1-1) 직책별 평균급여
select a.title, avg(b.salary)
	from titles a, salaries b
    where a.emp_no = b.emp_no
    and a.to_date like '9999%'
    and b.to_date like '9999%'
    group by a.title;
    
--		1-2) 가장 적은 평균급여
select min(c.avg_salary)
	from(select a.title, avg(b.salary) as avg_salary
		from titles a, salaries b
		where a.emp_no = b.emp_no
		and a.to_date like '9999%'
		and b.to_date like '9999%'
		group by a.title) c;
        
-- 2-1) so1: subquery
select a.title, avg(b.salary) as avg_salary
		from titles a, salaries b
		where a.emp_no = b.emp_no
		and a.to_date like '9999%'
		and b.to_date like '9999%'
		group by a.title
        having avg_salary = (select min(c.avg_salary)
							   from(select a.title, avg(b.salary) as avg_salary
									from titles a, salaries b
									where a.emp_no = b.emp_no
									and a.to_date like '9999%'
									and b.to_date like '9999%'
									group by a.title) c);

select min(c.avg_salary)
	from(select a.title, avg(b.salary) as avg_salary
		from titles a, salaries b
		where a.emp_no = b.emp_no
		and a.to_date like '9999%'
		and b.to_date like '9999%'
		group by a.title) c;

-- 2-2) sol2: top-k
select a.title, avg(b.salary)
	from titles a, salaries b
    where a.emp_no = b.emp_no
    and a.to_date like '9999%'
    and b.to_date like '9999%'
    group by a.title
    order by avg(b.salary) asc
    limit 0, 1;

-- 3-2) 복수행 연산자: in, not in, any, all
-- any 사용법
-- 1. =any : in
-- 2. >any, >=any : 최솟값
-- 3. <any, <=any : 최댓값
-- 4. <>any : not in 동일 

-- all 사용법
-- 1. =all : x
-- 2. >all, >=all : 최댓값
-- 3. <all, <=all : 최솟값
-- 4. <>all 

-- 실습문제 3: 현재 급여가  50000 이상인 직원의 이름과 급여를 출력하세요.(급여가 작은 순서대로)
-- 대혁 50001
-- 둘리 60000
select a.first_name, b.salary
	from employees a, salaries b
    where a.emp_no = b.emp_no
    and b.to_date like '9999%'
    and b.salary >=50000
    order by b.salary asc;

-- sol2)

select a.first_name, b.salary
	from employees a, salaries b
    where a.emp_no = b.emp_no
    and b.to_date like '9999%'
    and (a.emp_no, b.salary) in (select emp_no, salary 
								 from salaries
								 where to_date like '9999%'
                                 and salary >= 50000)
	order by b.salary asc;
    
    -- 실습문제 4: 현재, 각 부서별로 최고 월급을 받는 직원의 이름과 월급을 출력하세요.
    
	select a.dept_no ,max(b.salary)
		from dept_emp a, salaries b
        where a.emp_no = b.emp_no
        and a.to_date like '9999%'
        and b.to_date like '9999%'
        group by a.dept_no;
        
	-- sol1) where subquery: in(=any)
    SELECT 
    d.dept_name, b.first_name, c.salary
FROM
    dept_emp a,
    employees b,
    salaries c,
    departments d
WHERE
    a.emp_no = b.emp_no
        AND b.emp_no = c.emp_no
        AND d.dept_no = a.dept_no
        AND a.to_date LIKE '9999%'
        AND c.to_date LIKE '9999%'
        AND (a.dept_no , c.salary) IN (SELECT 
            a.dept_no, MAX(b.salary)
        FROM
            dept_emp a,
            salaries b
        WHERE
            a.emp_no = b.emp_no
                AND a.to_date LIKE '9999%'
                AND b.to_date LIKE '9999%'
        GROUP BY a.dept_no);
-- sol2) from subquery
SELECT 
    d.dept_name, b.first_name, c.salary
FROM
    dept_emp a,
    employees b,
    salaries c,
    departments d,
    (SELECT 
        a.dept_no, MAX(b.salary) AS max_salary
    FROM
        dept_emp a, salaries b
    WHERE
        a.emp_no = b.emp_no
            AND a.to_date LIKE '9999%'
            AND b.to_date LIKE '9999%'
    GROUP BY a.dept_no) e
WHERE
    a.emp_no = b.emp_no
        AND b.emp_no = c.emp_no
        AND d.dept_no = a.dept_no
        AND a.dept_no = e.dept_no
        AND a.to_date LIKE '9999%'
        AND c.to_date LIKE '9999%'
        AND c.salary = e.max_salary;
