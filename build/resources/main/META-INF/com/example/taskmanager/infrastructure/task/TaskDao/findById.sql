SELECT
    t.id,
    t.title,
    t.description,
    t.due_date,
    t.status,
    c.name AS category_name
FROM
    task t
INNER JOIN
    task_category tc ON t.id = tc.task_id
INNER JOIN
    category c ON tc.category_id = c.id
WHERE t.id = /* id */''
