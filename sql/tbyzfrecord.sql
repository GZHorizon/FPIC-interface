-- Create table
create table TB_YZF_RECORD
(
  ORDERID     VARCHAR2(50) not null,
  ORDERSEQ    VARCHAR2(50),
  TRANSEQ     VARCHAR2(50),
  MERCHANTID  VARCHAR2(50),
  PHONENUMBER VARCHAR2(20),
  ICCID       VARCHAR2(32),
  NAME        VARCHAR2(50),
  AMOUNT      VARCHAR2(32),
  ORDERDATE   VARCHAR2(50),
  CLIENTID    VARCHAR2(50),
  VERSIONID   VARCHAR2(50),
  CREATEDATE  DATE,
  JOINDESC    VARCHAR2(50),
  FLAG        NUMBER,
  DEALTIME    VARCHAR2(50),
  RESULT      VARCHAR2(20)
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table TB_YZF_RECORD
  add primary key (ORDERID);
  
  
  alter table fin_subsidy_info add source_id varchar2(50);