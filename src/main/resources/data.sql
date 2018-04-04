/**
 * CREATE Script for init of DB
 */

-- Create 3 OFFLINE drivers

insert into driver (driver_id, date_created, deleted, online_status, password, username) values (1, now(), false, 'OFFLINE',
'driver01pw', 'driver01');
insert into driver (driver_id, date_created, deleted, online_status, password, username) values (2, now(), false, 'OFFLINE',
'driver01pw', 'driver02');

insert into driver (driver_id, date_created, deleted, online_status, password, username) values (3, now(), false, 'OFFLINE',
'driver02pw', 'driver03');
insert into driver (driver_id, date_created, deleted, online_status, password, username) values (4, now(), false, 'OFFLINE',
'driver02pw', 'driver04');

insert into driver (driver_id, date_created, deleted, online_status, password, username) values (5, now(), false, 'OFFLINE',
'driver03pw', 'driver05');
insert into driver (driver_id, date_created, deleted, online_status, password, username) values (6, now(), false, 'OFFLINE',
'driver03pw', 'driver06');


-- Create 3 ONLINE drivers

insert into driver (driver_id, date_created, deleted, online_status, password, username) values (7, now(), false, 'ONLINE',
'driver04pw', 'driver07');
insert into driver (driver_id, date_created, deleted, online_status, password, username) values (8, now(), false, 'ONLINE',
'driver04pw', 'driver08');

insert into driver (driver_id, date_created, deleted, online_status, password, username) values (9, now(), false, 'ONLINE',
'driver05pw', 'driver09');
insert into driver (driver_id, date_created, deleted, online_status, password, username) values (10, now(), false, 'ONLINE',
'driver05pw', 'driver10');

insert into driver (driver_id, date_created, deleted, online_status, password, username) values (11, now(), false, 'ONLINE',
'driver06pw', 'driver11');
insert into driver (driver_id, date_created, deleted, online_status, password, username) values (12, now(), false, 'ONLINE',
'driver06pw', 'driver12');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (driver_id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (13,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver13');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (driver_id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (14,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE',
'driver08pw', 'driver14');

-- Create 4 manufacturer --

insert into manufacturer(id, date_created, name) values(1,  now(), 'BMW');
insert into manufacturer(id, date_created, name) values(2,  now(), 'VOLKSWAGEN');
insert into manufacturer(id, date_created, name) values(3,  now(), 'MERCEDES BENZ');
insert into manufacturer(id, date_created, name) values(4,  now(), 'AUDI');

-- Create 3 Cars --

insert into car(car_id, convertible, date_created, engine_type, license_plate, rating, seat_count, manufacturer, deleted,online_status,driver_id)
values(1, true, now(),  'gas', 'abc123', 4.0, 4, 1, false,'OFFLINE',1);

insert into car(car_id, convertible, date_created, engine_type, license_plate, rating, seat_count, manufacturer, deleted,online_status,driver_id)
values(2, true, now(),  'electric', 'abcd1234', 4.6, 4, 2, false,'ONLINE',5);

insert into car(car_id, convertible, date_created, engine_type, license_plate, rating, seat_count, manufacturer, deleted,online_status,driver_id)
values(3, false, now(),  'hybrid', 'abcde12345', 5.0, 7, 3,false,'ONLINE',7);
insert into car(car_id, convertible, date_created, engine_type, license_plate, rating, seat_count, manufacturer, deleted,online_status,driver_id)
values(4, false, now(),  'gas', 'abcdef123456', 3.0, 4, 4,false,'ONLINE',4);