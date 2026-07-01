package sagiAndOr;

public interface Articelable {

    void addArticel(String articelName) throws CollegeException;
    void removeArticel(String articelName) throws CollegeException;
    int getNumOfArticles();
    String getName();
    int compareTo(Articelable re);
}
