package com.a2devel.kisok.model;

import java.util.ArrayList;
import java.util.List;

public class Area extends Node {

	List<NewsPaperCategory> newsPapersCategories;

	public Area(String id) {
		super(id);
	}

	public void addNewsPaperCategory(NewsPaperCategory category) {
		if (category != null) {
			category.setParent(this);
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

	public List<NewsPaperCategory> getNewsPapersCategories() {
		return newsPapersCategories;
	}

}
