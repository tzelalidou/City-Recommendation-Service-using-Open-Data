
import exceptions.*;
import weather.OpenWeatherMap;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

//super
public abstract class Traveller   {
    //grade for each element of the term_vectors(city)
    private int[] grade=new int[10];
    //wikipedia data
    //geodesic terms of the local city that traveller choose to see
    private double[] local_geodesic= new double[2];
    private int age;


    //constructor

    public Traveller(int [] grade, double[] local_geodesic, int age) {
        this.grade=grade;
        this.local_geodesic = local_geodesic;
        this.age=age;
    }
    public Traveller(){
        grade=new int[City.MAX_SIZE];
        local_geodesic=new double[2];
        age=0;
    }

    public Traveller(int[] grade,int age) {
        this.grade = grade;
        this.age=age;
        local_geodesic=new double[2];
    }

    //getters setters
    public double[] getLocal_geodesic() { return local_geodesic; }
    public double getLocal_geodesic(int index) { return local_geodesic[index]; }
    public void setLocal_geodesic(double[] local_geodesic) {
        this.local_geodesic = local_geodesic; }
    public void setLocal_geodesic(int index, double value) { this.local_geodesic[index] = value; }
    public int[] getGrade() { return grade; }
    public int getGrade(int index) { return grade[index]; }
    public void setGrade(int index,int grade) { this.grade[index] = grade; }
    public void setGrade(int[] grade) { this.grade = grade; }
    public void setLocal_geodesic(String hisCountry,String hisCountryAbbrev) throws IOException {
        OpenWeatherMap weather_object = OpenData.RetrieveData(hisCountry, hisCountryAbbrev, "8a6696995628d4ee57726b05d30030d4");
        double [] ar={weather_object.getCoord().getLat(),weather_object.getCoord().getLon()};
        this.setLocal_geodesic(ar);
        //this.setLocal_geodesic(0, weather_object.getCoord().getLat());
        //this.setLocal_geodesic(1, weather_object.getCoord().getLon());

    }
    public int getAge() { return age;}
    //throw age_exception
    public void setAge(int age) {
        Scanner age_scanner = new Scanner(System.in);
        while(true) {
            try {
                if (age < 16 || age> 115) {
                    throw new AgeException();
                }else{
                    this.age=age;
                    break;
                }
            } catch (AgeException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Please enter a new age");
                age = age_scanner.nextInt();
            }

        }
    }
    //takes the lat lon from open data


    /*
    public Traveller make_obj_Traveller(int ageoftraveller) throws AgeException {
        handle_ageException(ageoftraveller);
        if(ageoftraveller>=16 || ageoftraveller<=25){
                Traveller young=new YoungTraveller();
                return young;
        }
        if(ageoftraveller>25 || ageoftraveller<=60){
            Traveller middle=new MiddleTraveller();
            return middle;
        }
        if(ageoftraveller>60 || ageoftraveller<=115){
            Traveller elder=new ElderTraveller();
            return elder;
        }
        return null;
    }


     */

    //return the grade based on the traveller terms that he put
    //abstract methods
    public abstract double calculate_similarity(City aCity);
    public abstract double similarity_terms_vector(City city);

    public  City compare_cities(ArrayList<City> cities){
        //copy an object to another
        City res=new City(cities.get(0));

        for(int k=0;k<cities.size();k++){
            //System.out.println(calculate_similarity(res));
            if( calculate_similarity(cities.get(k)) >= calculate_similarity(res) ) {
                res.setTerm_vectors(cities.get(k).getTerm_vectors());
                res.setGeodesic_vector((cities.get(k).getGeodesic_vector()));
                res.setName(cities.get(k).getName());
                //System.out.println(res.getName());
            }
        }
        return res ;
    }

    //public abstract City compare_cities(int x);
    public ArrayList<City> compare_cities(int x, ArrayList<City> lotcities){
        //check x must be [2,5]
        if(x<=2){
            System.out.println("We can show you 2 to 5 cities");
            System.out.println("You request to compare less than 2 cities so we show you minimum 2 cities");
            x=2;
        }else{
            if(x>=5){
                System.out.println("We can show you 2 to 5 cities");
                System.out.println("You request to compare more than 5 cities so we show you maximum 5 cities");
                x=5;
            }
        }
        Collections.sort(lotcities);
        //check if object is null
        //delete the new array after a period of time because chance of crashing or delay
        ArrayList<City> cities_with_max_sim=new ArrayList<>();
            try {
                for(int i=0;i<x;i++) {
                    cities_with_max_sim.add(lotcities.get(i));
                }
            }catch (IndexOutOfBoundsException exception){
                System.out.println("You requested more results-> "+x+" than the existing cities-> "+lotcities.size()+" you choose to compare");
                System.out.println("So we gave you all the "+lotcities.size()+" cities that exist in our system");
                return lotcities;
            }
        return cities_with_max_sim;

    }
    public  double  similarity_geodesic_vector(City city){
        double max_dist=15326d;
        //lat1,lon1,lat1,lot2
        double dis=distance(this.getLocal_geodesic(0),this.getLocal_geodesic(1),city.getGeodesic_vector(0),city.getGeodesic_vector(1),"K");
        return log2(2/(2-(dis/max_dist)));
    }
    private   static double log2(double v) {
        return Math.log(v) / Math.log(2);
    }
    //distance between two cities(geodesic vector)
    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }



}
