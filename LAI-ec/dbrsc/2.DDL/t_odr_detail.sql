-- �󒍖��׃e�[�u��
Create table t_odr_detail (
odr_id NUMERIC NOT NULL, --  �����ԍ�
customer_id NUMERIC NOT NULL, --  �ڋqID
detail_num NUMERIC(4) NOT NULL, --  ���הԍ�
prd_id NUMERIC NOT NULL, --  ���iID
quantity NUMERIC(4) NOT NULL, --  ����
unit_price NUMERIC(10) NOT NULL, --  �P��
total_price NUMERIC(10) NOT NULL, --  ���v
odr_status_id NUMERIC(4) NOT NULL, --  �����X�e�[�^�XID
odr_prd_nm VARCHAR(1024) NOT NULL, --  �󒍎����i��
odr_dd_desc VARCHAR(256) --  �󒍎��[���ڈ�
,
PRIMARY KEY (odr_id,detail_num)
)
;
