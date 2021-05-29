import exceptions.AgeException;

public class YoungTraveller extends Traveller  {

    public YoungTraveller(int [] grade, double [] local_geodesic,int age,String name) {
        super(grade,local_geodesic,age,name);
        }
    public YoungTraveller(int[] grade, int age) {
        super(grade, age);
    }
    public YoungTraveller(String name, int age,int[] grade)  {
        super(name,age,grade);
    }
    public YoungTraveller(String name,int[] grade)  {
        super(name,grade);
    }
    public YoungTraveller() {
        super();
    }


    @Override
    public double calculate_similarity(City aCity) {

        double p=0.95d; //assumption for young
        return p*similarity_terms_vector(aCity)+(1-p)*similarity_geodesic_vector(aCity);

    }
    @Override
    public  double similarity_terms_vector(City city) {
        int sum=0;
        for(int i=0;i<this.getGrade().length;i++){
            sum+=Math.pow(this.getGrade(i)-city.getTerm_vectors(i),2);
        }
        return 1/(1+(Math.sqrt(sum)));
    }
    @Override
    public String toString() {
        return "Young Traveller: "+super.toString();
    }



}
