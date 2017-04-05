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
