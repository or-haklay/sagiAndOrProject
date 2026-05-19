package object_class_first_project;
import java.util.Scanner;

public class Main {

    public static String[] ExtendedArray(String [] arr){
        String[] temp = new String[arr.length * 2];
        for (int i=0; i<arr.length; i++){
            temp[i] = arr[i];
        }
        return temp;
    }

    public static boolean FindNameInArray(String name, String[] arr){
        for (int i=0; i<arr.length; i++){
            if (arr[i] != null && arr[i].equals(name)){
                return true;
            }
        }
        return false;
    }

    public static String[] addNewItemToArray(String newitem, String[]list){
        if(FindNameInArray(newitem,list)){
            System.out.println("Item already in the list!");
            return list;
        }
        for(int i = 0 ; i < list.length; i++){
            if (list[i]==null){
                list[i]=newitem;
                System.out.println("Item added successfully!");
                return list;
            }
        }

        list = ExtendedArray(list);
        list[list.length/2]=newitem;

        System.out.println("Item added successfully! (after extending the array)");
        return list;
    }


     public static void ShowItemsInArray(String[] arr){
        for (String item : arr) {
            if (item != null) {
            System.out.println(item);
            }
        }
    }

    public static void main(String[] args){
        // Sagi Gilad - 324020825
        // Or Haklay - 322307687
        Scanner s = new Scanner((System.in));

        String[] Lecturers = new String[2];
        String[] Committees = new String[2];
        String[] Departments = new String[2];


        //start
        System.out.println("Enter Collage name: ");
        String Collage = s.next();
        boolean isRun = true;

        while (isRun){
            System.out.println("------------");
            System.out.println("Welcome to " + Collage);
            System.out.println("This is the menu to your collage admin system");
            System.out.println("choose one of the next opptions (0-8):");
            System.out.println("------------");
            System.out.println("0 - Exit");
            System.out.println("1 - Add Lecturer ");
            System.out.println("2 - Add Committee");
            System.out.println("3 - Add Department");
            System.out.println("4 - Add Lecturer to Committee");
            System.out.println("5 - Show average of Lecturers  wage");
            System.out.println("6 - Show average of Lecturers  wage of specific Department");
            System.out.println("7 - Show Lucturers info");
            System.out.println("8 - Show Departmens info");

            System.out.println("------------");
            System.out.println("Enter your choose: ");

            int userChoose = s.nextInt();

            if (userChoose == 0 ){
            System.out.println("Good bye!!");
                isRun = false;
            }
            else if (userChoose == 1 ){
                System.out.println("1 - Add Lecturer");
                boolean validName = false;
                String LecturerName="";
                s.nextLine();
                while(!validName){
                    System.out.println("Enter Lecturer name: ");
                    LecturerName = s.nextLine();
                    if( FindNameInArray(LecturerName, Lecturers)){
                        System.out.println("Name already in the list! Please enter a different name.");
                    } else {
                        validName = true;
                    }
                }

                Lecturers = addNewItemToArray(LecturerName, Lecturers);
                ShowItemsInArray(Lecturers);
            }
            else if (userChoose == 2 ){
                System.out.println("2 - Add Committee");
                boolean validName2 = false;
                String CommitteeName = "";
                s.nextLine();
                while(!validName2){
                    System.out.println("Enter Committee name: ");
                    CommitteeName = s.nextLine();
                    if(FindNameInArray(CommitteeName, Committees)){
                        System.out.println("Name already in the list! Please enter a different name.");
                    } else {
                        validName2 = true;
                    }
                }
                Committees = addNewItemToArray(CommitteeName, Committees);
                ShowItemsInArray(Committees);
            }
            else if (userChoose == 3 ){
                System.out.println("3 - Add Department");
                boolean validName3 = false;
                String DepartmentName = "";
                s.nextLine();
                while(!validName3){
                    System.out.println("Enter Department name: ");
                    DepartmentName = s.nextLine();
                    if(FindNameInArray(DepartmentName, Departments)){
                        System.out.println("Name already in the list! Please enter a different name.");
                    } else {
                        validName3 = true;
                    }
                }
                Departments = addNewItemToArray(DepartmentName, Departments);
                ShowItemsInArray(Departments);
            }
            else if (userChoose == 4 ){
                System.out.println("4 - Add Lecturer to Committee");
                if (Lecturers[0] == null){
                    System.out.println("No Lecturers in the list! Please add a Lecturer first.");
                    continue;
                }
                if (Committees[0] == null){
                    System.out.println("No Committees in the list! Please add a Committee first.");
                    continue;
                }
                boolean validLecturer = false;
                String LecturerNameToAdd = "";
                s.nextLine();
                while (!validLecturer) {
                    System.out.println("This is the list of Lecturers in " + Collage + ":");
                    ShowItemsInArray(Lecturers);
                    System.out.println("Enter Lecturer name to add to Committee: ");
                    LecturerNameToAdd = s.nextLine();
                    if (!FindNameInArray(LecturerNameToAdd, Lecturers)) {
                        System.out.println("Lecturer not found in the list! Please enter a valid Lecturer name.");
                    } else {
                        validLecturer = true;
                    }
                }
                boolean validCommittee = false;
                String CommitteeNameToAdd = "";
                while (!validCommittee) {
                    System.out.println("This is the list of Committees in " + Collage + ":");
                    ShowItemsInArray(Committees);
                    System.out.println("Enter Committee name to add Lecturer to: ");
                    CommitteeNameToAdd = s.nextLine();
                    if (!FindNameInArray(CommitteeNameToAdd, Committees)) {
                        System.out.println("Committee not found in the list! Please enter a valid Committee name.");
                    } else {
                        validCommittee = true;
                    }
                }
                System.out.println("Lecturer " + LecturerNameToAdd + " added to Committee " + CommitteeNameToAdd + " successfully!");
            }
            else if (userChoose == 5 ){
                System.out.println("5 Coming Soon...");
            }
            else if (userChoose == 6){
                System.out.println("6 Coming Soon...");
            }
            else if (userChoose == 7){
                System.out.println("7 - Show Lecturers info");
                System.out.println("This is the list of Lecturers in " + Collage + ":");
                ShowItemsInArray(Lecturers);
            }
            else if (userChoose == 8){
                System.out.println("8 - Show Departments info");
                System.out.println("This is the list of Departments in " + Collage + ":");
                ShowItemsInArray(Departments);
            }
            else{
                System.out.println("invalide value!!! ");
            }
        }

    }

}
