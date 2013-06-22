-- set @user_id = XXX;

# Hard delete a user
delete from `users` where `id` = @user_id;

delete from `user_sessions` where `user_id` = @user_id;
delete from `app_resources` where `user_id` = @user_id;
delete from `password_resets` where `user_id` = @user_id;

delete from `tooltips` where `user_id` = @user_id;

delete from `smart_groups` where `user_id` = @user_id and `visibility` = 0;
delete from `smart_group_results` where `user_id` = @user_id;

delete from `answers` where `user_id` = @user_id;
delete from `answer_options` where `user_id` = @user_id;
delete from `active_answers` where `user_id` = @user_id;

delete from `shared_items` where `user_id` = @user_id;
delete from `shared_comments` where `user_id` = @user_id;
delete from `shared_votes` where `user_id` = @user_id;

delete from `users_to_networks` where `user_id` = @user_id;
delete from `user_to_smart_groups` where `user_id` = @user_id;
delete from `user_links` where `from_user_id` = @user_id or `to_user_id` = @user_id;
delete from `user_messages` where `from_user_id` = @user_id or `to_user_id` = @user_id;

delete from `user_integer_settings` where `user_id` = @user_id;
delete from `user_alpha_settings` where `user_id` = @user_id;
delete from `user_to_network_integer_settings` where `user_id` = @user_id;


