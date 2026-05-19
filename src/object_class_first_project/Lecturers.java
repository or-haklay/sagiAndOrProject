package object_class_first_project;

public class Lecturers {
    public enum eDegree{first_degree,masters,phd}
    private String name;

    private eDegree theDegree;
    private int salary;
    private Department theDepartment;
    private Committee [] theCommittees;
    private int numOfCommittees;


    public  Lecturers(String name, eDegree degree){
        this.name = name;
        this.theDegree = degree;
        this.salary = 0;
        this.theCommittees = new Committee[5];
        this.numOfCommittees = 0;
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
        Committee[] oldCommittees = theCommittees;
        theCommittees =  new Committee[numOfCommittees*2];
        for(int i=0;i<oldCommittees.length;i++){
            theCommittees[i] = oldCommittees[i];
        }
    }
    theCommittees[numOfCommittees] = committee;
    numOfCommittees++;

    return true;
}

public boolean removeCommittee(Committee committee){
    for(int i =0 ; i < numOfCommittees ; i++){
        if(theCommittees[i] ==  committee){
            committee.removeLecturer(this);
            for(int j=i+1;j<numOfCommittees;j++){
                theCommittees[j-1]=theCommittees[j];
            }
            theCommittees[numOfCommittees-1]=null;
            numOfCommittees--;
            return true;
        }
    }
    return false;
}
}
