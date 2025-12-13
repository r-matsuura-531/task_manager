SELECT
    m.id,
    m.name,
    m.url,
    m.order_by
FROM
    menu m
ORDER BY m.order_by ASC