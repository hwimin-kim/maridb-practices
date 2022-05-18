-- DepartmentDao:findAll()
select no, name 
	from department
    order by no desc;
    
-- EmployeeDao:findAll()
select no, name, department_no 
	from employee 
    order by no desc;

-- DepartmentDao:update()
update department
	set name = '솔루션개발팀'
    where no = 3;
 -- EmployeeDao:delete()   
delete from employee;