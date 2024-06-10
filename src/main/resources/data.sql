INSERT INTO Category(name, description)
VALUES ('Feathered bipeds', 'Animals of different sizes and ages that have two legs and feathers'),
       ('Mammals', 'Warm-blooded vertebrates characterized by mammary glands, hair or fur, and typically giving birth to live young.'),
       ('Reptiles', 'Cold-blooded vertebrates characterized by dry, scaly skin and laying shelled eggs.'),
       ('Amphibians', 'Cold-blooded vertebrates that typically undergo metamorphosis from aquatic larvae to terrestrial adults.'),
       ('Fish', 'Aquatic vertebrates with gills for breathing and usually with fins for locomotion.'),
       ('Arthropods', 'Invertebrates with jointed legs, segmented bodies, and exoskeletons, including insects, arachnids (spiders, scorpions), crustaceans (crabs, lobsters), and myriapods (centipedes, millipedes).'),
       ('Mollusks', 'Soft-bodied invertebrates typically with a muscular foot, a mantle, and a radula.');

insert into article(name, description, price, category_id)
VALUES ('Ameraucana', 'This is a type of chicken that lays eggs', 130, 1),
       ('Andalusian', 'This is a type of chicken that lays eggs. Yearly Egg Production: 100-170, Preferred Climate: Warm Climates', 120, 1),
       ('Appenzeller', 'Another chicken - Main Use: Eggs, Temperament: Friendly, Noise Level: Loud', 80, 1),
       ('Black Copper Marans', 'Big big chicken. Yearly Egg Production: 100-170, Egg Color: Chocolate, Docile, Quiet', 50, 1),

       ('Monkey', 'AGOO AGOO AOHO AOHO AOHO AOHO', 600, 2),
       ('Dog', 'WOOF WOO WFOO WOF WOO WF WOO WFOO WOFOOFOOWO', 50, 2),
       ('Panda', 'Just sleeping', 2000, 2),
       ('Pig', 'Gremo prsut ist', 100, 2),
       ('Cat', 'moew moew moew moew moewm oewmo ewm oew', 10, 2),

       ('Garden lizard', 'Freshly caught', 10, 3),
       ('Chamaleon', 'Freshly caught, it might take us some time to find it', 160, 3),
       ('Iguana', 'Freshly caught, very big to transport', 199, 3),
       ('Snake', 'You will have to come catch this one yourself', 299, 3),

       ('Tuna', 'Rio mare', 120, 5);


insert into users(username, password) values('admin', '$2a$10$rXrPTb.4FDjWdkTkjhXxwu1fx2ICwc/JPsLekkRRT0Rknk9tNe42O'); -- admin
insert into users(username, password) values('user', '$2a$10$kXrvYR8IiTCXidBmLzcSzOAVVgXVIcB7SiO.ABWBk5W.DP8s0eD6K'); -- user
insert into users(username, password) values('locked', '$2a$10$8crjz.7h4zafNDdcGTyew.5owiwMd5CTmPKxjrHvyD2fX0KqxDY1q'); -- locked

insert into roles(name) values('ADMIN');
insert into roles(name) values('USER');

insert into role_user(web_user_id, role_id) values(1, 1);
insert into role_user(web_user_id, role_id) values(1, 2);
insert into role_user(web_user_id, role_id) values(2, 2);
insert into role_user(web_user_id, role_id) values(3, 2);