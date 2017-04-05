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
