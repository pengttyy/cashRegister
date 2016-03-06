package com.thoughtworks.cashRegister.obj;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Commodity {
	@XmlElement
	private String barcode;
	@XmlElement
	private String name;
	@XmlElement
	private String unit;
	@XmlElement
	@XmlJavaTypeAdapter(JaxbMoneyAdapter.class)
	private Money price;

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Money getPrice() {
		return price;
	}

	public void setPrice(Money price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Commodity [barcode=" + barcode + ", name=" + name + ", unit=" + unit + ", price=" + price + "]";
	}

}
