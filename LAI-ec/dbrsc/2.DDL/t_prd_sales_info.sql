-- ¤iÌîñ
Create table t_prd_sales_info (
prd_id NUMERIC NOT NULL, --  ¤iID
prd_cd VARCHAR(64) NOT NULL, --  ¤iR[h
allocatable_quantity NUMERIC(4) NOT NULL, --  øÂ\
initial_inv_quantity NUMERIC(4) NOT NULL, --  úÝÉ
sold_quantity NUMERIC(4) NOT NULL --  ÌÏ
,
PRIMARY KEY (prd_id)
)
;
create unique index idx_prd_sales_info_01 on t_prd_sales_info (prd_cd);
