package com.a2devel.kisok.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.a2devel.kisok.schema.Namespaces;

@XmlType(namespace = Namespaces.NAMESPACE, name = "area")
@XmlAccessorType(XmlAccessType.FIELD)
public class Area {

	@XmlElement(name = "category")
	private List<NewsPaperCategory> newsPapersCategories;

	@XmlElement(name = "area")
	private List<Area> areas;

	@XmlAttribute(required = true)
	private String id;

	@XmlAttribute(required = true)
	private String name;

	public void addNewsPaperCategory(NewsPaperCategory category) {
		if (category != null) {
			if (newsPapersCategories == null) {
				newsPapersCategories = new ArrayList<NewsPaperCategory>();
			}
			newsPapersCategories.add(category);
		}
	}

	public void setNewsPapersCategories(
			List<NewsPaperCategory> newsPapersCategories) {
		if (newsPapersCategories != null) {
			for (NewsPaperCategory category : newsPapersCategories) {
				addNewsPaperCategory(category);
			}
		}
	}

	public void addArea(Area child) {
		if (child != null) {
			if (areas == null) {
				areas = new ArrayList<Area>();
			}
			areas.add(child);
		}
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> children) {
		if (children != null) {
			for (Area child : children) {
				addArea(child);
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isEmpty() {
		return areas == null || areas.isEmpty();
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getClass().getSimpleName() + " - " + getId() + "[");

		if (newsPapersCategories != null) {
			for (NewsPaperCategory category : newsPapersCategories) {
				buffer.append("\n\t" + category.toString());
			}
		}

		if (areas != null) {
			for (Area area : areas) {
				buffer.append("\n\t" + area.toString());
			}
		}

		buffer.append("\n]");

		return buffer.toString();
	}

	public List<NewsPaperCategory> getNewsPapersCategories() {
		return newsPapersCategories;
	}

	public boolean hasNewsPapersCategories() {
		return newsPapersCategories != null && !newsPapersCategories.isEmpty();
	}

	public boolean isValid() {
		if (id != null && name != null) {
			if (areas != null) {
				for (Area child : areas) {
					if (!child.isValid()) {
						return false;
					}
				}
			}
			if (newsPapersCategories != null) {
				for (NewsPaperCategory category : newsPapersCategories) {
					if (!category.isValid()) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	public static Area create(String id) {
		Area area = new Area();
		area.setId(id);
		return area;
	}

	public int areasCount() {
		int i = 1;
		if (areas != null) {
			for (Area area : areas) {
				i = i + area.areasCount();
			}
		}
		return i;
	}

	public int categoriesCount() {
		int i = 0;

		if (newsPapersCategories != null) {
			i = newsPapersCategories.size();
		}

		if (areas != null) {
			for (Area area : areas) {
				i = i + area.categoriesCount();
			}
		}
		return i;
	}

	public int newsPapersCount() {
		int i = 0;

		if (newsPapersCategories != null) {
			for (NewsPaperCategory category : newsPapersCategories) {
				i = i + category.newsPapersCount();
			}
		}

		if (areas != null) {
			for (Area area : areas) {
				i = i + area.newsPapersCount();
			}
		}
		return i;
	}

	public List<String> emptyAreas() {
		List<String> emtpyAreas = new ArrayList<String>();

		if (newsPapersCount() == 0) {
			emptyAreas().add(getName());
		} else {
			if (areas != null) {
				for (Area area : areas) {
					emtpyAreas.addAll(area.emptyAreas());
				}
			}
		}

		return emtpyAreas;
	}

}
