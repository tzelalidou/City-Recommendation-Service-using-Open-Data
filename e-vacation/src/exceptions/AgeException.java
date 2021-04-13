package exceptions;
public class AgeException extends Exception {

    private static final long serialVersionUID = 1L;
    static int numExcepetions=0;

    public AgeException() {
        numExcepetions++;
    }

    public String getMessage() {

        return "You should put an age from 16-115";
    }
}

