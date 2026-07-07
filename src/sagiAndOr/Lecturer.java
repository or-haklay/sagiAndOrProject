package sagiAndOr;
import java.util.ArrayList;

public class Lecturer implements java.io.Serializable {
    public enum eDegree { first_degree, masters, phd, professor }

    private String name;
    private String id;
    private eDegree theDegree;
    private String degreeName;
    private int salary;
    private Department theDepartment;
    private ArrayList<Committee> committees;

    public Lecturer(String name, String id, eDegree degree, String degreeName, int salary) {
        this.name = name;
        this.id = id;
        this.theDegree = degree;
        this.degreeName = degreeName;
        this.salary = salary;
        this.committees = new ArrayList<Committee>();
    }

    public String getName() {
        return name;
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

    public ArrayList<Committee> getCommittees() {
        return committees;
    }

    public int getNumOfCommittees() {
        return committees.size();
    }

    public void addCommittee(Committee committee) throws CollegeException {
        if (committees.contains(committee)) {
            throw new CollegeException(name + " is already assigned to this committee");
        }
        committees.add(committee);
    }

    public void removeCommittee(Committee committee) throws CollegeException {

        if(committees.contains(committee)) {
            committees.remove(committee);
            return;
        }

        throw new CollegeException(name + " is not assigned to this committee");
    }

    public String toString() {
        StringBuffer str = new StringBuffer("[ name: " + name + " | id: " + id + " | education: " + theDegree + " | degree name: " + degreeName + " | salary: " + salary);
        if (theDepartment != null) {
            str.append(" | department: ").append(theDepartment.getName());
        }
        if (!committees.isEmpty()) {
            str.append(" | committees: ");
            for(Committee c : committees) {
                str.append(c.getName() + ", ");
            }
            str.delete(str.length() - 2, str.length()-1);
        }
        str.append(" ]");
        return str.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Lecturer))
            return false;
        Lecturer l = (Lecturer) obj;
        return l.getName().equals(this.name) && l.getId().equals(this.id);
    }
}
