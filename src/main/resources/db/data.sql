-- INSERT INTO address(city_name, country_name, postal_code, state_name, street_name)
-- VALUES ('Moscow','Russia','12321','MO','Arbatsckaya');
--
--
-- INSERT INTO basket(id)
-- VALUES (1);
--
-- INSERT INTO chosen(id)
-- VALUES (1);
--
-- INSERT INTO order_list(id)
-- VALUES (1);
--
-- INSERT INTO news(id, date_of_finish, date_of_start)
-- VALUES (1,'01-06-2023','01-03-2023');
--
-- INSERT INTO discounts(id, date_of_finish, date_of_start, percent)
-- VALUES (1,'01-03-2023','01-01-2023',25);
--
-- INSERT INTO promotions(id, date_of_finish, date_of_start)
-- VALUES (1,'01-03-2023','25-03-2023');
--
--
-- INSERT INTO products(id, appointment, brand,
--                      capacity_battery, color,
--                      cpu, date_of_issue,
--                      display_inch, guarantee,
--                      image, name, os,
--                      price, ram, rom,
--                      sim, weight,
--                      basket_id, category_id, chosen_id,
--                      discount_id,
--                      news_id, order_list_id, promotion_id)
-- VALUES (1,'phone','APPLE','128GB','RED','DWQ2','01-02-2024',
--         '5.6MPX','YES','HJF5849JK39JSK','IPHONE','4GB',
--         '45000','57TU','567JK','TELE2','450GR',1,1,1,1,1,1);
-- INSERT INTO categories(id, name, subcat, product_id)
-- VALUES (1,'SMARTPHONES','PHONE',1);