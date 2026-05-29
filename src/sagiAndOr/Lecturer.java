package sagiAndOr;

public class Lecturer {
    public enum eDegree { first_degree, masters, phd, professor }

    private String name;
    private String id;
    private eDegree theDegree;
    private String degreeName;
    private int salary;
    private Department theDepartment;
    private Committee[] theCommittees;
    private int numOfCommittees;

    public Lecturer(String name, String id, eDegree degree, String degreeName, int salary) {
        this.name = name;
        this.id = id;
        this.theDegree = degree;
        this.degreeName = degreeName;
        this.salary = salary;
        this.theCommittees = new Committee[5];
        this.numOfCommittees = 0;
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        if (name.length() >= 2) {
            this.name = name;
            return true;
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public eDegree getDegree() {
        return theDegree;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public int getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return theDepartment;
    }

    public void setDepartment(Department newDepartment) {
        this.theDepartment = newDepartment;
    }

    public Committee[] getCommittees() {
        return theCommittees;
    }

    public int getNumOfCommittees() {
        return numOfCommittees;
    }

    public boolean addCommittee(Committee committee) {
        for (int i = 0; i < numOfCommittees; i++) {
            if (theCommittees[i] == committee) {
                return false;
            }
        }
        if (numOfCommittees >= theCommittees.length) {
            theCommittees = Tools.doubleCommittees(theCommittees, numOfCommittees);
        }
        theCommittees[numOfCommittees] = committee;
        numOfCommittees++;
        return true;
    }

    public boolean removeCommittee(Committee committee) {
        for (int i = 0; i < numOfCommittees; i++) {
            if (theCommittees[i] == committee) {
                for (int j = i + 1; j < numOfCommittees; j++) {
                    theCommittees[j - 1] = theCommittees[j];
                }
                theCommittees[numOfCommittees - 1] = null;
                numOfCommittees--;
                committee.removeLecturer(this);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuffer str = new StringBuffer("[ name: " + name + " | id: " + id + " | education: " + theDegree + " | degree name: " + degreeName + " | salary: " + salary);
        if (theDepartment != null) {
            str.append(" | department: ").append(theDepartment.getName());
        }
        if (numOfCommittees > 0) {
            str.append(" | committees: ");
            for (int i = 0; i < numOfCommittees; i++) {
                str.append(theCommittees[i].getName());
                if (i < numOfCommittees - 1) str.append(", ");
            }
        }
        str.append(" ]");
        return str.toString();
    }
}
