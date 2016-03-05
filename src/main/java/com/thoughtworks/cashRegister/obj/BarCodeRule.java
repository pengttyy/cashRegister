package com.thoughtworks.cashRegister.obj;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;

@XmlAccessorType(XmlAccessType.FIELD)
public class BarCodeRule {
	@XmlAttribute
	private String barCode;
	@XmlElement
	@XmlList
	private List<String> roleClassNames;

	public String getBarCode() {
		return barCode;
	}

	public List<String> getRoleClassNames() {
		return roleClassNames;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public void setRoleClassNames(List<String> roleClassNames) {
		this.roleClassNames = roleClassNames;
	}

}
