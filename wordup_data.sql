use wordup;

/** Users */
delete from response where responseid < 15;
delete from attempt where attemptid < 5;
delete from card where cardid < 12;
delete from lesson where lessonid=1 or lessonID=2 or lessonID=3;
delete from user where userid > 1;
insert into user (userID, firstName, lastName, type, profession, email, password, salt) 
values (2, 'Student1', 'Tom', 1, 'student', 'student1@gmail.com', 'e1df2999f4aca2dac10dbd46eca3bb5a5d476580e95a8f40985bb6456c860445', 'P8NfJnOdYUu/pJfUbSC9kNZN1fdTqsVRbDomrha0gok=');

insert into user (userID, firstName, lastName, type, profession, email, password, salt) 
values (3, 'Teacher1', 'Tweety', 1, 'teaching professional', 'teacher1@gmail.com', '7172f847b4dca248a149c27244aa35160a4eed1bdb52c920f95b3b020ca4acd6', 'G0seeoXt8yXOs2YcHQ2t/2mkMj7bmK0DeZn+Yu4fyMw=');

/** Lessons */
insert into lesson (lessonID, title, file, subject, subject2, subject3, level, creatorID, dateCreated) 
values (1, 'Fruits in Spanish', 'C:\FruitFile.txt', 'Linguistics', '', '', 'private', 2, DATE '2018-12-7)');

insert into lesson (lessonID, title, file, subject, subject2, subject3, level, creatorID, dateCreated) 
values (2, 'Animal Facts', 'C:\Animals.txt', 'Biology', 'Other', '', 'public', 3, DATE '2017-3-4');

insert into lesson (lessonID, title, file, subject, subject2, subject3, level, creatorID, dateCreated) 
values (3, 'Bird Fun!', 'C:\Animals_Student1.txt', 'Zoology', 'Biology', 'Other', 'public', 2, DATE '2018-4-6');

/** Cards */
/** ---- Lesson 1 Fruit Cards ---- */
insert into card values (1, 'La Manzana', 'What do you call apple in Spanish?', 1);

insert into card values (2, 'Las Fresas', 'What are strawberries called?', 1);

insert into card values (3, 'El Tomate', 'How about the tomato?', 1);

/** ---- Lesson 2 Animal Cards ---- */
insert into card values (4, 'Elephant', 'Largest land animal', 2);

insert into card values (5, 'Blue Whale', 'Largest Water/Aquatic mammal', 2);

insert into card values (6, 'Lion', 'King of the Jungle', 2);

/** ---- Lesson 3 Bird Cards ---- */
insert into card values (7, 'Dove', 'This bird is white and is believed to represent peace and love.', 3);

insert into card values (8, 'Ostrich', 'Largest bird in the world; it also lays the largest eggs and has the fastest maximum running speed.', 3);

insert into card values (9, 'The Golden Eagle', 'The national bird of the United States', 3);

insert into card values (10, 'Hedwig', 'Bonus: Pet owl of Harry Potter ;) ', 3);

insert into card values (11, 'Peacock', 'The national bird of India', 3);

/** Attempt */

/** user 2 student took private lesson-quiz on Fruits (scored 3 out of 3) **/
insert into attempt values(1, 1, 2, 1, '2017-10-12 11:25:14', 3);

/** user 2 student took public lesson-quiz on Animals (scored 2 out of 3) **/
insert into attempt values(2, 2, 2, 1, '2018-3-4 11:46:21', 2);

/** user 3 teacher took lesson-quiz on Birds (scored 5 out of 5) **/
insert into attempt values(3, 3, 3, 1, '2018-1-12 23:16:49', 5);

/** user 2 student took lesson-quiz on Animals (scored 3 out of 3) **/
insert into attempt values(4, 2, 2, 2, '2018-1-12 23:16:49', 3);

/** Response */

/** Attempt 1 Responses */
insert into response values(1, 1, 1, 'la manzana');
insert into response values(2, 2, 1, 'las fresas');
insert into response values(3, 3, 1, 'el tomate');

/** Attempt 2 Responses */ 
insert into response values(4, 4, 2, 'elephant');
insert into response values(5, 5, 2, 'blue whale');
insert into response values(6, 6, 2, 'The lion');

/** Attempt 3 Responses */
insert into response values(7, 7, 3, 'dove');
insert into response values(8, 8, 3, 'ostrich');
insert into response values(10, 10, 3, 'Hedwig');
insert into response values(9, 9, 3, 'The Golden Eagle');
insert into response values(11, 11, 3, 'peacock');

/** Attempt 4 Responses */ 
insert into response values(12, 4, 4, 'elephant');
insert into response values(13, 5, 4, 'blue whale');
insert into response values(14, 6, 4, 'lion');
