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
