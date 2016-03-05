package com.thoughtworks.cashRegister.rule;

import java.math.BigDecimal;

import com.thoughtworks.cashRegister.obj.Commodity;
import com.thoughtworks.cashRegister.obj.CommodityItem;

public class BuyTwoGiveA implements IpreferentialRule {
	public CommodityItem execute(BigDecimal num, Commodity commodity) {
		BigDecimal[] divideAndRemainder = num.divideAndRemainder(new BigDecimal("3"));
		BigDecimal countNum = divideAndRemainder[0].multiply(new BigDecimal("2")).add(divideAndRemainder[1]);
		BigDecimal subtotal = (commodity.getPrice()).multiply(countNum);

		BigDecimal thrift = num.multiply(commodity.getPrice()).subtract(subtotal);

		CommodityItem commodityItem = new CommodityItem();
		commodityItem.setCommodity(commodity);
		commodityItem.setSubtotal(subtotal);
		commodityItem.setThriftMoney(thrift);
		commodityItem.setThriftNum(divideAndRemainder[0]);
		commodityItem.setNameOfPreferential(this.getRuleName());
		return commodityItem;
	}

	@Override
	public String getRuleName() {
		return "买二赠一";
	}
}
