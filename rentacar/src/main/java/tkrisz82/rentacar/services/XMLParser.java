package tkrisz82.rentacar.services;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XMLParser {
	
	public Double parser(String xml) throws JDOMException, IOException {

		Double eur = 0.0;

		SAXBuilder sb = new SAXBuilder();

		StringReader sReader = new StringReader(xml);

		Document doc = sb.build(sReader);

		Element rootElement = doc.getRootElement();

		Element valutaElement = rootElement.getChild("deviza");

		List<Element> itemElements = valutaElement.getChildren("item");

		for (int i = 0; i < itemElements.size(); i++) {

			Element itemElement = itemElements.get(i);

			Element bankElement = itemElement.getChild("bank");

			String bankForCheck = bankElement.getValue();

			String penznemForCheck = itemElement.getChild("penznem").getValue();

			if (bankForCheck.equals("mnb") && penznemForCheck.equals("EUR")) {
				eur = Double.parseDouble(itemElement.getChild("kozep").getValue());
				break;
			}
		}

		return eur;

	}
	
}
