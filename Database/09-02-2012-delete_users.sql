
-- set @user_id = 2015;
-- set @user_id = 2113;
-- set @user_id = 2114;
-- set @user_id = 2115;
-- set @user_id = 2116;
-- set @user_id = 2117;
-- set @user_id = 2118;
-- set @user_id = 2119;
-- set @user_id = 2120;
-- set @user_id = 2121;
-- set @user_id = 2122;
-- set @user_id = 2123;
-- set @user_id = 2124;
-- set @user_id = 2125;
-- set @user_id = 2126;
-- set @user_id = 2127;
-- set @user_id = 2128;
-- set @user_id = 2129;
-- set @user_id = 2130;
-- set @user_id = 2174;
-- set @user_id = 2273;
-- set @user_id = 2286;

-- 
# Hard delete a user
delete from `users` where `id` = @user_id;
delete from `email_confirmations` where `user_id` = @user_id;
delete from `user_sessions` where `user_id` = @user_id;
delete from `app_resources` where `user_id` = @user_id;
delete from `password_resets` where `user_id` = @user_id;
delete from `email_stops` where `user_id` = @user_id;
delete from `users_to_networks` where `user_id` = @user_id;
delete from `tooltips` where `user_id` = @user_id;
delete from `user_favorite_smart_groups` where `user_id` = @user_id;
delete from `user_links` where `from_user_id` = @user_id or `to_user_id` = @user_id;
delete from `smart_groups` where `user_id` = @user_id and `visibility` = 0;
delete from `smart_group_results` where `user_id` = @user_id;
delete from `answers` where `user_id` = @user_id;
delete from `answer_options` where `user_id` = @user_id;
delete from `active_answers` where `user_id` = @user_id;
delete from `shared_items` where `user_id` = @user_id;
delete from `shared_comments` where `user_id` = @user_id;

