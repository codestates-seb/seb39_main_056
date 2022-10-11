DROP TABLE IF EXISTS vegetarian CASCADE;

create table vegetarian (
                            vegetarian_type varchar(255),
                            levels integer
) engine=InnoDB;