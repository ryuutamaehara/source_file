-- Úq
Create table t_customer (
customer_id NUMERIC NOT NULL, --  ÚqID
login_nm VARCHAR(128) NOT NULL, --  OC¼
email VARCHAR(128), --  [AhX
password VARCHAR(64) NOT NULL, --  pX[h
customer_nm VARCHAR(128), --  Úq¼
address_1 VARCHAR(32), --  ZP
address_2 VARCHAR(32), --  ZQ
address_3 VARCHAR(32), --  ZR
address_4 VARCHAR(32) --  ZS
,
PRIMARY KEY (customer_id)
)
;
create unique index idx_customer_01 on t_customer (login_nm);
create index idx_customer_02 on t_customer (login_nm,password);
