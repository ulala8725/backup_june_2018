SELECT
    ID , PJNAME
FROM
    PROJECT
WHERE
    ID BETWEEN ? AND ?
ORDER BY
    ID