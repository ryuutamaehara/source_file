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
