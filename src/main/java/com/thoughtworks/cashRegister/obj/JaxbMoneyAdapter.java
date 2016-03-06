package com.thoughtworks.cashRegister.obj;

import java.math.BigDecimal;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class JaxbMoneyAdapter extends XmlAdapter<BigDecimal, Money> {
	private static final BigDecimal ZERO = new BigDecimal("0");
	private static final Money ZEROMONEY = new Money(ZERO);

	@Override
	public Money unmarshal(BigDecimal num) throws Exception {
		if (num != null) {
			return new Money(num);
		}
		return ZEROMONEY;
	}

	@Override
	public BigDecimal marshal(Money money) throws Exception {
		if (money != null) {
			return money.getNum();
		}
		return ZERO;

	}

}
