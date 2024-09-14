# Итоговая контрольная работа по блоку специализация
## Операционные системы и виртуализация (Linux)
### 1. Используя команду cat в терминале операционной системы Linux, создать два файла:  домашние животные (заполнив файл собаками, кошками, хомяками) и вьючные животные (заполнив файл лошадьми, верблюдами и ослами), а затем объединить их. Просмотреть содержимое созданного файла. Переименовать файл, дав ему новое имя (друзья человека).
![task_1!](/scrinshots/task_1.png)
### 2. Создать директорию, переместить файл туда.
![task_2!](/scrinshots/task_2.png)
### 3. Подключить дополнительный репозиторий MySQL. Установить любой пакет из этого репозитория.
![task_3](/scrinshots/task_3_1.png)
![task_3](/scrinshots/task_3_2.png)
![task_3](/scrinshots/task_3_3.png)
### 4. Установить и удалить deb-пакет с помощью dpkg.
![task_4](/scrinshots/task_4.png)
### 5. Выложить историю команд в терминале Ubuntu

* Task 1
```
cat > pets
dog
cat
hamster

cat > pack_animals
horse
camel
donkey

cat pets pack_animals > animals

mv animals human_friend
```
* Task 2
```
mkdir friends

sudo mv human_friend ~/friends

cd friends
```
* Task 3
```
sudo wget https://dev.mysql.com/get/mysql-apt-config_0.8.32-1_all.deb

sudo dpkg -i mysql-apt-config_0.8.32-1_all.deb

cd /etc/apt

cd /sources.list.d

sudo apt update

sudo apt install mysql-community-server
```
* Task 4
```
sudo wget https://desktop.docker.com/linux/main/amd64/docker-desktop-amd64.deb

sudo dpkg -i docker-desktop-amd64.deb

sudo apt -f install

sudo dpkg -r docker-desktop

sudo apt autoremove
```
### 6. Нарисовать диаграмму, в которой есть класс родительский класс, домашние животные и вьючные животные, в составы которых в случае домашних животных войдут классы: собаки, кошки, хомяки, а в класс вьючные животные войдут: Лошади, верблюды и ослы.
![task_4](/scrinshots/task_6.jpg)

