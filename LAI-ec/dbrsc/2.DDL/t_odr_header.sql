-- �󒍓`�[�e�[�u��
;
Create table t_odr_header (
odr_id NUMERIC NOT NULL, --  �����ԍ�
accepted_on TIMESTAMP NOT NULL default 0, --  �󒍓��� -- default 0�w���t���Ȃ��ƁAON UPDATE�w�肪����ɕt�������
customer_id NUMERIC NOT NULL, --  �ڋqID
odr_status_id NUMERIC(4) NOT NULL, --  �����X�e�[�^�XID
payment_method_id NUMERIC(2) NOT NULL, --  �x�����@ID
payment_method_sub_cd VARCHAR(16), --  �x�����@�⏕�R�[�h
payment_status_id NUMERIC(2) NOT NULL, --  �x�����X�e�[�^�XID
product_total_price NUMERIC(10) NOT NULL, --  ���i���v���z
discounted_value NUMERIC(10) NOT NULL, --  �l�����z
total_payment NUMERIC(10) NOT NULL, --  ���x���z
deliv_to NUMERIC(1) NOT NULL, --  ���͂����ʁi�����Ҏ���E���̑��j
customer_nm VARCHAR(128) NOT NULL, --  �ڋq��
address_1 VARCHAR(32) NOT NULL, --  �ڋq�Z���P
address_2 VARCHAR(32) NOT NULL, --  �ڋq�Z���Q
address_3 VARCHAR(32), --  �ڋq�Z���R
address_4 VARCHAR(32), --  �ڋq�Z���S
dt_nm VARCHAR(128) NOT NULL, --  ���͂��於
dt_address_1 VARCHAR(32) NOT NULL, --  ���͂���Z���P
dt_address_2 VARCHAR(32) NOT NULL, --  ���͂���Z���Q
dt_address_3 VARCHAR(32), --  ���͂���Z���R
dt_address_4 VARCHAR(32) --  ���͂���Z���S
,
PRIMARY KEY (odr_id)
)
;
create index idx_odr_header_01 on t_odr_header (customer_id);
