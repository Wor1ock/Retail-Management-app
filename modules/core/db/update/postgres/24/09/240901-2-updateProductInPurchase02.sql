alter table INTERSHIP_PRODUCT_IN_PURCHASE add constraint FK_INTERSHIP_PRODUCT_IN_PURCHASE_ON_PURCHASE foreign key (PURCHASE_ID) references INTERSHIP_PURCHASE(ID);
