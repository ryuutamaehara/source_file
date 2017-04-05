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
