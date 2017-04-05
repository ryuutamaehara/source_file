-- ID採番管理テーブル
Create table t_id_manager (
id_nm VARCHAR(128) NOT NULL, --  ID名
id_value NUMERIC NOT NULL, --  最後に使われたID値
updated_on TIMESTAMP NOT NULL --  最終更新日
,
PRIMARY KEY (id_nm)
)
;
