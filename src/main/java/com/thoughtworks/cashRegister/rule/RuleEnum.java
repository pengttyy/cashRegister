package com.thoughtworks.cashRegister.rule;

import java.util.List;

import com.thoughtworks.cashRegister.data.IRuleService;
import com.thoughtworks.cashRegister.obj.BarCodeRule;

public enum RuleEnum {
	BUYTWOGIVEA(new BuyTwoGiveA()), DISCOUNT(new Discount()), NORMALIZATION(new Normalization());

	private IpreferentialRule rule;

	private RuleEnum(IpreferentialRule rule) {
		this.rule = rule;
	}

	public static RuleEnum findRuleByBarCode(String barCode, IRuleService ruleService) {

		BarCodeRule rules = ruleService.findRuleByBarCode(barCode);
		if (rules == null) {
			return NORMALIZATION;
		}

		List<String> roleClassNames = rules.getRoleClassNames();
		String enumName = roleClassNames.get(0);
		return RuleEnum.valueOf(enumName.toUpperCase());

	}

	public IpreferentialRule getRule() {
		return rule;
	}

}
