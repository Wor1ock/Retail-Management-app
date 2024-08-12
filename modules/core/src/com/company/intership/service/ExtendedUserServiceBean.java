package com.company.intership.service;

import com.company.intership.entity.Customer;
import com.company.intership.entity.ExtendedUser;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(ExtendedUserService.NAME)
public class ExtendedUserServiceBean implements ExtendedUserService {
    @Inject
    private DataManager dataManager;

    @Override
    public boolean hasCustomerForUser(ExtendedUser user) {
        return dataManager.load(Customer.class)
                .query("SELECT c FROM intership_Customer c where c.extendedUser.id = :userId")
                .parameter("userId", user.getId())
                .list().isEmpty();
    }

    @Override
    public List<Customer> getCustomers() {
        return dataManager.load(Customer.class)
                .query("SELECT c FROM intership_Customer c")
                .view("customer-view")
                .list();
    }

    @Override
    public Customer getCustomer(ExtendedUser user) {
        return dataManager.load(Customer.class)
                .query("SELECT c FROM intership_Customer c WHERE c.extendedUser.id = :userId")
                .parameter("userId", user.getId())
                .view("customer-view")
                .optional()
                .orElse(null);
    }
}