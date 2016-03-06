package com.thoughtworks.cashRegister.rule;

import java.math.BigDecimal;

import com.thoughtworks.cashRegister.obj.Commodity;
import com.thoughtworks.cashRegister.obj.CommodityItem;
import com.thoughtworks.cashRegister.obj.Money;

public class Normalization implements IpreferentialRule {

	@Override
	public CommodityItem execute(BigDecimal num, Commodity commodity) {
		BigDecimal subtotal = (commodity.getPrice().getNum()).multiply(num);
		CommodityItem commodityItem = new CommodityItem();
		commodityItem.setCommodity(commodity);
		commodityItem.setSubtotal(new Money(subtotal));

		commodityItem.setThriftMoney(new Money(new BigDecimal("0.00")));
		commodityItem.setThriftNum(0);
		commodityItem.setNameOfPreferential(this.getRuleName());
		commodityItem.setNum(num.doubleValue());
		commodityItem.setDisplayThrift(false);
		return commodityItem;
	}

	@Override
	public String getRuleName() {
		return "正常计算";
	}

}
