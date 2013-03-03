package com.a2devel.kisok.adapter;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;

import com.a2devel.kisok.exception.SchemaReaderException;
import com.a2devel.kisok.model.Area;

public class KioskSchemaAdapter extends SchemaAdapter {

	protected List<SchemaAdapter> adapters;

	@Override
	public List<Area> adapt(List<Area> areas, Element element)
			throws SchemaReaderException {
		for (SchemaAdapter adapter : adapters) {
			areas = adapter.adapt(areas, element);
		}
		return areas;
	}

	public void addAdapter(SchemaAdapter adapter) {
		if (adapters == null) {
			adapters = new ArrayList<SchemaAdapter>();
		}
		adapters.add(adapter);
	}

	@Override
	public void setDocumentAdapter(DocumentAdapter documentAdapter) {
		super.setDocumentAdapter(documentAdapter);
		if (adapters != null) {
			for (SchemaAdapter adapter : adapters) {
				adapter.setDocumentAdapter(documentAdapter);
			}
		}
	}

}
