package com.a2devel.kisok.schema;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.a2devel.kisok.exception.SchemaReaderException;
import com.a2devel.kisok.model.Area;
import com.a2devel.kisok.model.Node;

public class JsoupSchemaReaderTest {

	JsoupSchemaReader reader;
	List<Area> areas;

	@Before
	public void setup() throws SchemaReaderException {
		String url = "http://kiosko.net/";
		reader = new JsoupSchemaReader(url);
		areas = reader.resolveSchema();
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
	public void allAreasMustHaveId() {
		assertThatAllNodesHaveId(areas);
	}

	private void assertThatAllNodesHaveId(List<? extends Node> nodes) {
		if (areas != null) {
			for (Area area : areas) {
				assertThat(area.getId(), notNullValue());
				assertThatAllNodesHaveId(area.getChildren());
			}
		}

	}

}
