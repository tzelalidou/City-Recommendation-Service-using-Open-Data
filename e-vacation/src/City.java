import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.WikipediaNoArcticleException;
import weather.OpenWeatherMap;
import wikipedia.MediaWiki;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class City implements Comparable<City> {
    //attributes
    //sea,,museum,University,stadium,Church,theater,park,Art,fashion,Bridge
    private int  [] term_vectors;
    //latitude,longitude
    protected static final int MAX_SIZE = 10;
    private double[] geodesic_vector;
    private String name;
    private String country_abbrev;
    private double similarity;
    private final String  [] criterion_wiki={"Sea","Museum","University","Stadium","Church","Theater","park","Art","fashion","Bridge"};

    //Constructors

    public City(City c){
        this.name=c.name;
        this.geodesic_vector=c.geodesic_vector;
        this.term_vectors=c.term_vectors;
        this.country_abbrev=c.country_abbrev;
    }

    public City(int[] term_vectors, double[] geodesic_vector,String name,String country_abbrev) {
        this.term_vectors = term_vectors;
        this.geodesic_vector=geodesic_vector;
        this.name=name;
        this.country_abbrev = country_abbrev;
    }
    public City(String name,String country_abbrev) {
        this.name=name;
        this.country_abbrev = country_abbrev;
    }
    public City() {
        term_vectors=new int[MAX_SIZE];
        geodesic_vector=new double[2];
        name="";
        country_abbrev ="";

    }
    public City(int[] term_vectors) { this.term_vectors = term_vectors; }
    public City(double[] geodesic_vector) { this.geodesic_vector=geodesic_vector; }
    //getters setters
    public double[] getGeodesic_vector(){ return geodesic_vector; }
    public double getGeodesic_vector(int index) { return geodesic_vector[index]; }
    public void setGeodesic_vector(double[] geodesic_vector) { this.geodesic_vector = geodesic_vector; }
    public void setGeodesic_vector(int index, double value) { this.geodesic_vector[index] = value; }
    public int[] getTerm_vectors() { return term_vectors; }
    public int getTerm_vectors(int index) { return term_vectors[index]; }
    public void setTerm_vectors(int index, int value) { this.term_vectors[index]=value; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public void setTerm_vectors(int[] term_vectors) { this.term_vectors = term_vectors; }
    public String getCountry_abbrev() { return country_abbrev; }
    public void setCountry_abbrev(String country_abbrev) { this.country_abbrev = country_abbrev; }

    //public double getSimilarity() { return similarity; }
    //similarity for ac  city about a traveller for compare cities
    //i put parameter traveller for my facilitation
    public void setSimilarity(double similarity,Traveller t) { this.similarity = similarity; }

    @Override
    public int compareTo(City o)  {
        if(this.similarity>o.similarity)
            return -1;
        else if(this.similarity<o.similarity)
            return 1;
        else
            return 0;
    }
    public Traveller free_ticket(ArrayList<Traveller> travellers) {
        double max=0;
        Traveller x=travellers.get(0);
        for(int i=0;i<travellers.size();i++){
            if(travellers.get(i).calculate_similarity(this) >= max){
                max=travellers.get(i).calculate_similarity(this);
                x=travellers.get(i);
            }
        }
        return x;
    }
    public void setCityFromOpenData() throws IOException {
        OpenWeatherMap weather_object;
        String wiki_article;

        try {
            wiki_article = OpenData.RetrieveData(this.getName());
            weather_object = OpenData.RetrieveData(this.getName(), this.getCountry_abbrev(), "8a6696995628d4ee57726b05d30030d4");
            double []temp_arr={ weather_object.getCoord().getLat(),weather_object.getCoord().getLon()};
            this.setGeodesic_vector(temp_arr);

            //this.setGeodesic_vector(0, weather_object.getCoord().getLat());
            //this.setGeodesic_vector(1, weather_object.getCoord().getLon());


        } catch (WikipediaNoArcticleException e) {
            System.out.println(e.getMessage());
            System.out.println("We will choose Rome for you");
            ObjectMapper mapper = new ObjectMapper();
            MediaWiki mediaWiki_obj = mapper.readValue(new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&titles=" + "Rome" + "&format=json&formatversion=2"), MediaWiki.class);
            wiki_article=mediaWiki_obj.getQuery().getPages().get(0).getExtract();
            weather_object = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q=" + "Rome" + "," + "it" + "&APPID=" + "8a6696995628d4ee57726b05d30030d4" + ""), OpenWeatherMap.class);
            double []temp_arr={ weather_object.getCoord().getLat(),weather_object.getCoord().getLon()};
            this.setGeodesic_vector(temp_arr);
            //this.setGeodesic_vector(0, weather_object.getCoord().getLat());
            //this.setGeodesic_vector(1, weather_object.getCoord().getLon());
            //System.out.println("This is the article of Rome"+wiki_article);

        }
        for (int i = 0; i < term_vectors.length; i++)  {
            int wordsOfCriterion = CountWords.countCriterionfCity(wiki_article, criterion_wiki[i]);
            setTerm_vectors(i, wordsOfCriterion);
        }

    }
}
