package object_class_first_project;

public class Lecturer {
    public enum eDegree{first_degree,masters,phd}
    private String name;

    private eDegree theDegree;
    private int salary;
    private Department theDepartment;
    private Committee [] theCommittees;
    private int numOfCommittees;


    public Lecturer(String name, eDegree degree,int salary){
        this.name = name;
        this.theDegree = degree;
        this.salary = salary;
        this.theCommittees = new Committee[5];
        this.numOfCommittees = 0;
    }

public Committee[] getCommittee(){
        return theCommittees;
}

public int getNumOfCommittees(){
        return numOfCommittees;
}
public boolean setName(String name){
    if(name.length()>=2){
        this.name=name;
        return true;
    }
    return false;

}

public String getName(){
        return name;
    }


public boolean setDegree(eDegree Newdegree){
    this.theDegree=Newdegree;
    return true;
}


public eDegree getDegree(){
    return theDegree;
}

public Boolean setSalary(int salary){
    if(salary>=0){
        this.salary=salary;
        return true;
    }
    return false;
}

public int getSalary(){
    return salary;
}

public boolean setDepartment(Department newDepartment){
    this.theDepartment=newDepartment;
    return true;
}
public Department getDepartment(){
    return theDepartment;
}


public boolean addCommittee(Committee committee){

    for(int i =0 ; i < numOfCommittees ; i++){
        if(theCommittees[i] ==  committee){
            return false;
        }
    }

    if(numOfCommittees >= theCommittees.length){
        Tools.doubleCommittees(theCommittees,numOfCommittees);
    }

    theCommittees[numOfCommittees] = committee;
    numOfCommittees++;

    return true;
}

public boolean removeCommittee(Committee committee){
    for(int i =0 ; i < numOfCommittees ; i++){
        if(theCommittees[i] ==  committee){
            for(int j=i+1;j<numOfCommittees;j++){
                theCommittees[j-1]=theCommittees[j];
            }
            theCommittees[numOfCommittees-1]=null;
            numOfCommittees--;
            committee.removeLecturer(this);
            return true;
        }
    }
    return false;
}

    public String toString() {

        StringBuffer str = new StringBuffer("[ name: " + name + " | education: " + theDegree + " | salary is: " + salary);
        if (theDepartment != null) {
            str.append(" department: ").append(theDepartment.getName());
        }

        if(numOfCommittees > 0){
        str.append(" committees: ");
        for (int i = 0; i < numOfCommittees; i++) {
            str.append(theCommittees[i].getName() + " ,");
        }
        str.append("\n\n ]");}
        return str.toString();
    }



}
