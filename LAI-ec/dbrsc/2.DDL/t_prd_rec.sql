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
