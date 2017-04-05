create table voice (
	voice_id	varchar(32)	not null,
	posted_by	varchar(32)	not null,
	posted_on	timestamp	not null,
	subject	varchar(128)	not null,
	voice_body	varchar(1024)	not null,
	primary key (voice_id)
);
