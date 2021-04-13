public class ElderTraveller extends Traveller {
    public ElderTraveller(int[] grade, double[] local_geodesic, int age) {
        super(grade, local_geodesic, age);
    }
    public ElderTraveller() {super(); }
    public ElderTraveller(int[] grade, int age) {
        super(grade, age);
    }
    @Override
    public double calculate_similarity(City aCity) {

        double p=0.05d; //assumption for elder
        return p*similarity_terms_vector(aCity)+(1-p)*similarity_geodesic_vector(aCity);

    }
    @Override
    public double similarity_terms_vector( City city) {
        int numerator_intersection = 0;
        double denominator_union = 0;
        for (int i = 0; i < this.getGrade().length; i++) {
            if (this.getGrade(i) >= 1 || city.getTerm_vectors(i) >= 1) {
                denominator_union += 1;
            }
            if (this.getGrade(i) >= 1 && city.getTerm_vectors(i) >= 1) {
                numerator_intersection += 1;
            }
        }
        if (denominator_union == 0d) {
            return 0;
        }
        return numerator_intersection/denominator_union;
    }
}

