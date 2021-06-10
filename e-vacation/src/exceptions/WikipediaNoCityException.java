package exceptions;

public class WikipediaNoCityException extends Exception {
    private static final long serialVersionUID = 1L;
    private String cityName;
    static int numExceptions = 0;
    public WikipediaNoCityException(String in_cityName) {
        numExceptions++;
        this.cityName = in_cityName;
    }
    public String getMessage() {
        return "There is not such country with this name " + cityName + ".";
    }
}

































//TRAVELLER CLASS

/*
    public  City compare_cities(ArrayList<City> cities){
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

 */

/*
    public  City compare_cities(Set<Entry<String, City> > entrySet){
        ArrayList<Entry<String, City> > cities
                = new ArrayList<>(entrySet);
        City res=new City(cities.get(0).getValue());

        for(int k=0;k<cities.size();k++){
            //System.out.println(calculate_similarity(res));
            if( calculate_similarity(cities.get(k).getValue()) >= calculate_similarity(res) ) {
                res.setTerm_vectors(cities.get(k).getValue().getTerm_vectors());
                res.setGeodesic_vector((cities.get(k).getValue().getGeodesic_vector()));
                res.setName(cities.get(k).getValue().getName());
                //System.out.println(res.getName());
            }
        }
        return res ;
    }

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








