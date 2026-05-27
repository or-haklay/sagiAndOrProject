package object_class_first_project;

public class Tools {

    /// double arrays functions
    public static Department[] doubleDepartments(Department[] theArray, int leangth){
        Department[] oldDepartment = theArray;
        theArray =  new Department[leangth*2];
        for(int i=0;i<oldDepartment.length;i++){
            theArray[i] = oldDepartment[i];
        }
        return theArray;
    }

    public static Committee[] doubleCommittees(Committee[] theArray,int leangth ){
        Committee[] oldCommittees = theArray;
        theArray =  new Committee[leangth*2];
        for(int i=0;i<oldCommittees.length;i++){
            theArray[i] = oldCommittees[i];
        }
        return theArray;

    }

    public static Lecturer[] doubleLecturers(Lecturer[] theArray,int leangth ){
        Lecturer[] oldLecturers = theArray;
        theArray =  new Lecturer[leangth*2];
        for(int i=0;i<oldLecturers.length;i++){
            theArray[i] = oldLecturers[i];
        }
        return theArray;

    }

    /// finds functions
   public static boolean findLecturInArray(String name, Lecturer[] arr, int length)   {
       for (int i=0; i<length; i++){
           if (arr[i] != null && (arr[i].getName()).equals(name)){
               return true;
           }
       }
       return false;
   }

    public static boolean findDepartmentInArray(String name, Department[] arr, int length)   {
        for (int i=0; i<length; i++){
            if (arr[i] != null && (arr[i].getName()).equals(name)){
                return true;
            }
        }
        return false;
    }

    public static boolean findCommitteeInArray(String name, Committee[] arr, int length)   {
        for (int i=0; i<length; i++){
            if (arr[i] != null && (arr[i].getName()).equals(name)){
                return true;
            }
        }
        return false;
    }



    public static String showDepartmentsByIndex(Department[] d ,int depLen){
        int cnt=0;
        String  s="";
        for(int i=0;i<depLen;i++){
            s+=cnt+" "+d[i].getName()+"\n";
            cnt++;

        }
        return s;
    }

    public static String showCommiteesByIndex(Committee[] c ,int CommLen){
        int cnt=0;
        String  s="";
        for(int i=0;i<CommLen;i++){
            s+=cnt+" "+c[i].getName()+"\n";
            cnt++;

        }
        return s;
    }

    public static String showLecturerByIndex(Lecturer[] l ,int LecturLen){
        int cnt=0;
        String  s="";
        for(int i=0;i<LecturLen;i++){
            s+=cnt+" "+l[i].getName()+"\n";
            cnt++;

        }
        return s;
    }





     ///Wage functions
     public static int getWageAve(Lecturer[] lecturers , int numOfLecturers){
         if (numOfLecturers == 0) return 0;
         int sum = 0;
         for(int i=0; i<numOfLecturers; i++){
             sum += lecturers[i].getSalary();
         }
         return sum/numOfLecturers;
     }

     public static Lecturer getLecturer(String lecturerName,Lecturer[] lec){
         for(int i=0;i<lec.length ;i++){
                if(lec[i] != null && lec[i].getName().equals(lecturerName)){
                    return lec[i];
                }
         }
         return null;
     }

    public static Committee getCommittee(String committeeName,Committee[] lec){
        for(int i=0;i<lec.length ;i++){
            if(lec[i] != null && lec[i].getName().equals(committeeName)){
                return lec[i];
            }
        }
        return null;
    }

}
