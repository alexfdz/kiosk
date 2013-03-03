package com.a2devel.kisok.runner;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.a2devel.kisok.exception.SchemaReaderException;
import com.a2devel.kisok.model.Kiosk;
import com.a2devel.kisok.schema.JsoupSchemaReader;

public class SchemaRunner {

	public static void main(String[] args) throws SchemaReaderException,
			JAXBException {
		String url = args[0];
		String outputFile = args[1];

		JsoupSchemaReader reader = new JsoupSchemaReader(url);
		Kiosk kiosk = reader.resolveKiosk();

		if (kiosk != null) {
			JAXBContext context = JAXBContext.newInstance(Kiosk.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			marshaller.marshal(kiosk, new File(outputFile));
		} else {
			throw new SchemaReaderException("Can't create the model");
		}
	}

}
