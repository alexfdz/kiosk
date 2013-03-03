package com.a2devel.kisok.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import com.a2devel.kisok.schema.Namespaces;

@XmlType(namespace = Namespaces.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class NewsPaperCategory {

	private List<NewsPaper> newsPapers;

	@XmlAttribute(required = true)
	private String id;

	public void setId(String id) {
		this.id = id;
	}

	public void addNewsPaper(NewsPaper newsPaper) {
		if (newsPaper != null) {
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

	public static NewsPaperCategory create(String id) {
		NewsPaperCategory category = new NewsPaperCategory();
		category.setId(id);
		return category;
	}
}
