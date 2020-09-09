*&---------------------------------------------------------------------*
*& Report ZMAKAT_PROG2
*&---------------------------------------------------------------------*
*&
*&---------------------------------------------------------------------*
REPORT zmakat_prog2.

PARAMETERS mkt TYPE zdt_makatid_sa.

"define table data types
DATA: lt_ordhd   TYPE TABLE OF ztb_orderhead_sa,
      lt_ordln   TYPE TABLE OF ztb_ordlines_sa,
      lt_pprchhd TYPE TABLE OF ztb_purchreq_sa,
      lt_purchln TYPE TABLE OF ztb_purchrows_sa,
      lt_makat   TYPE TABLE OF ztb_makat_sa.



"define workareas
DATA: wa_ordhd   TYPE ztb_orderhead_sa,
      wa_ordln   TYPE ztb_ordlines_sa,
      wa_makat   TYPE ztb_makat_sa,
      wa_pprchhd TYPE ztb_purchreq_sa,
      wa_purchln TYPE ztb_purchrows_sa,
      sl_mkt     TYPE ztb_makat_sa-makatid.



TABLES: ztb_makat_sa.

SELECT-OPTIONS: s_makat FOR sl_mkt.

"SELECT * FROM ztb_makat_sa INTO CORRESPONDING FIELDS OF TABLE lt_makat WHERE makatid IN s_makat.

"list purchase request headers and lines
WRITE: 'REQUESTS:' COLOR 5.
NEW-LINE.
SELECT * FROM ztb_purchreq_sa INTO CORRESPONDING FIELDS OF TABLE lt_pprchhd.
LOOP AT lt_pprchhd INTO wa_pprchhd.
  NEW-LINE.
  WRITE: 'Request ID',
         ' Date'.
  NEW-LINE.
  WRITE:/ wa_pprchhd-requestid,
         wa_pprchhd-purchdate.
  NEW-LINE.
  SELECT * FROM ztb_purchrows_sa INTO CORRESPONDING FIELDS OF TABLE lt_purchln WHERE ztb_purchrows_sa~requestid EQ wa_pprchhd-requestid AND ztb_purchrows_sa~makatid IN s_makat.
  IF lt_purchln IS INITIAL.
    WRITE: 'אין דרישות' COLOR 6.
  ELSE.
    WRITE: 'Amount',
         ' Req ID',
         'Row No'.
    NEW-LINE.
    LOOP AT lt_purchln INTO wa_purchln.
      WRITE:  wa_purchln-prch_amount,
              wa_purchln-requestid,
              wa_purchln-row_num.

    ENDLOOP.
  ENDIF.
ENDLOOP.

"list orders headers and lines
NEW-LINE.
WRITE: 'ORDERS' COLOR 5.
NEW-LINE.
SELECT * FROM ztb_orderhead_sa INTO CORRESPONDING FIELDS OF TABLE lt_ordhd.
LOOP AT lt_ordhd INTO wa_ordhd.
  NEW-LINE.
  FORMAT INTENSIFIED ON.
  WRITE: 'Order ID',
         ' Date'.
  NEW-LINE.

  WRITE:/ wa_ordhd-order_id,
         wa_ordhd-purch_date.
  NEW-LINE.

  SELECT * FROM ztb_ordlines_sa INTO CORRESPONDING FIELDS OF TABLE lt_ordln WHERE ztb_ordlines_sa~orderid EQ wa_ordhd-order_id AND ztb_ordlines_sa~makatid IN s_makat.
  IF lt_ordln IS INITIAL.
    WRITE: 'אין הזמנות' COLOR 6.
  ELSE.
    LOOP AT lt_ordln INTO wa_ordln.
      NEW-LINE.
      WRITE:
             ' Amount',
             ' Order',
             ' Row'.
      NEW-LINE.

      WRITE:
            wa_ordln-amount,
            wa_ordln-orderid,
            wa_ordln-row_id.
    ENDLOOP.
  ENDIF.
ENDLOOP.


*LOOP AT lt_makat INTO wa_makat
*  WHERE makatid IN s_makat.
*  NEW-LINE.
*  WRITE: 'Purchase request head:'.
*  SELECT * FROM ztb_purchreq_sa INTO CORRESPONDING FIELDS OF lt_pprchhd
*
*ENDLOOP.


*
*
*LOOP AT lt_ordhd INTO wa_ordhd.
*  NEW-LINE.
*  WRITE: '----------------------------'.
*  NEW-LINE.
*  WRITE: 'Order:'.
*  NEW-LINE.
*  WRITE: wa_ordhd-emp_id,
*        wa_ordhd-order_id,
*        wa_ordhd-purch_date,
*        wa_ordhd-supplier_id.
*  NEW-LINE.
*  WRITE: 'Details:'.
*  NEW-LINE.
*  SELECT * FROM ztb_ordlines_sa INTO CORRESPONDING FIELDS OF TABLE lt_ordln
*     WHERE ztb_ordlines_sa~orderid = wa_ordhd-order_id
*     AND ztb_ordlines_sa~makatid EQ mkt.
*  LOOP AT lt_ordln INTO wa_ordln.
*    NEW-LINE.
*    WRITE: wa_ordln-amount,
*           wa_ordln-makatid,
*           wa_ordln-orderid,
*           wa_ordln-row_id.
*  ENDLOOP.
*ENDLOOP.