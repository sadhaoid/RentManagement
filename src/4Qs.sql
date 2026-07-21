--1
select l."name",l.phone,COUNT(a.id) FROM landlord l LEFT JOIN apartment a on l.id = a.landlord_id and a.community_name LIKE '%Reggie%' GROUP BY l."name", l.phone;

--2
SELECT a.city,COUNT(DISTINCT a.community_name) AS apartment_count, COUNT(r.id) as room_count, TRUNC(AVG(monthly_rent),2) as avg_rent, COUNT(CASE WHEN r.status = 0 THEN 1 END) AS vacant_count,COUNT(CASE WHEN r.status = 1 THEN 1 END) AS rented_count
FROM apartment a LEFT JOIN room r ON r.apartment_id = a."id"
GROUP BY a.city;

--3
SELECT l."name", l.phone,COUNT(DISTINCT a.community_name) as apartment_count,TRUNC(AVG(monthly_rent),2) as avg_rent FROM landlord l
                                                                                                                             LEFT JOIN apartment a ON l."id" = a.landlord_id
                                                                                                                             LEFT JOIN room r ON r.apartment_id = a."id"
GROUP BY l."name",l.phone
HAVING COUNT(DISTINCT a.community_name) >= 2 AND AVG(monthly_rent)>3000

--4
SELECT a.community_name, r.room_number,r.monthly_rent, RANK() OVER(PARTITION BY a.community_name ORDER BY monthly_rent DESC) AS rnk FROM apartment a LEFT JOIN room r ON r.apartment_id = a."id"

SELECT * FROM (
                  SELECT a.community_name, r.room_number,r.monthly_rent, RANK() OVER(PARTITION BY a.community_name ORDER BY monthly_rent DESC) AS rnk FROM apartment a LEFT JOIN room r ON r.apartment_id = a."id") t WHERE t.rnk <=2