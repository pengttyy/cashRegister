package com.thoughtworks.cashRegister.obj;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Shoppinglist {
	private BigDecimal total;
	private List<CommodityItem> commodityItems;

	public Shoppinglist() {
		this.commodityItems = new ArrayList<>();
		this.total = new BigDecimal("0.00");
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void append(CommodityItem itemPrice) {
		this.commodityItems.add(itemPrice);
		this.total = this.total.add(itemPrice.getSubtotal());
	}

	public List<CommodityItem> getCommodityItems() {
		return commodityItems;
	}

}
