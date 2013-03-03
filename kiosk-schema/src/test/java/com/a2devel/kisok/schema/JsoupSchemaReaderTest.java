package com.a2devel.kisok.schema;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.a2devel.kisok.adapter.AreasSchemaAdapter;
import com.a2devel.kisok.adapter.FakeDocumentAdapter;
import com.a2devel.kisok.adapter.KioskSchemaAdapter;
import com.a2devel.kisok.adapter.NewsPapersSchemaAdapter;
import com.a2devel.kisok.adapter.SchemaAdapter;
import com.a2devel.kisok.exception.SchemaReaderException;
import com.a2devel.kisok.model.Area;
import com.a2devel.kisok.model.Kiosk;

public class JsoupSchemaReaderTest {

	static JsoupSchemaReader reader;
	static Kiosk kiosk;

	@BeforeClass
	public static void setup() throws SchemaReaderException {
		String url = "http://en.kiosko.net/";
		reader = new JsoupSchemaReader(url);
		reader.setAdapter(resolveAdapter());
		kiosk = reader.resolveKiosk();
	}

	private static SchemaAdapter resolveAdapter() {
		KioskSchemaAdapter adapter = new KioskSchemaAdapter();
		adapter.addAdapter(new AreasSchemaAdapter());
		adapter.addAdapter(new NewsPapersSchemaAdapter());
		adapter.setDocumentAdapter(new FakeDocumentAdapter());
		return adapter;
	}

	@Test
	public void readerMustNotBeNull() {
		assertThat(reader, notNullValue());
	}

	@Test
	public void kioskMustHaveAreas() {
		assertThat(kiosk.areasCount(), is(14));

	}

	@Test
	public void kioskMustHaveNewsCategories() {
		assertThat(kiosk.categoriesCount(), is(25));
	}

	@Test
	public void kioskMustHaveNewsPapers() {
		assertThat(kiosk.newsPapersCount(), is(167));
	}

	@Test
	public void givenACorrectUrlAreasMustNotBeEmpty() {

		assertThat(kiosk.getAreas(), notNullValue());
		assertThat(kiosk.getAreas().size(), not(0));
	}

	@Test
	public void allElementsMustBeValid() {
		assertThatAllNodesAreValid(kiosk.getAreas());
	}

	private void assertThatAllNodesAreValid(List<Area> areas) {
		if (areas != null) {
			for (Area area : areas) {
				assertNodeIsValid(area);
			}
		}
	}

	private void assertNodeIsValid(Area area) {
		if (area != null) {
			assertThat(area.isValid(), is(true));
		}
	}
}
