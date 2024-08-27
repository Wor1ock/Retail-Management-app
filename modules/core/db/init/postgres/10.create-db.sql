-- begin INTERSHIP_STORE
create table INTERSHIP_STORE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ADDRESS_CITY varchar(255),
    ADDRESS_STREET varchar(255),
    ADDRESS_BUILDING varchar(255),
    --
    NUMBER_ varchar(255) not null,
    STORE_TYPE varchar(50),
    NAME varchar(255) not null,
    TRADE_NETWORK_ID uuid not null,
    --
    primary key (ID)
)^
-- end INTERSHIP_STORE
-- begin INTERSHIP_TRADE_NETWORK
create table INTERSHIP_TRADE_NETWORK (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    FULL_NAME varchar(255),
    --
    primary key (ID)
)^
-- end INTERSHIP_TRADE_NETWORK
-- begin INTERSHIP_PRICE_HISTORY
create table INTERSHIP_PRICE_HISTORY (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PRODUCT_ID uuid not null,
    STORE_ID uuid not null,
    DATE_ date not null,
    OLD_PRICE decimal(19, 2),
    NEW_PRICE decimal(19, 2) not null,
    --
    primary key (ID)
)^
-- end INTERSHIP_PRICE_HISTORY
-- begin INTERSHIP_PRODUCT_MANUFACTURER
create table INTERSHIP_PRODUCT_MANUFACTURER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ADDRESS_CITY varchar(255),
    ADDRESS_STREET varchar(255),
    ADDRESS_BUILDING varchar(255),
    --
    NAME varchar(255) not null,
    FULL_NAME varchar(255),
    USER_ID uuid,
    --
    primary key (ID)
)^
-- end INTERSHIP_PRODUCT_MANUFACTURER
-- begin INTERSHIP_PRODUCT
create table INTERSHIP_PRODUCT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    MANUFACTURER_ID uuid not null,
    MANUFACTURER_PRICE decimal(19, 2) not null,
    --
    primary key (ID)
)^
-- end INTERSHIP_PRODUCT
-- begin INTERSHIP_PRODUCT_IN_PURCHASE
create table INTERSHIP_PRODUCT_IN_PURCHASE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PURCHASE_ID uuid not null,
    PRODUCT_ID uuid not null,
    PRICE decimal(19, 2) not null,
    QUANTITY integer not null,
    --
    primary key (ID)
)^
-- end INTERSHIP_PRODUCT_IN_PURCHASE
-- begin INTERSHIP_PRODUCT_IN_STORE
create table INTERSHIP_PRODUCT_IN_STORE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PRODUCT_ID uuid not null,
    STORE_ID uuid not null,
    PRICE decimal(19, 2) not null,
    QUANTITY integer not null,
    --
    primary key (ID)
)^
-- end INTERSHIP_PRODUCT_IN_STORE
-- begin INTERSHIP_CUSTOMER
create table INTERSHIP_CUSTOMER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    DTYPE varchar(31),
    --
    ADDRESS_CITY varchar(255),
    ADDRESS_STREET varchar(255),
    ADDRESS_BUILDING varchar(255),
    --
    FULL_NAME varchar(255),
    EMAIL varchar(255),
    USER_ID uuid,
    --
    primary key (ID)
)^
-- end INTERSHIP_CUSTOMER
-- begin INTERSHIP_PURCHASE
create table INTERSHIP_PURCHASE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    DTYPE varchar(31),
    --
    STORE_ID uuid not null,
    --
    -- from intership_OnlineOrder
    CUSTOMER_ID uuid,
    STATUS varchar(50),
    ORDER_NUMBER varchar(255),
    ORDER_TOTAL decimal(19, 2),
    DISCOUNT integer,
    --
    primary key (ID)
)^
-- end INTERSHIP_PURCHASE
-- begin INTERSHIP_INDIVIDUALCUSTOMER
create table intership_IndividualCustomer (
    ID uuid,
    --
    FIRST_NAME varchar(255),
    LAST_NAME varchar(255),
    MIDDLE_NAME varchar(255),
    --
    primary key (ID)
)^
-- end INTERSHIP_INDIVIDUALCUSTOMER
-- begin INTERSHIP_LEGALENTITYCUSTOMER
create table intership_LegalEntityCustomer (
    ID uuid,
    --
    COMPANY_NAME varchar(255),
    --
    primary key (ID)
)^
-- end INTERSHIP_LEGALENTITYCUSTOMER
-- begin SEC_USER
alter table SEC_USER add column DTYPE varchar(31) ^
update SEC_USER set DTYPE = 'intership_ExtendedUser' where DTYPE is null ^
-- end SEC_USER
alter table SEC_USER add column CUSTOMER_ID uuid ^
update SEC_USER set DTYPE = 'intership_ExtendedUser' where DTYPE is null ^
