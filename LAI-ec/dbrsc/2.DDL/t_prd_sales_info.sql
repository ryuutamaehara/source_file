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
