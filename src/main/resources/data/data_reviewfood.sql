USE review_food;

-- INSERT VIA POSTMAN 
/*age, user_id, first_name, last_name, email, food_type, hometown, password, phone, sex, user_name*/
-- INSERT INTO review_food.user VALUES (24, 1, 'Phu Thanh', 'Le', 'phuthanh@gmail.com','ALL_FOOD', 'Binh Duong','password', '0375254688', 'MALE', 'admin');
-- INSERT INTO review_food.user VALUES (20, 2, 'Thao Tram', 'Phan', 'thaotram@gmail.com','ALL_FOOD', 'Ho Chi Minh','passwordthaotram', '0375254693', 'FEMALE', 'thaotram');
-- INSERT INTO review_food.user VALUES (23, 3, 'Thanh Long', 'Nguyen', 'thanhlong@gmail.com','ALL_FOOD', 'Ba Ria Vung Tau','passwordthanhlong', '0375254689', 'MALE', 'thanhlong');
-- INSERT INTO review_food.user VALUES (40, 4, 'Minh Tam', 'Tran Dang', 'minhtam@gmail.com','NON_VEGETARIAN_FOOD', 'Ho Chi Minh', 'passwordminhtam', '0375254690', 'MALE', 'minhtam');
-- INSERT INTO review_food.user VALUES (62, 5, 'Quang Long', 'Banh', 'quanglong@gmail.com','VEGETARIAN_FOOD', 'Dong Nai','passwordquanglong', '0375254691', 'LGBT', 'quanglong');
-- INSERT INTO review_food.user VALUES (102, 6, 'Dang Khoa', 'Pham', 'dangkhoa@gmail.com','VEGETARIAN_FOOD', 'Bac Ninh','passworddangkhoa', '0375254692', 'MALE', 'dangkhoa');
-- INSERT INTO review_food.user VALUES (18, 7, 'Cong Truong', 'Trinh', 'trinhtruong@gmail.com','NON_VEGETARIAN_FOOD', 'Tay Ninh', 'passwordtrinhtruong', '0375254687', 'MALE', 'trinhtruong');
-- INSERT INTO review_food.user VALUES (75, 8, 'Hong Anh', 'Nguyen Thi', 'honganh@gmail.com','NON_VEGETARIAN_FOOD', 'Hai Duong','passwordhonganh', '0375254694', 'FEMALE', 'honganh');
-- INSERT INTO review_food.user VALUES (9, 9, 'Kim Ngan', 'Doan Vo', 'kimngan@gmail.com','ALL_FOOD', 'Ben Tre','passwordkimngan', '0375254695', 'FEMALE', 'kimngan');
-- INSERT INTO review_food.user VALUES (19, 10, 'Huu Cuong', 'Nguyen', 'huucuong@gmail.com','ALL_FOOD', 'Thanh Hoa','passwordhuucuong', '0375254696', 'MALE', 'huucuong');
-- INSERT INTO review_food.user VALUES (35, 11, 'Hoai Linh', 'Nguyen', 'hoailinh@gmail.com','NON_VEGETARIAN_FOOD', 'Quang Nam','passwordhoailinh', '0375254697', 'LGBT', 'hoailinh');
-- INSERT INTO review_food.user VALUES (57, 12, 'Tran Thanh', 'Le', 'tranthanh@gmail.com','ALL_FOOD', 'Ha Noi','passwordtranthanh', '0375254698', 'LGBT', 'tranthanh');
-- INSERT INTO review_food.user VALUES (17, 13, 'Ha Linh', 'Tran', 'halinh@gmail.com','VEGETARIAN_FOOD', 'Thanh Hoa','passwordhalinh', '0375254699', 'FEMALE', 'halinh');
-- INSERT INTO review_food.user VALUES (28, 14, 'Louis', 'David', 'louis@gmail.com','ALL_FOOD', 'Usa','passwordlouis', '0375254601', 'MALE', 'louisdavid');
-- INSERT INTO review_food.user VALUES (20, 15, 'Linda', 'Lee', 'linda@gmail.com','ALL_FOOD', 'Korea','passwordlinda', '0375254602', 'FEMALE', 'lindalee');
-- INSERT INTO review_food.user VALUES (32, 16, 'Kevin', 'Winsor', 'kevin@gmail.com','VEGETARIAN_FOOD', 'Spain','passwordkevin', '0375254603', 'LGBT', 'kevin');
-- INSERT INTO review_food.user VALUES (68, 17, 'Hook', 'Kabang', 'hook@gmail.com','NON_VEGETARIAN_FOOD', 'Thailand','passwordhook', '0375254604', 'MALE', 'hookkabang');
-- INSERT INTO review_food.user VALUES (21, 18, 'Maxwell', 'Vip', 'maxwell@gmail.com','NON_VEGETARIAN_FOOD', 'England','passwordmaxwell', '0375254605', 'MALE', 'maxwell');

