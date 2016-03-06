package com.thoughtworks.cashRegister.print;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.cashRegister.obj.Commodity;
import com.thoughtworks.cashRegister.obj.CommodityItem;
import com.thoughtworks.cashRegister.obj.Money;
import com.thoughtworks.cashRegister.obj.Shoppinglist;

public class PrintConsole implements IPrintReceipts {
	private Shoppinglist shoppinglist;

	@Override
	public void printReceipts(Shoppinglist shoppinglist) {
		this.shoppinglist = shoppinglist;

		StringBuilder receipts = new StringBuilder();
		receipts.append("***<没钱赚商店>购物清单***").append("\n");
		receipts.append(printItem());
		receipts.append("----------------------");
		receipts.append(printPreferential());
		receipts.append("\n----------------------");
		receipts.append(printTotal());
		receipts.append("\n**********************");
		System.out.println(receipts);
	}

	private StringBuilder printTotal() {
		StringBuilder totalStr = new StringBuilder();
		BigDecimal total = new BigDecimal("0");
		BigDecimal thrift = new BigDecimal("0");
		for (CommodityItem commodityItem : shoppinglist.getCommodityItems()) {
			total = total.add(commodityItem.getSubtotal().getNum());
			thrift = thrift.add(commodityItem.getThriftMoney().getNum());
		}
		totalStr.append("\n总计:").append(new Money(total)).append("(元)");

		if (thrift.doubleValue() != 0) {
			totalStr.append("\n节省:").append(thrift).append("(元)");
		}
		return totalStr;
	}

	private StringBuilder printPreferential() {
		StringBuilder preferential = new StringBuilder();
		Map<String, StringBuilder> splicePreferentialItem = splicePreferentialItem();
		for (Map.Entry<String, StringBuilder> entry : splicePreferentialItem.entrySet()) {
			preferential.append("\n" + entry.getKey()).append("商品:").append(entry.getValue());
		}
		return preferential;
	}

	private Map<String, StringBuilder> splicePreferentialItem() {
		Map<String, StringBuilder> preferentialItem = new HashMap<>();
		for (CommodityItem commodityItem : shoppinglist.getCommodityItems()) {
			if (commodityItem.isDisplayThrift()) {
				Commodity commodity = commodityItem.getCommodity();

				StringBuilder preferential = new StringBuilder();
				preferential.append("\n名称:").append(commodity.getName()).append(",数量:")
						.append(commodityItem.getThriftNum()).append(commodity.getUnit());

				String nameOfPreferential = commodityItem.getNameOfPreferential();
				if (preferentialItem.containsKey(nameOfPreferential)) {
					preferentialItem.get(nameOfPreferential).append(preferential);
				} else {
					preferentialItem.put(commodityItem.getNameOfPreferential(), preferential);
				}

			}
		}
		return preferentialItem;
	}

	private StringBuilder printItem() {
		StringBuilder receipts = new StringBuilder();
		for (CommodityItem commodityItem : shoppinglist.getCommodityItems()) {
			Commodity commodity = commodityItem.getCommodity();
			receipts.append("名称:").append(commodity.getName()).append(",数量:").append(commodityItem.getNum())
					.append("(" + commodity.getUnit() + ")").append(",单价:").append(commodity.getPrice())
					.append("(元),小计：").append(commodityItem.getSubtotal()).append("(元)");

			if (commodityItem.getThriftMoney().getNum().doubleValue() > 0) {
				receipts.append(",节省:").append(commodityItem.getThriftMoney()).append("(元)");
			}
			receipts.append("\n");
		}
		return receipts;
	}

}
