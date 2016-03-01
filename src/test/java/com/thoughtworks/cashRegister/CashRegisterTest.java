package com.thoughtworks.cashRegister;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.cashRegister.obj.Shoppinglist;

import static org.junit.Assert.*;

public class CashRegisterTest {
	private CashRegister cashRegister;

	@Before
	public void setUp() {
		this.cashRegister = new CashRegister();
	}

	@Test
	public void testCashRegister() throws Exception {
		List<String> barcodes = new ArrayList<String>();
		barcodes.add("ITEM000001");
		barcodes.add("ITEM000002");
		this.cashRegister.setBarcode(barcodes);
		assertTotal(cashRegister.calculate(), new BigDecimal(25));
	}

	@Test
	public void testCashRegister_amount() throws Exception {
		List<String> barcodes = new ArrayList<String>();
		barcodes.add("ITEM000001-2");
		barcodes.add("ITEM000002-3");
		this.cashRegister.setBarcode(barcodes);
		assertTotal(cashRegister.calculate(), new BigDecimal(65));
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
			barcodes.add("ITEM000002");
			barcodes.add(noIncludCommodity);// 未收录的商品

			this.cashRegister.setBarcode(barcodes);
			cashRegister.calculate();
			fail("计算未收录的商品时应该抛出异常");
		} catch (Exception e) {
			String message = e.getMessage();
			assertEquals("未收录[" + noIncludCommodity + "]此商品信息，请先收录商品信息！", message);
		}
	}

	private void assertTotal(Shoppinglist shoppings, BigDecimal expectTotal) {
		assertNotNull(shoppings);
		assertEquals(expectTotal, shoppings.getTotal());
	}

}
