
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class VacationApp {
    public static void main(String[] args) throws IOException, NullPointerException, InterruptedException, SQLException {
        System.out.println("Waiting for results...");
        boolean x=Traveller.getTravellers().isEmpty();
        if(x){
            System.out.println("File is empty");
        }
        Traveller.initialize_travellers_from_file(); //read from file at first empty
        System.out.println("Elements of file");
        for (Traveller item : Traveller.getTravellers()) {
            System.out.println(item.getName()+" with recommended country to be : "+item.getVisit());
        }
        int[] grade = {0, 4, 2, 0, 2, 3, 4, 7, 3, 1};
        int[] grade2 = {9, 1, 3, 4, 1, 7, 0, 0, 8, 9};

        City athens = new City( "Athens","gr");
        athens.setCityFromOpenData();

        City city2 = new City( "Sydney","au");
        city2.setCityFromOpenData();

        City city3 = new City( "Rome","it");
        city3.setCityFromOpenData();
        //System.out.println(city3.getGeodesic_vector(0));

        City city4 = new City( "Tokyo","jp");
        city4.setCityFromOpenData();

        City city5 = new City("Corfu","gr");
        city5.setCityFromOpenData();

        City city6 = new City( "Budapest","hu");
        city6.setCityFromOpenData();
        /*
        ASSUMPTIONS
        young0 lives in Sydney(takes the lat lon from open data)
        young1 lives in rome(takes the lat lon from open data)
        middle0 lives in tokyo(takes the lat lon from open data)
        elder0 lives in corfu(takes the lat lon from open data)
        elder1 lives in corfu( takes lat lon from open data data)
        */

        //when the program starts i reed from the database and then i add in hash
        DataBase.makeJDBCConnection();
        DataBase.ReadData();

        Set<Map.Entry<String, City>> city_set=City.getAll_cities().entrySet();
        Iterator<?> i = city_set.iterator();
        while(i.hasNext()) { // We iterate and display Entries (nodes) one by one.
            @SuppressWarnings("rawtypes")
            Map.Entry me = (Map.Entry)i.next();
            System.out.print("name: "+((City)me.getValue()).getName() + ". ");
            System.out.println("terms: "+((City)me.getValue()).getTerm_vectors(0) + ". ");
        }


        YoungTraveller young0 = new YoungTraveller(grade, 16);
        young0.setLocal_geodesic("Sydney", "au");
        young0.setVisit();
        YoungTraveller young1 = new YoungTraveller(grade2, 19);
        young1.setLocal_geodesic("Rome", "it");
        young1.setVisit();
        MiddleTraveller middle0 = new MiddleTraveller(grade, 35);
        middle0.setLocal_geodesic("Tokyo", "jp");
        middle0.setVisit();
        ElderTraveller elder0=new ElderTraveller(grade2,82);
        elder0.setLocal_geodesic("Corfu", "gr");
        elder0.setVisit();

        //this is a city that is new there isn't in my sql data base
        City.put_new_city_in_map("Tokyo","jp");
        Set<Map.Entry<String, City>> c=City.getAll_cities().entrySet();
        Iterator<?> a = c.iterator();
        while(a.hasNext()) { // We iterate and display Entries (nodes) one by one.
            @SuppressWarnings("rawtypes")
            Map.Entry me = (Map.Entry)a.next();
            System.out.print("name: "+((City)me.getValue()).getName() + ". ");
            System.out.println("terms: "+((City)me.getValue()).getTerm_vectors(0) + ". ");
        }


        System.out.println("now lets see about rome again");
        City.put_new_city_in_map(city3.getName(),city3.getCountry_abbrev());
        Set<Map.Entry<String, City>> set=City.getAll_cities().entrySet();
        Iterator<?> w = set.iterator();
        while(w.hasNext()) { // We iterate and display Entries (nodes) one by one.

            @SuppressWarnings("rawtypes")
            Map.Entry me = (Map.Entry)w.next();
            System.out.print("name: "+((City)me.getValue()).getName() + ". ");
            System.out.println("terms: "+((City)me.getValue()).getTerm_vectors(0) + ". ");
        }



        //CHECK FOR DUPLICATES
        young0.setName("Maria");

        young1.setName("Eleni");

        middle0.setName("Angela");

        elder0.setName("Demetra");

        MiddleTraveller middle1=new MiddleTraveller(grade2,41); //different grade,age
        middle1.setName("Angela");
        middle1.setVisit();
        middle1.setLocal_geodesic("Rome","it");
        Thread.sleep(1);
        MiddleTraveller middle2=new MiddleTraveller(grade,53); //different age
        middle2.setName("Eleni");
        middle2.setLocal_geodesic("Paris","fr");
        middle2.setVisit();

        Thread.sleep(1);
        ElderTraveller elder2=new ElderTraveller(grade,67); //different age
        elder2.setName("Angela");
            elder2.setLocal_geodesic("Naples","it");
        Thread.sleep(1);
        elder2.setVisit();

        ElderTraveller elder3=new ElderTraveller(grade,90); //different age
        elder3.setName("Kwstas");
        elder3.setVisit();
        elder3.setLocal_geodesic("Rome","it");
        Thread.sleep(1);
        YoungTraveller young3=new YoungTraveller(grade,17); //different age
        young3.setName("Kwstas");
        young3.setVisit();
        young3.setLocal_geodesic("Tokyo","jp");
        Thread.sleep(1);
        YoungTraveller young2=new YoungTraveller(grade2,17); //different age
        young2.setName("Demetra");
        young2.setVisit();
        young2.setLocal_geodesic("Athens","gr");
        Thread.sleep(1);

/*
        //ALREADY IN FILE

        Traveller.addTraveller(young0);
        Traveller.addTraveller(young1);
        Traveller.addTraveller(middle0);
        Traveller.addTraveller(elder0);
        Traveller.addTraveller(middle1);
        Traveller.addTraveller(middle2);
        Traveller.addTraveller(elder2);
        Traveller.addTraveller(elder3);
        Traveller.addTraveller(young2);
        Traveller.addTraveller(young3);
 */
        System.out.println("no sorted ");

        for (Traveller item : Traveller.getTravellers()) {
            System.out.println(item.getName());

        }
         //sorted by timelapse
        System.out.println("now sorted list by timestamp");
        Collections.sort(Traveller.getTravellers());
        for (Traveller item : Traveller.getTravellers()) {
            System.out.println(item.getName()+" with timestamp: "+item.getTimestamp());
        }

        System.out.println("now a list without duplicates");
        ArrayList<Traveller> unique_trav=Traveller.no_duplicate_arraylist_travellers();
        for (Traveller item : unique_trav) {
            System.out.println(item.getName() + " with age " + item.getAge());
        }

        //WHEN IN THE GUI WE PUSS THE BUTTON EXIT THEN WE WRITE IN FILE THE TRAVELLERS
        Traveller.write_travellers_in_file();

    }

}


