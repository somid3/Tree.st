-- select concat(email)
-- from `users`
-- where `id` in (
  select `user_id` from `user_sessions` group by `user_id` order by sum(updates) desc
);