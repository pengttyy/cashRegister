package com.thoughtworks.cashRegister.obj;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Shoppinglist {
	private Money total;
	private List<CommodityItem> commodityItems;

	public Shoppinglist() {
		this.commodityItems = new ArrayList<>();
		this.total = new Money(new BigDecimal("0.00"));
	}

	public Money getTotal() {
		return this.total;
	}

	public void append(CommodityItem itemPrice) {
		this.commodityItems.add(itemPrice);
		BigDecimal sum = this.total.getNum().add(itemPrice.getSubtotal().getNum());
		this.total = new Money(sum);
	}

	public List<CommodityItem> getCommodityItems() {
		return commodityItems;
	}
}
