
-- set @user_id = 2666;
-- set @user_id = 2660;
-- set @user_id = 2740;
-- set @user_id = 2738;


-- 
# Hard delete a user
delete from `users` where `id` = @user_id;
delete from `email_confirmations` where `user_id` = @user_id;
delete from `user_sessions` where `user_id` = @user_id;
delete from `app_resources` where `user_id` = @user_id;
delete from `password_resets` where `user_id` = @user_id;
delete from `email_stops` where `user_id` = @user_id;
delete from `email_notification_rates` where `user_id` = @user_id;
delete from `users_to_networks` where `user_id` = @user_id;
delete from `tooltips` where `user_id` = @user_id;
delete from `user_to_smart_groups` where `user_id` = @user_id;
delete from `user_links` where `from_user_id` = @user_id or `to_user_id` = @user_id;
delete from `smart_groups` where `user_id` = @user_id;
delete from `smart_group_results` where `user_id` = @user_id;
delete from `answers` where `user_id` = @user_id;
delete from `answer_options` where `user_id` = @user_id;
delete from `active_answers` where `user_id` = @user_id;
delete from `shared_items` where `user_id` = @user_id;
delete from `shared_comments` where `user_id` = @user_id;

