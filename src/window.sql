CREATE TABLE sales (
                       id INTEGER PRIMARY KEY,
                       emp_name    VARCHAR(20),   -- 员工姓名
                       department  VARCHAR(20),   -- 部门
                       sale_month  DATE,          -- 业绩所属月份
                       amount      INTEGER        -- 当月业绩金额
);


INSERT INTO sales (id, emp_name, department, sale_month, amount)
VALUES
    (1, '张伟', '销售部', '2026-01-01', 42000),
    (2, '张伟', '销售部', '2026-02-01', 38000),
    (3, '张伟', '销售部', '2026-03-01', 51000),
    (4, '李娜', '销售部', '2026-01-01', 39000),
    (5, '李娜', '销售部', '2026-02-01', 41000),
    (6, '李娜', '销售部', '2026-03-01', 43000),
    (7, '王芳', '市场部', '2026-01-01', 28000),
    (8, '王芳', '市场部', '2026-02-01', 30000),
    (9, '王芳', '市场部', '2026-03-01', 27000);


SELECT *, SUM(amount) OVER(PARTITION BY department ORDER BY sale_month) AS total FROM sales ORDER BY "id";

SELECT *, SUM(amount) OVER(PARTITION BY department) AS total FROM sales ORDER BY "id";

SELECT *, SUM(amount) OVER(PARTITION BY emp_name ORDER BY sale_month) AS total FROM sales ORDER BY "id";

SELECT *, ROW_NUMBER() OVER(ORDER BY amount) as rn FROM sales;

SELECT *, ROW_NUMBER() OVER(PARTITION BY department ORDER BY amount) as rn FROM sales;

SELECT *, LAG(amount) OVER(PARTITION BY department ORDER BY sale_month) AS pre FROM sales;

SELECT department,
       sale_month,
       SUM(amount) AS current_month_total -- 当月部门总业绩
--        LAG(SUM(amount)) OVER(PARTITION BY department ORDER BY sale_month) AS pre_month_total -- 上月部门总业绩
FROM sales
GROUP BY department, sale_month;


SELECT department,
       sale_month,
       SUM(amount) AS current_month_total -- 当月部门总业绩
--        LAG(SUM(amount)) OVER(PARTITION BY department ORDER BY sale_month) AS pre_month_total -- 上月部门总业绩
FROM sales
GROUP BY department, sale_month;


SELECT
    sale_month ,
    SUM(amount) AS current_month_total -- 当月部门总业绩
--        LAG(SUM(amount)) OVER(PARTITION BY department ORDER BY sale_month) AS pre_month_total -- 上月部门总业绩
FROM sales
GROUP BY sale_month;


SELECT department,

       SUM(amount) AS current_month_total -- 当月部门总业绩
--        LAG(SUM(amount)) OVER(PARTITION BY department ORDER BY sale_month) AS pre_month_total -- 上月部门总业绩
FROM sales
GROUP BY department;