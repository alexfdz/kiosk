package com.a2devel.kisok.schema;

import com.a2devel.kisok.exception.SchemaReaderException;
import com.a2devel.kisok.model.Kiosk;

public interface SchemaReader {

	public Kiosk resolveKiosk() throws SchemaReaderException;

}
