delete from `questy`.`user_to_smart_groups`
where
    `smart_group_ref` in (select `ref` from `smart_groups` where `visibility` = 0 and `network_id` = 2000)
and
    `network_id` = 2000;



