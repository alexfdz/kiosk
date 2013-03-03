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

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getClass().getSimpleName() + " [");

		if (areas != null) {
			for (Area area : areas) {
				buffer.append("\n\t" + area.toString());
			}
		}

		buffer.append("\n]");

		return buffer.toString();
	}

	public int areasCount() {
		int i = 0;
		if (areas != null) {
			for (Area area : areas) {
				i = i + area.areasCount();
			}
		}
		return i;
	}

	public int categoriesCount() {
		int i = 0;
		if (areas != null) {
			for (Area area : areas) {
				i = i + area.categoriesCount();
			}
		}
		return i;
	}

	public int newsPapersCount() {
		int i = 0;
		if (areas != null) {
			for (Area area : areas) {
				i = i + area.newsPapersCount();
			}
		}
		return i;
	}

	public String[] emptyAreas() {
		List<String> emtpyAreas = new ArrayList<String>();
		if (areas != null) {
			for (Area area : areas) {
				emtpyAreas.addAll(area.emptyAreas());
			}
		}
		return emtpyAreas.toArray(new String[emtpyAreas.size()]);
	}

}
