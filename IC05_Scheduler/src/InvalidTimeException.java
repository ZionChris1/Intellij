public class InvalidTimeException extends Exception{

    public InvalidTimeException() {
        super("The time slot entered is invalid. Please try another");
    }
}
