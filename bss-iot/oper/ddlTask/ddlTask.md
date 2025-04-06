# ddlTask

## prompt

```
/************************************************************************
환경
************************************************************************/
java 8, spring batch, ppas 9.3

/************************
table data sample
************************/
table_name: oper_ddl_task_info
column:
    - sr_id         (pk): DR-2025-10011
    - schema_type   (pk): NB
    - task_type     (pk): TABLE_CREATE
    - target_name   (pk): snssai
    - column_names      : snssai_id&sst&sd
    - column_types      : varchar(10)&numeric(3)&varchar(10)
    - constraints_types : PK&NOT NULL&NOT NULL
    - sequence_params
    - ddl_script
    - rollback_script
    - status

/************************
ddlTask Job
************************/
- BO
    - DdlTaskBO 사용
        - SrInfoVO
        - List<OperDdlTaskInfoVO>

- param
    - 없음
- reader
    - oper_ddl_task_info 의 status 가 null 인 대상을 조회하여 List<OperDdlTaskInfoVO> 생성
    - DdlTaskBO 는 OperDdlTaskInfoVO 변수를 가지고 있음
    - DdlTaskBO 객체를 생성하고,  OperDdlTaskInfoVO 에 값을 설정하여 processor 로 전달
- processor
    - ddl 문장 생성 (ddlScript, rollbackScript), 그리고 DdlTaskBO 에 setting 한다.
        - task_type 은 TABLE_CREATE, COLUMN_ADD, INDEX_CREATE, SEQUENCE_CREATE 중 한개의 값이 들어감
    - 원상복구 ddl script 생성, rollbackScript 에 setting
- writer
    - ddl_task 의 ddl_script, rollback_script, status update
    - status 는 성공 시 S, 실패 시 F

/************************************************************************
아래 내용에 대해서 소스를 알려줘
************************************************************************/
1. processor 중 ddl_script 를 구현하도록 메소드 생성
2. processor 중 rollback_script 를 구현하도록 메소드 생성
3. comment
    - TABLE_CREATE, COLUMN_ADD 에 대해서는 comment 생성 스크립트도 포함이 되어야 해
    - comment 는 TABLE 및 COLUMN 에 대해 생성해야 해.
4. DdlTaskBO
    - ItemProcessor<DdlTaskBO, DdlTaskBO> 로 선언해줘

```
