-- �⍇�킹�e�[�u��
Create table t_inquiry (
inq_id NUMERIC NOT NULL, --  �⍇��ID
customer_id NUMERIC NOT NULL, --  �ڋqID
accepted_on TIMESTAMP NOT NULL default 0, --  �⍇����t���� -- default 0�w���t���Ȃ��ƁAON UPDATE�w�肪����ɕt�������
inq_status_id NUMERIC(2) NOT NULL, --  �⍇���X�e�[�^�XID
inq_subject VARCHAR(256) NOT NULL, --  �⍇���^�C�g��
inq_body VARCHAR(4000) NOT NULL --  �⍇���{��
,
PRIMARY KEY (inq_id)
)
;
