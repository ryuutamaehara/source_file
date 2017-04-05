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
