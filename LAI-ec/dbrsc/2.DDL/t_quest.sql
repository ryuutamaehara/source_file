-- クエストテーブル
Create table t_quest (
quest_cd VARCHAR(16) NOT NULL, --  クエストコード
quest_nm VARCHAR(128) NOT NULL, --  クエスト名
description VARCHAR(4000) --  説明
,
PRIMARY KEY (quest_cd)
)
;
