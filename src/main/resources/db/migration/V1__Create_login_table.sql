create table login
(
    id   int auto_increment primary key,
    account_id   character varying(100),
    name         character varying(50),
    token        character(36),
    gmt_create   bigint,
    gmt_modified bigint
);
