package com.thoughtworks.cashRegister.rule;

import java.math.BigDecimal;

import com.thoughtworks.cashRegister.obj.Commodity;
import com.thoughtworks.cashRegister.obj.CommodityItem;
import com.thoughtworks.cashRegister.obj.Money;

public class Discount implements IpreferentialRule {

	public CommodityItem execute(BigDecimal num, Commodity commodity) {
		BigDecimal subtotal = (commodity.getPrice().getNum()).multiply(num).multiply(new BigDecimal("0.95"));
		CommodityItem commodityItem = new CommodityItem();
		commodityItem.setCommodity(commodity);
		commodityItem.setSubtotal(new Money(subtotal));

		BigDecimal thriftMoney = num.multiply(commodity.getPrice().getNum()).subtract(subtotal);
		commodityItem.setThriftMoney(new Money(thriftMoney));
		commodityItem.setThriftNum(0);
		commodityItem.setNameOfPreferential(this.getRuleName());
		commodityItem.setNum(num.doubleValue());
		commodityItem.setDisplayThrift(false);
		return commodityItem;
	}

	public String getRuleName() {
		return "95折";
	}

}