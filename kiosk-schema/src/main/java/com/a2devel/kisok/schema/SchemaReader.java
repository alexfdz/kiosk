package com.a2devel.kisok.schema;

import java.util.List;

import com.a2devel.kisok.exception.SchemaReaderException;
import com.a2devel.kisok.model.Area;

public interface SchemaReader {

	public List<Area> resolveSchema() throws SchemaReaderException;

}
