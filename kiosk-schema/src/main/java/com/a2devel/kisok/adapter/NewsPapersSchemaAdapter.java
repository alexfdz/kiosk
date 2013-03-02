package com.a2devel.kisok.adapter;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.a2devel.kisok.exception.SchemaReaderException;
import com.a2devel.kisok.model.Area;
import com.a2devel.kisok.model.NewsPaper;
import com.a2devel.kisok.model.NewsPaperCategory;
import com.a2devel.kisok.model.NewsPaperCategoryId;

public class NewsPapersSchemaAdapter extends JsoupSchemaAdapter {

	private String baseUri;

	@Override
	public List<Area> adapt(List<Area> areas, Element element)
			throws SchemaReaderException {
		baseUri = element.baseUri();
		resolveAreasNewsPapers(areas);
		return areas;
	}

	protected void resolveAreasNewsPapers(List<Area> areas)
			throws SchemaReaderException {

		if (areas != null && baseUri != null) {
			for (Area area : areas) {
				resolveAreaNewsPapers(area);
			}
		}
	}

	protected void resolveAreaNewsPapers(Area area)
			throws SchemaReaderException {

		String url = baseUri + area.getId();
		Document document = resolveDocument(url);

		resolveAreaNewsPapers(area, document);
	}

	protected void resolveAreaNewsPapers(Area area, Document document)
			throws SchemaReaderException {

		Elements expoElements = document.select("div.expo div");

		for (int i = 0; i < expoElements.size(); i++) {
			Element expoElement = expoElements.get(i);

			if (isTitleElement(expoElement)) {
				Elements lineElements = new Elements();
				Element nextLineElement = nextLineElementSibling(expoElement);
				while (nextLineElement != null) {
					i++;
					lineElements.add(nextLineElement);
					nextLineElement = nextLineElementSibling(nextLineElement);
				}

				NewsPaperCategory category = resolveNewsPapersCategory(
						expoElement, lineElements);
				area.addNewsPaperCategory(category);
			}
		}

		if (!area.isEmpty()) {
			resolveAreasNewsPapers(area.getChildren());
		}
	}

	private NewsPaperCategory resolveNewsPapersCategory(Element titleElement,
			Elements lineElements) throws SchemaReaderException {

		Element header = resolveTitleHeader(titleElement);
		if (header != null) {
			Elements anchorElements = header.getElementsByTag("a");
			if (anchorElements != null && anchorElements.size() == 1) {
				return resolveCategoryByAnchor(anchorElements.get(0));
			} else {
				return resolveCategoryByHeaderText(header, lineElements);
			}
		}

		return null;
	}

	private Element resolveTitleHeader(Element titleElement)
			throws SchemaReaderException {
		if (titleElement != null) {
			Elements headerElements = titleElement.getElementsByTag("h2");
			if (headerElements != null && headerElements.size() == 1) {
				return headerElements.get(0);
			} else {
				throw new SchemaReaderException(
						"Header element not found for :" + titleElement);
			}
		}
		return null;
	}

	private NewsPaperCategory resolveCategoryByHeaderText(Element header,
			Elements lineElements) throws SchemaReaderException {

		NewsPaperCategory category = null;

		if (header != null) {
			String label = header.text();
			NewsPaperCategoryId categoryId = NewsPaperCategoryId.byLabel(label);
			if (categoryId != null) {
				category = new NewsPaperCategory(categoryId);
				List<NewsPaper> newsPapers = resolveNewsPapersByLineElements(lineElements);
				category.setNewsPapers(newsPapers);
			}
		}
		return category;
	}

	private List<NewsPaper> resolveNewsPapersByLineElements(
			Elements lineElements) throws SchemaReaderException {
		if (lineElements != null) {
			Elements anchors = lineElements.select("a.thcover");
			return resolveNewsPaperByAnchorElements(anchors);
		}

		return null;
	}

	private List<NewsPaper> resolveNewsPaperByAnchorElements(Elements anchors)
			throws SchemaReaderException {
		List<NewsPaper> newsPapers = new ArrayList<NewsPaper>();
		if (anchors != null) {
			for (Element anchor : anchors) {
				NewsPaper newsPaper = resolveNewsPaperByAnchorElement(anchor);
				if (newsPaper != null) {
					newsPapers.add(newsPaper);
				}
			}
		}
		return newsPapers;
	}

	private NewsPaper resolveNewsPaperByAnchorElement(Element anchor) {
		if (anchor != null && anchor.children() != null
				&& anchor.children().size() > 0) {

			String id = resolveNewsPaperIdByHref(anchor.attr("href"));
			String name = resolveNewsPaperNameByImage(anchor.child(0));

			if (id != null && name != null) {
				return new NewsPaper(id, name);
			}
		}

		return null;
	}

	private String resolveNewsPaperIdByHref(String href) {
		if (href != null) {
			if (href.contains("/")) {
				String[] paths = href.split("/");
				href = paths[paths.length - 1];
			}
			return href.replace(".html", "");
		}
		return null;
	}

	private String resolveNewsPaperNameByImage(Element image) {
		if (image != null) {
			return image.attr("alt");
		}
		return null;
	}

	private NewsPaperCategory resolveCategoryByAnchor(Element anchor)
			throws SchemaReaderException {
		if (anchor != null) {
			String href = anchor.attr("href");
			String id = resolveCategoryIdByHref(href);
			NewsPaperCategoryId categoryId = NewsPaperCategoryId.byId(id);
			if (categoryId != null) {
				NewsPaperCategory category = new NewsPaperCategory(categoryId);
				List<NewsPaper> newsPapers = resolveNewsPapersByUrl(baseUri
						+ href);
				category.setNewsPapers(newsPapers);
				return category;
			} else {
				throw new SchemaReaderException("Can't resolve the category "
						+ id + "for href = " + href);
			}
		}
		return null;
	}

	private List<NewsPaper> resolveNewsPapersByUrl(String url)
			throws SchemaReaderException {
		Document document = resolveDocument(url);
		Elements anchors = document.select("a.thcover");
		return resolveNewsPaperByAnchorElements(anchors);
	}

	private String resolveCategoryIdByHref(String href) {
		if (href != null) {
			if (href.contains("/")) {
				String[] paths = href.split("/");
				href = paths[paths.length - 1];
			}
			return href.replace(".html", "");
		}
		return null;
	}

	private Element nextLineElementSibling(Element element) {
		if (element != null && isLineElement(element.nextElementSibling())) {
			return element.nextElementSibling();
		}
		return null;
	}

	private boolean isTitleElement(Element element) {
		return element != null && "titPpal".equals(element.className());
	}

	private boolean isLineElement(Element element) {
		return element != null && "line".equals(element.className());
	}

}
