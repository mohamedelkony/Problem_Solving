 -- https://www.hackerrank.com/challenges/harry-potter-and-wands/problem?isFullScreen=false
 select id,age,coins_needed,power 
 from
    wands inner join wands_property using(code)
 where
    (age,power,coins_needed) in (
            select age,power,min(coins_needed) 
    from wands inner join  wands_property using(code)
    group by age,power,code,is_evil
    having is_evil=0
 )
 order by power desc,age desc;

