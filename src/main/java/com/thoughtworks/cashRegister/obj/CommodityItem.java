package com.thoughtworks.cashRegister.obj;

import java.math.BigDecimal;

public class CommodityItem {
	private BigDecimal subtotal;
	private Commodity commodity;
	private BigDecimal thriftMoney;
	private BigDecimal thriftNum;
	private String nameOfPreferential;

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public BigDecimal getThriftMoney() {
		return thriftMoney;
	}

	public BigDecimal getThriftNum() {
		return thriftNum;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public void setThriftMoney(BigDecimal thriftMoney) {
		this.thriftMoney = thriftMoney;
	}

	public void setThriftNum(BigDecimal thriftNum) {
		this.thriftNum = thriftNum;
	}

	public void setNameOfPreferential(String nameOfPreferential) {
		this.nameOfPreferential = nameOfPreferential;
	}

	public String getNameOfPreferential() {
		return this.nameOfPreferential;
	}

}
