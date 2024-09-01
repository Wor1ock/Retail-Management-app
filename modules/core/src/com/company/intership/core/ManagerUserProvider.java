package com.company.intership.core;

import com.haulmont.addon.bproc.provider.UserProvider;
import com.haulmont.addon.bproc.service.BprocRuntimeService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.security.entity.User;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component(ManagerUserProvider.NAME)
public class ManagerUserProvider implements UserProvider {
    public static final String NAME = "intership_ManagerUserProvider";
    private static final String MANAGER_ROLE_NAME = "OnlineStoreEmployee";
    @Inject
    private BprocRuntimeService bprocRuntimeService;
    @Inject
    private Logger log;
    @Inject
    private DataManager dataManager;

    @Override
    public User get(String executionId) {
        User customer = (User) bprocRuntimeService.getVariable(executionId, "customer");
        if(customer!= null) {
            log.info("We found the customer:{}, but we won't use the value", customer.getLogin());
        }
        List<User> managers = dataManager.load(User.class)
                .query("SELECT u FROM sec$User u JOIN u.userRoles ur WHERE ur.roleName=:roleName")
                .parameter("roleName", MANAGER_ROLE_NAME)
                .list();
        return !managers.isEmpty() ? managers.get(0) : null;
    }
}