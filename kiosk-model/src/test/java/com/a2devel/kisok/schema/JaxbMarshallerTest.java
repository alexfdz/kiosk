package com.a2devel.kisok.schema;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;

import com.a2devel.kisok.model.Area;
import com.a2devel.kisok.model.Kiosk;
import com.a2devel.kisok.model.NewsPaper;
import com.a2devel.kisok.model.NewsPaperCategory;

public class JaxbMarshallerTest {

	private Kiosk kiosk;

	@Before
	public void setup() {

		Area es = Area.create("es");
		Area fr = Area.create("fr");
		Area en = Area.create("en");
		Area eur = Area.create("eur");
		eur.addArea(es);
		eur.addArea(fr);
		eur.addArea(en);

		NewsPaperCategory categoryEsGen = NewsPaperCategory.create("general");
		NewsPaperCategory categoryEsSports = NewsPaperCategory.create("sports");
		NewsPaperCategory categoryFrGen = NewsPaperCategory.create("general");
		NewsPaperCategory categoryEnGen = NewsPaperCategory.create("general");
		NewsPaperCategory categoryEnEconomy = NewsPaperCategory
				.create("economy");
		es.addNewsPaperCategory(categoryEsGen);
		es.addNewsPaperCategory(categoryEsSports);
		fr.addNewsPaperCategory(categoryFrGen);
		en.addNewsPaperCategory(categoryEnGen);
		en.addNewsPaperCategory(categoryEnEconomy);

		NewsPaper paper1 = NewsPaper.create("1", "1");
		NewsPaper paper2 = NewsPaper.create("2", "2");
		NewsPaper paper3 = NewsPaper.create("3", "3");
		NewsPaper paper4 = NewsPaper.create("4", "4");
		NewsPaper paper5 = NewsPaper.create("5", "5");
		NewsPaper paper6 = NewsPaper.create("6", "6");
		NewsPaper paper7 = NewsPaper.create("7", "7");
		NewsPaper paper8 = NewsPaper.create("8", "8");
		NewsPaper paper9 = NewsPaper.create("9", "9");
		NewsPaper paper10 = NewsPaper.create("10", "10");

		categoryEsGen.addNewsPaper(paper1);
		categoryEsGen.addNewsPaper(paper2);
		categoryEsSports.addNewsPaper(paper3);
		categoryFrGen.addNewsPaper(paper4);
		categoryFrGen.addNewsPaper(paper5);
		categoryEnGen.addNewsPaper(paper6);
		categoryEnGen.addNewsPaper(paper7);
		categoryEnGen.addNewsPaper(paper8);
		categoryEnEconomy.addNewsPaper(paper9);
		categoryEnEconomy.addNewsPaper(paper10);

		kiosk = new Kiosk();

		kiosk.addArea(eur);

	}

	@Test
	public void testMarshalOperation() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Kiosk.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		StringWriter writer = new StringWriter();
		m.marshal(kiosk, writer);

		assertThat(writer.toString().length(), not(0));
	}

	@Test
	public void testUnMarshalOperation() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Kiosk.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		StringWriter writer = new StringWriter();
		m.marshal(kiosk, writer);

		Unmarshaller um = context.createUnmarshaller();
		Kiosk readedKiosk = (Kiosk) um.unmarshal(new StringReader(writer
				.toString()));

		assertThat(readedKiosk.getAreas().size(), equalTo(kiosk.getAreas()
				.size()));
		assertThat(readedKiosk.getAreas().get(0).getAreas().get(0)
				.getNewsPapersCategories().size(), equalTo(kiosk.getAreas()
				.get(0).getAreas().get(0).getNewsPapersCategories().size()));
	}
}
