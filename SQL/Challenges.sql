--https://www.hackerrank.com/challenges/challenges
select hacker_id,name,num from 
(
    select a.num,count(1) as rep
    from 
    (
        select hacker_id,count(1) as num
        from challenges 
        inner join hackers 
        using(hacker_id)
        group by hacker_id
    )as a
    group by a.num
) as b
inner join 
(
    select hacker_id,name,count(1) as num 
    from hackers 
    inner join challenges  
    using(hacker_id) 
    group by hacker_id,name
) as c 
using(num)
where not(rep>1 and num !=50)
order by num desc,hacker_id;
