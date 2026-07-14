SELECT * from apartment;
SELECT * from room;
SELECT * FROM lease_contract limit 1000;

SELECT landlord_id, COUNT("id") as apartment_count from apartment  GROUP BY landlord_id ORDER BY  landlord_id;

SELECT a.name,COUNT(b."id") as apartment_count
FROM apartment b
         JOIN landlord a ON  a."id" = b.landlord_id
GROUP BY a.name
ORDER BY apartment_count;

SELECT landlord_id,COUNT(b."id") as room_count
FROM apartment a
         JOIN room b ON a."id" = b.apartment_id
GROUP BY landlord_id
ORDER BY room_count;

SELECT landlord_id,COUNT(b."id") as contract_count
FROM apartment a
         JOIN room b ON a."id" = b.apartment_id
         JOIN lease_contract c on c.room_id = b."id"
WHERE c.status = 1
GROUP BY landlord_id
ORDER BY  landlord_id;

--需要理解为什么需要distinct
SELECT b.name,COUNT(DISTINCT a."id") as apartment_count ,COUNT(DISTINCT c."id") as room_count,COUNT(d."id") AS contract_count
from apartment a
         JOIN landlord b ON a.landlord_id = b."id"
         JOIN room c ON a."id" = c.apartment_id
         JOIN lease_contract d ON c.id = d.room_id
WHERE d.status = 1
GROUP BY b.name