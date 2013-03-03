package com.a2devel.kisok.schema;

import java.util.List;

import org.jsoup.nodes.Document;

import com.a2devel.kisok.adapter.SchemaAdapter;
import com.a2devel.kisok.exception.SchemaReaderException;
import com.a2devel.kisok.model.Area;
import com.a2devel.kisok.model.Kiosk;

public class JsoupSchemaReader implements SchemaReader {

	protected String url;
	protected SchemaAdapter adapter;

	public JsoupSchemaReader(String url) {
		this.url = url;
	}

	@Override
	public Kiosk resolveKiosk() throws SchemaReaderException {
		Document document = adapter.resolveDocument(url);
		List<Area> areas = adapter.adapt(null, document);
		Kiosk kiosk = new Kiosk();
		kiosk.setAreas(areas);
		return kiosk;
	}

	public void setAdapter(SchemaAdapter adapter) {
		this.adapter = adapter;
	}
}
