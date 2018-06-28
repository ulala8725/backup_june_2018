--account: hr
--table structure
	SQL> desc employees
	 名前                                      NULL?    型
	 ----------------------------------------- -------- ----------------------------
	 EMPLOYEE_ID                               NOT NULL NUMBER(6)
	 FIRST_NAME                                         VARCHAR2(20)
	 LAST_NAME                                 NOT NULL VARCHAR2(25)
	 EMAIL                                     NOT NULL VARCHAR2(25)
	 PHONE_NUMBER                                       VARCHAR2(20)
	 HIRE_DATE                                 NOT NULL DATE
	 JOB_ID                                    NOT NULL VARCHAR2(10)
	 SALARY                                             NUMBER(8,2)
	 COMMISSION_PCT                                     NUMBER(2,2)
	 MANAGER_ID                                         NUMBER(6)
	 DEPARTMENT_ID                                      NUMBER(4)

--1) employeesテーブルで部署番号が30である社員のすべての情報を名前を基準にして昇順に出力。
	select * 
	from employees
	where department_id = 30
	order by first_name asc;
	
--2) employeesテーブルで部署番号が30でなくて、職業（job_id）がPU_CLERKではない社員の名前、職業、部署番号を名前を基準にして昇順に出力。
	select first_name||' '||last_name name, job_id, department_id
	from employees
	where department_id != 30
	and job_id != 'pu_clerk'
	order by name asc;
	 
--3) employeesテーブルで入社日が2006年6月15日以前の社員の名字と入社日を入社日を基準にして昇順に出力。
	select first_name, hire_date
	from employees
	where hire_date < to_date('2006-06-15', 'yyyy-mm-dd')
	order by first_name asc;
	 
--4) employeesテーブルで名前が'Ma'に始まる社員の名字と名を出力。
	select first_name, last_name
	from employees
	where first_name like 'Ma%';
	 
--5) employeesテーブルで給料が１１０００以上、コミッションがnullではない社員の名字、名、給料、コミッションを出力。
	select first_name, last_name, salary, commission_pct
	from employees
	where commission_pct is not null
	and salary >= 11000;
	
--6) employeesテーブルで部署番号が90の社員の給料、給料に12をかけた値を給料を基準にして降順出力。
	select salary, salary*12 year_salary
	from employees
	where department_id = 90
	order by salary desc;
	
--7) employeesテーブルで部署番号が100の社員の名前、日給（小数点以下は、切り捨てる）、給料を名前を基準にして整列して出力。
	select first_name||' '||last_name name, floor(salary/30) day_salary, salary
	from employees
	order by salary asc;
	
--8) employeesテーブルで名字と名をつなげて"Name"というカラムに名前を基準にして整列、出力。
	select first_name||' '||last_name name
	from employees
	order by name;
	
--9) 社員番号が偶数の社員の社員番号、名前、職業を出力。
	select employee_id, first_name||' '||last_name name, job_id
	from employees
	where mod(employee_id, 2) = 0;
	
--10) 名前の二番目の文字が 'a'の社員の社員番号、名前、職業を出力。
	select employee_id, last_name, job_id
	from employees
	where substr(last_name, 2, 1) like 'a'; --where last_name like '_a%'; ???
	
--11) 名前が 's'に終わる社員の社員番号、名前、職業を出力。
	select employee_id, last_name, job_id
	from employees
	where substr(last_name, -1, 1) like 's'; --where last_name like '%s';
	
--12) 2007年度に入社した社員の社員番号、名前、入社日を出力。
	select employee_id, first_name||' '||last_name name, hire_date
	from employees
	where to_char(hire_date, 'yyyy') like '2007'; --where substr(hire_date,1,2) = '07';
	 
--13) 名前の文字の数が６字以上の社員の社員番号、名前、給料を出力。
	select employee_id, first_name||' '||last_name name, salary
	from employees
	where length(first_name) >= 6;
	
--14) 格職業別の社員数、最小給与、最大給与、給与の合計、平均給与を出力。
	select job_id, count(*), min(salary), max(salary), sum(salary), avg(salary)
	from employees
	group by job_id;
	
--15) 社員'Chung'が入社した以降に入社した社員の名前と入社日を出力。
	select first_name||' '||last_name name, hire_date
	from employees
	where hire_date > (
		select hire_date
		from employees
		where last_name = 'Chung'
		)
	;
	
