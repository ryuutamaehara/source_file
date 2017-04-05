-- 受注伝票テーブル
;
Create table t_odr_header (
odr_id NUMERIC NOT NULL, --  注文番号
accepted_on TIMESTAMP NOT NULL default 0, --  受注日時 -- default 0指定を付けないと、ON UPDATE指定が勝手に付加される
customer_id NUMERIC NOT NULL, --  顧客ID
odr_status_id NUMERIC(4) NOT NULL, --  注文ステータスID
payment_method_id NUMERIC(2) NOT NULL, --  支払方法ID
payment_method_sub_cd VARCHAR(16), --  支払方法補助コード
payment_status_id NUMERIC(2) NOT NULL, --  支払いステータスID
product_total_price NUMERIC(10) NOT NULL, --  商品合計金額
discounted_value NUMERIC(10) NOT NULL, --  値引き額
total_payment NUMERIC(10) NOT NULL, --  総支払額
deliv_to NUMERIC(1) NOT NULL, --  お届け先種別（注文者自宅・その他）
customer_nm VARCHAR(128) NOT NULL, --  顧客名
address_1 VARCHAR(32) NOT NULL, --  顧客住所１
address_2 VARCHAR(32) NOT NULL, --  顧客住所２
address_3 VARCHAR(32), --  顧客住所３
address_4 VARCHAR(32), --  顧客住所４
dt_nm VARCHAR(128) NOT NULL, --  お届け先名
dt_address_1 VARCHAR(32) NOT NULL, --  お届け先住所１
dt_address_2 VARCHAR(32) NOT NULL, --  お届け先住所２
dt_address_3 VARCHAR(32), --  お届け先住所３
dt_address_4 VARCHAR(32) --  お届け先住所４
,
PRIMARY KEY (odr_id)
)
;
create index idx_odr_header_01 on t_odr_header (customer_id);
