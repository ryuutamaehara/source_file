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
