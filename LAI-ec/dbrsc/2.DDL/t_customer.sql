-- ŒÚ‹q
Create table t_customer (
customer_id NUMERIC NOT NULL, --  ŒÚ‹qID
login_nm VARCHAR(128) NOT NULL, --  ƒƒOƒCƒ“–¼
email VARCHAR(128), --  ƒ[ƒ‹ƒAƒhƒŒƒX
password VARCHAR(64) NOT NULL, --  ƒpƒXƒ[ƒh
customer_nm VARCHAR(128), --  ŒÚ‹q–¼
address_1 VARCHAR(32), --  ZŠ‚P
address_2 VARCHAR(32), --  ZŠ‚Q
address_3 VARCHAR(32), --  ZŠ‚R
address_4 VARCHAR(32) --  ZŠ‚S
,
PRIMARY KEY (customer_id)
)
;
create unique index idx_customer_01 on t_customer (login_nm);
create index idx_customer_02 on t_customer (login_nm,password);
