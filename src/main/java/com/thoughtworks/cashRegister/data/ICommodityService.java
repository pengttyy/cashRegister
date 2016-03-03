package com.thoughtworks.cashRegister.data;

import com.thoughtworks.cashRegister.obj.Commodity;

public interface ICommodityService {
	public Commodity find(String barcode);
}
