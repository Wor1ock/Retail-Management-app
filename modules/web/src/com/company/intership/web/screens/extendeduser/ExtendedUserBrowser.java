package com.company.intership.web.screens.extendeduser;

import com.company.intership.entity.Customer;
import com.company.intership.entity.ExtendedUser;
import com.company.intership.service.ExtendedUserService;
import com.haulmont.cuba.gui.app.security.user.browse.UserBrowser;
import com.haulmont.cuba.gui.screen.Subscribe;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ExtendedUserBrowser extends UserBrowser {
    @Inject
    private ExtendedUserService extendedUserService;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        List<Customer> customers = extendedUserService.getCustomers();

        Map<UUID, ExtendedUser> userMap = usersDs.getItems().stream()
                .filter(user -> user instanceof ExtendedUser)
                .map(user -> (ExtendedUser) user)
                .collect(Collectors.toMap(ExtendedUser::getId, user -> user));

        for (Customer customer : customers) {
            ExtendedUser user = customer.getExtendedUser();
            if (user != null) {
                ExtendedUser existingUser = userMap.get(user.getId());
                if (existingUser != null) {
                    existingUser.setCustomer(customer);
                }
            }
        }
    }
}