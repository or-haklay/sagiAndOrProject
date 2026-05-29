package object_class_first_project;

public class Committee {
    private String name;
    private Lecturer[] lecturers;
    private int numOfLecturers;
    private Lecturer ceo;

    public Committee(String name, Lecturer ceo) {
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

    public boolean addLecturer(Lecturer lecturer) {
        if (lecturer == null) {
            return false;
        }
        for (int i = 0; i < numOfLecturers; i++) {
            if (lecturers[i] == lecturer) {
                return false;
            }
        }
        if (numOfLecturers >= lecturers.length) {
            lecturers = Tools.doubleLecturers(lecturers, numOfLecturers);
        }
        lecturers[numOfLecturers] = lecturer;
        numOfLecturers++;
        lecturer.addCommittee(this);
        return true;
    }

    public boolean removeLecturer(Lecturer lecturer) {
        if (lecturer == null) {
            return false;
        }
        for (int i = 0; i < numOfLecturers; i++) {
            if (lecturers[i] == lecturer) {
                for (int j = i + 1; j < numOfLecturers; j++) {
                    lecturers[j - 1] = lecturers[j];
                }
                lecturers[numOfLecturers - 1] = null;
                numOfLecturers--;
                lecturer.removeCommittee(this);
                return true;
            }
        }
        return false;
    }

    public boolean setCeo(Lecturer ceo) {
        if (ceo == null || ceo == this.ceo) {
            return false;
        }
        if (ceo.getDegree() != Lecturer.eDegree.phd) {
            return false;
        }
        for (int i = 0; i < numOfLecturers; i++) {
            if (ceo == lecturers[i]) {
                for (int j = i + 1; j < numOfLecturers; j++) {
                    lecturers[j - 1] = lecturers[j];
                }
                lecturers[numOfLecturers - 1] = null;
                numOfLecturers--;
                break;
            }
        }
        if (this.ceo != null) {
            this.ceo.removeCommittee(this);
        }
        ceo.addCommittee(this);
        this.ceo = ceo;
        return true;
    }

    public int getWageAve() {
        return Tools.getWageAve(lecturers, numOfLecturers);
    }

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
}
