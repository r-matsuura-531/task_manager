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
WHERE
    1 = 1
/*%if taskCondition.hasTitle() */
    AND t.title LIKE /* @infix(taskCondition.getTitle()) */'' ESCAPE '$'
/*%end*/
/*%if taskCondition.hasDescription() */
    AND t.description LIKE /* @infix(taskCondition.getDescription()) */'' ESCAPE '$'
/*%end*/
/*%if taskCondition.hasDueDate() */
    AND t.due_date >= /* taskCondition.getFormattedDueDate() */''
/*%end*/
/*%if taskCondition.hasStatus() */
    AND t.status = /* taskCondition.getStatus().value */''
/*%end*/
/*%if taskCondition.hasCategoryId() */
    AND c.id = /* taskCondition.getCategoryId() */''
/*%end*/
ORDER BY t./*# taskCondition.getOrderBy() */