/*create_date, person_post_id, post_feed_id, shared_post_id, content, title*/ 
INSERT INTO review_food.post_feed VALUES ('2023-02-06', 1, 1, null,'Delicious grilled pork restaurant','Delicious restaurant'); 
INSERT INTO review_food.post_feed VALUES ('2022-10-25', 4, 2, null,'The best vegetarian restaurant in Ho Chi Minh.','Vegetarian restaurant');
INSERT INTO review_food.post_feed VALUES ('2023-05-18', 2, 3, null,'Hare meat restaurant','New restaurant');
INSERT INTO review_food.post_feed VALUES ('2022-12-10', 2, 4, null,'The restaurant serves the best in the Binh Duong province','Good restaurant service');
INSERT INTO review_food.post_feed VALUES ('2023-07-28', 3, 5, null,'French cuisine restaurant opened','French restaurant');
INSERT INTO review_food.post_feed VALUES ('2023-01-16', 4, 6, null,'Dimsum restaurant offers 200 vouchers','Dimsum restaurant');
/*sharing activity is to post with shared_post_id*/
INSERT INTO review_food.post_feed VALUES ('2022-02-10', 2, 7, 1,'Best restaurant I have ever eaten at','Sharing restaurant'); 
INSERT INTO review_food.post_feed VALUES ('2022-10-26', 2, 8, 2,'Must try','Sharing restaurant');
INSERT INTO review_food.post_feed VALUES ('2022-02-11', 4, 9, 1,'The best restaurant','Sharing restaurant');
INSERT INTO review_food.post_feed VALUES ('2022-02-12', 6, 10, 1,'Delicious!','Sharing restaurant');
INSERT INTO review_food.post_feed VALUES ('2023-05-20', 18, 11, 3,'The fucking delicious food!','Sharing restaurant');

/*res_phone, post_feed_id, restaurant_id, description, res_address, res_country, res_food_type, res_name*/ 
INSERT INTO review_food.restaurant VALUES ('0906123456', 5, 1, 'French cuisine', '123 To Hien Thanh, district 10, Ho Chi Minh', 'France', 'NON_VEGETARIAN_FOOD', 'Truffle Restaurant');
INSERT INTO review_food.restaurant VALUES ('0906123457', 1, 2, 'Grilled pork', '25 Ham Nghi, district 1, Ho Chi Minh', 'Viet Nam', 'NON_VEGETARIAN_FOOD', 'Nhau Thoi Restaurant');
INSERT INTO review_food.restaurant VALUES ('0906123458', 2, 3, 'Vegetarian restaurant', '7 Nguyen Thi Thap, district 7, Ho Chi Minh', 'Viet Nam', 'VEGETARIAN_FOOD', 'Huong Que Restaurant');
INSERT INTO review_food.restaurant VALUES ('0906123459', 3, 4, 'Best seller: Hare meat', '752 Vo Van Ngan, Thu Duc City, Ho Chi Minh', 'Viet Nam', 'ALL_FOOD', '123 Zo Restaurant');
INSERT INTO review_food.restaurant VALUES ('0906123460', 4, 5, 'The best services', '45 Cach Mang Thang 8, Thu Dau Mot City, Binh Duong', 'Viet Nam', 'ALL_FOOD', 'Pho Nuong Restaurant');
INSERT INTO review_food.restaurant VALUES ('0906123461', 6, 6, 'Dimsum cuisine', '28 Chau Van Liem, district 5, Ho Chi Minh', 'China', 'NON_VEGETARIAN_FOOD', 'HongKong Restaurant');

