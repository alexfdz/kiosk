package com.a2devel.kisok.runner;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.a2devel.kisok.adapter.AreasSchemaAdapter;
import com.a2devel.kisok.adapter.HttpDocumentAdapter;
import com.a2devel.kisok.adapter.KioskSchemaAdapter;
import com.a2devel.kisok.adapter.NewsPapersSchemaAdapter;
import com.a2devel.kisok.adapter.SchemaAdapter;
import com.a2devel.kisok.exception.SchemaReaderException;
import com.a2devel.kisok.model.Kiosk;
import com.a2devel.kisok.schema.JsoupSchemaReader;

public class SchemaRunner {

	public static void main(String[] args) throws SchemaReaderException,
			JAXBException {
		new SchemaRunner().execute(args[0], args[1]);
	}

	public void execute(String url, String outputFile)
			throws SchemaReaderException, JAXBException {
		JsoupSchemaReader reader = new JsoupSchemaReader(url);
		reader.setAdapter(resolveAdapter());

		Kiosk kiosk = reader.resolveKiosk();

		if (kiosk != null) {

			System.out.println("Kiosk generated");
			System.out.println("\tAreas:" + kiosk.areasCount());
			System.out.println("\tCategories:" + kiosk.categoriesCount());
			System.out.println("\tNews papers:" + kiosk.newsPapersCount());
			System.out.println("\tEmpty areas:" + kiosk.emptyAreas());
			System.out.println("\n\n\n" + kiosk.toString());

			JAXBContext context = JAXBContext.newInstance(Kiosk.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			marshaller.marshal(kiosk, new File(outputFile));
		} else {
			throw new SchemaReaderException("Can't create the model");
		}

	}

	private SchemaAdapter resolveAdapter() {
		KioskSchemaAdapter adapter = new KioskSchemaAdapter();
		adapter.addAdapter(new AreasSchemaAdapter());
		adapter.addAdapter(new NewsPapersSchemaAdapter());
		adapter.setDocumentAdapter(new HttpDocumentAdapter());
		return adapter;
	}

}
