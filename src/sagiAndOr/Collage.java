package sagiAndOr;
import java.util.ArrayList;

public class Collage implements java.io.Serializable {
    private String collageName;
    private ArrayList<Committee> committees;
    private ArrayList<Department> departments;
    private ArrayList<Lecturer> lecturers;

    public Collage(String name) {
        collageName = name;
        committees = new ArrayList<Committee>();
        departments = new ArrayList<Department>();
        lecturers = new ArrayList<Lecturer>();
    }

    public String getCollageName() {
        return collageName;
    }

    public ArrayList<Committee> getCommittees() {
        return committees;
    }

    public int getNumOfCommittees() {
        return committees.size();
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public int getNumOfDepartments() {
        return departments.size();
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public int getNumOfLecturers() {
        return lecturers.size();
    }

    public void addLecturer(Lecturer lecturer) throws CollegeException {
        if (lecturer == null) {
            throw new CollegeException("lecturer cannot be null");
        }

            if (lecturers.contains(lecturer)) {
                throw new CollegeException(lecturer.getName() + " already exists in the collage");
            }


        lecturers.add(lecturer);
    }

    public void addCommittee(Committee committee) throws CollegeException {
        if (committee == null) {
            throw new CollegeException("committee cannot be null");
        }

        if (committees.contains(committee)) {
            throw new CollegeException(committee.getName() + " already exists in the collage");
        }

        committees.add(committee);
    }

    public void addLecturerToCommittee(Lecturer lecturer, Committee committee) throws AlreadyMemberException, CollegeException {
        if ((!lecturers.contains(lecturer)) ||(!committees.contains(committee)))
                 {
            throw new CollegeException("lecturer or committee not found in the collage");
        }
        committee.addLecturer(lecturer);
    }

    public void setCeo(Committee c, Lecturer ceo) throws NotEligibleCeoException, CollegeException {
        if (!committees.contains(c)) {
            throw new CollegeException("committee not found!");
        }
        c.setCeo(ceo);
    }

    public void removeLecturerFromCommittee(Committee com, Lecturer l) throws CollegeException {
        if (!com.getLecturers().contains(l)) {
            throw new CollegeException(l.getName() + " is not a member of this committee");
        }
        com.removeLecturer(l);
    }

    public void addDepartment(Department department) throws CollegeException {
        if (department == null) {
            throw new CollegeException("department cannot be null");
        }

            if (departments.contains(department)) {
                throw new CollegeException(department.getName() + " already exists in the collage");
            }


        departments.add(department);

    }

    public void addLecturerToDepartment(Department department, Lecturer lecturer) throws CollegeException {
        if ((!lecturers.contains(lecturer))  ||
                (!departments.contains(department))) {
            throw new CollegeException("lecturer or department not found in the collage");
        }
        department.addLecturer(lecturer);
    }

    public int getWageAve() {
        return Tools.getWageAve(lecturers);
    }

    public int getWageAveForDepartment(Department department) {
        return department.getWageAve();
    }

    public String getLecturersInfo() {
        StringBuffer res = new StringBuffer("Lecturers: \n-----------");
        for(Lecturer l : lecturers) {
            res.append("\n").append(l.toString());
        }
        return res.toString();
    }

    public String getCommitteesInfo() {
        StringBuffer res = new StringBuffer("Committees: \n-----------");
        for(Committee m : committees) {
            res.append("\n-----------");
            res.append("\nName: ").append(m.getName())
               .append(" | CEO: ").append(m.getCeo() != null ? m.getCeo().getName() : "none");
        }
        return res.toString();
    }

    public Lecturer getLecturerByName(String lecturerName) {
        for (int i = 0; i < lecturers.size(); i++) {
            if (lecturers.get(i).getName().equals(lecturerName)) {
                return lecturers.get(i);
            }
        }
        return null;
    }

    public Committee getCommitteeByName(String committeeName) {
        for (int i = 0; i < committees.size(); i++) {
            if (committees.get(i).getName().equals(committeeName)) {
                return committees.get(i);
            }
        }
        return null;
    }

    public Department getDepartmentByName(String departmentName) {
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getName().equals(departmentName)) {
                return departments.get(i);
            }
        }
        return null;
    }

    public ArrayList<Articelable> getAricleableLecturers() {


        ArrayList<Articelable> res = new ArrayList<Articelable>();
        int idx = 0;
        for (int i = 0; i < lecturers.size(); i++) {
            if (lecturers.get(i) instanceof Articelable) {
                res.add((Articelable) lecturers.get(i));
            }
        }
        return res;
    }

    public boolean includesLecturer(String l) {
        for(Lecturer lecturer : lecturers){
            if(lecturer.getName().equals(l)){
                return true;
            }
        }
        return false;
    }

    public boolean includesCommittee(String c) {
        for(Committee committee : committees) {
            if(committee.getName().equals(c)){
                return true;
            }
        }
        return false;
    }

    public boolean includesDepartment(String departmentName) {
        for(Department department : departments) {
            if(department.getName().equals(departmentName)){
                return true;
            }
        }
        return false;
    }



@Override
    public String toString() {
    StringBuffer str = new StringBuffer("collage name is:  " + collageName);
    str.append("the committees are");
    for (int i = 0; i < committees.size(); i++) {
        str.append(committees.get(i).getName());
    }
    return str.toString();

}

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Collage))
            return false;
        Collage other = (Collage) obj;
        return other.getCollageName().equals(this.collageName);
    }


}
