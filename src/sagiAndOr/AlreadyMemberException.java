package sagiAndOr;

public class AlreadyMemberException extends CollegeException {
    public AlreadyMemberException(String name) {
        super(name + " is already a member of this committee");
    }
}
