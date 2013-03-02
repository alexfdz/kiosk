package com.a2devel.kisok.schema;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.a2devel.kisok.exception.SchemaReaderException;
import com.a2devel.kisok.model.Area;

public class JsoupSchemaReader implements SchemaReader {

	private String url;
	protected Document document;

	public JsoupSchemaReader(String url) {
		this.url = url;
	}

	@Override
	public List<Area> resolveSchema() throws SchemaReaderException {
		List<Area> areas = new ArrayList<Area>();
		document = resolveDocument();

		areas = resolveRootAreas();

		return areas;
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

	protected List<Area> resolveRootAreas() {
		return resolveAreas(document, "#menu li:has(.submenu)");
	}

	protected List<Area> resolveChildAreas(Element areaElement) {
		return resolveAreas(areaElement, "ul li a[href]");
	}

	protected Area resolveArea(Element areaElement) {
		Area area = null;
		if (areaElement != null) {
			String areaId = resolveAreaId(areaElement);
			if (areaId != null) {
				area = new Area(areaId);
				area.setChildren(resolveChildAreas(areaElement));
			}
		}
		return area;
	}

	protected List<Area> resolveAreas(Element rootElement, String cssSelector) {
		List<Area> areas = new ArrayList<Area>();

		Elements elements = rootElement.select(cssSelector);
		if (elements != null && !elements.isEmpty()) {
			for (Element element : elements) {
				Area area = resolveArea(element);
				if (area != null) {
					areas.add(area);
				}
			}
		}
		return areas;
	}

	protected String resolveAreaId(Element areaElement) {
		String areaId = null;
		if (areaElement != null) {
			String href = areaElement.select("a[href]").attr("href");
			if (href != null && href.startsWith("/") && href.endsWith("/")) {
				areaId = href.substring(1, href.length() - 1);
			}
		}
		return areaId;
	}
}
