package com.company.intership.web.screens.tradenetwork;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.TradeNetwork;

@UiController("intership_TradeNetwork.browse")
@UiDescriptor("trade-network-browse.xml")
@LookupComponent("tradeNetworksTable")
@LoadDataBeforeShow
public class TradeNetworkBrowse extends StandardLookup<TradeNetwork> {
}