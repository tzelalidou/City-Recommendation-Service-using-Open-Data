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
