package com.thoughtworks.cashRegister.data;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXB;

import com.thoughtworks.cashRegister.obj.BarCodeRule;
import com.thoughtworks.cashRegister.obj.BarCodeRules;

public class XMLRuleService implements IRuleService {
	private List<BarCodeRule> rules;

	public XMLRuleService(String fileName) {
		BarCodeRules barCodeRules = JAXB.unmarshal(new File(fileName), BarCodeRules.class);
		this.rules = barCodeRules.getBarCodeRules();
	}

	public BarCodeRule findRuleByBarCode(String barCode) {
		for (BarCodeRule barCodeRule : rules) {
			if (barCode.equals(barCodeRule.getBarCode())) {
				return barCodeRule;
			}
		}
		return null;
	}

}
