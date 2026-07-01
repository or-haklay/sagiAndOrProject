package sagiAndOr;

public class Prof extends Doctor {
    private String diplomaGiver;

    public Prof(String name, String id, String degreeName, int salary){
        super(name, id, eDegree.professor, degreeName, salary);
    }

    public String getDiplomaGiver() {
        return diplomaGiver;
    }

    public void setDiplomGiver(String diplomaGiver){
        this.diplomaGiver = diplomaGiver;
    }

    @Override
    public String toString() {
        String res = super.toString();
        if (diplomaGiver != null) {
            res += " | diploma giver: " + diplomaGiver;
        }
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Prof))
            return false;
        Prof p = (Prof) obj;
        return p.getName().equals(this.getName()) && p.getId().equals(this.getId());
    }
}
