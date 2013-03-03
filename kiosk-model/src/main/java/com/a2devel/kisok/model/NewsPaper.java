package com.a2devel.kisok.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import com.a2devel.kisok.schema.Namespaces;

@XmlType(namespace = Namespaces.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class NewsPaper {

	@XmlAttribute(required = true)
	private String id;
	@XmlAttribute(required = true)
	private String name;

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public boolean isValid() {
		return id != null;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " - " + getId() + " - " + getName();
	}

	public static NewsPaper create(String id, String name) {
		NewsPaper newsPaper = new NewsPaper();
		newsPaper.setId(id);
		newsPaper.setName(name);
		return newsPaper;
	}

}
