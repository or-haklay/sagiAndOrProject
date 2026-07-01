package sagiAndOr;

public class Committee {
    private String name;
    private Lecturer[] lecturers;
    private int numOfLecturers;
    private Lecturer ceo;

    public Committee(String name, Lecturer ceo) throws CollegeException {
        this.name = name;
        this.lecturers = new Lecturer[5];
        this.numOfLecturers = 0;
        this.ceo = ceo;
        if (ceo != null) ceo.addCommittee(this);
    }

    public String getName() {
        return name;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public int getNumOfLecturers() {
        return numOfLecturers;
    }

    public Lecturer getCeo() {
        return ceo;
    }

    public void addLecturer(Lecturer lecturer) throws AlreadyMemberException, CollegeException {
        if (lecturer == null) {
            throw new CollegeException("lecturer cannot be null");
        }
        for (int i = 0; i < numOfLecturers; i++) {
            if (lecturers[i] == lecturer) {
                throw new AlreadyMemberException(lecturer.getName());
            }
        }
        if (numOfLecturers >= lecturers.length) {
            lecturers = Tools.doubleLecturers(lecturers, numOfLecturers);
        }
        lecturers[numOfLecturers] = lecturer;
        numOfLecturers++;
        lecturer.addCommittee(this);
    }

    public void removeLecturer(Lecturer lecturer) throws CollegeException {
        if (lecturer == null) {
            throw new CollegeException("lecturer cannot be null");
        }
        for (int i = 0; i < numOfLecturers; i++) {
            if (lecturers[i] == lecturer) {
                for (int j = i + 1; j < numOfLecturers; j++) {
                    lecturers[j - 1] = lecturers[j];
                }
                lecturers[numOfLecturers - 1] = null;
                numOfLecturers--;
                lecturer.removeCommittee(this);
                return;
            }
        }
        throw new CollegeException(lecturer.getName() + " is not a member of this committee");
    }

    private boolean isEligibleCeo(Lecturer lecturer) {
        return lecturer instanceof Doctor || lecturer instanceof Prof;
    }

    public void setCeo(Lecturer ceo) throws NotEligibleCeoException, CollegeException {
        if (ceo == null || ceo == this.ceo) {
            throw new CollegeException("cannot set, the ceo is already been sign");
        }
        if (!isEligibleCeo(ceo)) {
           throw new NotEligibleCeoException(ceo.getName());
        }
        boolean wasMember = false;
        for (int i = 0; i < numOfLecturers; i++) {
            if (ceo == lecturers[i]) {
                for (int j = i + 1; j < numOfLecturers; j++) {
                    lecturers[j - 1] = lecturers[j];
                }
                lecturers[numOfLecturers - 1] = null;
                numOfLecturers--;
                wasMember = true;
                break;
            }
        }
        if (this.ceo != null) {
            this.ceo.removeCommittee(this);
        }
        // if the new ceo was already a member it is still linked to this committee, so skip re-linking
        if (!wasMember) {
            ceo.addCommittee(this);
        }
        this.ceo = ceo;

    }

    public int getWageAve() {
        return Tools.getWageAve(lecturers, numOfLecturers);
    }

    public int getTotalArticles() {
        int sum = 0;
        if (ceo instanceof Articelable) {
            sum += ((Articelable) ceo).getNumOfArticles();
        }
        for (int i = 0; i < numOfLecturers; i++) {
            if (lecturers[i] instanceof Articelable) {
                sum += ((Articelable) lecturers[i]).getNumOfArticles();
            }
        }
        return sum;
    }

    public Committee duplicate() throws AlreadyMemberException, CollegeException {
        Committee copy = new Committee(name + "-new", ceo);
        for (int i = 0; i < numOfLecturers; i++) {
            copy.addLecturer(lecturers[i]);
        }
        return copy;
    }
@Override
    public String toString() {
        StringBuffer res = new StringBuffer("Name: " + name + " | CEO: " + (ceo != null ? ceo.getName() : "none"));
        if (numOfLecturers > 0) {
            res.append(" | Members: ");
            for (int i = 0; i < numOfLecturers; i++) {
                res.append(lecturers[i].getName());
                if (i < numOfLecturers - 1) res.append(", ");
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
