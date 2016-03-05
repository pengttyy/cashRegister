package com.thoughtworks.cashRegister.rule;

import java.math.BigDecimal;

import com.thoughtworks.cashRegister.obj.Commodity;
import com.thoughtworks.cashRegister.obj.CommodityItem;

public class Normalization implements IpreferentialRule {

	@Override
	public CommodityItem execute(BigDecimal num, Commodity commodity) {
		BigDecimal subtotal = (commodity.getPrice()).multiply(num);

		CommodityItem commodityItem = new CommodityItem();
		commodityItem.setCommodity(commodity);
		commodityItem.setSubtotal(subtotal);

		commodityItem.setThriftMoney(new BigDecimal("0.00"));
		commodityItem.setThriftNum(new BigDecimal("0"));
		commodityItem.setNameOfPreferential(this.getRuleName());
		return commodityItem;
	}

	@Override
	public String getRuleName() {
		return "正常计算";
	}

}
