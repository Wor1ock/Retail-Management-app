package com.company.intership.service;

import com.company.intership.entity.ExtendedUser;

public interface ExtendedUserService {
    String NAME = "intership_ExtendedUserService";

    boolean hasCustomerForUser(ExtendedUser user);
}