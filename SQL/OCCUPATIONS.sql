-- https://www.hackerrank.com/challenges/occupations/problem
set @Maxrows=0;
set @r1=1;
set @r2=1;
set @r3=1;
set @r4=1;

select max(T.num) into @maxrows 
from
(select count(1) as num from occupations group by occupation) AS T;
with recursive loc(rownum) as
(
    select 1
    union all
    select 1+rownum
    from loc
    where rownum<@maxrows
), d as
(
select name ,@r1 as rownum,@r1:=@r1+1
from occupations 
where occupation='doctor'
order by name
), p as
(
select name ,@r2 as rownum,@r2:=@r2+1
from occupations 
where occupation='professor'
order by name
), s as
(
select name ,@r3 as rownum,@r3:=@r3+1
from occupations 
where occupation='singer'
order by name
) ,a as
(
select name ,@r4 as rownum,@r4:=@r4+1
from occupations 
where occupation='actor'
order by name
)
select d.name,p.name,s.name,a.name
from loc 
left join d using(rownum)
left join p using(rownum)
left join s using(rownum)
left join a using(rownum);
