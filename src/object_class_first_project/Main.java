package object_class_first_project;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        // Sagi Gilad - 324020825
        // Or Haklay - 322307687
        Scanner s = new Scanner((System.in));

        //start
        System.out.println("Enter Collage name: ");
        String collageName = s.next();
        Collage c = new Collage(collageName);
        boolean isRun = true;

        while (isRun){
            System.out.println("------------");
            System.out.println("Welcome to " + c.getCollageName());
            System.out.println("This is the menu to your collage admin system");
            System.out.println("choose one of the next opptions (0-11):");
            System.out.println("------------");
            System.out.println("0 - Exit");
            System.out.println("1 - Add Lecturer To Collage ");
            System.out.println("2 - Add Committee To Collage");
            System.out.println("3 - Add Lecturer to Committee");
            System.out.println("4 - Update CEO to Committee");
            System.out.println("5 - Remove Lecturer From Committee");
            System.out.println("6 - Add Department To Collage");
            System.out.println("7 - Add Lecturer to Department");
            System.out.println("8 - Show average of Lecturers  wage");
            System.out.println("9 - Show average of Lecturers  wage of specific Committee");
            System.out.println("10 - Show Lucturers info");
            System.out.println("11 - Show Committees info");
            System.out.println("------------");
            System.out.println("Enter your choose: ");

            int userChoose = s.nextInt();

            if (userChoose == 0 ){
                System.out.println("goodbye");
                break;
            }
            else if (userChoose == 1 ){
                System.out.println("enter lecturer name: ");
                s.nextLine();
                String lecturerName = s.nextLine();
                Lecturer.eDegree selectedDegree = null;
                while (selectedDegree == null) {
                    System.out.println("choose lecturer degree from list (first_degree, masters, phd):");
                    String input = s.next();
                    if (input.equals("first_degree") || input.equals("masters") || input.equals("phd") ){
                        selectedDegree = Lecturer.eDegree.valueOf(input);

                    }
                    else{
                        System.out.println("invalid input!");
                    }
                }

                System.out.println("Enter Salary: ");
                int salary = s.nextInt();

                Lecturer l1 = new Lecturer(lecturerName, selectedDegree, salary);
                c.addLecturer(l1);
                System.out.println(l1.toString());


            }

            else if (userChoose == 2 ) {

                boolean validComm = false;
                String CommitteesName;
                s.nextLine(); // consume leftover newline from nextInt()
                do {
                    System.out.println("Enter committee name: ");
                    CommitteesName = s.nextLine();
                    if (!Tools.findCommitteeInArray(CommitteesName, c.getCommittees(), c.getNumOfCommittees())) {
                        validComm = true;
                    } else {
                        System.out.println("commettiee is already exist!");

                    }

                } while (!validComm);

                ////ceo check
                boolean validceo = false;
                Lecturer l = null;
                do {
                    System.out.println("Enter ceo name: ");
                    String ceoName = s.nextLine();
                    if (Tools.findLecturInArray(ceoName, c.getLecturers(), c.getNumOfLecturers())) {
                        l = Tools.getLecturer(ceoName, c.getLecturers());
                        if (l.getDegree() == Lecturer.eDegree.phd) {
                            validceo = true;
                        } else {
                            System.out.println("invalid lecturer degree! try again!");
                        }
                    } else {
                        System.out.println("invalid lecture name! try again!");
                    }

                } while (!validceo);

                Committee com = new Committee(CommitteesName, l);
                if(c.addCommittee(com)){
                    System.out.println("committee is added successfully!");
                }
            }

            else if (userChoose == 3 ){
                boolean validComm=false;
                boolean validLecturer=false;
                String committeeName;
                String lucturerName;

                s.nextLine(); // consume leftover newline from nextInt()
                do {
                    System.out.println("Enter committee name: ");
                    committeeName = s.nextLine();
                    if(Tools.findCommitteeInArray(committeeName,c.getCommittees(),c.getNumOfCommittees())){
                        validComm=true;
                    }
                    else{
                        System.out.println("invalid committee name! try again!");
                    }
                }while(!validComm);

                do {
                    System.out.println("Enter lucturer name: ");
                    lucturerName = s.nextLine();
                    if(Tools.findLecturInArray(lucturerName,c.getLecturers(),c.getNumOfLecturers())){
                        validLecturer=true;
                    }
                    else{
                        System.out.println("invalid lucturer name! try again!");
                    }
                }while(!validLecturer);

                if(Tools.getLecturer(lucturerName, c.getLecturers()) != null){
                    c.addLecturerToCommittee(Tools.getLecturer(lucturerName, c.getLecturers()), Tools.getCommittee(committeeName, c.getCommittees()));
                }


            }
            else if (userChoose == 4 ){
                boolean validComm=false;
                boolean validLecturer=false;
                String committeeName;
                String lucturerName;

                s.nextLine(); // consume leftover newline from nextInt()
                do {
                    System.out.println("Enter committee name: ");
                    committeeName = s.nextLine();
                    if(Tools.findCommitteeInArray(committeeName,c.getCommittees(),c.getNumOfCommittees())){
                        validComm=true;
                    }
                    else{
                        System.out.println("invalid committee name! try again!");
                    }
                }while(!validComm);

                do {
                    System.out.println("Enter lucturer name: ");
                    lucturerName = s.nextLine();
                    if(Tools.findLecturInArray(lucturerName,c.getLecturers(),c.getNumOfLecturers())){
                        validLecturer=true;
                    }
                    else{
                        System.out.println("invalid lucturer name! try again!");
                    }
                }while(!validLecturer);

                boolean isDone = c.setCeo(Tools.getCommittee(committeeName, c.getCommittees()), Tools.getLecturer(lucturerName, c.getLecturers()));
                if(isDone){
                    System.out.println("committee is added successfully!");
                }else {
                    System.out.println("invalid committee name! try again!");
                }

            }
            else if (userChoose == 5 ){
                System.out.println("choose lecturer number");

                System.out.println(Tools.showLecturerByIndex(c.getLecturers(),c.getNumOfLecturers()));
                int LectureIndex = s.nextInt();
                Lecturer toRemove= c.getLecturers()[LectureIndex];
                System.out.println("choose the committee to remove lecturer from");
                System.out.println(Tools.showCommiteesByIndex(toRemove.getCommittee(),toRemove.getNumOfCommittees()));
                int commetiiChoose = s.nextInt();
                Committee cTemp=toRemove.getCommittee()[commetiiChoose];

                c.removeLecturerFromCommittee(toRemove, cTemp);





            }
            else if (userChoose == 6){
                s.nextLine();
                System.out.print("Enter commit tee name: ");
                String departmentName = s.nextLine();
                System.out.println("Enter number of students: ");
                int numOfStudents = s.nextInt();
                Department d1 = new Department(numOfStudents,departmentName);
                c.addDepartment(d1);
                System.out.print("Name:" + d1.getName() + " | Number of students: " + d1.getNumOfStudents());
            }
            else if (userChoose == 7){
                System.out.println("choose lecturer number");
                System.out.println(Tools.showLecturerByIndex(c.getLecturers(), c.getNumOfLecturers()));
                int lecIndex = s.nextInt();
                Lecturer lecToAdd = c.getLecturers()[lecIndex];

                System.out.println("choose department number");
                System.out.println(Tools.showDepartmentsByIndex(c.getDepartments(), c.getNumOfDepartments()));
                int depIndex = s.nextInt();
                Department depToAdd = c.getDepartments()[depIndex];

                if (c.addLecturerToDepartment(depToAdd, lecToAdd)) {
                    System.out.println("lecturer added to department successfully!");
                } else {
                    System.out.println("failed to add lecturer to department!");
                }
            }
            else if (userChoose == 8){
                System.out.println("average salary of all lecturers");
                System.out.println(Tools.getWageAve(c.getLecturers(),c.getNumOfLecturers()));

            }
            else if (userChoose == 9){
                System.out.println("choose department number for avg");
                System.out.println(Tools.showDepartmentsByIndex(c.getDepartments(),c.getNumOfDepartments()));
                int numOfDep = s.nextInt();
                if (numOfDep < 0 || numOfDep >= c.getNumOfDepartments()) {
                    System.out.println("invalid department number!");
                } else {
                    System.out.println(c.getDepartments()[numOfDep].getWageAve());
                }

            }
            else if (userChoose == 10){
                System.out.println(c.getLecturersInfo());

            }
            else if (userChoose == 11){
                System.out.println(c.getCommitteesInfo());

            }
            else{
                System.out.println("invalide value!!! ");
            }
        }

    }

}
