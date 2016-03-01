package com.thoughtworks.cashRegister;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.cashRegister.obj.Shoppinglist;

public class CashRegister {
	private List<String> barcodes;

	// private List<Commodity> list;
	private Map<String, BigDecimal> commoditys = new HashMap<>();

	{
		commoditys.put("ITEM000001", new BigDecimal(10));
		commoditys.put("ITEM000002", new BigDecimal(15));
		commoditys.put("ITEM000003", new BigDecimal(20));
	}

	public void setBarcode(List<String> barcodes) {
		this.barcodes = barcodes;
	}

	public Shoppinglist calculate() {
		BigDecimal total = new BigDecimal(0);
		for (String barcode : barcodes) {
			String[] barCodeInfo = barcode.split("-");
			total = total.add(itemPrice(barcode, barCodeInfo));
		}
		Shoppinglist shoppinglist = new Shoppinglist();
		shoppinglist.setTotal(total);

		return shoppinglist;
	}

	private BigDecimal itemPrice(String barcode, String[] barCodeInfo) {
		if (commoditys.containsKey(barCodeInfo[0])) {
			return commoditys.get(barCodeInfo[0])
					.multiply(barCodeInfo.length == 2 ? new BigDecimal(barCodeInfo[1]) : new BigDecimal(1));
		} else {
			throw new RuntimeException("未收录[" + barcode + "]此商品信息，请先收录商品信息！");
		}

	}

}
