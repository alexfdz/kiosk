package com.a2devel.kisok.adapter;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.a2devel.kisok.exception.SchemaReaderException;
import com.a2devel.kisok.model.Area;

public abstract class JsoupSchemaAdapter {

	public abstract List<Area> adapt(List<Area> areas, Element element)
			throws SchemaReaderException;

	protected Document resolveDocument(String url) throws SchemaReaderException {
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			throw new SchemaReaderException(e);
		}
		return document;
	}

}
