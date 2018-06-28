SELECT
	pj.id AS pj_id,
	pj.pjName,
	ta.id AS task_id,
	ta.task_name
FROM
	project pj,
	task ta
WHERE
	pj.id = ta.pj_id