--16) 会社内の最大給与と最小給与の差を出力。
	select max(salary)-min(salary)
	from employees;
	
--17) 同一な職業を持っている社員の数を出力。
	select job_id, count(*)
	from employees
	group by job_id;

--18) 名前が'S'に始まる社員の名前、入社日、給与を出力。
	select first_name, hire_date, salary
	from employees
	where first_name like 'S%';
	
--19) 給与が 3000以上で、部署番号が30の社員の中で職業が PU_MANの社員の社員番号、名前、給与を出力。
	select employee_id, first_name||' '||last_name name, salary
	from employees
	where salary >= 3000 and department_id = 30;
	
--20) 各部署の平均給与より高い給与をもらう社員の名前、給与を出力。(テーブル２つ）
	SQL> desc departments;
	 名前                                      NULL?    型
	 ----------------------------------------- -------- ----------------------------
	 DEPARTMENT_ID                             NOT NULL NUMBER(4)
	 DEPARTMENT_NAME                           NOT NULL VARCHAR2(30)
	 MANAGER_ID                                         NUMBER(6)
	 LOCATION_ID                                        NUMBER(4)

	select first_name||' '||last_name name, salary
	from employees e
	where salary > (
		select avg(salary)
		from employees e, departments d
		where e.department_id = d.department_id
		)
	;
--21) 30番の部署の最低給与よりもっと高い給与をもらう社員全員を出力。
	select first_name||' '||last_name name
	from employees
	where salary > (
		select min(salary)
		from employees
		where department_id = 30
	);
	
--22) 会社の平均給与よりもっと高くもらい、名前の文字が５字の社員の名前、給与を出力。
	select first_name||' '||last_name name, salary
	from employees
	where salary > (
		select min(salary)
		from employees
		)
	and length(first_name) = 5;
	
--23) マネージャー番号別の社員数をマネージャー番号を基準にして昇順に整列して出力。 
	select manager_id, count(*)
	from employees
	group by manager_id
	order by manager_id asc;
	
--24) １年間給与として1000000を超えて支給する部署の部署番号と１年間の支給額を出力。
	select 	department_id, sum(salary)*12
	from employees
	group by department_id
	having sum(salary)*12 > 1000000;
	
--25) 名前が 'B'に始まる社員の名前、給与、年給与（給与*12　+　給与*コミッション）を名前を基準にして昇順に出力。
	select first_name||' '||last_name name, salary,
		nvl2(commission_pct, salary*12+salary*commission_pct, salary*12) year_salary
	from employees
	where first_name like 'B%'
	order by name asc;
	
--26) 社員全員の入社日を参照して、1~3月は1/4分岐、4~6月は2/4分岐、7~9月は3/4分岐、10~12月は4/4分岐に出力。
	select hire_date, 
		case
			when substr(hire_date,4,2) between 1 and 3 then '1/4分岐'
			when substr(hire_date,4,2) between 4 and 6 then '2/4分岐'
			when substr(hire_date,4,2) between 7 and 9 then '3/4分岐'
			else '4/4分岐'
		end hire_quarter
	from employees;
	
--27)　社員の職級（job_id)で 文字'_'の以前までの文字列を社員番号と一緒に出力。
사원의 직급 에서 '_'문자의 위치 이전까지의 문자열을 사원번호와 함께 출력하시오.
	select substr(job_id, 1, instr(job_id,'_')-1) job_id, employee_id
	from employees;
	
--28) 各社員の社員番号、名前、部署番号、給与、等級を出力。
	--(部署番号がない場合、Assigningを出力）
	--給与25000未満 20000以上、等級S
	--給与20000未満 15000以上、等級A
	--給与15000未満 10000以上、等級B
	--給与10000未満 5000以上 、等級C
	--給与5000未満、等級D
	select employee_id, first_name||' '||last_name name, 
		nvl(to_char(department_id), 'Assigning') department_id, salary, --nvl2(department_id, to_char(department_id), 'Assigning')
		case 
			when salary < 25000 and salary >= 20000 then 'S'
			when salary >= 15000 then 'A'
			when salary >= 10000 then 'B'
			when salary >= 5000 then 'C'
			else 'D'
		end grade
	from employees;