/*rating_date, rating_id, restaurant_id, user_id, rating*/ 
INSERT INTO review_food.rating VALUES ('2023-09-09', 1, 1, 1, 'EIGHT_STAR');
INSERT INTO review_food.rating VALUES ('2023-09-20', 2, 1, 2, 'SEVEN_STAR');
INSERT INTO review_food.rating VALUES ('2022-12-26', 3, 2, 1, 'SIX_STAR');
INSERT INTO review_food.rating VALUES ('2023-01-15', 4, 3, 18, 'EIGHT_STAR');
INSERT INTO review_food.rating VALUES ('2023-05-22', 5, 6, 2, 'NINE_STAR');
INSERT INTO review_food.rating VALUES ('2023-08-27', 6, 6, 15, 'TEN_STAR');
INSERT INTO review_food.rating VALUES ('2023-07-19', 7, 4, 11, 'NINE_STAR');
INSERT INTO review_food.rating VALUES ('2023-02-26', 8, 5, 10, 'EIGHT_STAR');
INSERT INTO review_food.rating VALUES ('2023-08-10', 9, 1, 13, 'SEVEN_STAR');
INSERT INTO review_food.rating VALUES ('2023-03-02', 10, 5, 13, 'SIX_STAR');
INSERT INTO review_food.rating VALUES ('2023-04-19', 11, 2, 17, 'TEN_STAR');
INSERT INTO review_food.rating VALUES ('2023-06-04', 12, 4, 5, 'ONE_STAR');

/*comment_id, create_date, post_feed_id, user_id, content*/ 
INSERT INTO review_food.comment VALUES (1, '2023-03-02', 1, 1, 'Delicious bro!');
INSERT INTO review_food.comment VALUES (2, '2023-03-02', 1, 1, 'Stop by!');
INSERT INTO review_food.comment VALUES (3, '2023-04-20', 1, 2, 'Must try!');
INSERT INTO review_food.comment VALUES (4, '2023-04-20', 1, 2, 'I like it!');
INSERT INTO review_food.comment VALUES (5, '2022-11-19', 2, 16, 'Amazing!');
INSERT INTO review_food.comment VALUES (6, '2022-12-03', 2, 17, 'hehe');
INSERT INTO review_food.comment VALUES (7, '2022-01-04', 2, 10, 'Delicious');
INSERT INTO review_food.comment VALUES (8, '2023-06-29', 3, 2, 'I love it!');
INSERT INTO review_food.comment VALUES (9, '2023-07-01', 3, 5, 'Must try!');
INSERT INTO review_food.comment VALUES (10, '2023-03-02', 4, 18, 'My son like the here food');
INSERT INTO review_food.comment VALUES (11, '2023-08-07', 5, 13, 'I love the here food');
INSERT INTO review_food.comment VALUES (12, '2023-09-23', 5, 12, 'The fucking delicious food');

/*friend_id, make_friend_date, receiver_id, request_date, sender_id, status*/ 
INSERT INTO review_food.friend VALUES (1, '2022-02-21', 5, null, 1, 'ACCEPTED');
INSERT INTO review_food.friend VALUES (2, '2023-01-17', 2, null, 1, 'ACCEPTED');
INSERT INTO review_food.friend VALUES (3, '2023-04-02', 3, null, 2, 'ACCEPTED');
INSERT INTO review_food.friend VALUES (4, null, 4, '2022-02-15', 1, 'WAITING');
INSERT INTO review_food.friend VALUES (5, null, 15, '2022-05-25', 2, 'REFUSED');
INSERT INTO review_food.friend VALUES (6, '2022-06-09', 8, null, 3, 'ACCEPTED');
INSERT INTO review_food.friend VALUES (7, '2023-01-12', 16, null, 4, 'ACCEPTED');
INSERT INTO review_food.friend VALUES (8, '2022-07-18', 14, null, 5, 'ACCEPTED');

