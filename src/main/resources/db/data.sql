INSERT INTO address(city_name, country_name, postal_code, state_name, street_name)
VALUES ('Moscow','Russia','12321','MO','Arbatsckaya');


INSERT INTO basket(id)
VALUES (1);

INSERT INTO chosen(id)
VALUES (1);

INSERT INTO order_list(id)
VALUES (1);

INSERT INTO news(id, date_of_finish, date_of_start)
VALUES (1,'01-06-2023','01-03-2023');

INSERT INTO discounts(id, date_of_finish, date_of_start, percent)
VALUES (1,'01-03-2023','01-01-2023',25);

INSERT INTO promotions(id, date_of_finish, date_of_start)
VALUES (1,'2023-04-01','2023-03-25');

INSERT INTO categories(id, name, subcat)
VALUES (1,'Phone','SMARTPHONES');

INSERT INTO products(id, appointment, brand,
                     capacity_battery, color,price,
                     cpu, date_of_issue,
                     display_inch, guarantee,
                     image, name, os,
                      ram, rom,
                     sim, weight,
                     basket_id, chosen_id,
                     discount_id,
                     news_id, order_list_id, promotion_id,category_id)
VALUES (1,'phone','ASUS','128GB','RED',45000,'DWQ2','01-02-2024',
        '5.6MPX','YES','HJF5849JK39JSK','IPHONE','IOS'
        ,'57TU','567JK','TELE2','450GR',1,1,1,1,1,1,1);

INSERT INTO payments(id, amount, created_date, payment_method)
VALUES (1,53,'01-02-2023','CART');
INSERT INTO delivery_men(id, last_name, name, phone_number)
VALUES (1,'Mamatbekova','Aidai','+78956673387');

INSERT INTO users(id, email, first_name, last_name,
                  password, role,
                  address_id, basket_id,
                  chosen_id, order_list_id, payments_id)
VALUES (1,'user@mail.ru','User','Userov',
        'user','USER',1,1,1,1,1);

INSERT INTO users(id, email, first_name, last_name,
                  password, role,
                  address_id, basket_id,
                  chosen_id, order_list_id, payments_id)
VALUES (2,'admin@mail.ru','Admin','Adminov',
        'admin','ADMIN',1,1,1,1,1);

INSERT INTO orders(id, count_of_product, order_status,
                   shipping, total_sum,
                   type_payment, delivery_man_id,
                   product_id, user_id)
VALUES (1,105,'CREATED','dsk',12000,'card',1,1,1)
