package com.a2devel.kisok.adapter;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.a2devel.kisok.exception.SchemaReaderException;
import com.a2devel.kisok.model.Area;

public class AreasSchemaAdapter extends JsoupSchemaAdapter {

	private String baseUri;

	@Override
	public List<Area> adapt(List<Area> areas, Element element)
			throws SchemaReaderException {
		if (areas == null) {
			areas = new ArrayList<Area>();
		}

		baseUri = element.baseUri();

		List<Area> resolvedAreas = resolveAreas(element,
				"#menu li:has(.submenu)");
		if (resolvedAreas != null) {
			areas.addAll(resolvedAreas);
		}

		return areas;
	}

	protected List<Area> resolveChildAreas(Element areaElement)
			throws SchemaReaderException {
		return resolveAreas(areaElement, "ul li a[href]");
	}

	protected Area resolveArea(Element areaElement)
			throws SchemaReaderException {
		Area area = null;
		if (areaElement != null) {
			Element anchor = resolveAnchor(areaElement);
			area = resolveAreaByAnchor(anchor);
			if (area != null) {
				area.setAreas(resolveChildAreas(areaElement));
			}
		}
		return area;
	}

	protected List<Area> resolveAreas(Element rootElement, String cssSelector)
			throws SchemaReaderException {
		Elements elements = rootElement.select(cssSelector);
		if (elements != null && !elements.isEmpty()) {
			List<Area> areas = new ArrayList<Area>();
			for (Element element : elements) {
				Area area = resolveArea(element);
				if (area != null) {
					areas.add(area);
				}
			}
			return areas;
		} else {
			String href = rootElement.attr("href");
			return resolveChildAreasByUrl(baseUri + href);
		}
	}

	private List<Area> resolveChildAreasByUrl(String url)
			throws SchemaReaderException {
		Document document = resolveDocument(url);
		Elements anchors = document.select("li.reg a[href]");
		List<Area> areas = new ArrayList<Area>();
		if (anchors != null && anchors.size() > 0) {
			for (Element anchor : anchors) {
				Area area = resolveAreaByAnchor(anchor);
				if (area != null) {
					areas.add(area);
				}
			}
		}
		return areas;
	}

	protected Element resolveAnchor(Element element) {
		if (element != null) {
			Elements anchors = element.select("a[href]");
			if (anchors != null && anchors.size() > 0) {
				return anchors.get(0);
			}
		}
		return null;
	}

	protected String resolveAreaIdByAnchor(Element anchor) {
		if (anchor != null) {
			return anchor.attr("href");
		}
		return null;
	}

	protected Area resolveAreaByAnchor(Element anchor) {
		if (anchor != null) {
			String areaId = resolveAreaIdByAnchor(anchor);
			if (areaId != null) {
				Area area = new Area();
				area.setId(areaId);
				return area;
			}
		}
		return null;
	}

}
