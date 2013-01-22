-- set @network_id = XXX;

# Hard delete a network
delete from `networks` where `id` = @network_id;
delete from `network_depends_on` where `network_id` = @network_id or `depends_on` = @network_id;

delete from `network_email_endings` where `network_id` = @network_id;

delete from `network_alpha_settings` where `network_id` = @network_id;
delete from `network_integer_settings` where `network_id` = @network_id;

delete from `users_to_networks` where `network_id` = @network_id;
delete from `user_to_network_integer_settings` where `network_id` = @network_id;

delete from `questions` where `network_id` = @network_id;
delete from `question_options` where `network_id` = @network_id;

delete from `flow_rules` where `network_id` = @network_id;

delete from `answers` where `network_id` = @network_id;
delete from `answer_options` where `network_id` = @network_id;
delete from `active_answers` where `network_id` = @network_id;

delete from `smart_groups` where `network_id` = @network_id;
delete from `smart_group_results` where `network_id` = @network_id;
delete from `user_to_smart_groups` where `network_id` = @network_id;

delete from `user_links` where `network_id` = @network_id;

delete from `shared_items` where `network_id` = @network_id;
delete from `shared_comments` where `network_id` = @network_id;