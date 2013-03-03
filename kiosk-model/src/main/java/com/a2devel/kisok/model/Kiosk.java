package com.a2devel.kisok.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.a2devel.kisok.schema.Namespaces;

@XmlRootElement(namespace = Namespaces.NAMESPACE)
public class Kiosk {

	private List<Area> areas;

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public void addArea(Area area) {
		if (area != null) {
			if (areas == null) {
				areas = new ArrayList<Area>();
			}
			areas.add(area);
		}
	}

}
