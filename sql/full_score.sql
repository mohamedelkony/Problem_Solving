-- https://www.hackerrank.com/challenges/full-score/problem?h_r=profile
select h.hacker_id,h.name
 from 
 Submissions s inner join Challenges c using(challenge_id)
 inner join Difficulty d using(difficulty_level)
  inner join hackers h on s.hacker_id=h.hacker_id
 where s.score=d.score
 group by h.hacker_id,h.name
 having count(1) >1
 order by count(1) desc,h.name;
    
