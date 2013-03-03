package com.a2devel.kisok.adapter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.a2devel.kisok.exception.SchemaReaderException;
import com.a2devel.kisok.model.Area;
import com.a2devel.kisok.model.Kiosk;

public class AreasSchemaAdapterTest {

	private static List<Area> areas;

	@BeforeClass
	public static void setup() throws SchemaReaderException {
		DocumentAdapter documentAdapter = new FakeDocumentAdapter();
		AreasSchemaAdapter adapter = new AreasSchemaAdapter();
		adapter.setDocumentAdapter(documentAdapter);
		String url = "http://en.kiosko.net/";
		areas = adapter.adapt(null, documentAdapter.adapt(url));
	}

	@Test
	public void kioskMustHaveAreas() {
		Kiosk kiosk = new Kiosk();
		kiosk.setAreas(areas);
		assertThat(kiosk.areasCount(), is(14));
	}

}
