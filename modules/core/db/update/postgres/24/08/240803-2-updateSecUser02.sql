alter table SEC_USER add constraint FK_SEC_USER_ON_CUSTOMER foreign key (CUSTOMER_ID) references INTERSHIP_CUSTOMER(ID);
create index IDX_SEC_USER_ON_CUSTOMER on SEC_USER (CUSTOMER_ID);
