package com.company.intership.service;

import com.company.intership.entity.Customer;
import com.company.intership.entity.ExtendedUser;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(ExtendedUserService.NAME)
public class ExtendedUserServiceBean implements ExtendedUserService {
    @Inject
    private DataManager dataManager;

    @Override
    public boolean hasCustomerForUser(ExtendedUser user) {
        return dataManager.load(Customer.class)
                .query("SELECT c FROM intership_Customer c where c.user = :user")
                .parameter("user", user)
                .list().isEmpty();
    }
}