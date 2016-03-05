package com.thoughtworks.cashRegister.rule;

import java.math.BigDecimal;

import com.thoughtworks.cashRegister.obj.Commodity;
import com.thoughtworks.cashRegister.obj.CommodityItem;

public interface IpreferentialRule {
	CommodityItem execute(BigDecimal num, Commodity commodity);

	abstract String getRuleName();
}