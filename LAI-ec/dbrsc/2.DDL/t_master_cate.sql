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
