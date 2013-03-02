package com.a2devel.kisok.schema;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.a2devel.kisok.exception.SchemaReaderException;
import com.a2devel.kisok.model.Area;

public class JsoupSchemaReaderTest {

	static JsoupSchemaReader reader;
	static List<Area> areas;

	@BeforeClass
	public static void setup() throws SchemaReaderException {
		String url = "http://en.kiosko.net/";
		reader = new JsoupSchemaReader(url);
		areas = reader.resolveSchema();

		for (Area area : areas) {
			System.out.println(area.toString());
		}
	}

	@Test
	public void readerMustNotBeNull() {
		assertThat(reader, notNullValue());
	}

	@Test
	public void givenACorrectUrlAreasMustNotBeEmpty() {

		assertThat(areas, notNullValue());
		assertThat(areas.size(), not(0));
	}

	@Test
	public void allElementsMustBeValid() {
		assertThatAllNodesAreValid(areas);
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
