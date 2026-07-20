-- 1. 插入语句
insert into apartment (landlord_id,roomcount) values (1,3);
insert into apartment (landlord_id,roomcount) values (1,3),(2,4);
insert into apartment  values (1,3...); --写入剩下所有的字段值
-- 2. 更新语句
update  apartment set landload_id = 12345, roomcount=99 where id = 88;
-- 3. 删除语句
delete from apartment where id = 99;
-- 4. select查询
select * from apartment;
select column1,column2 from apartment;
-- 5. where查询
select * from apartment where id = 88;
select column1 from apartment where id = 88;
-- 6. 聚合函数查询
select count(*) from apartment;
select sum(area) from room where apartment_id = 88;
select avg(area) from room where apartment_id = 88;
select max(area) from room where apartment_id = 88;
select min(area) from room where apartment_id = 88;
-- 7. 分组查询（必须配合聚合函数一起使用）
select landlord_id, count(*) from apartment group by landlord_id order by landlord_id asc;
-- 8. 排序查询
select * from apartment order by landlord_id;
-- 9. 分页查询
select * from apartment limit 0,10; --第一页十条记录
select * from apartment limit 1,10; --第二页十条记录
-- 10. 内连接
select * from apartment a join landlord l on a.landlord_id = l.id;
select a.community_name,l.name from apartment a join landlord l on a.landlord_id = l.id;

select * from apartment a , landlord l where a.landlord_id = l.id;
select a.community_name,l.name from apartment a, landlord l where a.landlord_id = l.id;
-- 11. 外连接
select * from apartment a left join landlord l on l.id = a.landlord_id;
-- 12. 自链接
select * from TABLE1 a join TABLE1 b on a.column1 = b.column2;
-- 13. 标量子查询
select id from landlord where name = 'xxx';
select id from apartment where landlord_id = (select id from landlord where name = 'xxx');
-- 14. 列子查询
select * from room where apartment_id in (select id from apartment where landlord_id = (select id from landlord where name = 'xxx'));


-- 15. 行子查询
select are,monthly_rent from room where apartment_id in (select id from apartment where landlord_id = (select id from landlord where name = 'xxx'));

select * from room where (are,monthly_rent) in (select are,monthly_rent from room where apartment_id in (select id from apartment where landlord_id = (select id from landlord where name = 'xxx')))
-- 16. 表子查询
select col1,clo2 from TABLE1 where col3=xxx or col4 =xxx;
select * from TABLE1 where (col1,col2) in (select col1,clo2 from TABLE1 where col3=xxx or col4 =xxx);