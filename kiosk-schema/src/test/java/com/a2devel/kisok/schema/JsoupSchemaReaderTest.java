package com.a2devel.kisok.schema;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.a2devel.kisok.exception.SchemaReaderException;
import com.a2devel.kisok.model.Area;
import com.a2devel.kisok.model.Kiosk;

@Ignore
public class JsoupSchemaReaderTest {

	static JsoupSchemaReader reader;
	static Kiosk kiosk;

	@BeforeClass
	public static void setup() throws SchemaReaderException {
		String url = "http://en.kiosko.net/";
		reader = new JsoupSchemaReader(url);
		kiosk = reader.resolveKiosk();
	}

	@Test
	public void readerMustNotBeNull() {
		assertThat(reader, notNullValue());
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
