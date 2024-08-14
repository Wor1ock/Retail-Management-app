package com.company.intership.web.screens.randomproducts;

import com.company.intership.entity.Product;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.DataGrid;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

@UiController("intership_RandomProduct.browse")
@UiDescriptor("random-product-browse.xml")
@LookupComponent("productsTable")
@LoadDataBeforeShow
public class RandomProductBrowse extends StandardLookup<Product> {
    @Inject
    private DataGrid<Product> productsTable;
    @Inject
    private CollectionContainer<Product> productsDc;
    @Inject
    private Messages messages;

    int discount = LocalDate.now().getDayOfMonth();
    @Inject
    private DataManager dataManager;


    @Subscribe
    protected void onInit(InitEvent event) {

        DataGrid.Column columnDiscount = productsTable.addGeneratedColumn("Discount", new DataGrid.ColumnGenerator<Product, Integer>() {
            @Override
            public Integer getValue(DataGrid.ColumnGeneratorEvent<Product> event) {
                return discount;
            }

            @Override
            public Class<Integer> getType() {
                return Integer.class;
            }
        }, 3);

        DataGrid.Column columnNewPrice = productsTable.addGeneratedColumn("New price", new DataGrid.ColumnGenerator<Product, BigDecimal>() {
            @Override
            public BigDecimal getValue(DataGrid.ColumnGeneratorEvent<Product> event) {
                return calculateDiscountedPrice(event.getItem());
            }

            @Override
            public Class<BigDecimal> getType() {
                return BigDecimal.class;
            }
        }, 4);
        columnNewPrice.setCaption(messages.formatMessage(getClass(), "randomProductBrowse.NewPriceCaption"));
        columnDiscount.setCaption(messages.formatMessage(getClass(), "randomProductBrowse.DiscountCaption"));
    }

    private BigDecimal calculateDiscountedPrice(Product product) {
        return product.getManufacturerPrice()
                .multiply(BigDecimal.valueOf(100 - discount))
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
    }

    private List<Product> getRandomElements(List<Product> products) {
        Random random = new Random();
        Set<Product> randomElements = new HashSet<>();
        int randomCount = random.nextInt(products.size()) + 1;

        while (randomElements.size() < randomCount) {
            randomElements.add(products.get(random.nextInt(products.size())));
        }
        return new ArrayList<>(randomElements);
    }

    @Subscribe("productsTable.createAct")
    public void onProductsTableCreateAct(Action.ActionPerformedEvent event) {
        List<Product> allProducts = dataManager.load(Product.class)
                .view("random-product-view")
                .list();
        productsDc.setItems(getRandomElements(allProducts));
    }

}