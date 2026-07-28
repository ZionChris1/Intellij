public class TimeInUseException extends Exception{

    public TimeInUseException() {
        super("The time slot has already been used. Please try another.");
    }
}
