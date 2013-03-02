package com.a2devel.kisok.adapter;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;

import com.a2devel.kisok.exception.SchemaReaderException;
import com.a2devel.kisok.model.Area;

public class SchemaAdapter extends JsoupSchemaAdapter {

	private List<JsoupSchemaAdapter> adapters;

	@SuppressWarnings("serial")
	public SchemaAdapter() {

		adapters = new ArrayList<JsoupSchemaAdapter>() {
			{
				add(new AreasSchemaAdapter());
				add(new NewsPapersSchemaAdapter());
			}
		};
	}

	@Override
	public List<Area> adapt(List<Area> areas, Element element)
			throws SchemaReaderException {
		for (JsoupSchemaAdapter adapter : adapters) {
			areas = adapter.adapt(areas, element);
		}
		return areas;
	}

}
