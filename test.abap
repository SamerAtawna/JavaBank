*&---------------------------------------------------------------------*
*& Report ZSELECT_TEST4_EG
*&---------------------------------------------------------------------*
*&
*&---------------------------------------------------------------------*
REPORT zselect_test4_eg.

PARAMETERS: p_mkt TYPE zmkt_eg-code.

DATA: lt_mkt TYPE STANDARD TABLE OF zmkt_eg.

SELECT *
   FROM zmkt_eg
   INTO TABLE lt_mkt
  WHERE code EQ p_mkt.

TYPES: BEGIN OF tt_require,
         code                 TYPE zrequirements_eg-code,
         date_require         TYPE zrequirements_eg-date_require,
         code_require         TYPE zrequire_line_eg-code,
         code_line            TYPE zrequire_line_eg-code_line,
         amount_require_lines TYPE zrequire_line_eg-amount_require_lines,
       END OF tt_require.

TYPES: BEGIN OF tt_order,
         code_order      TYPE zpoh_eg-code,
         date_order      TYPE zpoh_eg-date_order,
         order_code      TYPE zpol_eg-code,
         code_line_order TYPE zpol_eg-code_line_order,
         amount          TYPE zpol_eg-amount,
       END OF tt_order.

DATA:lt_ord TYPE STANDARD TABLE OF  tt_order,
     ls_ord TYPE tt_order.
DATA: lt_req TYPE STANDARD TABLE OF tt_require,
      ls_req TYPE tt_require.

SELECT zrequirements_eg~code  ,zrequirements_eg~date_require,zrequire_line_eg~code AS code_require  ,zrequire_line_eg~code_line ,zrequire_line_eg~amount_require_lines
  FROM zrequirements_eg JOIN zrequire_line_eg ON zrequirements_eg~code EQ zrequire_line_eg~code
  INTO CORRESPONDING FIELDS OF TABLE @lt_req
  WHERE zrequire_line_eg~mkt eq @p_mkt.



SELECT zpoh_eg~code  AS code_order ,zpoh_eg~date_order,zpol_eg~code AS order_code ,zpol_eg~code_line_order, zpol_eg~amount FROM zpoh_eg JOIN zpol_eg ON zpoh_eg~code EQ zpol_eg~code
INTO CORRESPONDING FIELDS OF TABLE @lt_ord
  WHERE zpol_eg~code_mkt eq @p_mkt.

WRITE:'Requires:'.
LOOP AT  lt_req INTO ls_req.
  WRITE:/'Code Require:',ls_req-code,
         'Date Require:',ls_req-date_require.


  AT NEW code.
    WRITE:/'Code Require:',ls_req-code_require,
    'Code Line:',ls_req-code_line,
    'Amount:',ls_req-amount_require_lines.
  ENDAT.

ENDLOOP.

WRITE:/'Orders:'.

LOOP AT lt_ord INTO ls_ord.
  WRITE:/'Code Order:',ls_ord-code_order,
  'Date Order:',ls_ord-date_order.

  AT NEW code_order.
    WRITE:/'Code Order:',ls_ord-order_code,
    'Code Line Order:',ls_ord-code_line_order,
    'Amount:',ls_ord-amount.
  ENDAT.

ENDLOOP.




  BREAK-POINT.