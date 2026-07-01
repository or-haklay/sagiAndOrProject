package sagiAndOr;

public class Department {
    private String name;
    private int numOfStudents;
    private int numOfLecturers = 0;
    private Lecturer[] lecturers = new Lecturer[10];

    public Department(int numOfStudents, String name, Lecturer firstLecturer) throws CollegeException {
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

    public void addLecturer(Lecturer theLecturer) throws CollegeException {
        if (theLecturer == null) {
            throw new CollegeException("lecturer cannot be null");
        }
        for (int i = 0; i < numOfLecturers; i++) {
            if (lecturers[i] == theLecturer) {
                throw new CollegeException(theLecturer.getName() + " is already in this department");
            }
        }
        if (numOfLecturers >= lecturers.length) {
            lecturers = Tools.doubleLecturers(lecturers, numOfLecturers);
        }
        lecturers[numOfLecturers] = theLecturer;
        theLecturer.setDepartment(this);
        numOfLecturers++;
    }

    public void removeLecturer(Lecturer theLecturer) throws CollegeException {
        for (int i = 0; i < numOfLecturers; i++) {
            if (theLecturer == lecturers[i]) {
                theLecturer.setDepartment(null);
                for (int j = i + 1; j < numOfLecturers; j++) {
                    lecturers[j - 1] = lecturers[j];
                }
                lecturers[numOfLecturers - 1] = null;
                numOfLecturers--;
                return;
            }
        }
        throw new CollegeException(theLecturer.getName() + " is not in this department");
    }

    public int getWageAve() {
        return Tools.getWageAve(lecturers, numOfLecturers);
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer("depatment name: "+name+" num of student is"
        +numOfStudents +" lecturs: ");
        for (int i = 0; i < numOfLecturers; i++) {
            str.append(lecturers[i].getName()+" ");
        }

        return str.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Department))
            return false;
        Department d = (Department)obj;
        return d.getName().equals(this.getName()) &&
                d.getNumOfStudents()==this.getNumOfStudents();
    }
}
