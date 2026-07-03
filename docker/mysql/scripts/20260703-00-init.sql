CREATE TABLE `users` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `name` varchar(100) NOT NULL,
                         `email` varchar(100) NOT NULL,
                         `created_at` datetime NOT NULL,
                         `updated_at` datetime NOT NULL,
                         CONSTRAINT `PK_USERS` PRIMARY KEY (`id`),
                         CONSTRAINT `UK_USERS_EMAIL` UNIQUE (`email`)
);

CREATE TABLE `schedules` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `user_id` bigint NOT NULL,
                             `title` varchar(100) NOT NULL,
                             `content` text NOT NULL,
                             `created_at` datetime NOT NULL,
                             `updated_at` datetime NOT NULL,
                             CONSTRAINT `PK_SCHEDULES` PRIMARY KEY (`id`),
                             CONSTRAINT `FK_USERS_TO_SCHEDULES`
                                 FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `comments` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `schedule_id` bigint NOT NULL,
                            `user_id` bigint NOT NULL,
                            `content` text NOT NULL,
                            `created_at` datetime NOT NULL,
                            `updated_at` datetime NOT NULL,
                            CONSTRAINT `PK_COMMENTS` PRIMARY KEY (`id`),
                            CONSTRAINT `FK_SCHEDULES_TO_COMMENTS`
                                FOREIGN KEY (`schedule_id`) REFERENCES `schedules` (`id`)
                                ON DELETE CASCADE,
                            CONSTRAINT `FK_USERS_TO_COMMENTS`
                                FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);