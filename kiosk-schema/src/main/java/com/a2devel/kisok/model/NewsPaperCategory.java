package com.a2devel.kisok.model;

import java.util.ArrayList;
import java.util.List;

public class NewsPaperCategory extends Node {

	public static final String DEFAULT_CATEGORY_ID = "general";

	List<NewsPaper> newsPapers;

	public NewsPaperCategory(String id) {
		super(id);
	}

	public void addNewsPaper(NewsPaper newsPaper) {
		if (newsPaper != null) {
			newsPaper.setParent(this);
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

}
