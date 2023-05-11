INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date)
VALUES ('name1', 'description1', '128.3', '12', '2023-04-07T13:06:35.76', '2022-04-07T13:06:35.76');

INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date)
VALUES ('name2', 'description2', '321', '2', '2023-03-03T13:03:35.76', '2022-04-05T13:06:35.76');

INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date)
VALUES ('name3', 'description3', '12345.3', '12', '2023-01-07T15:06:35.76', '2021-05-07T17:07:35.76');

INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date)
VALUES ('name4', 'description2', '321', '2', '2023-03-03T13:03:35.76', '2022-04-05T13:06:35.76');

INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date)
VALUES ('name5', 'description3', '12345.3', '12', '2023-01-07T15:06:35.76', '2021-05-07T17:07:35.76');

INSERT INTO tag(name)
VALUES ('tag1');

INSERT INTO tag(name)
VALUES ('tag2');

INSERT INTO tag(name)
VALUES ('tag3');

INSERT INTO tag(name)
VALUES ('tag4');

INSERT INTO tag(name)
VALUES ('tag5');

INSERT INTO tag(name)
VALUES ('tag6');

INSERT INTO tag(name)
VALUES ('tag7');

INSERT INTO gift_certificate_tag(gift_certificate_id, tag_id)
VALUES (1, 1);

INSERT INTO gift_certificate_tag(gift_certificate_id, tag_id)
VALUES (1, 2);

INSERT INTO gift_certificate_tag(gift_certificate_id, tag_id)
VALUES (1, 3);

INSERT INTO gift_certificate_tag(gift_certificate_id, tag_id)
VALUES (2, 3);

INSERT INTO gift_certificate_tag(gift_certificate_id, tag_id)
VALUES (3, 3);

INSERT INTO gift_certificate_tag(gift_certificate_id, tag_id)
VALUES (3, 1);

INSERT INTO gift_certificate_tag(gift_certificate_id, tag_id)
VALUES (4, 4);

INSERT INTO gift_certificate_tag(gift_certificate_id, tag_id)
VALUES (5, 5);

INSERT INTO gift_certificate_tag(gift_certificate_id, tag_id)
VALUES (6, 6);

INSERT INTO user_data (first_name, last_name)
VALUES ('Ivan', 'Ivanov');

INSERT INTO user_data (first_name, last_name)
VALUES ( 'Petr', 'Petrov');

INSERT INTO user_data (first_name, last_name)
VALUES ('Alex', 'Popov');

INSERT INTO user_data (first_name, last_name)
VALUES ('Dmitriy', 'Morozov');


INSERT INTO order_data(user_id, certificate_id, price, purchase_date)
VALUES (1, 2, 445, '2023-05-07T17:07:35.76');

INSERT INTO order_data(user_id, certificate_id, price, purchase_date)
VALUES (2, 4, 45.3, '2023-05-07T17:07:35.76');

INSERT INTO order_data(user_id, certificate_id, price, purchase_date)
VALUES (3, 2, 898, '2023-05-07T17:07:35.76');

INSERT INTO order_data(user_id, certificate_id, price, purchase_date)
VALUES (4, 2, 234.4, '2023-05-07T17:07:35.76');

INSERT INTO order_data(user_id, certificate_id, price, purchase_date)
VALUES (4, 3, 456.3, '2023-05-07T17:07:35.76');