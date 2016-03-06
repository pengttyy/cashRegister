package com.thoughtworks.cashRegister.rule;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.thoughtworks.cashRegister.obj.Commodity;
import com.thoughtworks.cashRegister.obj.CommodityItem;
import com.thoughtworks.cashRegister.obj.Money;

public class PreferentialRuleTest {
	
	/**
	 * 测试“买二送一”规则
	 */
	@Test
	public void testExecuteBuyTwoGiveA() {
		IpreferentialRule rule = new BuyTwoGiveA();
		Commodity commodity = createCommodity();
		CommodityItem item = rule.execute(new BigDecimal(3), commodity);

		Money expectedSubtotal = new Money("6.00");
		Money expectedThriftMoney = new Money("3.00");
		double expectedThriftNum = 1;
		String expectedNameOfPreferential = "买二赠一";

		assertCommodityItem(commodity, item, expectedSubtotal, expectedThriftMoney, expectedThriftNum,
				expectedNameOfPreferential);
	}
	
	/**
	 * 测试“95折”规则
	 */
	@Test
	public void testExecuteDiscount() {
		IpreferentialRule rule = new Discount();
		Commodity commodity = createCommodity();
		CommodityItem item = rule.execute(new BigDecimal(3), commodity);

		Money expectedSubtotal = new Money("8.55");
		Money expectedThriftMoney = new Money("0.45");
		double expectedThriftNum = 0;
		String expectedNameOfPreferential = "95折";

		assertCommodityItem(commodity, item, expectedSubtotal, expectedThriftMoney, expectedThriftNum,
				expectedNameOfPreferential);
	}

	private void assertCommodityItem(Commodity commodity, CommodityItem item, Money expectedSubtotal,
			Money expectedThriftMoney, double expectedThriftNum, String expectedNameOfPreferential) {
		assertEquals(expectedSubtotal, item.getSubtotal());
		assertEquals(commodity, item.getCommodity());
		assertEquals(expectedThriftMoney, item.getThriftMoney());
		assertEquals(expectedThriftNum, item.getThriftNum(), 0);
		assertEquals(expectedNameOfPreferential, item.getNameOfPreferential());
	}

	private Commodity createCommodity() {
		Commodity commodity = new Commodity();
		commodity.setBarcode("ITEM000001");
		commodity.setName("可乐");
		commodity.setPrice(new Money(new BigDecimal(3)));
		commodity.setUnit("瓶");
		return commodity;
	}

}
