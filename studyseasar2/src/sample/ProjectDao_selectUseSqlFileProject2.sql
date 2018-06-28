SELECT
    ID , PJNAME
FROM
    PROJECT
WHERE
    ID BETWEEN /*startId*/'2' AND /*endId*/'4'
AND
	pjname = /*pjName*/'SqlFile'
ORDER BY
    ID