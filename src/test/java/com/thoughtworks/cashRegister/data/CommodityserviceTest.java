package com.thoughtworks.cashRegister.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.cashRegister.obj.Commodity;

public class CommodityserviceTest {
	private ICommodityService commodityService;

	@Before
	public void setUp() {
		this.commodityService = new XMLCommodityService();
	}

	@Test
	public void testFind() {
		String barcode = "ITEM000001";
		assertCommodityBybarcode(barcode);
	}

	@Test
	public void testFind_Err() {
		String notIncludeBarcode = "ITEM00000x";
		try {
			assertCommodityBybarcode(notIncludeBarcode);
			fail("未收录的商品应该出现异常！");
		} catch (NullPointerException e) {
			assertEquals("未根据条形码【" + notIncludeBarcode + "】，找到对应商品！请录入", e.getMessage());
		}
	}

	private void assertCommodityBybarcode(String barcode) {
		Commodity commodity = this.commodityService.find(barcode);
		assertNotNull(commodity);
		assertEquals(barcode, commodity.getBarcode());

	}

}
