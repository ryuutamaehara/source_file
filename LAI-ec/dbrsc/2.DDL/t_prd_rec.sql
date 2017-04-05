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
