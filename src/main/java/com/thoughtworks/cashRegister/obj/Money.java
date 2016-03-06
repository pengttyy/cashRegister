package com.thoughtworks.cashRegister.obj;

import java.math.BigDecimal;

public class Money {
	private BigDecimal num;

	public Money(BigDecimal num) {
		this.num = num;
	}

	public Money(String num) {
		this.num = new BigDecimal(num);
	}

	public BigDecimal getNum() {
		return num.setScale(2);
	}

	@Override
	public String toString() {
		return num.setScale(2).toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((num == null) ? 0 : num.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		if (other.toString().equals(this.toString())) {
			return true;
		}
		return false;
	}

}
