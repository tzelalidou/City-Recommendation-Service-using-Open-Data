
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class VacationApp {
    public static void main(String[] args) throws IOException, NullPointerException {

        int[] wiki_result = {0, 2, 11, 7, 8, 9, 1, 3, 22, 10};
        int[] grade = {0, 4, 2, 0, 2, 3, 4, 7, 3, 1};
        int[] grade2 = {9, 1, 3, 4, 1, 7, 0, 0, 8, 9};

        double[] athens = {37.979179, 23.716646};
        double[] sidnei = {-33.867138, 151.207108};
        double[] rome = {41.902782, 12.496366};
        double[] tokio = {39.758602, -104.997437};
        double[] corfu = {39.074207, 21.824312};
        double[] budapest = {36.089275, -95.903687};

        City city = new City(wiki_result, athens, "Athens","gr");
        City city2 = new City(wiki_result, sidnei, "Sydney","au");
        City city3 = new City(wiki_result, rome, "Rome","it");
        City city4 = new City(wiki_result, tokio, "Tokyo","jp");
        City city5 = new City(wiki_result, corfu, "Corfu","gr");
        City city6 = new City(wiki_result, budapest, "Budapest","hu");
        ArrayList<City> cities = new ArrayList<>();
        cities.add(city);
        cities.add(city2);
        cities.add(city3);
        cities.add(city4);
        cities.add(city5);
        cities.add(city6);
        //ASSUPTIONS
        //young0 lives in Sydney(takes the lat lon from open data)
        //young1 lives in rome(takes the lat lon from open data)
        //middle0 lives in tokyo(takes the lat lon from open data)
        //elder0 lives in corfu(takes the lat lon from open data)
        //elder1 lives in corfu( takes lat lon from open data data)
        YoungTraveller young0 = new YoungTraveller(grade, 13);
        young0.setLocal_geodesic("Sydney", "au");
        System.out.println(young0.getLocal_geodesic(0));
        YoungTraveller young1 = new YoungTraveller(grade2, 19);
        young1.setLocal_geodesic("Rome", "it");
        System.out.println(young1.getLocal_geodesic(0));
        MiddleTraveller middle0 = new MiddleTraveller(grade, 35);
        middle0.setLocal_geodesic("Tokyo", "jp");
        System.out.println(middle0.getLocal_geodesic(0));
        ElderTraveller elder0=new ElderTraveller(grade2,82);
        elder0.setLocal_geodesic("Corfu", "gr");
        System.out.println(elder0.getLocal_geodesic(0));

        ElderTraveller elder1 = new ElderTraveller();
        elder1.setLocal_geodesic("Crete","gr");
        elder1.setGrade(grade2);
        System.out.println(elder1.getLocal_geodesic(0));

        System.out.println("Check the exception for age range [16,115]");


        System.out.println("Enter your age");
        Scanner scanner=new Scanner(System.in);
        int a =scanner.nextInt();


        elder1.setAge(a); //handling exception in setter
        System.out.println("Your age:"+elder1.getAge()+" is accepted");
        System.out.println("Continue...");


        System.out.println("Similarity of young0, athens: "+young0.calculate_similarity(city));
        System.out.println("Similarity of young1, athens: "+young1.calculate_similarity(city));
        System.out.println("Similarity of middle0, athens: "+middle0.calculate_similarity(city));
        System.out.println("Similarity of elder0, athens: "+elder0.calculate_similarity(city));



        System.out.println("Similarity of elder0,athens: " + elder0.calculate_similarity(city));
        city.setSimilarity(elder0.calculate_similarity(city), elder0);
        System.out.println("Similarity of elder0,sideni: " + elder0.calculate_similarity(city2));
        city2.setSimilarity(elder0.calculate_similarity(city2), elder0);
        System.out.println("Similarity of elder0,rome: " + elder0.calculate_similarity(city3));
        city3.setSimilarity(elder0.calculate_similarity(city3), elder0);
        System.out.println("Similarity of elder0,Tokyo: " + elder0.calculate_similarity(city4));
        city4.setSimilarity(elder0.calculate_similarity(city4), elder0);
        System.out.println("Similarity of elder0,corfu " + elder0.calculate_similarity(city5));
        city5.setSimilarity(elder0.calculate_similarity(city5), elder0);
        System.out.println("Similarity of elder0: budapest " + elder0.calculate_similarity(city6));
        city6.setSimilarity(elder0.calculate_similarity(city6), elder0);



        System.out.println("Name of city with max similarity for elder0: " + elder0.compare_cities(cities).getName());
        System.out.println("LAT of city with max similarity for elder0: " + elder0.compare_cities(cities).getGeodesic_vector(0));
        System.out.println("LON of city with max similarity for elder0: " + elder0.compare_cities(cities).getGeodesic_vector(1));
        System.out.println("The critirion museum appeared " + elder0.compare_cities(cities).getTerm_vectors(1)+"times in the article for the city "
                +elder0.compare_cities(cities).getName()+" witch has the max similarity for the traveler");
        Collections.sort(cities);
        ArrayList<City> cities1 = elder0.compare_cities(2, cities);
        for (int i = 0; i < cities1.size(); i++) {
            System.out.println("Sort city list compare to similarity: "+cities1.get(i).getName());
        }

        //Teacher wants to see this
        ArrayList<Traveller> trav_clients = new ArrayList<>();
        trav_clients.add(young0);
        trav_clients.add(young1);
        trav_clients.add(middle0);
        trav_clients.add(elder0);
        Traveller winner_of_free_ticket = city.free_ticket(trav_clients);

        System.out.println("young similarity for athens(age:"+young0.getAge()+"): " + young0.calculate_similarity(city));
        System.out.println("young1  similarity for athens(age:"+young1.getAge()+"): "  + young1.calculate_similarity(city));
        System.out.println("middle  similarity for athens(age:"+middle0.getAge()+"): " + middle0.calculate_similarity(city));
        System.out.println("elder  similarity for athens(age:"+elder0.getAge()+"): " + elder0.calculate_similarity(city));
        System.out.println("The winners age is....." + winner_of_free_ticket.getAge()+"years old!");



        //String appid = "8a6696995628d4ee57726b05d30030d4";
        System.out.println("Check for wikiNoArticleException");
        City city7=new City();
        city7.setName("kalaolakala");
        city7.setCountry_abbrev("it");
        city7.setCityFromOpenData();
        System.out.println(city7.getGeodesic_vector(0));
        System.out.println(city7.getGeodesic_vector(1));
    }
}


