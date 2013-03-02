package com.a2devel.kisok.model;

public enum NewsPaperCategoryId {

	GENERAL("general", "General press"), SPORT("sport", "Sports newspapers"), ECONOMY(
			"economy", "Economic press"), MAGAZINE("magazine", "Magazine"), TABLOID(
			"tabloid", "Tabloid"), POPULAR("popular", "Tabloid");

	private String id;
	private String label;

	private NewsPaperCategoryId(String id, String label) {
		this.id = id;
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public static NewsPaperCategoryId byLabel(String label) {
		if (label != null) {
			for (NewsPaperCategoryId category : NewsPaperCategoryId.values()) {
				if (label.equals(category.getLabel())) {
					return category;
				}
			}
		}
		return GENERAL;
	}

	public static NewsPaperCategoryId byId(String id) {
		if (id != null) {
			for (NewsPaperCategoryId category : NewsPaperCategoryId.values()) {
				if (id.equals(category.getId())) {
					return category;
				}
			}
		}
		return GENERAL;
	}

}
