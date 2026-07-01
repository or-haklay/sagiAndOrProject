package sagiAndOr;

public class Collage {
    private String collageName;
    private Committee[] committees;
    private Department[] departments;
    private Lecturer[] lecturers;

    private int numOfCommittees;
    private int numOfDepartments;
    private int numOfLecturers;

    public Collage(String name) {
        collageName = name;
        committees = new Committee[2];
        departments = new Department[2];
        lecturers = new Lecturer[2];
    }

    public String getCollageName() {
        return collageName;
    }

    public Committee[] getCommittees() {
        return committees;
    }

    public int getNumOfCommittees() {
        return numOfCommittees;
    }

    public Department[] getDepartments() {
        return departments;
    }

    public int getNumOfDepartments() {
        return numOfDepartments;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public int getNumOfLecturers() {
        return numOfLecturers;
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
        return true;
    }

    public boolean addCommittee(Committee committee) {
        if (committee == null) {
            return false;
        }
        for (int i = 0; i < numOfCommittees; i++) {
            if (committees[i] == committee) {
                return false;
            }
        }
        if (numOfCommittees >= committees.length) {
            committees = Tools.doubleCommittees(committees, numOfCommittees);
        }
        committees[numOfCommittees] = committee;
        numOfCommittees++;
        return true;
    }

    public boolean addLecturerToCommittee(Lecturer lecturer, Committee committee) {
        if (!Tools.findLecturInArray(lecturer.getName(), lecturers, numOfLecturers) ||
                !Tools.findCommitteeInArray(committee.getName(), committees, numOfCommittees)) {
            return false;
        }
        return committee.addLecturer(lecturer);
    }

    public void setCeo(Committee c, Lecturer ceo) throws NotEligibleCeoException, CollegeException {
        if (!Tools.findCommitteeInArray(c.getName(), committees, numOfCommittees)) {
            throw new CollegeException("committee not found!");
        }
        c.setCeo(ceo);
    }

    public boolean removeLecturerFromCommittee(Committee com, Lecturer l) {
        if (!Tools.findLecturInArray(l.getName(), com.getLecturers(), com.getNumOfLecturers())) {
            return false;
        }
        return com.removeLecturer(l);
    }

    public boolean addDepartment(Department department) {
        if (department == null) {
            return false;
        }
        for (int i = 0; i < numOfDepartments; i++) {
            if (departments[i] == department) {
                return false;
            }
        }
        if (numOfDepartments >= departments.length) {
            departments = Tools.doubleDepartments(departments, numOfDepartments);
        }
        departments[numOfDepartments] = department;
        numOfDepartments++;
        return true;
    }

    public boolean addLecturerToDepartment(Department department, Lecturer lecturer) {
        if (!Tools.findLecturInArray(lecturer.getName(), lecturers, numOfLecturers) ||
                !Tools.findDepartmentInArray(department.getName(), departments, numOfDepartments)) {
            return false;
        }
        return department.addLecturer(lecturer);
    }

    public int getWageAve() {
        return Tools.getWageAve(lecturers, numOfLecturers);
    }

    public int getWageAveForDepartment(Department department) {
        return department.getWageAve();
    }

    public String getLecturersInfo() {
        StringBuffer res = new StringBuffer("Lecturers: \n-----------");
        for (int i = 0; i < numOfLecturers; i++) {
            res.append("\n").append(lecturers[i].toString());
        }
        return res.toString();
    }

    public String getCommitteesInfo() {
        StringBuffer res = new StringBuffer("Committees: \n-----------");
        for (int i = 0; i < numOfCommittees; i++) {
            res.append("\n-----------");
            res.append("\nName: ").append(committees[i].getName())
               .append(" | CEO: ").append(committees[i].getCeo() != null ? committees[i].getCeo().getName() : "none");
        }
        return res.toString();
    }

    public Lecturer getLecturerByName(String lecturerName) {
        for (int i = 0; i < numOfLecturers; i++) {
            if (lecturers[i].getName().equals(lecturerName)) {
                return lecturers[i];
            }
        }
        return null;
    }

    public Committee getCommitteeByName(String committeeName) {
        for (int i = 0; i < numOfCommittees; i++) {
            if (committees[i].getName().equals(committeeName)) {
                return committees[i];
            }
        }
        return null;
    }

    public Department getDepartmentByName(String departmentName) {
        for (int i = 0; i < numOfDepartments; i++) {
            if (departments[i].getName().equals(departmentName)) {
                return departments[i];
            }
        }
        return null;
    }

    public Articelable[] getAricleableLecturers() {
        int count = 0;
        for (int i = 0; i < numOfLecturers; i++) {
            if (lecturers[i] instanceof Articelable) {
                count++;
            }
        }
        Articelable[] res = new Articelable[count];
        int idx = 0;
        for (int i = 0; i < numOfLecturers; i++) {
            if (lecturers[i] instanceof Articelable) {
                res[idx++] = (Articelable) lecturers[i];
            }
        }
        return res;
    }
@Override
    public String toString() {
    StringBuffer str = new StringBuffer("collage name is:  " + collageName);
    str.append("the committees are");
    for (int i = 0; i < numOfCommittees; i++) {
        str.append(committees[i].getName());
    }
    return str.toString();

}


}
