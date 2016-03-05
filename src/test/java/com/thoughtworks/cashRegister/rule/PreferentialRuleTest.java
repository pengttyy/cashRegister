package com.thoughtworks.cashRegister.rule;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.thoughtworks.cashRegister.obj.Commodity;
import com.thoughtworks.cashRegister.obj.CommodityItem;

public class PreferentialRuleTest {

	@Test
	public void testExecuteBuyTwoGiveA() {
		IpreferentialRule rule = new BuyTwoGiveA();
		Commodity commodity = createCommodity();
		CommodityItem item = rule.execute(new BigDecimal(3), commodity);

		BigDecimal expectedSubtotal = new BigDecimal("6.00");
		BigDecimal expectedThriftMoney = new BigDecimal("3.00");
		BigDecimal expectedThriftNum = new BigDecimal("1");
		String expectedNameOfPreferential = "买二赠一";

		assertCommodityItem(commodity, item, expectedSubtotal, expectedThriftMoney, expectedThriftNum,
				expectedNameOfPreferential);
	}

	@Test
	public void testExecuteDiscount() {
		IpreferentialRule rule = new Discount();
		Commodity commodity = createCommodity();
		CommodityItem item = rule.execute(new BigDecimal(3), commodity);

		BigDecimal expectedSubtotal = new BigDecimal("8.55");
		BigDecimal expectedThriftMoney = new BigDecimal("0.45");
		BigDecimal expectedThriftNum = new BigDecimal("0");
		String expectedNameOfPreferential = "95折";

		assertCommodityItem(commodity, item, expectedSubtotal, expectedThriftMoney, expectedThriftNum,
				expectedNameOfPreferential);
	}

	private void assertCommodityItem(Commodity commodity, CommodityItem item, BigDecimal expectedSubtotal,
			BigDecimal expectedThriftMoney, BigDecimal expectedThriftNum, String expectedNameOfPreferential) {
		assertEquals(expectedSubtotal, item.getSubtotal().setScale(2));
		assertEquals(commodity, item.getCommodity());
		assertEquals(expectedThriftMoney, item.getThriftMoney().setScale(2));
		assertEquals(expectedThriftNum, item.getThriftNum().setScale(0));
		assertEquals(expectedNameOfPreferential, item.getNameOfPreferential());
	}

	private Commodity createCommodity() {
		Commodity commodity = new Commodity();
		commodity.setBarcode("ITEM000001");
		commodity.setName("可乐");
		commodity.setPrice(new BigDecimal(3));
		commodity.setUnit("瓶");
		return commodity;
	}

}
