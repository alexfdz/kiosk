package com.a2devel.kisok.model;

import java.util.ArrayList;
import java.util.List;

public class NewsPaperCategory {

	public static final String DEFAULT_CATEGORY_ID = "general";

	private List<NewsPaper> newsPapers;

	private String id;

	private Area area;

	public NewsPaperCategory(NewsPaperCategoryId id) {
		this.id = id.getId();
	}

	public void addNewsPaper(NewsPaper newsPaper) {
		if (newsPaper != null) {
			newsPaper.setCategory(this);
			if (newsPapers == null) {
				newsPapers = new ArrayList<NewsPaper>();
			}
			newsPapers.add(newsPaper);
		}
	}

	public void setNewsPapers(List<NewsPaper> newsPapers) {
		if (newsPapers != null) {
			for (NewsPaper newsPaper : newsPapers) {
				addNewsPaper(newsPaper);
			}
		}
	}

	public List<NewsPaper> getNewsPapers() {
		return newsPapers;
	}

	public boolean isValid() {
		if (id != null) {
			if (newsPapers != null) {
				for (NewsPaper newsPaper : newsPapers) {
					if (!newsPaper.isValid()) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	public boolean hasNewsPapers() {
		return newsPapers != null && !newsPapers.isEmpty();
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getClass().getSimpleName() + " - " + getId() + " [");

		if (newsPapers != null) {
			for (NewsPaper newsPaper : newsPapers) {
				buffer.append("\n\t" + newsPaper.toString());
			}
		}

		buffer.append("\n]");

		return buffer.toString();
	}
}
