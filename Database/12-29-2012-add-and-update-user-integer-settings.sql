insert into `user_integer_settings` (`user_id`)
select `id` from `users` where `face_ref` is null;

update `user_integer_settings`
set `setting_id` = 200, `setting_value` = 10
where `setting_id` = 0;