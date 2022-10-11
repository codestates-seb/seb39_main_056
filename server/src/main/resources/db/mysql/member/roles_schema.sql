DROP TABLE IF EXISTS roles CASCADE;

CREATE TABLE roles (
                              member_id bigint,
                              roles varchar(255)
) engine=InnoDB;