-- https://www.hackerrank.com/challenges/sql-projects/problem
set @pro_id=0;
set @pre_date=null;
select min(start_date),max(end_date)from
(
select task_id,start_date,end_date,
if(@pre_date is not null  and datediff (end_date,@pre_date) =1 ,@pro_id ,@pro_id:=@pro_id+1) as pro_id,
@pre_date:=end_date
from projects 
order by start_date
)as t
group by pro_id
order by  datediff(max(end_date),min(start_date)),min(start_date);
