-- 집계쿼리: select에 그룹함수가 적용된 경우
-- avg, max, min, count, sum, ...

select avg(salary)
	from salaries;

-- select 절에 그룹함수가 있는 경우, 어떤 컬럼도 select 절에 올 수 없다.
-- emp_no는 아무 의미가 없다.
-- 오류!!!!
select emp_no, avg(salary)
	from salaries;
    
-- query 순서
-- 1) from: 접근 테이블 확인
-- 2) where: 조건에 맞는 row을 선택
-- 3) 집계
-- 4) projection
select avg(salary)
	from salaries
    where emp_no = '10060';
    
-- group by 에 참여하고 있는 컬럼은 projection 이 가능하다.(select 절에 올 수 있다.)
select emp_no, avg(salary) as '평균 연봉', sum(salary)
	from salaries
    group by emp_no
    order by sum(salary) desc, avg(salary) desc;
    
-- having
-- 집계결과(결과 임시 테이블)에서 row를 선택해야 하는 경우라면
-- 이미 where 절은 실행이 끝났기 때문에 having 절에서 이 조건 을 주어야 한다.
select emp_no, avg(salary) as '평균 연봉'
	from salaries
    group by emp_no
    having avg(salary) >= 70000;
    
-- order by
select emp_no, avg(salary) as '평균 연봉'
	from salaries
    group by emp_no
    having avg(salary) >= 70000 
    order by  avg(salary) asc ;

-- 예제
-- salaries 테이블에서 사번이 10060인 직원의 급여 평균과 총합을 출력하세요.
-- 문법적으로 오류!!
-- 의미적으로는 맞다.(where)
select emp_no, avg(salary), sum(salary)
	from salaries
    where emp_no = '10060';
    
-- 문법적으로 옳다.    
select emp_no, avg(salary), sum(salary)
	from salaries
    group by emp_no
    having emp_no = '10060';

