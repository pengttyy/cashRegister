package com.thoughtworks.cashRegister.data;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

import com.thoughtworks.cashRegister.obj.BarCodeRule;

public class RuleServiceTest {
	@Test
	public void test() {
		IRuleService ruleService = new XMLRuleService("barCodeRule.xml");
		String barCode = "ITEM000002";
		BarCodeRule barCodeRule = ruleService.findRuleByBarCode(barCode);
		List<String> roleClassNames = barCodeRule.getRoleClassNames();

		assertEquals(1, roleClassNames.size());
	}

	@Test(expected = NullPointerException.class)
	public void testFindRuleByBarCode_entey() throws NullPointerException {
		IRuleService ruleService = new XMLRuleService("barCodeRule.xml");
		String barCode = "ITEM00000x";
		ruleService.findRuleByBarCode(barCode);
	}

}
