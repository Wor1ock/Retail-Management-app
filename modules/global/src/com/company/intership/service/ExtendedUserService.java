package com.company.intership.service;

import com.company.intership.entity.Customer;
import com.company.intership.entity.ExtendedUser;

import java.util.List;

public interface ExtendedUserService {
    String NAME = "intership_ExtendedUserService";

    /**
     * Проверяет, связан ли указанный пользователь с каким-либо покупателем.
     *
     * @param user Расширенный пользователь, для которого выполняется проверка.
     * @return {@code true}, если у пользователя нет связанного покупателя, иначе {@code false}.
     */
    boolean hasCustomerForUser(ExtendedUser user);

    /**
     * Возвращает список всех покупателей, которые связаны с любыми пользователями.
     *
     * @return Список покупателей, связанных с пользователями.
     */
    List<Customer> getCustomers();

    /**
     * Возвращает покупателя, связанного с указанным пользователем.
     *
     * @param user Расширенный пользователь, для которого нужно найти связанного покупателя.
     * @return Объект {@link Customer}, связанный с пользователем, или {@code null}, если покупатель не найден.
     */
    Customer getCustomer(ExtendedUser user);
}