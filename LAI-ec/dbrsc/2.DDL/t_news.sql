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
