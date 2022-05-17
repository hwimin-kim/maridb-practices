-- 서브쿼리(SUBQUERY) SQL 문제입니다.

-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
-- SELECT 
--     AVG(salary) AS avg_salary
-- FROM
--     salaries
-- WHERE
--     to_date LIKE '9999%';
SELECT 
    COUNT(*)
FROM
    salaries
WHERE
    to_date LIKE '9999%'
        AND salary > (SELECT 
            AVG(salary) AS avg_salary
        FROM
            salaries
        WHERE
            to_date LIKE '9999%');

-- 문제2. 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 
-- SELECT 
--     a.dept_no, MAX(b.salary)
-- FROM
--     dept_emp a,
--     salaries b
-- WHERE
--     a.emp_no = b.emp_no
--         AND a.to_date LIKE '9999%'
--         AND b.to_date LIKE '9999%'
-- GROUP BY a.dept_no;
SELECT 
    a.emp_no, concat(a.first_name, ' ',a.last_name) as emp_fullname, c.dept_name, d.salary
FROM
    employees a,
    dept_emp b,
    departments c,
    salaries d
WHERE
    a.emp_no = b.emp_no
        AND b.dept_no = c.dept_no
        AND a.emp_no = d.emp_no
        AND b.to_date LIKE '9999%'
        AND d.to_date LIKE '9999%'
        AND (c.dept_no , d.salary) IN (SELECT 
            a.dept_no, MAX(b.salary)
        FROM
            dept_emp a,
            salaries b
        WHERE
            a.emp_no = b.emp_no
                AND a.to_date LIKE '9999%'
                AND b.to_date LIKE '9999%'
        GROUP BY a.dept_no)
ORDER BY d.salary;

-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 
-- SELECT 
--     a.dept_no, AVG(b.salary) AS avg_salary
-- FROM
--     dept_emp a,
--     salaries b
-- WHERE
--     a.emp_no = b.emp_no
--         AND a.to_date LIKE '9999%'
--         AND b.to_date LIKE '9999%'
-- GROUP BY a.dept_no;
SELECT 
    a.emp_no, concat(a.first_name, ' ',a.last_name) as emp_fullname, b.salary, d.avg_salary
FROM
    employees a,
    salaries b,
    dept_emp c,
    (SELECT 
        a.dept_no, AVG(b.salary) AS avg_salary
    FROM
        dept_emp a, salaries b
    WHERE
        a.emp_no = b.emp_no
            AND a.to_date LIKE '9999%'
            AND b.to_date LIKE '9999%'
    GROUP BY a.dept_no) d
WHERE
    a.emp_no = b.emp_no
        AND b.emp_no = c.emp_no
        AND c.dept_no = d.dept_no
        AND b.to_date LIKE '9999%'
        AND c.to_date LIKE '9999%'
        AND b.salary > d.avg_salary;
        
-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
-- SELECT 
--     b.dept_no,
--     CONCAT(a.first_name, ' ', a.last_name) AS manager_name
-- FROM
--     employees a,
--     dept_manager b
-- WHERE
--     a.emp_no = b.emp_no
--         AND b.to_date LIKE '9999%';
SELECT 
    a.emp_no, concat(a.first_name, ' ',a.last_name) as emp_fullname, m.manager_name, b.dept_name
FROM
    employees a,
    departments b,
    dept_emp c,
    (SELECT 
        b.dept_no,
            CONCAT(a.first_name, ' ', a.last_name) AS manager_name
    FROM
        employees a, dept_manager b
    WHERE
        a.emp_no = b.emp_no
            AND b.to_date LIKE '9999%') m
WHERE
    a.emp_no = c.emp_no
        AND c.dept_no = b.dept_no
        AND b.dept_no = m.dept_no
        AND c.to_date LIKE '9999%';

