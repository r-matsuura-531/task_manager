SELECT
    *
FROM
    category c
WHERE
    1 = 1
/*%if categorySearchCondition.hasName() */
    AND c.name LIKE /* @infix(categorySearchCondition.getName()) */'' ESCAPE '$'
/*%end*/
ORDER BY c./*# categorySearchCondition.getOrderBy() */
