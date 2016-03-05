package com.thoughtworks.cashRegister.rule;

import java.math.BigDecimal;

import com.thoughtworks.cashRegister.obj.Commodity;
import com.thoughtworks.cashRegister.obj.CommodityItem;

public class Discount implements IpreferentialRule {

	public CommodityItem execute(BigDecimal num, Commodity commodity) {
		BigDecimal subtotal = (commodity.getPrice()).multiply(num).multiply(new BigDecimal("0.95"));
		CommodityItem commodityItem = new CommodityItem();
		commodityItem.setCommodity(commodity);
		commodityItem.setSubtotal(subtotal);

		BigDecimal thriftMoney = num.multiply(commodity.getPrice()).subtract(subtotal);
		commodityItem.setThriftMoney(thriftMoney);
		commodityItem.setThriftNum(new BigDecimal("0"));
		commodityItem.setNameOfPreferential(this.getRuleName());
		return commodityItem;
	}

	public String getRuleName() {
		return "95折";
	}

}