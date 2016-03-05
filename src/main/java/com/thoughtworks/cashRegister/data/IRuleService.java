package com.thoughtworks.cashRegister.data;

import com.thoughtworks.cashRegister.obj.BarCodeRule;

public interface IRuleService {
	BarCodeRule findRuleByBarCode(String barCode);
}
