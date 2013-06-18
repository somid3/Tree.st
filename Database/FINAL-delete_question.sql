-- set @network_id = XXX;
-- set @question_ref = XXX;

delete from `questions` where `network_id` = @network_id and `ref` = @question_ref;
delete from `question_options` where `network_id` = @network_id and `question_ref` = @question_ref;

delete from `flow_rules` where `network_id` = @network_id and `to_question_ref` = @question_ref;
delete from `flow_rules` where `network_id` = @network_id and `from_question_ref` = @question_ref;

delete from `answers` where `network_id` = @network_id and `question_ref` = @question_ref;
delete from `answer_options` where `network_id` = @network_id and `question_ref` = @question_ref;
delete from `active_answers` where `network_id` = @network_id and `question_ref` = @question_ref;
