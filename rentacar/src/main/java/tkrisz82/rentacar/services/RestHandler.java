package tkrisz82.rentacar.services;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.springframework.web.client.RestTemplate;



public class RestHandler {

public double getCurrentRate() throws JDOMException, IOException {
		
		double eur = 0;
		
		RestTemplate rt = new RestTemplate();
		
		String xml = rt.getForObject("http://api.napiarfolyam.hu/?deviza=eur", String.class);
		
		XMLParser xmlP = new XMLParser();
		
		eur = xmlP.parser(xml);
		
		return eur;
	}
	
	
}
