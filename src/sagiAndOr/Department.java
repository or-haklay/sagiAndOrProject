package sagiAndOr;

import java.util.ArrayList;

public class Department implements java.io.Serializable {
    private String name;
    private int numOfStudents;
    private ArrayList<Lecturer> lecturers;

    public Department(int numOfStudents, String name, Lecturer firstLecturer) throws CollegeException {
        this(numOfStudents, name);
        addLecturer(firstLecturer);
    }

    public Department(int numOfStudents, String name) {
        this.numOfStudents = numOfStudents;
        this.name = name;
        lecturers = new ArrayList<Lecturer>();

    }

    public String getName() {
        return name;
    }

    public int getNumOfStudents() {
        return numOfStudents;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }


    public void addLecturer(Lecturer theLecturer) throws CollegeException {
        if (theLecturer == null) {
            throw new CollegeException("lecturer cannot be null");
        }
        if(lecturers.contains(theLecturer)){
            throw new CollegeException(theLecturer.getName() + " is already in this department");
        }
        lecturers.add(theLecturer);
        theLecturer.setDepartment(this);
    }

    public void removeLecturer(Lecturer theLecturer) throws CollegeException {
        if(lecturers.contains(theLecturer)){
            theLecturer.setDepartment(null);
            lecturers.remove(theLecturer);
            return;
        }
        throw new CollegeException(theLecturer.getName() + " is not in this department");
    }

    public int getWageAve() {
        return Tools.getWageAve(lecturers);
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer("depatment name: "+name+" num of student is"
        +numOfStudents +" lectures: ");
        for (int i = 0; i < lecturers.size(); i++) {
            str.append(lecturers.get(i).getName()+" ");
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
