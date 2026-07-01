package sagiAndOr;

public class Prof extends Lecturer implements Articelable, Comparable<Articelable>{
    private String[] articles;
    private int numOfArticles;
    private String diplomaGiver;

    public Prof(String name, String id, String degreeName, int salary){
        super(name, id,eDegree.professor,degreeName,salary);
        this.numOfArticles = 0;
        this.articles = new String[5];
    }

    public String getDiplomaGiver() {
        return diplomaGiver;
    }

    public void setDiplomGiver(String diplomaGiver){
        this.diplomaGiver = diplomaGiver;
    }

    public int getNumOfArticles() {
        return numOfArticles;
    }

    @Override
    public void addArticel(String articelName) throws CollegeException{
        if (articelName == null) {
            throw new CollegeException("Article name cannot be null");
        }
        for (int i = 0; i < numOfArticles; i++) {
            if (articles[i].equals(articelName)) {
                throw new CollegeException("Article already exists ");
            }
        }
        if (numOfArticles >= articles.length) {
            articles = Tools.doubleNumOfArticals(articles, numOfArticles);
        }
        articles[numOfArticles] = articelName;
        numOfArticles++;
    }

    @Override
    public void removeArticel(String articelName) throws CollegeException {
        for (int i = 0; i < numOfArticles; i++) {
            if (articles[i].equals(articelName)) {
                for (int j = i + 1; j < numOfArticles; j++) {
                    articles[j - 1] = articles[j];
                }
                articles[numOfArticles - 1] = null;
                numOfArticles--;
                return;
            }
        }
        throw new CollegeException("Article not in the list!");
    }

    @Override
    public int compareTo(Articelable o) {
        if(o.getNumOfArticles() > this.getNumOfArticles()) return -1;
        if(o.getNumOfArticles() < this.getNumOfArticles()) return 1;
        return 0;
    }

}


