create table user_profile (
	user_id	varchar(32)	not null,
	password	varchar(32)	not null,
	user_name	varchar(32)	not null,
	is_super_user	boolean	not null,
	gender	varchar(32)	not null,
	age	numeric(3)	not null,
	primary key (user_id)
);
