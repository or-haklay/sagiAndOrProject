package sagiAndOr;

import java.util.ArrayList;

public class Committee<T extends Lecturer> implements java.io.Serializable {
    private String name;
    private ArrayList<T> lecturers;
    private Lecturer ceo;
    private eCommitteeType type;

    public enum eCommitteeType {regular, doctors, professors;}

    public boolean matches(Lecturer l) {
        switch (this.type) {
            case eCommitteeType.professors: return l instanceof Prof;
            case eCommitteeType.doctors:    return (l instanceof Doctor) && !(l instanceof Prof);
            case eCommitteeType.regular:    return !(l instanceof Doctor);
        }
        return false;
    }




    public Committee(String name, Lecturer ceo, eCommitteeType type) throws CollegeException {
        this.name = name;
        this.lecturers = new ArrayList<T>();
        this.ceo = ceo;
        this.type = type;
        if (ceo != null) ceo.addCommittee(this);
    }

    public String getName() {
        return name;
    }

    public ArrayList<T> getLecturers() {
        return lecturers;
    }

    public eCommitteeType getType() {
        return type;
    }

    public Lecturer getCeo() {
        return ceo;
    }

    public void addLecturer(Lecturer lecturer) throws AlreadyMemberException, CollegeException {
        if (lecturer == null) {
            throw new CollegeException("lecturer cannot be null");
        }
        if (lecturers.contains(lecturer)) {
            throw new AlreadyMemberException(lecturer.getName());
        }
        if (!matches(lecturer)) {
            throw new CollegeException(lecturer.getName() + " does not match this committee's degree type (" + type + ")");
        }

        lecturers.add((T) lecturer);
        lecturer.addCommittee(this);
    }

    public void removeLecturer(Lecturer lecturer) throws CollegeException {
        if (lecturer == null) {
            throw new CollegeException("lecturer cannot be null");
        }

        if (lecturers.contains(lecturer)) {
            lecturers.remove(lecturer);
            lecturer.removeCommittee(this);
            return;
        }

        throw new CollegeException(lecturer.getName() + " is not a member of this committee");
    }

    public int getNumOfLecturers() {
        return lecturers.size();
    }

    private boolean isEligibleCeo(Lecturer lecturer) {
        return lecturer instanceof Doctor;
    }

    public void setCeo(Lecturer ceo) throws NotEligibleCeoException, CollegeException {
        if (ceo == null || ceo == this.ceo) {
            throw new CollegeException("cannot set, the ceo is already been sign");
        }
        if (!isEligibleCeo(ceo)) {
           throw new NotEligibleCeoException(ceo.getName());
        }

        if(lecturers.contains(ceo)) {
            lecturers.remove(ceo);
        }else{
            ceo.addCommittee(this);
        }

        this.ceo = ceo;
    }

    public int getWageAve() {
        return Tools.getWageAve(lecturers);
    }


    public int getTotalArticles() {
        int sum = 0;
        if (ceo instanceof Articelable) {
            sum += ((Articelable) ceo).getNumOfArticles();
        }

        for (Lecturer lecturer : lecturers) {
            if(lecturer instanceof Articelable){
                sum += ((Articelable) lecturer).getNumOfArticles();
            }
        }

        return sum;
    }

    public Committee duplicate() throws AlreadyMemberException, CollegeException {
        Committee copy = new Committee(name + "-new", ceo, type);
        for(Lecturer lecturer : lecturers){
            copy.addLecturer(lecturer);
        }
        return copy;
    }
@Override
    public String toString() {
        StringBuffer res = new StringBuffer("Name: " + name + " | CEO: " + (ceo != null ? ceo.getName() : "none"));
        if (! lecturers.isEmpty()) {
            res.append(" | Members: ");
            for (int i=0; i<lecturers.size(); i++) {
                res.append(lecturers.get(i).getName());
                if(i<lecturers.size()-1) res.append(", ");
            }
        }
        return res.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Committee))
            return false;
        Committee c = (Committee) obj;
        return c.getName().equals(this.name);
    }


}
