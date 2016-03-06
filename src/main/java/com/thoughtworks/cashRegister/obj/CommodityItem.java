package com.thoughtworks.cashRegister.obj;

public class CommodityItem {
	private Money subtotal;// 小结
	private Commodity commodity;// 商品信息
	private Money thriftMoney;// 节省金额
	private double thriftNum;// 优惠数量
	private String nameOfPreferential;// 优惠名称
	private double num;// 购买量
	private boolean displayThrift;// 是否显示优惠信息

	public Money getSubtotal() {
		return subtotal;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public Money getThriftMoney() {
		return thriftMoney;
	}

	public double getThriftNum() {
		return thriftNum;
	}

	public void setSubtotal(Money subtotal) {
		this.subtotal = subtotal;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public void setThriftMoney(Money thriftMoney) {
		this.thriftMoney = thriftMoney;
	}

	public void setThriftNum(double thriftNum) {
		this.thriftNum = thriftNum;
	}

	public void setNameOfPreferential(String nameOfPreferential) {
		this.nameOfPreferential = nameOfPreferential;
	}

	public String getNameOfPreferential() {
		return this.nameOfPreferential;
	}

	public double getNum() {
		return num;
	}

	public void setNum(double num) {
		this.num = num;
	}

	public boolean isDisplayThrift() {
		return displayThrift;
	}

	public void setDisplayThrift(boolean displayThrift) {
		this.displayThrift = displayThrift;
	}

}