### 7. В подключенном MySQL репозитории создать базу данных “Друзья человека”
``` sql
DROP DATABASE IF EXISTS human_friends;
CREATE DATABASE human_friends;
USE human_friends;
```
### 8. Создать таблицы с иерархией из диаграммы в БД
``` sql
DROP TABLE IF EXISTS animal_class;
CREATE TABLE animal_class (
	id INT AUTO_INCREMENT PRIMARY KEY, 
	class_name VARCHAR(20)
);

DROP TABLE IF EXISTS pet_animals;
CREATE TABLE pet_animals
(
	id INT AUTO_INCREMENT PRIMARY KEY,
    animal_species VARCHAR (20),
    class_id INT,
    FOREIGN KEY (class_id) REFERENCES animal_class (id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS pack_animals;
CREATE TABLE pack_animals
(
	id INT AUTO_INCREMENT PRIMARY KEY,
    animal_species VARCHAR (20),
    class_id INT,
    FOREIGN KEY (class_id) REFERENCES animal_class (id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS dogs;
CREATE TABLE dogs 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(20), 
    birthday DATE,
    commands VARCHAR(50),
    species_id int,
    FOREIGN KEY (species_id) REFERENCES pet_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS cats;
CREATE TABLE cats 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(20), 
    birthday DATE,
    commands VARCHAR(50),
    species_id int,
    FOREIGN KEY (species_id) REFERENCES pet_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS hamsters;
CREATE TABLE hamsters 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(20), 
    birthday DATE,
    commands VARCHAR(50),
    species_id int,
    FOREIGN KEY (species_id) REFERENCES pet_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS camels;
CREATE TABLE camels 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(20), 
    birthday DATE,
    commands VARCHAR(50),
    species_id int,
    FOREIGN KEY (species_id) REFERENCES pack_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS horses;
CREATE TABLE horses 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(20), 
    birthday DATE,
    commands VARCHAR(50),
    species_id int,
    FOREIGN KEY (species_id) REFERENCES pack_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS donkeys;
CREATE TABLE donkeys 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(20), 
    birthday DATE,
    commands VARCHAR(50),
    species_id int,
    FOREIGN KEY (species_id) REFERENCES pack_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);
```
### 9. Заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения
``` sql
INSERT INTO animal_class (class_name)
VALUES ('Pet'), ('Pack Animal');

INSERT INTO pet_animals (animal_species, class_id)
VALUES ('Dog', 1), ('Cat', 1), ('Hamster', 1);

INSERT INTO pack_animals (animal_species, class_id)
VALUES ('Camel', 2), ('Horse', 2), ('Donkey', 2); 

INSERT INTO dogs (name, birthday, commands, species_id)
VALUES 
	('Buddy', '2023-03-12', 'sit, stay, fetch', 1),
	('Bella', '2020-05-24', 'sit, roll over', 1),
	('Max', '2022-09-14', 'fetch, lie down', 1),
	('Luna', '2020-02-01', 'sit, come, shake hands', 1),
	('Charlie', '2016-11-18', 'stay, play dead, jump', 1);

INSERT INTO cats (name, birthday, commands, species_id)
VALUES
	('Whiskers', '2018-06-15', 'sit, come', 2),
	('Mittens', '2022-11-22', 'jump, scratch', 2),
	('Simba', '2019-03-09', 'roll over, come', 2),
	('Lily', '2023-07-30', 'jump, sit', 2),
	('Oscar', '2016-01-14', 'stay, fetch', 2);

INSERT INTO hamsters (name, birthday, commands, species_id)
VALUES
	('Nibbles', '2023-04-10', 'spin wheel, climb', 3),
	('Squeaky', '2024-08-22', 'dig, hide', 3),
	('Fluffy', '2023-12-05', 'run, explore', 3),
	('Chubby', '2022-02-14', 'spin wheel, hide', 3),
	('Tiny', '2021-06-23', 'run, dig', 3);
    
INSERT INTO camels (name, birthday, commands, species_id)
VALUES
	('Sahara', '2015-04-21', 'carry, sit, stand', 1),
	('Dusty', '2016-09-10', 'walk, sit', 1),
	('Oasis', '2014-06-15', 'run, carry', 1),
	('Dune', '2018-02-25', 'sit, stand, carry', 1),
	('Desert', '2017-11-09', 'walk, run', 1);

INSERT INTO horses (name, birthday, commands, species_id)
VALUES
	('Thunder', '2015-05-20', 'trot, gallop, jump', 2),
	('Bella', '2016-08-12', 'walk, canter', 2),
	('Shadow', '2017-03-30', 'gallop, jump', 2),
	('Storm', '2014-11-05', 'trot, walk, halt', 2),
	('Spirit', '2018-07-14', 'gallop, jump, rear', 2);

INSERT INTO donkeys (name, birthday, commands, species_id)
VALUES
	('Eeyore', '2016-03-18', 'carry, walk', 3),
	('Daisy', '2017-07-12', 'stand, sit', 3),
	('Benny', '2015-10-04', 'walk, pull, carry', 3),
	('Milo', '2018-05-25', 'run, walk', 3),
	('Coco', '2019-09-14', 'carry, trot, stand', 3);
```
### 10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.
``` sql
SET SQL_SAFE_UPDATES = 0;
DELETE FROM camels;
 
SELECT name, birthday, commands 
FROM horses
UNION 
SELECT name, birthday, commands 
FROM donkeys; 
```
### 11.Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице
``` sql
DROP TABLE IF EXISTS young_animals;
CREATE TABLE young_animals (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20),
    species VARCHAR(20),
    birthday DATE,
    age_in_months INT
);

INSERT INTO young_animals (name, species, birthday, age_in_months)
SELECT name, 'dog' AS species, birthday, TIMESTAMPDIFF(MONTH, birthday, CURDATE()) AS age_in_months
FROM dogs
WHERE birthday BETWEEN DATE_SUB(CURDATE(), INTERVAL 3 YEAR) AND DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
UNION
SELECT name, 'cat' AS species, birthday, TIMESTAMPDIFF(MONTH, birthday, CURDATE()) AS age_in_months
FROM cats
WHERE birthday BETWEEN DATE_SUB(CURDATE(), INTERVAL 3 YEAR) AND DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
UNION
SELECT name, 'hamster' AS species, birthday, TIMESTAMPDIFF(MONTH, birthday, CURDATE()) AS age_in_months
FROM hamsters
WHERE birthday BETWEEN DATE_SUB(CURDATE(), INTERVAL 3 YEAR) AND DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
UNION
SELECT name, 'camel' AS species, birthday, TIMESTAMPDIFF(MONTH, birthday, CURDATE()) AS age_in_months
FROM camels
WHERE birthday BETWEEN DATE_SUB(CURDATE(), INTERVAL 3 YEAR) AND DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
UNION
SELECT name, 'horse' AS species, birthday, TIMESTAMPDIFF(MONTH, birthday, CURDATE()) AS age_in_months
FROM horses
WHERE birthday BETWEEN DATE_SUB(CURDATE(), INTERVAL 3 YEAR) AND DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
UNION
SELECT name, 'donkey' AS species, birthday, TIMESTAMPDIFF(MONTH, birthday, CURDATE()) AS age_in_months
FROM donkeys
WHERE birthday BETWEEN DATE_SUB(CURDATE(), INTERVAL 3 YEAR) AND DATE_SUB(CURDATE(), INTERVAL 1 YEAR);
```
### 12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам.
``` sql
SELECT 
    id,
    name,
    birthday,
    commands,
    species_id,
    'dog' AS species,
    'dogs' AS source_table
FROM dogs

UNION ALL

SELECT 
    id,
    name,
    birthday,
    commands,
    species_id,
    'cat' AS species,
    'cats' AS source_table
FROM cats

UNION ALL

SELECT 
    id,
    name,
    birthday,
    commands,
    species_id,
    'hamster' AS species,
    'hamsters' AS source_table
FROM hamsters

UNION ALL

SELECT 
    id,
    name,
    birthday,
    commands,
    species_id,
    'camel' AS species,
    'camels' AS source_table
FROM camels

UNION ALL

SELECT 
    id,
    name,
    birthday,
    commands,
    species_id,
    'horse' AS species,
    'horses' AS source_table
FROM horses

UNION ALL

SELECT 
    id,
    name,
    birthday,
    commands,
    species_id,
    'donkey' AS species,
    'donkeys' AS source_table
FROM donkeys;
```
### 13.Создать класс с Инкапсуляцией методов и наследованием по диаграмме.

### 14. Написать программу, имитирующую работу реестра домашних животных.
