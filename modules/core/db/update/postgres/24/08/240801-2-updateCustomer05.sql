update INTERSHIP_CUSTOMER set ADDRESS_CITY = '' where ADDRESS_CITY is null ;
alter table INTERSHIP_CUSTOMER alter column ADDRESS_CITY drop not null ;
