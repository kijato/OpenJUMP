alter session set NLS_NUMERIC_CHARACTERS = '.,';
alter session set nls_date_format='YYYY-MM-DD';
set echo off;
set linesize 10000;
set pagesize 50000;
set heading on;
set feedback on;
set headsep ';';
set colsep ';';
set verify off;
--set null -;
--SHOW MARKUP;
--SET MARKUP HTML ON;
--SET SHOWMODE ON;
--SET TRIMSPOOL ON;

WITH f AS (	
SELECT f.terkep_id, felulet_id, subfel_id, hsub_id
       /*, f.hatar_id, hatar_valt, h.hatarvonal_id, hvsub_id irany_valt, pont_id_1, pont_id_2
       , CASE WHEN irany_valt = '+' THEN pont_id_1 WHEN irany_valt = '-' THEN pont_id_2 ELSE NULL END pont_id*/
       , CASE WHEN irany_valt = '+' THEN ( SELECT pont_y||' '||pont_x FROM dt_pont WHERE terkep_id = hv.terkep_id AND pont_id = hv.pont_id_1 )
              WHEN irany_valt = '-' THEN ( SELECT pont_y||' '||pont_x FROM dt_pont WHERE terkep_id = hv.terkep_id AND pont_id = hv.pont_id_2)
              ELSE NULL
         END yx
FROM DT_FELULET f
     JOIN DT_HATAR h ON h.TERKEP_ID = f.TERKEP_ID AND h.HATAR_ID = f.HATAR_ID
     JOIN DT_HATARVONAL hv ON hv.TERKEP_ID = h.TERKEP_ID AND hv.HATARVONAL_ID = h.hatarvonal_id
WHERE 1 = 1
  AND f.TERKEP_ID = 101
  AND felulet_id IN (4973, 5582, 4975, 6433)
ORDER BY felulet_id, subfel_id, f.hatar_id, hsub_id, h.hatarvonal_id, hvsub_id
)
SELECT *
FROM f;




/*
     ( SELECT terkep_id, objektum_id, obj_fels, felulet_id, min_y, min_x, max_y, max_x
       FROM dt_obj_attrbc
       WHERE verzio_jogeros IS NOT NULL AND verzio_ki IS NULL
       UNION
       SELECT terkep_id, objektum_id, obj_fels, felulet_id, min_y, min_x, max_y, max_x
       FROM dt_obj_attrbd
       WHERE verzio_jogeros IS NOT NULL AND verzio_ki IS NULL
     ) o,
*/

exit;