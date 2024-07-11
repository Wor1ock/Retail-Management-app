package com.company.intership.web.screens.tradenetwork;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.TradeNetwork;

@UiController("intership_TradeNetwork.edit")
@UiDescriptor("trade-network-edit.xml")
@EditedEntityContainer("tradeNetworkDc")
@LoadDataBeforeShow
public class TradeNetworkEdit extends StandardEditor<TradeNetwork> {
}