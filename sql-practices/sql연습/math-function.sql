-- 함수: 수학 함수


-- abs
select abs(-1), abs(1);

-- mod
select mod(10, 3);

-- ceil
select ceil(3.14), ceiling(3.14);

-- floor
select floor(3.14);

-- round(x) : x에 가장 근접한 정수
-- round(x, d) : x값 중에 소수점 d 자리에 가장 근접한 실수
select round(1.498),
	   round(1.498, 1),
       round(1.498, 0);
       
-- power(x, y) pow(x, y) : x의 y승
select power(2, 10),
	   pow(2, 10);
       
-- sign(x)
select sing(20), 
	   sign(-100),
       sign(0);
       
-- greatest(x, y, .....), least(x, y, .....)
select greatest(10, 40, 20, 50),
	   least(10, 40, 20, 50);
select greatest('b', 'z', 'k'),
	   greatest('hello', 'hellp');