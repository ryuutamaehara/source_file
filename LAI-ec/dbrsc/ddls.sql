-- キャンペーンテーブル
Create table t_campaign (
campaign_id NUMERIC(10) NOT NULL, --  キャンペーンID
campaign_nm VARCHAR(128) NOT NULL, --  キャンペーンID名
valid_start_dttm TIMESTAMP NOT NULL, --  有効開始日時
valid_end_dttm TIMESTAMP NOT NULL, --  有効終了日時
discount_type_id NUMERIC NOT NULL, --  ディスカウント種別ID
discount_value NUMERIC NOT NULL, --  ディスカウント値
is_active BOOLEAN NOT NULL --  有効である
,
PRIMARY KEY (campaign_id)
)
;
create index idx_campaign_01 on t_campaign (campaign_nm);
create index idx_campaign_02 on t_campaign (valid_start_dttm,valid_end_dttm);
-- デフォルトで余計な設定（以下の２つ）が付加されるので、除去する。
-- DEFAULT CURRENT_TIMESTAMP
-- ON UPDATE CURRENT_TIMESTAMP
alter table t_campaign modify valid_start_dttm timestamp default 0;
-- カテゴリテーブル
Create table t_cate (
cate_cd VARCHAR(16) NOT NULL, --  カテゴリコード
master_cate_cd VARCHAR(16) NOT NULL, --  マスターカテゴリコード
cate_nm VARCHAR(128) NOT NULL, --  カテゴリ名
kana_cate_nm VARCHAR(128), --  カテゴリ名（カナ）
cate_nm_for_url VARCHAR(1024) NOT NULL, --  URL用カテゴリ名
display_order NUMERIC(12) NOT NULL --  表示順
,
PRIMARY KEY (cate_cd)
)
;
-- 顧客
Create table t_customer (
customer_id NUMERIC NOT NULL, --  顧客ID
login_nm VARCHAR(128) NOT NULL, --  ログイン名
email VARCHAR(128), --  メールアドレス
password VARCHAR(64) NOT NULL, --  パスワード
customer_nm VARCHAR(128), --  顧客名
address_1 VARCHAR(32), --  住所１
address_2 VARCHAR(32), --  住所２
address_3 VARCHAR(32), --  住所３
address_4 VARCHAR(32) --  住所４
,
PRIMARY KEY (customer_id)
)
;
create unique index idx_customer_01 on t_customer (login_nm);
create index idx_customer_02 on t_customer (login_nm,password);
-- ID採番管理テーブル
Create table t_id_manager (
id_nm VARCHAR(128) NOT NULL, --  ID名
id_value NUMERIC NOT NULL, --  最後に使われたID値
updated_on TIMESTAMP NOT NULL --  最終更新日
,
PRIMARY KEY (id_nm)
)
;
-- マスターカテゴリテーブル
Create table t_master_cate (
master_cate_cd VARCHAR(16) NOT NULL, --  マスターカテゴリコード
master_cate_nm VARCHAR(128) NOT NULL, --  マスターカテゴリ名
kana_master_cate_nm VARCHAR(128), --  マスターカテゴリ名（カナ）
master_cate_nm_for_url VARCHAR(1024) NOT NULL, --  URL用マスターカテゴリ名
display_order NUMERIC(12) NOT NULL --  表示順
,
PRIMARY KEY (master_cate_cd)
)
;
-- 管理者ユーザ
Create table t_mc_operator (
operator_id NUMERIC(10) NOT NULL, --  オペレータＩＤ
operator_login_nm VARCHAR(32) NOT NULL, --  オペレータログイン識別名
operator_display_nm VARCHAR(128) NOT NULL, --  オペレータ表示名
password VARCHAR(64) NOT NULL, --  パスワード（暗号化）
last_login_dttm DATE, --  最新ログイン日時
dept_cd VARCHAR(16), --  部署コード
is_active BOOLEAN NOT NULL --  有効である
,
PRIMARY KEY (operator_id)
)
;
create unique index idx_mc_operator_01 on t_mc_operator (operator_login_nm);
-- ニューステーブル
Create table t_news (
news_id NUMERIC NOT NULL, --  ニュースID
news_category_id NUMERIC NOT NULL, --  ニュース種別ID
valid_start_dttm TIMESTAMP NOT NULL, --  有効開始日時
valid_end_dttm TIMESTAMP NOT NULL, --  有効終了日時
display_order NUMERIC(12) NOT NULL, --  表示順
is_active BOOLEAN NOT NULL, --  有効である
is_on_view BOOLEAN NOT NULL, --  公開中である
title VARCHAR(128) NOT NULL, --  タイトル
body VARCHAR(4000) --  本文
,
PRIMARY KEY (news_id)
)
;
-- デフォルトで余計な設定（以下の２つ）が付加されるので、除去する。
-- DEFAULT CURRENT_TIMESTAMP
-- ON UPDATE CURRENT_TIMESTAMP
alter table t_news modify valid_start_dttm timestamp default 0;
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
-- 受注伝票テーブル
;
Create table t_odr_header (
odr_id NUMERIC NOT NULL, --  注文番号
accepted_on TIMESTAMP NOT NULL, --  受注日時
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
-- デフォルトで余計な設定（以下の２つ）が付加されるので、除去する。
-- DEFAULT CURRENT_TIMESTAMP
-- ON UPDATE CURRENT_TIMESTAMP
alter table t_odr_header modify accepted_on timestamp default 0;
Create table t_odr_status (
odr_status_id NUMERIC(4) NOT NULL, --  受注ステータスID
odr_status_nm VARCHAR(128) NOT NULL --  受注ステータス名
,
PRIMARY KEY (odr_status_id)
)
;
Create table t_payment_method (
payment_method_id NUMERIC(2) NOT NULL, --  支払方法ID
payment_method_nm VARCHAR(128) NOT NULL --  支払方法名
,
PRIMARY KEY (payment_method_id)
)
;
-- 商品テーブル
Create table t_prd (
prd_id NUMERIC NOT NULL, --  商品ID
prd_cd VARCHAR(64) NOT NULL, --  商品コード
prd_nm VARCHAR(128) NOT NULL, --  商品名
prd_nm_kn VARCHAR(256) NOT NULL, --  商品名カナ
prd_nm_for_url VARCHAR(256) NOT NULL, --  URL用商品モデル名
dd_desc VARCHAR(256), --  納期目安
selling_situ_id NUMERIC(2) NOT NULL, --  販売状況ID
is_on_view BOOLEAN NOT NULL, --  公開中である
is_active BOOLEAN NOT NULL, --  有効である
list_price NUMERIC(10) NOT NULL, --  定価
selling_price NUMERIC(10) NOT NULL, --  販売価格
purchase_price NUMERIC(10) NOT NULL, --  仕入単価
display_start_dttm TIMESTAMP, --  表示開始日時
display_end_dttm TIMESTAMP, --  表示終了日時
selling_start_dttm TIMESTAMP, --  販売開始日時
selling_end_dttm TIMESTAMP, --  販売終了日時
is_on_back_order BOOLEAN NOT NULL, --  取り寄せが必要
leading_category_cd VARCHAR(16) NOT NULL, --  主カテゴリコード
description VARCHAR(256) NOT NULL, --  説明
is_review_target BOOLEAN NOT NULL, --  レビュー対象である
memo VARCHAR(4000) --  メモ（備考）
,
PRIMARY KEY (prd_id)
)
-- 以下は、インデクス項目に767bytesを超える項目を使用するための追加設定（my.iniの設定の変更も必要）
-- ENGINE=InnoDB, ROW_FORMAT=DYNAMIC
;
create unique index idx_prd_01 on t_prd (prd_cd);
-- create index idx_prd_03 on t_prd (description); -- こちらをインデクスにするためにはmy.ini設定の変更が必要
-- デフォルトで余計な設定（以下の２つ）が付加されるので、除去する。
-- DEFAULT CURRENT_TIMESTAMP
-- ON UPDATE CURRENT_TIMESTAMP
alter table t_prd modify display_start_dttm timestamp default 0;
-- テンチョーのオススメテーブル
Create table t_prd_rec (
prd_rec_id NUMERIC NOT NULL, --  商品レコメンドID
prd_id NUMERIC NOT NULL, --  商品ID
valid_start_dttm TIMESTAMP NOT NULL, --  有効開始日時
valid_end_dttm TIMESTAMP NOT NULL, --  有効終了日時
is_active BOOLEAN NOT NULL, --  有効である
is_on_view BOOLEAN NOT NULL, --  公開中である
display_order NUMERIC(12) NOT NULL, --  表示順
description VARCHAR(4000) --  説明
,
PRIMARY KEY (prd_rec_id)
)
;
-- デフォルトで余計な設定（以下の２つ）が付加されるので、除去する。
-- DEFAULT CURRENT_TIMESTAMP
-- ON UPDATE CURRENT_TIMESTAMP
alter table t_prd_rec modify valid_start_dttm timestamp default 0;
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
-- クエストテーブル
Create table t_quest (
quest_cd VARCHAR(16) NOT NULL, --  クエストコード
quest_nm VARCHAR(128) NOT NULL, --  クエスト名
description VARCHAR(4000) --  説明
,
PRIMARY KEY (quest_cd)
)
;
create view v_odr_header as 
select
 t_odr_header.odr_id
,t_odr_header.accepted_on
,t_odr_header.customer_id
,t_odr_header.odr_status_id
,t_odr_header.payment_method_id
,t_odr_header.payment_method_sub_cd
,t_odr_header.payment_status_id
,t_odr_header.product_total_price
,t_odr_header.discounted_value
,t_odr_header.total_payment
,t_odr_header.deliv_to
,t_odr_header.customer_nm
,t_odr_header.address_1
,t_odr_header.address_2
,t_odr_header.address_3
,t_odr_header.address_4
,t_odr_header.dt_nm
,t_odr_header.dt_address_1
,t_odr_header.dt_address_2
,t_odr_header.dt_address_3
,t_odr_header.dt_address_4
,t_odr_status.odr_status_nm
,t_payment_method.payment_method_nm
from 
 t_odr_header
,t_odr_status
,t_payment_method
 where t_odr_header.odr_status_id = t_odr_status.odr_status_id
 and t_odr_header.payment_method_id = t_payment_method.payment_method_id
;

create view v_prd_rec as 
select
 t_prd_rec.prd_rec_id
,t_prd_rec.prd_id
,t_prd_rec.valid_start_dttm
,t_prd_rec.valid_end_dttm
,t_prd_rec.is_active as r_is_active
,t_prd_rec.is_on_view as r_is_on_view
,t_prd_rec.display_order
,t_prd_rec.description as r_description
,t_prd.prd_cd
,t_prd.prd_nm
,t_prd.prd_nm_kn
,t_prd.prd_nm_for_url
,t_prd.dd_desc
,t_prd.selling_situ_id
,t_prd.is_on_view as p_is_on_view
,t_prd.is_active as p_is_active
,t_prd.list_price
,t_prd.selling_price
,t_prd.purchase_price
,t_prd.display_start_dttm
,t_prd.display_end_dttm
,t_prd.selling_start_dttm
,t_prd.selling_end_dttm
,t_prd.is_on_back_order
,t_prd.leading_category_cd
,t_prd.description as p_description
,t_prd.is_review_target
,t_prd.memo
from 
 t_prd_rec
,t_prd
 where t_prd_rec.prd_id = t_prd.prd_id
;
