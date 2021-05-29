import exceptions.AgeException;

public class MiddleTraveller extends Traveller {
        public MiddleTraveller(int [] grade, double [] local_geodesic,int age,String name) {
            super(grade,local_geodesic,age,name);
        }
    public MiddleTraveller() { super(); }
    public MiddleTraveller(int[] grade, int age) { super(grade, age); }
    public MiddleTraveller(String name, int age,int[] grade)  {
        super(name,age,grade);
    }
    public MiddleTraveller(String name,int[] grade)  {
        super(name,grade);
    }
    @Override
        public double calculate_similarity(City aCity) {
            double p=0.7d; //assumption for middle
            return p*similarity_terms_vector(aCity)+(1-p)*similarity_geodesic_vector(aCity);
        }
        @Override
        public  double similarity_terms_vector(City city) {
            int numerator=0;
            double denominator_x=0,denominator_y=0;
            for(int i=0;i<this.getGrade().length;i++) {
                numerator += this.getGrade(i) * city.getTerm_vectors(i);
                denominator_x += Math.pow(this.getGrade(i), 2);
                denominator_y += Math.pow(city.getTerm_vectors(i), 2);
            }
            if(denominator_x==0d || denominator_y==0d){
                return 0;
            }

            return numerator/Math.sqrt(denominator_x*denominator_y);
        }
    @Override
    public String toString() {
        return "Middle Traveller "+super.toString();
    }

}
