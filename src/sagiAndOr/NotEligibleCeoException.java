package sagiAndOr;

public class NotEligibleCeoException extends CollegeException {
    public NotEligibleCeoException(String name) {
        super(name + " is not eligible to be CEO (must be Dr. or Prof.)");
    }
}
