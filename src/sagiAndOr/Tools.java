package sagiAndOr;

public class Tools {

    public static Department[] doubleDepartments(Department[] theArray, int length) {
        Department[] oldArray = theArray;
        theArray = new Department[length * 2];
        for (int i = 0; i < oldArray.length; i++) {
            theArray[i] = oldArray[i];
        }
        return theArray;
    }

    public static Committee[] doubleCommittees(Committee[] theArray, int length) {
        Committee[] oldArray = theArray;
        theArray = new Committee[length * 2];
        for (int i = 0; i < oldArray.length; i++) {
            theArray[i] = oldArray[i];
        }
        return theArray;
    }

    public static Lecturer[] doubleLecturers(Lecturer[] theArray, int length) {
        Lecturer[] oldArray = theArray;
        theArray = new Lecturer[length * 2];
        for (int i = 0; i < oldArray.length; i++) {
            theArray[i] = oldArray[i];
        }
        return theArray;
    }

    public static boolean findLecturInArray(String name, Lecturer[] arr, int length) {
        for (int i = 0; i < length; i++) {
            if (arr[i] != null && arr[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean findDepartmentInArray(String name, Department[] arr, int length) {
        for (int i = 0; i < length; i++) {
            if (arr[i] != null && arr[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean findCommitteeInArray(String name, Committee[] arr, int length) {
        for (int i = 0; i < length; i++) {
            if (arr[i] != null && arr[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static String showDepartmentsByIndex(Department[] d, int length) {
        String s = "";
        for (int i = 0; i < length; i++) {
            s += i + " " + d[i].getName() + "\n";
        }
        return s;
    }

    public static String showCommiteesByIndex(Committee[] c, int length) {
        String s = "";
        for (int i = 0; i < length; i++) {
            s += i + " " + c[i].getName() + "\n";
        }
        return s;
    }

    public static String showLecturerByIndex(Lecturer[] l, int length) {
        String s = "";
        for (int i = 0; i < length; i++) {
            s += i + " " + l[i].getName() + "\n";
        }
        return s;
    }

    public static int getWageAve(Lecturer[] lecturers, int numOfLecturers) {
        if (numOfLecturers == 0) return 0;
        int sum = 0;
        for (int i = 0; i < numOfLecturers; i++) {
            sum += lecturers[i].getSalary();
        }
        return sum / numOfLecturers;
    }

    public static Lecturer getLecturer(String lecturerName, Lecturer[] lec) {
        for (int i = 0; i < lec.length; i++) {
            if (lec[i] != null && lec[i].getName().equals(lecturerName)) {
                return lec[i];
            }
        }
        return null;
    }

    public static Committee getCommittee(String committeeName, Committee[] com) {
        for (int i = 0; i < com.length; i++) {
            if (com[i] != null && com[i].getName().equals(committeeName)) {
                return com[i];
            }
        }
        return null;
    }
}
