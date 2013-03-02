package com.a2devel.kisok.model;

import java.util.ArrayList;
import java.util.List;

public class Area {

	private List<NewsPaperCategory> newsPapersCategories;

	private List<Area> children;

	private Area parent;

	private String id;

	public Area(String id) {
		this.id = id;
	}

	public void addNewsPaperCategory(NewsPaperCategory category) {
		if (category != null) {
			category.setArea(this);
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

	public void addChild(Area child) {
		if (child != null) {
			child.setParent(this);
			if (children == null) {
				children = new ArrayList<Area>();
			}
			children.add(child);
		}
	}

	public Area getParent() {
		return parent;
	}

	public void setParent(Area parent) {
		this.parent = parent;
	}

	public List<Area> getChildren() {
		return children;
	}

	public void setChildren(List<Area> children) {
		if (children != null) {
			for (Area child : children) {
				addChild(child);
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isEmpty() {
		return children == null || children.isEmpty();
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

		if (children != null) {
			for (Area area : children) {
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
		if (id != null) {
			if (children != null) {
				for (Area child : children) {
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

}
