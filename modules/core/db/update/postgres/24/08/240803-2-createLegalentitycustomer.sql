alter table intership_LegalEntityCustomer add constraint FK_INTERSHIP_LEGALENTITYCUSTOMER_ON_ID foreign key (ID) references INTERSHIP_CUSTOMER(ID) on delete CASCADE;
