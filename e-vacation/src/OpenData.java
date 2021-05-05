import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.WikipediaNoArcticleException;
import weather.OpenWeatherMap;
import wikipedia.MediaWiki;

import java.io.IOException;
import java.net.URL;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**City description and weather information using OpenData with Jackson JSON processor.
* @since 29-2-2020
* @version 1.0
* @author John Violos */
public class OpenData {

	/**
	 * Retrieves weather information, geotag (lan, lon) and a Wikipedia article for a given city.
	 *
	 * @param city    The Wikipedia article and OpenWeatherMap city.
	 * @param country The country initials (i.e. gr, it, de).
	 * @param appid   Your API key of the OpenWeatherMap.
	 */
	public static OpenWeatherMap RetrieveData(String city, String country, String appid) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		OpenWeatherMap weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + country + "&APPID=" + appid + ""), OpenWeatherMap.class);
		//System.out.println(city + " lat: " + weather_obj.getCoord().getLat() + " lon: " + weather_obj.getCoord().getLon());
		return weather_obj;
	}

	public static String RetrieveData(String city) throws IOException, WikipediaNoArcticleException {
		/*
		ObjectMapper mapper = new ObjectMapper();
		MediaWiki mediaWiki_obj = mapper.readValue(new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&titles=" + city + "&format=json&formatversion=2"), MediaWiki.class);
		///System.out.println(city + " Wikipedia article: " + mediaWiki_obj.getQuery().getPages().get(0).getExtract());
		return mediaWiki_obj;

		 */
		String article="";
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(UriBuilder.fromUri("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&titles="+city+"&format=json&formatversion=2").build());
		ObjectMapper mapper = new ObjectMapper();
		String json= service.accept(MediaType.APPLICATION_JSON).get(String.class);
		if (json.contains("pageid")) {
			MediaWiki mediaWiki_obj =  mapper.readValue(json, MediaWiki.class);
			article= mediaWiki_obj.getQuery().getPages().get(0).getExtract();
			//System.out.println(city+" Wikipedia article: "+article);
		} else throw new WikipediaNoArcticleException(city);
		return article;
	}
}
/*public static void main(String[] args) throws IOException {
	String appid ="8a6696995628d4ee57726b05d30030d4";
	RetrieveData("Rome","it",appid);	
	RetrieveData("Athens","gr",appid);
	RetrieveData("Corfu","gr",appid);	
	RetrieveData("Berlin","de",appid);
}


 */
