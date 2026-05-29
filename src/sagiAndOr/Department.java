package sagiAndOr;

public class Department {
    private String name;
    private int numOfStudents;
    private int numOfLecturers = 0;
    private Lecturer[] lecturers = new Lecturer[10];

    public Department(int numOfStudents, String name, Lecturer firstLecturer) {
        this(numOfStudents, name);
        addLecturer(firstLecturer);
    }

    public Department(int numOfStudents, String name) {
        this.numOfStudents = numOfStudents;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNumOfStudents() {
        return numOfStudents;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public int getNumOfLecturers() {
        return numOfLecturers;
    }

    public boolean addLecturer(Lecturer theLecturer) {
        if (theLecturer == null) {
            return false;
        }
        for (int i = 0; i < numOfLecturers; i++) {
            if (lecturers[i] == theLecturer) {
                return false;
            }
        }
        if (numOfLecturers >= lecturers.length) {
            lecturers = Tools.doubleLecturers(lecturers, numOfLecturers);
        }
        lecturers[numOfLecturers] = theLecturer;
        theLecturer.setDepartment(this);
        numOfLecturers++;
        return true;
    }

    public boolean removeLecturer(Lecturer theLecturer) {
        for (int i = 0; i < numOfLecturers; i++) {
            if (theLecturer == lecturers[i]) {
                theLecturer.setDepartment(null);
                for (int j = i + 1; j < numOfLecturers; j++) {
                    lecturers[j - 1] = lecturers[j];
                }
                lecturers[numOfLecturers - 1] = null;
                numOfLecturers--;
                return true;
            }
        }
        return false;
    }

    public int getWageAve() {
        return Tools.getWageAve(lecturers, numOfLecturers);
    }
}
