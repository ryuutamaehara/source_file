Create table t_odr_status (
odr_status_id NUMERIC(4) NOT NULL, --  受注ステータスID
odr_status_nm VARCHAR(128) NOT NULL --  受注ステータス名
,
PRIMARY KEY (odr_status_id)
)
;
