-- 商品販売情報
Create table t_prd_sales_info (
prd_id NUMERIC NOT NULL, --  商品ID
prd_cd VARCHAR(64) NOT NULL, --  商品コード
allocatable_quantity NUMERIC(4) NOT NULL, --  引当可能数
initial_inv_quantity NUMERIC(4) NOT NULL, --  初期在庫数
sold_quantity NUMERIC(4) NOT NULL --  販売済数
,
PRIMARY KEY (prd_id)
)
;
create unique index idx_prd_sales_info_01 on t_prd_sales_info (prd_cd);
