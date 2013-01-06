delete from `users_to_networks` where `user_id` = 3 and `network_id` > 2003;

delete from `user_links` where `from_user_id` = 3 or `to_user_id` = 3;

select * from `users` where `id` in (2812, 2802, 2803, 2407, 2801, 2800, 2796, 2663, 2753, 2792, 2727, 2055, 2769, 2686, 2758, 2778, 2693, 2586);


update `users` set email = 'zspinozo@mit.edu', first_name = 'Zach', last_name = 'Spiniozo', face_url = '/resources/faces/40.jpg' where id = 2055;
update `users` set email = 'laseras@mit.edu', first_name = 'Sandra', last_name = 'Lasera', face_url = '/resources/faces/22.jpg' where id = 2407;
update `users` set email = 'laseras@mit.edu', first_name = 'Sato', last_name = 'Ching', face_url = '/resources/faces/26.jpg' where id = 2586;
update `users` set email = 'bxima@mit.edu', first_name = 'Burlas', last_name = 'Xima', face_url = '/resources/faces/21.jpg' where id = 2663;
update `users` set email = 'rchandler@mit.edu', first_name = 'Ryan', last_name = 'Chandler', face_url = '/resources/faces/20.jpg' where id = 2686;

update `users` set email = 'chimlind@mit.edu', first_name = 'Lindsay', last_name = 'Chimpkuy', face_url = '/resources/faces/23.jpg' where id = 2693;
update `users` set email = 'ashmc@mit.edu', first_name = 'Ashley', last_name = 'McCalib', face_url = '/resources/faces/24.jpg' where id = 2727;
update `users` set email = 'mbenoy@mit.edu', first_name = 'Michalis', last_name = 'Benoy', face_url = '/resources/faces/28.jpg' where id = 2753;
update `users` set email = 'xiwan@mit.edu', first_name = 'Xi', last_name = 'Wan', face_url = '/resources/faces/30.jpg' where id = 2758;
update `users` set email = 'jvelaz@mit.edu', first_name = 'Jorge', last_name = 'Velazquez', face_url = '/resources/faces/32.jpg' where id = 2769;
update `users` set email = 'gotvi@mit.edu', first_name = 'Vishal', last_name = 'Gotrik', face_url = '/resources/faces/33.jpg' where id = 2778;
update `users` set email = 'wlh@mit.edu', first_name = 'Wang-Li', last_name = 'Hirono', face_url = '/resources/faces/36.jpg' where id = 2792;
update `users` set email = 'loesmith@mit.edu', first_name = 'Brian (Loe)', last_name = 'Smith', face_url = '/resources/faces/25.jpg' where id = 2796;
update `users` set email = 'abeboi@mit.edu', first_name = 'Åbebi', last_name = 'Boipelo', face_url = '/resources/faces/27.jpg' where id = 2800;
update `users` set email = 'glarson@mit.edu', first_name = 'Greg', last_name = 'Larson', face_url = '/resources/faces/29.jpg' where id = 2801;
update `users` set email = 'mshapiro@mit.edu', first_name = 'Monique', last_name = 'Shapiro', face_url = '/resources/faces/31.jpg' where id = 2802;
update `users` set email = 'laseras@mit.edu', first_name = 'Harish', last_name = 'Chatur', face_url = '/resources/faces/34.jpg' where id = 2803;
update `users` set email = 'nori_weng@mit.edu', first_name = 'Nori', last_name = 'Weng', face_url = '/resources/faces/39.jpg' where id = 2812;