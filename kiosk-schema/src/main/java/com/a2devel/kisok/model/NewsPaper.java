package com.a2devel.kisok.model;

public class NewsPaper {

	private NewsPaperCategory category;
	private String id;
	private String name;

	public NewsPaper(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public NewsPaperCategory getCategory() {
		return category;
	}

	public void setCategory(NewsPaperCategory category) {
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public boolean isValid() {
		if (id != null && category != null) {
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " - " + getId() + " - " + getName();
	}

}
