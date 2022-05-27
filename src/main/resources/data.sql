

INSERT INTO foods (name) VALUES ('carrot');
INSERT INTO foods (name) VALUES ( 'leafs');
INSERT INTO foods (name) VALUES ( 'insects');
INSERT INTO foods (name) VALUES ( 'vermin');
INSERT INTO foods (name) VALUES ( 'birdseed');

INSERT INTO animalFamily (name)  VALUES('mammal');
INSERT INTO animalFamily (name)  VALUES('reptile');
INSERT INTO animalFamily (name)  VALUES('birds');
INSERT INTO animalFamily (name)  VALUES('arthropod');

INSERT INTO animals (name, legs, idFood, idFamily )VALUES ('Rabbit', 4,1,1);
INSERT INTO animals (name, legs, idFood, idFamily ) VALUES ( 'Ape', 2,1,1);
INSERT INTO animals (name, legs, idFood, idFamily ) VALUES ( 'Deer', 4,1,1);
INSERT INTO animals (name, legs, idFood, idFamily ) VALUES ( 'Snake', 0,2,1);
INSERT INTO animals (name, legs, idFood, idFamily ) VALUES ( 'Coocodrile',4,3,1);
INSERT INTO animals (name, legs, idFood, idFamily ) VALUES ( 'Chicken', 2,4,2);
INSERT INTO animals (name, legs, idFood, idFamily ) VALUES ( 'Spider', 8,2,3);

INSERT INTO studyExcluded (idAnimal) VALUES (3);