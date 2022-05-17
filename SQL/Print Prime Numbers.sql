-- https://www.hackerrank.com/challenges/print-prime-numbers/problem
with recursive nums(i) as
(
    select 2
    union all
    select 1+i
    from nums
    where i<1000
)
(
select   GROUP_CONCAT(i
        ORDER BY i
        SEPARATOR '&')
from nums  
where i not in
(
select distinct n1.i as i
from nums as n1
inner join nums as n2 on n2.i<n1.i and n1.i%n2.i=0
)
);
