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
