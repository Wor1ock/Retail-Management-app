alter table SEC_USER rename column customer_id to customer_id__u88968 ;
alter table SEC_USER drop constraint FK_SEC_USER_ON_CUSTOMER ;
drop index IDX_SEC_USER_ON_CUSTOMER ;
