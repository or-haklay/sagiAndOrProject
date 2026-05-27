package object_class_first_project;


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

    public String getCollageName(){
        return collageName;
    }

    public boolean setCollageName(String collageName){
        if(collageName==null || collageName.length()<2){
            return false;
        }
        this.collageName = collageName;
        return true;
    }

    public Committee[] getCommittees(){
        return committees;
    }

    public int getNumOfCommittees(){return numOfCommittees;}

    public Department[] getDepartments(){return departments;}

    public int getNumOfDepartments(){return numOfDepartments;}

    public Lecturer[] getLecturers(){
        return lecturers;
    }

    public int getNumOfLecturers(){return numOfLecturers;}






    ///menu functions
    ///#1
    public boolean addLecturer(Lecturer lecturer){
        if(lecturer==null){
            return false;
        }

        for(int i=0; i<numOfLecturers; i++){
            if(lecturers[i]==lecturer){
                return false;
            }
        }

        if(numOfLecturers >= lecturers.length){
            lecturers = Tools.doubleLecturers(lecturers , numOfLecturers);
        }

        lecturers[numOfLecturers] = lecturer;
        numOfLecturers++;
        return true;
    }

    ///#2
    public boolean addCommittee(Committee committee){
        if(committee==null){
            return false;
        }

        for(int i=0; i<numOfCommittees; i++){
            if(committees[i]==committee){
                return false;
            }
        }

        if(numOfCommittees >= committees.length){
            committees = Tools.doubleCommittees(committees , numOfCommittees);
        }

        committees[numOfCommittees] = committee;
        numOfCommittees++;
        return true;
    }

    ///#3
    public boolean addLecturerToCommittee(Lecturer lecturer,  Committee committee){
        if( !Tools.findLecturInArray(lecturer.getName() , lecturers,numOfLecturers) || !Tools.findCommitteeInArray(committee.getName() , committees,numOfCommittees)){
            return false;
        }
        committee.addLecturer(lecturer);
        return true;
    }

    ///#4
    public boolean setCeo(Committee c,Lecturer ceo) {
        if(!(Tools.findCommitteeInArray(c.getName(),committees, numOfCommittees)))
            {return false;}

        return c.setCeo(ceo);

    }

    ///#5
    public boolean removeLecturerFromCommittee(Lecturer l,Committee c){
      if( !( Tools.findLecturInArray(l.getName(),c.getLecturers(), c.getLecturers().length)))
            {return false;}
      c.removeLecturer(l);
      l.removeCommittee(c);
      return true;
    }

    ///#6
    public boolean addDepartment(Department department){
        if(department==null){
            return false;
        }

        for(int i=0; i<numOfDepartments; i++){
            if(departments[i]==department){
                return false;
            }
        }

        if(numOfDepartments >= departments.length){
            departments = Tools.doubleDepartments(departments , numOfDepartments);
        }

        departments[numOfDepartments] = department;
        numOfDepartments++;
        return true;
    }


    ///#7
    public boolean addLecturerToDepartment(Department department, Lecturer lecturer){
        if( !Tools.findLecturInArray(lecturer.getName() , lecturers,numOfLecturers) || !Tools.findDepartmentInArray(department.getName() , departments,numOfDepartments)){
            return false;
        }
        department.addLecturer(lecturer);
        return true;
    }

    ///#8
    public int getWageAve(){
        return Tools.getWageAve(lecturers , numOfLecturers);
    }

    ///#9
    public int getWageAveForDepartment(Department department){
        return department.getWageAve();
    }

    ///#10
    public String getLecturersInfo() {
        StringBuffer res = new StringBuffer();
        res.append("Lecturers: \n");
        res.append("-----------");
        for (int i = 0; i < numOfLecturers; i++) {
            res.append("\n-----------");
            res.append("\n" + lecturers[i].toString());
        }
        return res.toString();

    }

    ///#11
    public String getCommitteesInfo(){
        StringBuffer res = new StringBuffer();
        res.append("Committees: ");
        res.append("\n-----------");
        for(int i=0; i<numOfCommittees; i++){
            res.append("\n-----------");
            res.append("\nName: " + committees[i].getName() + " | CEO: " +  committees[i].getCeo().getName());
        }

        return res.toString();
    }



}
