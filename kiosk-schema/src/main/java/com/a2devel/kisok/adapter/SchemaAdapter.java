package com.a2devel.kisok.adapter;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.a2devel.kisok.exception.SchemaReaderException;
import com.a2devel.kisok.model.Area;

public abstract class SchemaAdapter {

	DocumentAdapter documentAdapter;

	public abstract List<Area> adapt(List<Area> areas, Element element)
			throws SchemaReaderException;

	public Document resolveDocument(String url) throws SchemaReaderException {
		return documentAdapter.adapt(url);
	}

	public void setDocumentAdapter(DocumentAdapter documentAdapter) {
		this.documentAdapter = documentAdapter;
	}
}
