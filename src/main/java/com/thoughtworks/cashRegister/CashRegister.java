package com.thoughtworks.cashRegister;

import java.math.BigDecimal;
import java.util.List;

import com.thoughtworks.cashRegister.data.ICommodityService;
import com.thoughtworks.cashRegister.data.IRuleService;
import com.thoughtworks.cashRegister.obj.Commodity;
import com.thoughtworks.cashRegister.obj.CommodityItem;
import com.thoughtworks.cashRegister.obj.Shoppinglist;
import com.thoughtworks.cashRegister.rule.BuyTwoGiveA;
import com.thoughtworks.cashRegister.rule.Discount;
import com.thoughtworks.cashRegister.rule.Normalization;
import com.thoughtworks.cashRegister.rule.RuleEnum;

public class CashRegister {
	private List<String> barcodes;
	private ICommodityService commodityService;
	private IRuleService ruleService;

	public CashRegister(ICommodityService commodityService, IRuleService ruleService) {
		this.commodityService = commodityService;
		this.ruleService = ruleService;
	}

	public void setBarcode(List<String> barcodes) {
		this.barcodes = barcodes;
	}

	public Shoppinglist calculate() {
		Shoppinglist shoppinglist = new Shoppinglist();
		for (String barcode : barcodes) {
			String[] barCodeInfo = barcode.split("-");
			shoppinglist.append(itemPrice(barCodeInfo));
		}
		return shoppinglist;
	}

	private BigDecimal getNum(String[] barCodeInfo) {
		if (barCodeInfo.length != 2) {
			return new BigDecimal(1);
		}
		return new BigDecimal(barCodeInfo[1]);
	}

	private CommodityItem itemPrice(String[] barCodeInfo) {
		String barcode = barCodeInfo[0];
		Commodity commodity = commodityService.find(barcode);

		RuleEnum ruleByBarCode = RuleEnum.findRuleByBarCode(barcode, ruleService);

		BigDecimal num = getNum(barCodeInfo);
		
		return ruleByBarCode.getRule().execute(num, commodity);
		

	}
}
