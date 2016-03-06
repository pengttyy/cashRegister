package com.thoughtworks.cashRegister;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.cashRegister.data.XMLCommodityService;
import com.thoughtworks.cashRegister.data.XMLRuleService;
import com.thoughtworks.cashRegister.obj.Shoppinglist;
import com.thoughtworks.cashRegister.print.PrintConsole;

public class CashRegisterTest {
	/**
	 * 当购买的商品中，有符合“买二赠一”优惠条件的商品时：
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCashRegister_BuyTwoGiveA() throws Exception {
		CashRegister cashRegister = new CashRegister(new XMLCommodityService(),
				new XMLRuleService("testCashRegister_BuyTwoGiveA.xml"), new PrintConsole());
		cashRegister.setBarcode(createBarCodes());
		cashRegister.printReceipts();
	}

	private List<String> createBarCodes() {
		List<String> barcodes = new ArrayList<String>();
		barcodes.add("ITEM000001-3");
		barcodes.add("ITEM000002-5");
		barcodes.add("ITEM000003-2");
		return barcodes;
	}

	/**
	 * 当购买的商品中，没有符合“买二赠一”优惠条件的商品时：
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCashRegister_No_BuyTwoGiveA() throws Exception {
		CashRegister cashRegister = new CashRegister(new XMLCommodityService(),
				new XMLRuleService("testCashRegister_No_BuyTwoGiveA.xml"), new PrintConsole());
		cashRegister.setBarcode(createBarCodes());
		cashRegister.printReceipts();
	}

	/**
	 * 当购买的商品中，有符合“95折”优惠条件的商品时
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCashRegister_Discount() throws Exception {
		CashRegister cashRegister = new CashRegister(new XMLCommodityService(),
				new XMLRuleService("testCashRegister_Discount.xml"), new PrintConsole());
		cashRegister.setBarcode(createBarCodes());
		cashRegister.printReceipts();
		assertTotal(cashRegister.calculate(), new BigDecimal("24.45"));
	}

	/**
	 * 当购买的商品中，有符合“95折”优惠条件的商品，又有符合“买二赠一”优惠条件的商品时
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCashRegister_BuyTwoGiveA_Discount() throws Exception {
		CashRegister cashRegister = new CashRegister(new XMLCommodityService(),
				new XMLRuleService("testCashRegister_BuyTwoGiveA_Discount.xml"), new PrintConsole());
		List<String> barcodes = new ArrayList<String>();
		barcodes.add("ITEM000001-3");
		barcodes.add("ITEM000002-6");
		barcodes.add("ITEM000003-2");
		cashRegister.setBarcode(barcodes);
		cashRegister.printReceipts();

		assertTotal(cashRegister.calculate(), new BigDecimal("20.45"));
	}

	private void assertTotal(Shoppinglist shoppings, BigDecimal expectTotal) {
		assertNotNull(shoppings);
		assertEquals(expectTotal.setScale(2), shoppings.getTotal().getNum());
	}

}
