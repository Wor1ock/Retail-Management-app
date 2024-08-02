alter table intership_IndividualCustomer add constraint FK_INTERSHIP_INDIVIDUALCUSTOMER_ON_ID foreign key (ID) references INTERSHIP_CUSTOMER(ID) on delete CASCADE;
