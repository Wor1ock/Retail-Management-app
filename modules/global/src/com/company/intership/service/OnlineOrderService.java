package com.company.intership.service;

import com.company.intership.entity.OnlineOrder;
import com.company.intership.entity.OrderStatus;

public interface OnlineOrderService {
    String NAME = "intership_OnlineOrderService";

    /**
     * Обновляет количество продуктов в магазине на основе информации о заказе.
     * <p>
     * Этот метод уменьшает количество доступных продуктов в магазине на количество,
     * указанное в заказе. Если количество заказанных продуктов превышает доступное,
     * количество продукта в магазине устанавливается в 0.
     *
     * @param onlineOrder заказ, на основании которого будет произведено обновление количеств продуктов
     */
    void updateProductQuantities(OnlineOrder onlineOrder);

    /**
     * Устанавливает статус для заказа.
     * <p>
     * Если новый статус заказа - {@link OrderStatus#CANCELED}, и текущий статус требует
     * возврата товаров на склад, метод автоматически возвращает товары в магазин.
     *
     * @param onlineOrder заказ, для которого необходимо установить новый статус
     * @param statusString      новый статус заказа
     */
    void setStatus(OnlineOrder onlineOrder, String statusString);

    void setStatusCanceled(OnlineOrder onlineOrder);
}