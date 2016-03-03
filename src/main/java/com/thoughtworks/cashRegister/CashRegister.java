package com.thoughtworks.cashRegister;

import java.math.BigDecimal;
import java.util.List;

import com.thoughtworks.cashRegister.data.ICommodityService;
import com.thoughtworks.cashRegister.obj.Commodity;
import com.thoughtworks.cashRegister.obj.Shoppinglist;

public class CashRegister {
	private List<String> barcodes;
	private ICommodityService commodityService;

	public CashRegister(ICommodityService commodityService) {
		this.commodityService = commodityService;
	}

	public void setBarcode(List<String> barcodes) {
		this.barcodes = barcodes;
	}

	public Shoppinglist calculate() {
		BigDecimal total = new BigDecimal(0);
		for (String barcode : barcodes) {
			String[] barCodeInfo = barcode.split("-");
			total = total.add(itemPrice(barCodeInfo[0], getNum(barCodeInfo)));
		}
		Shoppinglist shoppinglist = new Shoppinglist();
		shoppinglist.setTotal(total);
		return shoppinglist;
	}

	private BigDecimal getNum(String[] barCodeInfo) {
		if (barCodeInfo.length != 2) {
			return new BigDecimal(1);
		}
		return new BigDecimal(barCodeInfo[1]);
	}

	private BigDecimal itemPrice(String barcode, BigDecimal num) {
		Commodity commodity = commodityService.find(barcode);
		return (commodity.getPrice()).multiply(num);
	}

}
