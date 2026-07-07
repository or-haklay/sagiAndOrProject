package sagiAndOr;

import java.util.ArrayList;

public class Tools {
    public static String showDepartmentsByIndex(ArrayList<Department> d, int length) {
        String s = "";
        for (int i = 0; i < length; i++) {
            s += i + " " + d.get(i).getName() + "\n";
        }
        return s;
    }

    public static String showCommitteesByIndex(ArrayList<Committee> c, int length) {
        String s = "";
        for (int i = 0; i < length; i++) {
            s += i + " " + c.get(i).getName() + "\n";
        }
        return s;
    }

    public static String showLecturerByIndex(ArrayList<Lecturer> l, int length) {
        String s = "";
        for (int i = 0; i < length; i++) {
            s += i + " " + l.get(i).getName() + "\n";
        }
        return s;
    }

    public static int getWageAve(ArrayList<? extends Lecturer> lecturers) {
        if (lecturers.isEmpty()) return 0;
        int sum = 0;
        for(Lecturer lecturer : lecturers) {
            sum += lecturer.getSalary();
        }
        return sum / lecturers.size();
    }

}
