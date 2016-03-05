package com.thoughtworks.cashRegister;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.cashRegister.data.XMLCommodityService;
import com.thoughtworks.cashRegister.data.XMLRuleService;
import com.thoughtworks.cashRegister.obj.Shoppinglist;

import static org.junit.Assert.*;

public class CashRegisterTest {
	private CashRegister cashRegister;

	@Before
	public void setUp() {
		this.cashRegister = new CashRegister(new XMLCommodityService(), new XMLRuleService("barCodeRule.xml"));
	}

	@Test
	public void testCashRegister() throws Exception {
		List<String> barcodes = new ArrayList<String>();
		barcodes.add("ITEM000001");
		barcodes.add("ITEM000002");
		this.cashRegister.setBarcode(barcodes);
		assertTotal(cashRegister.calculate(), new BigDecimal("4"));
	}

	@Test
	public void testCashRegister_amount() throws Exception {
		List<String> barcodes = new ArrayList<String>();
		barcodes.add("ITEM000001-2");
		barcodes.add("ITEM000002-3");
		this.cashRegister.setBarcode(barcodes);
		assertTotal(cashRegister.calculate(), new BigDecimal("9"));
	}

	/**
	 * 收银机未收录的商品
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCashRegister_NoCommodity() throws Exception {
		String noIncludCommodity = "ITEM00000x";
		try {
			List<String> barcodes = new ArrayList<String>();
			barcodes.add("ITEM000001");
			barcodes.add(noIncludCommodity);// 未收录的商品

			this.cashRegister.setBarcode(barcodes);
			cashRegister.calculate();
			fail("计算未收录的商品时应该抛出异常");
		} catch (Exception e) {
			String message = e.getMessage();
			assertEquals("未根据条形码【" + noIncludCommodity + "】，找到对应商品！请录入", message);
		}
	}

	/**
	 * 可口可乐，买二赠一
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCashRegister_BuyTwoGiveA() throws Exception {
		this.cashRegister.setBarcode(Arrays.asList("ITEM000001-3"));
		assertTotal(cashRegister.calculate(), new BigDecimal("6"));

	}

	/**
	 * 苹果95折
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCashRegister_discount() throws Exception {
		this.cashRegister.setBarcode(Arrays.asList("ITEM000003-2"));
		Shoppinglist calculate = cashRegister.calculate();
		assertTotal(calculate, new BigDecimal("10.45"));

	}

	private void assertTotal(Shoppinglist shoppings, BigDecimal expectTotal) {
		assertNotNull(shoppings);
		assertEquals(expectTotal.setScale(2), shoppings.getTotal().setScale(2));
	}

}
