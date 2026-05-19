package object_class_first_project;

public class Department {
    private String name;
    private int numOfStudents;
    private int numOflecturers=0;
    private Lecturers[] lecturers = new Lecturers[10];

    public Department (int numOfStudents , String  name, Lecturers firstFecturer) {
        this(numOfStudents, name);
        addLecturer(firstFecturer);
    }

    public Department (int numOfStudents , String  name){
        this.numOfStudents = numOfStudents;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNumOfStudents() {
        return numOfStudents;
    }

    public Lecturers[] getLecturers() {
        return lecturers;
    }

    public boolean addLecturer(Lecturers theLecturer){
        if(theLecturer == null){
            return false;
        }

        if(numOflecturers >= lecturers.length){
            Lecturers[] oldLecturers = lecturers;
            lecturers =  new Lecturers[numOflecturers*2];
            for(int i=0;i<oldLecturers.length;i++){
                lecturers[i] = oldLecturers[i];
            }
        }

        lecturers[numOflecturers] = theLecturer;
        theLecturer.setDepartment(this);
        numOflecturers++;

        return true;
    }

    public boolean removeLecturer(Lecturers theLecturer){
        for(int i=0;i<numOflecturers;i++){
            if(theLecturer==lecturers[i]){
                theLecturer.setDepartment(null);
                for(int j=i+1;j<numOflecturers;j++){
                    lecturers[j-1]=lecturers[j];
                }
                lecturers[numOflecturers-1]=null;
                numOflecturers--;
                return true;
            }
        }
        return false;
    }


}
