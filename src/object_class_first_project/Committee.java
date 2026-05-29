package object_class_first_project;

public class Committee {
    private String name;
    private Lecturer[] lecturers;
    private int numOfLecturers;
    private Lecturer ceo;

    //Add Ceo
    public Committee(String name, Lecturer ceo) {
        this.name = name;
        lecturers =  new Lecturer[5];
        numOfLecturers = 0;
        this.ceo = ceo;
        if(ceo != null) ceo.addCommittee(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public int getNumOfLecturers() {
        return numOfLecturers;
    }

    public Lecturer getCeo() {
        return ceo;
    }

    public boolean addLecturer(Lecturer lecturer){
        if(lecturer == null){
            return false;
        }
        if(numOfLecturers >= lecturers.length){
            lecturers = Tools.doubleLecturers(lecturers,numOfLecturers);
        }
        lecturers[numOfLecturers] = lecturer;
        numOfLecturers++;
        lecturer.addCommittee(this);
        return true;
    }

    public boolean removeLecturer(Lecturer lecturer){
        if(lecturer == null){
            return false;
        }

        for(int i=0;i<numOfLecturers;i++){
            if(lecturers[i] == lecturer){
                for(int j=i+1;j<numOfLecturers;j++){
                    lecturers[j-1] = lecturers[j];
                }
                lecturers[numOfLecturers-1] = null;
                numOfLecturers--;
                lecturer.removeCommittee(this);
                return  true;
            }
        }
        return  false;
    }

    public boolean setCeo(Lecturer ceo) {
        if(ceo == null || ceo == this.ceo){
            return false;
        }
        if(ceo.getDegree() != Lecturer.eDegree.phd){
            return false;
        }
        for(int i = 0; i < numOfLecturers; i++){
            if(ceo == lecturers[i]){
                for(int j=i+1; j<numOfLecturers; j++){
                    lecturers[j-1] = lecturers[j];
                }
                lecturers[numOfLecturers-1] = null;
                numOfLecturers--;
                break;
            }
        }
        if(this.ceo != null){
            this.ceo.removeCommittee(this);
        }
        ceo.addCommittee(this);
        this.ceo = ceo;
        return true;
    }

    public int getWageAve(){
        return Tools.getWageAve(lecturers , numOfLecturers);
    }

    public String toString(){
        StringBuffer res = new StringBuffer();
        res.append("Name: " + this.name);
        res.append("\nCEO: " + this.ceo);
        res.append("\n Lecturers: [");
        for(int i=0;i<numOfLecturers;i++){
            res.append(lecturers[i].getName() + " , ");
        }
        res.append("\b\b ]");
        return res.toString();
    }



}
