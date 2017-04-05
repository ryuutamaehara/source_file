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
