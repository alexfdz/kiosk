package com.a2devel.kisok.adapter;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.a2devel.kisok.exception.SchemaReaderException;

public class HttpDocumentAdapter implements DocumentAdapter {

	@Override
	public Document adapt(String url) throws SchemaReaderException {
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			throw new SchemaReaderException(e);
		}
		return document;
	}

}
