INSERT INTO "user" ("id", "name", "username", "password", "descripton", "created_at", "updated_at") VALUES (1, 'Admin', 'admin', '$2a$10$uU.TQ3O51roiE.nknmPui.Uej8TRsFd1lWC3Mkn22AWJBmujzQ8X6', '', '2020-07-11 16:25:58.000000', '2020-07-11 16:25:58.000000');

INSERT INTO "config_info" ("id", "data_id", "group_id", "namespace", "content", "description", "created_at", "updated_at") VALUES (1, 'conf.properties', 'my-app', 'e7cca53b-df63-461c-923a-5aeade0d826a', 'debug=true\nlog.level=error', '', '2020-08-04 13:59:23.953000', '2020-08-04 13:59:23.953000');

INSERT INTO "namespace" ("id", "name", "key", "description", "created_at", "updated_at") VALUES (1, 'default', 'a7b9f3a4-a257-46e3-a456-15c1a75b6dbc', '', '2020-08-04 13:53:12.615000', '2020-08-04 13:53:12.615000');
INSERT INTO "namespace" ("id", "name", "key", "description", "created_at", "updated_at") VALUES (2, 'dev', 'e7cca53b-df63-461c-923a-5aeade0d826a', '', '2020-08-04 13:53:12.646000', '2020-08-04 13:53:12.646000');
INSERT INTO "namespace" ("id", "name", "key", "description", "created_at", "updated_at") VALUES (3, 'test', '59b94166-37fa-4e37-a87f-4f9673d3cce2', '', '2020-08-04 13:53:12.677000', '2020-08-04 13:53:12.677000');