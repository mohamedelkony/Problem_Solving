--https://www.hackerrank.com/challenges/binary-search-tree-1/problem
SELECT 
    a.N,
    CASE
        WHEN (min(a.p) IS NOT NULL AND min(b.n) IS NOT NULL) THEN 'Inner'
        WHEN (min(a.p) IS NULL) THEN 'Root'
        ELSE 'Leaf'
    END AS 'node type'
FROM
    bst a
        LEFT JOIN
    bst b ON a.n = b.p
GROUP BY a.n
ORDER BY a.n;
