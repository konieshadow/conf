DROP TABLE IF EXISTS "config_info";
create table "config_info"
(
    "id"          BIGINT auto_increment
        primary key,
    "data_id"     VARCHAR(255) default ''                not null,
    "group_id"    VARCHAR(255) default ''                not null,
    "namespace"   VARCHAR(255) default ''                not null,
    "content"     LONGTEXT,
    "description" VARCHAR(255) default ''                not null,
    "created_at"  DATETIME     default CURRENT_TIMESTAMP not null,
    "updated_at"  DATETIME     default CURRENT_TIMESTAMP not null
);

DROP TABLE IF EXISTS "namespace";
create table "namespace"
(
    "id"          BIGINT auto_increment
        primary key,
    "name"        VARCHAR(50)  default ''                not null,
    "key"         VARCHAR(255) default ''                not null,
    "description" VARCHAR(255) default ''                not null,
    "created_at"  DATETIME     default CURRENT_TIMESTAMP not null,
    "updated_at"  DATETIME     default CURRENT_TIMESTAMP not null
);

DROP TABLE IF EXISTS "user";
create table "user"
(
    "id"         BIGINT auto_increment
        primary key,
    "name"       VARCHAR(50)  default ''                not null,
    "username"   VARCHAR(50)  default ''                not null,
    "password"   VARCHAR(255) default ''                not null,
    "descripton" VARCHAR(255) default ''                not null,
    "created_at" DATETIME     default CURRENT_TIMESTAMP not null,
    "updated_at" DATETIME     default CURRENT_TIMESTAMP not null
);