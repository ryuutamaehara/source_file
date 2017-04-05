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
