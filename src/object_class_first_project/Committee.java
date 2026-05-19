package object_class_first_project;

public class Committee {
    private String name;
    private Lecturers[] lecturers;
    private int numOfLecturers;
    private Lecturers ceo;

    public Committee(String name) {
        this.name = name;
        lecturers =  new Lecturers[5];
        numOfLecturers = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lecturers[] getLecturers() {
        return lecturers;
    }

    public Lecturers getCeo() {
        return ceo;
    }

    public boolean addLecturer(Lecturers lecturer){
        if(lecturer == null){
            return false;
        }
        if(numOfLecturers >= lecturers.length){
            Lecturers[] oldLecturers = lecturers;
            lecturers =  new Lecturers[numOfLecturers*2];
            for(int i=0;i<oldLecturers.length;i++){
                lecturers[i] = oldLecturers[i];
            }
        }
        lecturers[numOfLecturers] = lecturer;
        numOfLecturers++;
        return true;
    }

    public boolean removeLecturer(Lecturers lecturer){
        if(lecturer == null){
            return false;
        }

        for(int i=0;i<numOfLecturers;i++){
            if(lecturers[i] == lecturer){
                lecturer.removeCommittee(this);
                for(int j=i+1;j<numOfLecturers;j++){
                    lecturers[j-1] = lecturers[j];
                }
                lecturers[numOfLecturers-1] = null;
                numOfLecturers--;
                return  true;
            }
        }
        return  false;
    }

    public boolean setCeo(Lecturers ceo) {
        Lecturers oldCeo = this.ceo;
        if(ceo == oldCeo || ceo == null){
            return false;
        }
        for(int i =0 ; i< numOfLecturers;i++){
            if(ceo == lecturers[i]){
                if(oldCeo != null){
                    oldCeo.removeCommittee(this);
                }
                ceo.addCommittee(this);
                for(int j=i+1;j<numOfLecturers;j++){
                    lecturers[j-1]=lecturers[j];
                }
                lecturers[numOfLecturers-1]=null;
                numOfLecturers--;
            }
        }
        this.ceo = ceo;

        return true;
    }



}
