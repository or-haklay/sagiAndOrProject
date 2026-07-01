package sagiAndOr;

public class Doctor extends Lecturer implements Articelable,Comparable<Articelable>{
    private String[] articles;
    private int numOfArticles;
    public Doctor(String name, String id,String degreeName, int salary) {
        super(name, id, eDegree.phd, degreeName, salary);
        this.numOfArticles = 0;
        this.articles = new String[5];
    }

    @Override
    public void addArticel(String articelName) throws CollegeException{
        if (articelName == null) {
            throw new CollegeException("Article name cannot be null");
        }
        for (int i = 0; i < numOfArticles; i++) {
            if (articles[i].equals(articelName)) {
                throw new CollegeException("Article already exists");
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
        throw new CollegeException("article not in the list!");
    }

    public int getNumOfArticles() {
        return numOfArticles;
    }

    @Override
    public String toString() {
       return super.toString()+" num of atricles "+ numOfArticles;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Doctor))
            return false;
        Doctor d=(Doctor)obj;
        return d.getName().equals(this.getName()) && d.getId().equals(this.getId());
    }

    @Override
    public int compareTo(Articelable o) {
        if(o.getNumOfArticles() > this.getNumOfArticles()) return -1;
        if(o.getNumOfArticles() < this.getNumOfArticles()) return 1;
        return 0;
    }
}
