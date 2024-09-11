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
);