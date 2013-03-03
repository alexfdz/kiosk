package com.a2devel.kisok.schema;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.a2devel.kisok.adapter.JsoupSchemaAdapter;
import com.a2devel.kisok.adapter.SchemaAdapter;
import com.a2devel.kisok.exception.SchemaReaderException;
import com.a2devel.kisok.model.Area;
import com.a2devel.kisok.model.Kiosk;

public class JsoupSchemaReader implements SchemaReader {

	private String url;
	protected Document document;
	private JsoupSchemaAdapter adapter;

	public JsoupSchemaReader(String url) {
		this.url = url;
		this.adapter = new SchemaAdapter();
	}

	@Override
	public Kiosk resolveKiosk() throws SchemaReaderException {
		Document document = resolveDocument();
		List<Area> areas = adapter.adapt(null, document);
		Kiosk kiosk = new Kiosk();
		kiosk.setAreas(areas);
		return kiosk;
	}

	protected Document resolveDocument() throws SchemaReaderException {
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			throw new SchemaReaderException(e);
		}
		return document;
	}
}
