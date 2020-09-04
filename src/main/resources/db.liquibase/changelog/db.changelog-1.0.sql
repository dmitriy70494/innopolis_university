--liquibase formatted sql

--changeset balandin:20200705-create-table-test1 context:prod
create  table  test1  (
    id  int  primary  key,
    name  varchar(255)
);
--rollback drop table test1;

--changeset balandin:20200705-insert-table-test1 context:test
insert  into  test1  (id,  name)  values  (1,  'name  1');
insert  into  test1  (id,  name)  values  (2,  'name  2');
