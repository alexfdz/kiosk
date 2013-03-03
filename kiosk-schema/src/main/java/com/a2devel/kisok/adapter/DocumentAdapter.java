package com.a2devel.kisok.adapter;

import org.jsoup.nodes.Document;

import com.a2devel.kisok.exception.SchemaReaderException;

public interface DocumentAdapter {

	public Document adapt(String url) throws SchemaReaderException;

}
