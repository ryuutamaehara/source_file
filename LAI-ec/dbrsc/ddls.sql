-- �L�����y�[���e�[�u��
Create table t_campaign (
campaign_id NUMERIC(10) NOT NULL, --  �L�����y�[��ID
campaign_nm VARCHAR(128) NOT NULL, --  �L�����y�[��ID��
valid_start_dttm TIMESTAMP NOT NULL, --  �L���J�n����
valid_end_dttm TIMESTAMP NOT NULL, --  �L���I������
discount_type_id NUMERIC NOT NULL, --  �f�B�X�J�E���g���ID
discount_value NUMERIC NOT NULL, --  �f�B�X�J�E���g�l
is_active BOOLEAN NOT NULL --  �L���ł���
,
PRIMARY KEY (campaign_id)
)
;
create index idx_campaign_01 on t_campaign (campaign_nm);
create index idx_campaign_02 on t_campaign (valid_start_dttm,valid_end_dttm);
-- �f�t�H���g�ŗ]�v�Ȑݒ�i�ȉ��̂Q�j���t�������̂ŁA��������B
-- DEFAULT CURRENT_TIMESTAMP
-- ON UPDATE CURRENT_TIMESTAMP
alter table t_campaign modify valid_start_dttm timestamp default 0;
-- �J�e�S���e�[�u��
Create table t_cate (
cate_cd VARCHAR(16) NOT NULL, --  �J�e�S���R�[�h
master_cate_cd VARCHAR(16) NOT NULL, --  �}�X�^�[�J�e�S���R�[�h
cate_nm VARCHAR(128) NOT NULL, --  �J�e�S����
kana_cate_nm VARCHAR(128), --  �J�e�S�����i�J�i�j
cate_nm_for_url VARCHAR(1024) NOT NULL, --  URL�p�J�e�S����
display_order NUMERIC(12) NOT NULL --  �\����
,
PRIMARY KEY (cate_cd)
)
;
-- �ڋq
Create table t_customer (
customer_id NUMERIC NOT NULL, --  �ڋqID
login_nm VARCHAR(128) NOT NULL, --  ���O�C����
email VARCHAR(128), --  ���[���A�h���X
password VARCHAR(64) NOT NULL, --  �p�X���[�h
customer_nm VARCHAR(128), --  �ڋq��
address_1 VARCHAR(32), --  �Z���P
address_2 VARCHAR(32), --  �Z���Q
address_3 VARCHAR(32), --  �Z���R
address_4 VARCHAR(32) --  �Z���S
,
PRIMARY KEY (customer_id)
)
;
create unique index idx_customer_01 on t_customer (login_nm);
create index idx_customer_02 on t_customer (login_nm,password);
-- ID�̔ԊǗ��e�[�u��
Create table t_id_manager (
id_nm VARCHAR(128) NOT NULL, --  ID��
id_value NUMERIC NOT NULL, --  �Ō�Ɏg��ꂽID�l
updated_on TIMESTAMP NOT NULL --  �ŏI�X�V��
,
PRIMARY KEY (id_nm)
)
;
-- �}�X�^�[�J�e�S���e�[�u��
Create table t_master_cate (
master_cate_cd VARCHAR(16) NOT NULL, --  �}�X�^�[�J�e�S���R�[�h
master_cate_nm VARCHAR(128) NOT NULL, --  �}�X�^�[�J�e�S����
kana_master_cate_nm VARCHAR(128), --  �}�X�^�[�J�e�S�����i�J�i�j
master_cate_nm_for_url VARCHAR(1024) NOT NULL, --  URL�p�}�X�^�[�J�e�S����
display_order NUMERIC(12) NOT NULL --  �\����
,
PRIMARY KEY (master_cate_cd)
)
;
-- �Ǘ��҃��[�U
Create table t_mc_operator (
operator_id NUMERIC(10) NOT NULL, --  �I�y���[�^�h�c
operator_login_nm VARCHAR(32) NOT NULL, --  �I�y���[�^���O�C�����ʖ�
operator_display_nm VARCHAR(128) NOT NULL, --  �I�y���[�^�\����
password VARCHAR(64) NOT NULL, --  �p�X���[�h�i�Í����j
last_login_dttm DATE, --  �ŐV���O�C������
dept_cd VARCHAR(16), --  �����R�[�h
is_active BOOLEAN NOT NULL --  �L���ł���
,
PRIMARY KEY (operator_id)
)
;
create unique index idx_mc_operator_01 on t_mc_operator (operator_login_nm);
-- �j���[�X�e�[�u��
Create table t_news (
news_id NUMERIC NOT NULL, --  �j���[�XID
news_category_id NUMERIC NOT NULL, --  �j���[�X���ID
valid_start_dttm TIMESTAMP NOT NULL, --  �L���J�n����
valid_end_dttm TIMESTAMP NOT NULL, --  �L���I������
display_order NUMERIC(12) NOT NULL, --  �\����
is_active BOOLEAN NOT NULL, --  �L���ł���
is_on_view BOOLEAN NOT NULL, --  ���J���ł���
title VARCHAR(128) NOT NULL, --  �^�C�g��
body VARCHAR(4000) --  �{��
,
PRIMARY KEY (news_id)
)
;
-- �f�t�H���g�ŗ]�v�Ȑݒ�i�ȉ��̂Q�j���t�������̂ŁA��������B
-- DEFAULT CURRENT_TIMESTAMP
-- ON UPDATE CURRENT_TIMESTAMP
alter table t_news modify valid_start_dttm timestamp default 0;
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
-- �󒍓`�[�e�[�u��
;
Create table t_odr_header (
odr_id NUMERIC NOT NULL, --  �����ԍ�
accepted_on TIMESTAMP NOT NULL, --  �󒍓���
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
-- �f�t�H���g�ŗ]�v�Ȑݒ�i�ȉ��̂Q�j���t�������̂ŁA��������B
-- DEFAULT CURRENT_TIMESTAMP
-- ON UPDATE CURRENT_TIMESTAMP
alter table t_odr_header modify accepted_on timestamp default 0;
Create table t_odr_status (
odr_status_id NUMERIC(4) NOT NULL, --  �󒍃X�e�[�^�XID
odr_status_nm VARCHAR(128) NOT NULL --  �󒍃X�e�[�^�X��
,
PRIMARY KEY (odr_status_id)
)
;
Create table t_payment_method (
payment_method_id NUMERIC(2) NOT NULL, --  �x�����@ID
payment_method_nm VARCHAR(128) NOT NULL --  �x�����@��
,
PRIMARY KEY (payment_method_id)
)
;
-- ���i�e�[�u��
Create table t_prd (
prd_id NUMERIC NOT NULL, --  ���iID
prd_cd VARCHAR(64) NOT NULL, --  ���i�R�[�h
prd_nm VARCHAR(128) NOT NULL, --  ���i��
prd_nm_kn VARCHAR(256) NOT NULL, --  ���i���J�i
prd_nm_for_url VARCHAR(256) NOT NULL, --  URL�p���i���f����
dd_desc VARCHAR(256), --  �[���ڈ�
selling_situ_id NUMERIC(2) NOT NULL, --  �̔���ID
is_on_view BOOLEAN NOT NULL, --  ���J���ł���
is_active BOOLEAN NOT NULL, --  �L���ł���
list_price NUMERIC(10) NOT NULL, --  �艿
selling_price NUMERIC(10) NOT NULL, --  �̔����i
purchase_price NUMERIC(10) NOT NULL, --  �d���P��
display_start_dttm TIMESTAMP, --  �\���J�n����
display_end_dttm TIMESTAMP, --  �\���I������
selling_start_dttm TIMESTAMP, --  �̔��J�n����
selling_end_dttm TIMESTAMP, --  �̔��I������
is_on_back_order BOOLEAN NOT NULL, --  ���񂹂��K�v
leading_category_cd VARCHAR(16) NOT NULL, --  ��J�e�S���R�[�h
description VARCHAR(256) NOT NULL, --  ����
is_review_target BOOLEAN NOT NULL, --  ���r���[�Ώۂł���
memo VARCHAR(4000) --  �����i���l�j
,
PRIMARY KEY (prd_id)
)
-- �ȉ��́A�C���f�N�X���ڂ�767bytes�𒴂��鍀�ڂ��g�p���邽�߂̒ǉ��ݒ�imy.ini�̐ݒ�̕ύX���K�v�j
-- ENGINE=InnoDB, ROW_FORMAT=DYNAMIC
;
create unique index idx_prd_01 on t_prd (prd_cd);
-- create index idx_prd_03 on t_prd (description); -- ��������C���f�N�X�ɂ��邽�߂ɂ�my.ini�ݒ�̕ύX���K�v
-- �f�t�H���g�ŗ]�v�Ȑݒ�i�ȉ��̂Q�j���t�������̂ŁA��������B
-- DEFAULT CURRENT_TIMESTAMP
-- ON UPDATE CURRENT_TIMESTAMP
alter table t_prd modify display_start_dttm timestamp default 0;
-- �e���`���[�̃I�X�X���e�[�u��
Create table t_prd_rec (
prd_rec_id NUMERIC NOT NULL, --  ���i���R�����hID
prd_id NUMERIC NOT NULL, --  ���iID
valid_start_dttm TIMESTAMP NOT NULL, --  �L���J�n����
valid_end_dttm TIMESTAMP NOT NULL, --  �L���I������
is_active BOOLEAN NOT NULL, --  �L���ł���
is_on_view BOOLEAN NOT NULL, --  ���J���ł���
display_order NUMERIC(12) NOT NULL, --  �\����
description VARCHAR(4000) --  ����
,
PRIMARY KEY (prd_rec_id)
)
;
-- �f�t�H���g�ŗ]�v�Ȑݒ�i�ȉ��̂Q�j���t�������̂ŁA��������B
-- DEFAULT CURRENT_TIMESTAMP
-- ON UPDATE CURRENT_TIMESTAMP
alter table t_prd_rec modify valid_start_dttm timestamp default 0;
-- ���i�̔����
Create table t_prd_sales_info (
prd_id NUMERIC NOT NULL, --  ���iID
prd_cd VARCHAR(64) NOT NULL, --  ���i�R�[�h
allocatable_quantity NUMERIC(4) NOT NULL, --  �����\��
initial_inv_quantity NUMERIC(4) NOT NULL, --  �����݌ɐ�
sold_quantity NUMERIC(4) NOT NULL --  �̔��ϐ�
,
PRIMARY KEY (prd_id)
)
;
create unique index idx_prd_sales_info_01 on t_prd_sales_info (prd_cd);
-- �N�G�X�g�e�[�u��
Create table t_quest (
quest_cd VARCHAR(16) NOT NULL, --  �N�G�X�g�R�[�h
quest_nm VARCHAR(128) NOT NULL, --  �N�G�X�g��
description VARCHAR(4000) --  ����
,
PRIMARY KEY (quest_cd)
)
;
create view v_odr_header as 
select
 t_odr_header.odr_id
,t_odr_header.accepted_on
,t_odr_header.customer_id
,t_odr_header.odr_status_id
,t_odr_header.payment_method_id
,t_odr_header.payment_method_sub_cd
,t_odr_header.payment_status_id
,t_odr_header.product_total_price
,t_odr_header.discounted_value
,t_odr_header.total_payment
,t_odr_header.deliv_to
,t_odr_header.customer_nm
,t_odr_header.address_1
,t_odr_header.address_2
,t_odr_header.address_3
,t_odr_header.address_4
,t_odr_header.dt_nm
,t_odr_header.dt_address_1
,t_odr_header.dt_address_2
,t_odr_header.dt_address_3
,t_odr_header.dt_address_4
,t_odr_status.odr_status_nm
,t_payment_method.payment_method_nm
from 
 t_odr_header
,t_odr_status
,t_payment_method
 where t_odr_header.odr_status_id = t_odr_status.odr_status_id
 and t_odr_header.payment_method_id = t_payment_method.payment_method_id
;

create view v_prd_rec as 
select
 t_prd_rec.prd_rec_id
,t_prd_rec.prd_id
,t_prd_rec.valid_start_dttm
,t_prd_rec.valid_end_dttm
,t_prd_rec.is_active as r_is_active
,t_prd_rec.is_on_view as r_is_on_view
,t_prd_rec.display_order
,t_prd_rec.description as r_description
,t_prd.prd_cd
,t_prd.prd_nm
,t_prd.prd_nm_kn
,t_prd.prd_nm_for_url
,t_prd.dd_desc
,t_prd.selling_situ_id
,t_prd.is_on_view as p_is_on_view
,t_prd.is_active as p_is_active
,t_prd.list_price
,t_prd.selling_price
,t_prd.purchase_price
,t_prd.display_start_dttm
,t_prd.display_end_dttm
,t_prd.selling_start_dttm
,t_prd.selling_end_dttm
,t_prd.is_on_back_order
,t_prd.leading_category_cd
,t_prd.description as p_description
,t_prd.is_review_target
,t_prd.memo
from 
 t_prd_rec
,t_prd
 where t_prd_rec.prd_id = t_prd.prd_id
;
