INSERT INTO Category(name, description)
VALUES ('Feathered bipeds', 'Animals of different sizes and ages that have two legs and feathers'),
       ('Mammals', 'Warm-blooded vertebrates characterized by mammary glands, hair or fur, and typically giving birth to live young.'),
       ('Reptiles', 'Cold-blooded vertebrates characterized by dry, scaly skin and laying shelled eggs.'),
       ('Amphibians', 'Cold-blooded vertebrates that typically undergo metamorphosis from aquatic larvae to terrestrial adults.'),
       ('Fish', 'Aquatic vertebrates with gills for breathing and usually with fins for locomotion.'),
       ('Arthropods', 'Invertebrates with jointed legs, segmented bodies, and exoskeletons, including insects, arachnids (spiders, scorpions), crustaceans (crabs, lobsters), and myriapods (centipedes, millipedes).'),
       ('Mollusks', 'Soft-bodied invertebrates typically with a muscular foot, a mantle, and a radula.');

insert into article(name, description, price, category_id)
VALUES ('Tesla Model Y', 'Electric car', 50000, 3),
       ('Apartment on the main square', 'Luxury apartment', 500000, 2),
       ('House on the beach', 'Vacation house', 5000000, 1),
       ('Oldtimer Mercedes X 1800', 'Vintage car', 100000, 2),
       ('Oldtimer Mercedes X 1800', 'Vintage car', 100000, 2),
       ('Oldtimer Mercedes X 1800', 'Vintage car', 100000, 2),
       ('Oldtimer Mercedes X 1800', 'Vintage car', 100000, 2),
       ('Chicken chicken', 'Vintage car', 100000, 2);


insert into users(username, password) values('admin', '$2a$10$rXrPTb.4FDjWdkTkjhXxwu1fx2ICwc/JPsLekkRRT0Rknk9tNe42O'); -- admin
insert into users(username, password) values('user', '$2a$10$kXrvYR8IiTCXidBmLzcSzOAVVgXVIcB7SiO.ABWBk5W.DP8s0eD6K'); -- user
insert into users(username, password) values('locked', '$2a$10$8crjz.7h4zafNDdcGTyew.5owiwMd5CTmPKxjrHvyD2fX0KqxDY1q'); -- locked

insert into roles(name) values('ADMIN');
insert into roles(name) values('USER');

insert into role_user(web_user_id, role_id) values(1, 1);
insert into role_user(web_user_id, role_id) values(1, 2);
insert into role_user(web_user_id, role_id) values(2, 2);
insert into role_user(web_user_id, role_id) values(3, 2);