-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.
-- SELECT 
--     b.dept_no, AVG(a.salary) AS avg_salary
-- FROM
--     salaries a,
--     dept_emp b
-- WHERE
--     a.emp_no = b.emp_no
--         AND a.to_date LIKE '9999%'
--         AND b.to_date LIKE '9999%'
-- GROUP BY b.dept_no
-- ORDER BY AVG(a.salary) DESC
-- LIMIT 1
-- ;
SELECT 
    a.emp_no,
    CONCAT(a.first_name, ' ', a.last_name) AS emp_fullname,
    b.title,
    c.salary
FROM
    employees a,
    titles b,
    salaries c,
    dept_emp d,
    (SELECT 
        b.dept_no, AVG(a.salary) AS avg_salary
    FROM
        salaries a, dept_emp b
    WHERE
        a.emp_no = b.emp_no
            AND a.to_date LIKE '9999%'
            AND b.to_date LIKE '9999%'
    GROUP BY b.dept_no
    ORDER BY AVG(a.salary) DESC
    LIMIT 1) e
WHERE
    a.emp_no = b.emp_no
        AND b.emp_no = c.emp_no
        AND c.emp_no = d.emp_no
        AND d.dept_no = e.dept_no
        AND b.to_date LIKE '9999%'
        AND c.to_date LIKE '9999%'
        AND d.to_date LIKE '9999%'
ORDER BY c.salary DESC
;

-- 문제6.
-- 평균 연봉이 가장 높은 부서는? 
-- SELECT 
--     a.dept_no, AVG(b.salary) AS avg_salary
-- FROM
--     dept_emp a,
--     salaries b
-- WHERE
--     a.emp_no = b.emp_no
-- GROUP BY a.dept_no;
SELECT 
    dept_name
FROM
    departments a,
    (SELECT 
        a.dept_no, AVG(b.salary) AS avg_salary
    FROM
        dept_emp a, salaries b
    WHERE
        a.emp_no = b.emp_no
    GROUP BY a.dept_no) b
WHERE
    a.dept_no = b.dept_no
ORDER BY b.avg_salary DESC
LIMIT 1;
    
-- 문제7.
-- 평균 연봉이 가장 높은 직책?
-- SELECT 
--     b.title, AVG(salary) AS avg_salary
-- FROM
--     salaries a,
--     titles b
-- WHERE
--     a.emp_no = b.emp_no
-- GROUP BY b.title;
SELECT 
    a.title
FROM
    (SELECT 
        b.title, AVG(salary) AS avg_salary
    FROM
        salaries a, titles b
    WHERE
        a.emp_no = b.emp_no
    GROUP BY b.title) a
ORDER BY a.avg_salary DESC
LIMIT 1;

-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.
-- SELECT 
--     a.dept_no,
--     CONCAT(c.first_name, ' ', c.last_name) AS manager_fullname,
--     b.salary AS manager_salary
-- FROM
--     dept_manager a,
--     salaries b,
--     employees c
-- WHERE
--     a.emp_no = b.emp_no
--         AND b.emp_no = c.emp_no
--         AND a.to_date LIKE '9999%'
--         AND b.to_date LIKE '9999%';
SELECT 
    c.dept_name,
    CONCAT(a.first_name, ' ', a.last_name) AS emp_fullname,
    d.salary,
    e.manager_fullname,
    e.manager_salary
FROM
    employees a,
    dept_emp b,
    departments c,
    salaries d,
    (SELECT 
        a.dept_no,
            CONCAT(c.first_name, ' ', c.last_name) AS manager_fullname,
            b.salary AS manager_salary
    FROM
        dept_manager a, salaries b, employees c
    WHERE
        a.emp_no = b.emp_no
            AND b.emp_no = c.emp_no
            AND a.to_date LIKE '9999%'
            AND b.to_date LIKE '9999%') e
WHERE
    a.emp_no = b.emp_no
        AND b.dept_no = c.dept_no
        AND a.emp_no = d.emp_no
        AND c.dept_no = e.dept_no
        AND b.to_date LIKE '9999%'
        AND d.to_date LIKE '9999%'
        AND d.salary > e.manager_salary;