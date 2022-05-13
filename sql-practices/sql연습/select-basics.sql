select *
	from departments
    order by dept_no limit 6, 3;

select dept_no, dept_name 
	from departments;
    
-- alias
-- 예제1: employees 테이블에서 직원의 이름, 성별, 입사일을 출력
select first_name as "firstName", gender as "성별", hire_date as "입사일"
	from employees;  
    
-- 예제2: employees 테이블에서 직원의 이름(성+이름), 성별, 입사일을 출력
select concat(first_name, last_name) as "이름", gender as "성별", hire_date as "입사일"
	from employees;
    
-- distinct
-- 예제: titles 테이블에서 모든 직급의 이름을 출력
select distinct title 
	from titles;

-- where 절 #1
-- 예제: 1991년 이전에 입사한 직원의 이름, 성별, 입사일을 출력
select concat(first_name, last_name) as "이름", gender as "성별", hire_date as "입사일"
	from employees
    where hire_date < '1991-01-01'
    order by hire_date desc;
    
-- where 절 #2: 논리 연산자
-- 예제: 1989년 이전에 입사한 직원의 이름, 성별, 입사일을 출력
select concat(first_name, last_name) as "이름", gender as "성별", hire_date as "입사일"
	from employees
    where hire_date < '1989-01-01' 
    and gender = 'f'  
    order by hire_date desc;
    
-- where 절 #3: in 연산자
-- 예제: dept_emp 테이블에서 부서번호가 d005 or d009에 속한 사원의 사번, 부서번호를 출력
select emp_no, dept_no 
	from dept_emp 
    where dept_no = 'd005' or dept_no = 'd009';

 select emp_no, dept_no 
	from dept_emp 
    where dept_no in ('d005', 'd009');
    
-- where 절 #4: Like
-- 예제: 1989년에 입사한 직원의 이름, 성별, 입사일을 출력
select concat(first_name, last_name) as "이름", gender as "성별", hire_date as "입사일"
	from employees
    where hire_date <='1989-12-31' 
    and hire_date >= '1989-01-01'  
    order by hire_date desc;
    
select concat(first_name, last_name) as "이름", gender as "성별", hire_date as "입사일"
	from employees
    where hire_date between '1989-01-01' and '1989-12-31';
    
select concat(first_name, last_name) as "이름", gender as "성별", hire_date as "입사일"
	from employees
    where hire_date like '1989%';
    
-- order by 절
-- 예제1: 남자 직원의 이름, 성별, 입사일을 입사일 순서로
select concat(first_name, last_name) as "이름", gender as "성별", hire_date as "입사일"
	from employees
    where gender = 'm' 
    order by hire_date;
    
-- 예제2: 직원들의 사번, 월급을 사번과 월급순으로
select emp_no, salary, from_date, to_date
	from salaries
    order by emp_no asc, salary desc;