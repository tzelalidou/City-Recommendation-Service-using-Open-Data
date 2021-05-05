
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import exceptions.*;
import weather.OpenWeatherMap;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

//super
public abstract class Traveller implements Comparable<Traveller> {
    //grade for each element of the term_vectors(city)
    private int[] grade;
    //wikipedia data
    //geodesic terms of the local city that traveller choose to see
    private double[] local_geodesic;
    private int age;
    private String name;
    private long timestamp;
    private String visit;
    private static ArrayList<Traveller> travellers=new ArrayList<>();
    //constructor

    public Traveller(int [] grade, double[] local_geodesic, int age,String name) {
        this.grade=grade;
        this.local_geodesic = local_geodesic;
        this.age=age;
        this.name=name;
        visit="";
        setTimestamp(new Date().getTime());

    }
    public Traveller(){
        grade=new int[City.MAX_SIZE];
        local_geodesic=new double[2];
        age=0;
        visit="";
        name="";
        setTimestamp(new Date().getTime());
    }

    public Traveller(int[] grade,int age) {
        this.grade = grade;
        this.age=age;
        local_geodesic=new double[2];
        visit="";
        setTimestamp(new Date().getTime());
        name="";
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
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp)  {

        this.timestamp = timestamp; }

    public String getVisit() { return visit; }
    public void setVisit() {
        City c=compare_cities();
        this.visit = c.getName();
    }
    public static ArrayList<Traveller> getTravellers() { return travellers; }
    public static void setTravellers(ArrayList<Traveller> travellers) { Traveller.travellers = travellers; }

    public static void addTraveller(Traveller tr) { travellers.add(tr); }
    @Override
    public String toString(){
        return " [ name: "+name+", age: "+ age+" ,latitude: "+local_geodesic[0]+",longitude: "+local_geodesic[1]+",timestamp: "+timestamp+",recommended country: "+visit+
                ", grade: "+grade[0]+"]";
    }

    public void setLocal_geodesic(String hisCountry, String hisCountryAbbrev) throws IOException {
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
  
    //return the grade based on the traveller terms that he put
    //abstract methods
    public abstract double calculate_similarity(City aCity);
    public abstract double similarity_terms_vector(City city);

    public  City compare_cities(){
        //Collection<City> city_values=City.getAll_cities().values();
        //ArrayList<City> cities = new ArrayList<>(city_values);
        ArrayList<City> cities = new ArrayList<>(City.getAll_cities().values());
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
    public ArrayList<City> compare_cities(int x){
        Collection<City> city_values=City.getAll_cities().values();
        ArrayList<City> lotcities = new ArrayList<>(city_values);
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
    @Override
    public int compareTo(Traveller o) {
        if (this.timestamp > o.timestamp)
            return 1;
        else if (this.timestamp < o.timestamp)
            return -1;
        else
            return 0;
    }
    @Override
    public boolean equals(Object o){
        Traveller tr=(Traveller) o;
        if(this==o){

            return true;
        }
        if(o==null){
            return false;
        }

        if((name).equals(tr.getName())){

            return true;
        }
        else{

            return false;
        }
    }
   
        public static ArrayList<Traveller> no_duplicate_arraylist_travellers() {
            ArrayList<Traveller> no_duplic_trav=new ArrayList<>();
            for(Traveller traveller: getTravellers()) {
                if (!no_duplic_trav.contains(traveller)) {
                    no_duplic_trav.add(traveller);
                }
            }
           return no_duplic_trav;
    }
    public static void initialize_travellers_from_file()   {
        FileReader tester1 = new FileReader();
        try {
            //We create and store in the file abs_arraylist.json objects from the classes Young, Middle and Elder that extend the abstract class Traveller.
            //i read when program starts and write before exit the program !!
            Traveller.setTravellers(tester1.readJSON());	//We read from the file abs_arraylist.json the objects from the classes YoungStudents, MiddleStudents and ElderStudents that extend the abstract class Student
        }
        catch (FileNotFoundException e) {
            try{
                ArrayList<Traveller> empty=new ArrayList<>();
                tester1.writeJSON(empty);
            }
            catch (JsonParseException ex) {
                e.printStackTrace();
            } catch (JsonMappingException ex) {
                e.printStackTrace();
            } catch (IOException ex) {
                e.printStackTrace();
            }
        }
        catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write_travellers_in_file(){
        FileReader tester1 = new FileReader();
        try {
            tester1.writeJSON(Traveller.getTravellers());
        }
        catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
