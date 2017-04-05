-- 受注明細テーブル
Create table t_odr_detail (
odr_id NUMERIC NOT NULL, --  注文番号
customer_id NUMERIC NOT NULL, --  顧客ID
detail_num NUMERIC(4) NOT NULL, --  明細番号
prd_id NUMERIC NOT NULL, --  商品ID
quantity NUMERIC(4) NOT NULL, --  数量
unit_price NUMERIC(10) NOT NULL, --  単価
total_price NUMERIC(10) NOT NULL, --  小計
odr_status_id NUMERIC(4) NOT NULL, --  注文ステータスID
odr_prd_nm VARCHAR(1024) NOT NULL, --  受注時商品名
odr_dd_desc VARCHAR(256) --  受注時納期目安
,
PRIMARY KEY (odr_id,detail_num)
)
;
