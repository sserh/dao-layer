-- query.sql
SELECT product_name
FROM superschema.ORDERS
         JOIN superschema.CUSTOMERS ON superschema.ORDERS.customer_id = superschema.CUSTOMERS.id
WHERE LOWER(superschema.CUSTOMERS.name) =