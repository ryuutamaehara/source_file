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
