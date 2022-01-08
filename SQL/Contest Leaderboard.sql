-- https://www.hackerrank.com/challenges/contest-leaderboard/problem?isFullScreen=true
select hacker_id,name,total_score from
(
select hacker_id,sum(mx_score) as total_score from
(
select hacker_id,challenge_id,max(score) as mx_score 
from submissions
group by hacker_id,challenge_id
) as a
group by hacker_id
) as b
inner join hackers using(hacker_id)
where total_score !=0
order by total_score desc ,hacker_id asc;
