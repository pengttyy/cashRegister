package com.thoughtworks.cashRegister.obj;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BarCodeRules {
	@XmlElement(name = "barCodeRule")
	private List<BarCodeRule> barCodeRules;

	public List<BarCodeRule> getBarCodeRules() {
		return barCodeRules;
	}

	public void setBarCodeRules(List<BarCodeRule> barCodeRules) {
		this.barCodeRules = barCodeRules;
	}
}
