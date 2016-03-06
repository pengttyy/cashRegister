package com.thoughtworks.cashRegister.data;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXB;

import com.thoughtworks.cashRegister.obj.Commodity;
import com.thoughtworks.cashRegister.obj.Commoditys;

public class XMLCommodityService implements ICommodityService {
	private List<Commodity> commodityList;

	public XMLCommodityService(String fileName) {
		init(fileName);
	}

	private void init(String fileName) {
		Commoditys commoditys = JAXB.unmarshal(new File(fileName), Commoditys.class);
		this.commodityList = commoditys.getCommoditys();
	}

	public Commodity find(String barcode) {
		for (Commodity commodity : commodityList) {
			if ((commodity.getBarcode()).equals(barcode)) {
				return commodity;
			}
		}
		throw new NullPointerException("未根据条形码【" + barcode + "】，找到对应商品！请录入");
	}

}
