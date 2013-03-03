package com.a2devel.kisok.adapter;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.a2devel.kisok.exception.SchemaReaderException;
import com.a2devel.kisok.model.Area;

public class AreasSchemaAdapter extends SchemaAdapter {

	private String baseUri;

	@Override
	public List<Area> adapt(List<Area> areas, Element element)
			throws SchemaReaderException {
		if (areas == null) {
			areas = new ArrayList<Area>();
		}

		baseUri = element.baseUri();

		List<Area> resolvedAreas = resolveMainAreas(element);
		if (resolvedAreas != null) {
			areas.addAll(resolvedAreas);
		}

		return areas;
	}

	protected List<Area> resolveMainAreas(Element rootElement)
			throws SchemaReaderException {

		Elements anchors = rootElement
				.select("#menu li:has(.submenu) a[accesskey]");
		if (anchors != null && !anchors.isEmpty()) {
			List<Area> areas = new ArrayList<Area>();
			for (Element element : anchors) {
				Area area = resolveAreaByAnchor(element);
				if (area != null) {
					areas.add(area);
				}
			}
			return areas;
		}
		return null;
	}

	protected Area resolveAreaByAnchor(Element anchor)
			throws SchemaReaderException {
		if (anchor != null) {
			String areaId = resolveAreaIdByAnchor(anchor);
			if (areaId != null) {
				Area area = new Area();
				area.setId(areaId);
				area.setName(anchor.text());
				setAreaChildsByAnchor(area, anchor);
				return area;
			}
		}
		return null;
	}

	protected void setAreaChildsByAnchor(Area parent, Element anchor)
			throws SchemaReaderException {
		if (parent != null && anchor != null) {

			String href = anchor.attr("href");
			Document document = resolveDocument(baseUri + href);

			String headerId = getHeaderAnchorHref(document);

			if (parent.getId().equals(headerId)) {
				parent.setAreas(resolveRegionAreas(document));
			} else {
				parent.setAreas(resolveLineAreas(document));
			}

		}
	}

	private List<Area> resolveRegionAreas(Document document)
			throws SchemaReaderException {
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

	private List<Area> resolveLineAreas(Document document)
			throws SchemaReaderException {

		Elements anchors = document.select("div.titPpal li a[href]");
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

	private String getHeaderAnchorHref(Document document) {
		if (document != null) {
			Elements headerAnchors = document
					.select("div.auxCol div.co strong a[href]");
			if (headerAnchors != null && headerAnchors.size() == 1) {
				return headerAnchors.attr("href");
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
}
