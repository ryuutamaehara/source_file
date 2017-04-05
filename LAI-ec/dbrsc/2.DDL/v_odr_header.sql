CREATE VIEW v_odr_header AS 
SELECT
 H.odr_id
,H.accepted_on
,H.customer_id
,H.odr_status_id
,S.odr_status_nm
FROM 
 t_odr_header H
,t_odr_status S
 WHERE H.odr_status_id = S.odr_status_id
;
