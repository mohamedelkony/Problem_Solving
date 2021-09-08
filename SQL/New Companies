--https://www.hackerrank.com/challenges/the-company/problem
select company_code,founder,count(distinct l.lead_manager_code)
,count(distinct s.senior_manager_code)
,count(distinct m.manager_code),
count(distinct e.employee_code)
from 
    company c inner join lead_manager l using(company_code)
    inner join senior_manager s using(company_code)
    inner join manager m using (company_code)
    inner join employee e using (company_code)
 group by company_code,founder
order by company_code;
    
