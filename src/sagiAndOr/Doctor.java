package sagiAndOr;

import java.util.ArrayList;

public class Doctor extends Lecturer implements Articelable,Comparable<Articelable>{
    private ArrayList<String> articles;

    public Doctor(String name, String id,String degreeName, int salary) {
        this(name, id, eDegree.phd, degreeName, salary);
    }

    // lets subclasses (Prof) set their own degree while reusing the articles logic
    protected Doctor(String name, String id, eDegree degree, String degreeName, int salary) {
        super(name, id, degree, degreeName, salary);
        this.articles = new ArrayList<String>();
    }

    @Override
    public void addArticel(String articleName) throws CollegeException{
        if (articleName == null) {
            throw new CollegeException("Article name cannot be null");
        }
        if(articles.contains(articleName)){
            throw new CollegeException("Article already exists");
        }
        articles.add(articleName);
    }

    @Override
    public void removeArticel(String articleName) throws CollegeException {
        if(articles.contains(articleName)){
            articles.remove(articleName);
            return;
        }
        throw new CollegeException("article not in the list!");
    }

    public int getNumOfArticles() {
        return articles.size();
    }

    @Override
    public String toString() {
       return super.toString()+" num of articles "+ articles.size();
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