/*follow_id, follower_id, following_id*/ 
INSERT INTO review_food.follow VALUES (1,2,3); 
INSERT INTO review_food.follow VALUES (2,3,1); 
INSERT INTO review_food.follow VALUES (3,4,2); 
INSERT INTO review_food.follow VALUES (4,2,5); 
INSERT INTO review_food.follow VALUES (5,2,6); 
INSERT INTO review_food.follow VALUES (6,2,7); 

/*comment_id, create_date, like_cmt_id, user_id*/  
INSERT INTO review_food.like_comment VALUES (1, '2023-03-03', 1 ,7);    
INSERT INTO review_food.like_comment VALUES (1, '2023-03-04', 2, 6); 
INSERT INTO review_food.like_comment VALUES (1, '2023-03-05', 3, 2);
INSERT INTO review_food.like_comment VALUES (2, '2023-03-05', 4, 2);
INSERT INTO review_food.like_comment VALUES (3, '2023-04-21', 5, 2);
INSERT INTO review_food.like_comment VALUES (2, '2023-03-03', 6, 16); 
INSERT INTO review_food.like_comment VALUES (3, '2023-04-21', 7, 10); 

/*create_date, like_post_id, post_feed_id, user_id*/ 
INSERT INTO review_food.like_post_feed VALUES ('2023-02-07', 1, 1, 7); 
INSERT INTO review_food.like_post_feed VALUES ('2023-02-07', 2, 1, 2);
INSERT INTO review_food.like_post_feed VALUES ('2022-10-26', 3, 2, 2);
INSERT INTO review_food.like_post_feed VALUES ('2022-03-12', 4, 1, 12);
INSERT INTO review_food.like_post_feed VALUES ('2022-10-30', 5, 2, 10);
INSERT INTO review_food.like_post_feed VALUES ('2023-05-19', 6, 3, 2);
INSERT INTO review_food.like_post_feed VALUES ('2022-12-11', 7, 4, 2);

/*is_read, comment_id, like_cmt_id, like_post_id, notif_date, notif_id, post_feed_id, user_id*/ 
/*notif ==> comment_id*/
INSERT INTO review_food.notification VALUES (false, 1, null, null,'2023-03-02', 1, 1, 'You have commented on your post!');
INSERT INTO review_food.notification VALUES (false, 3, null, null,'2023-04-20', 2, 1, 'Your post has had (id = 2) comment!');
/*notif ==> like_cmt_id*/ 
INSERT INTO review_food.notification VALUES (false, null, 1, null,'2023-03-03', 3, 1, 'Your comment has had (id = 7) like!');
INSERT INTO review_food.notification VALUES (false, null, 2, null,'2023-03-04', 4, 1, 'Your comment has had (id = 6) like!');
/*notif ==> like_post_id*/ 
INSERT INTO review_food.notification VALUES (false, null, null, 1,'2023-02-07', 5, 1, 'Your post has had (id = 7) like!');
INSERT INTO review_food.notification VALUES (false, null, null, 2,'2023-02-07', 6, 1, 'Your comment has had (id = 2) like!');

/*check table*/ 
SELECT* FROM post_feed;
SELECT* FROM restaurant;
SELECT* FROM users;
SELECT* FROM comment;
SELECT* FROM friend;
SELECT* FROM notification;
SELECT* FROM image;
SELECT* FROM like_comment;
SELECT* FROM like_post_feed;
SELECT* FROM follow;
SELECT* FROM rating;
