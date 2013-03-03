package com.a2devel.kisok.adapter;

import java.io.IOException;
import java.io.InputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.a2devel.kisok.exception.SchemaReaderException;

public class FakeDocumentAdapter implements DocumentAdapter {

	@Override
	public Document adapt(String url) throws SchemaReaderException {
		Document document = null;
		String filePath = normalize(url);

		if (this.getClass().getResourceAsStream(filePath) == null) {
			System.out.println();
		}

		InputStream stream = this.getClass().getResourceAsStream(filePath);

		try {
			document = Jsoup.parse(stream, "UTF-8", url);
		} catch (IOException e) {
			throw new SchemaReaderException(e);
		}
		return document;
	}

	private String normalize(String url) {
		url = url.replace("http://", "");
		url = url.replace("/", "_");
		url = "/html/" + url + ".xml";
		return url;
	}

}
