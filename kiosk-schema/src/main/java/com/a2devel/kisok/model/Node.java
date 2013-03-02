package com.a2devel.kisok.model;

import java.util.ArrayList;
import java.util.List;

public class Node {

	Node parent;

	List<Node> children;

	String id;

	public Node(String id) {
		this.id = id;
	}

	public void addChild(Node child) {
		if (child != null) {
			child.setParent(this);
			if (children == null) {
				children = new ArrayList<Node>();
			}
			children.add(child);
		}
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<? extends Node> children) {
		if (children != null) {
			for (Node child : children) {
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

	public String getPath() {
		String path = "/";
		if (parent != null) {
			path = parent.getPath().concat(path);
		}
		path = path.concat(getId());
		return path;
	}

	@Override
	public String toString() {
		return getClass().getName() + "[" + getPath() + "]";
	}

}
