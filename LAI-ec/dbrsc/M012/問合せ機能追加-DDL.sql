-- 問合わせテーブル
Create table t_inquiry (
inq_id NUMERIC NOT NULL, --  問合せID
customer_id NUMERIC NOT NULL, --  顧客ID
accepted_on TIMESTAMP NOT NULL default 0, --  問合せ受付日時 -- default 0指定を付けないと、ON UPDATE指定が勝手に付加される
inq_status_id NUMERIC(2) NOT NULL, --  問合せステータスID
inq_subject VARCHAR(256) NOT NULL, --  問合せタイトル
inq_body VARCHAR(4000) NOT NULL --  問合せ本文
,
PRIMARY KEY (inq_id)
)
;
