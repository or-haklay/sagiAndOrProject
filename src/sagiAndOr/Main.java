// Sagi Gilad - 324020825
// Or Haklay - 322307687
package sagiAndOr;
import java.util.Scanner;

public class Main {
    // Sagi Gilad - 324020825
    // Or Haklay - 322307687
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter Collage name: ");
        String collageName = s.next();
        Collage c = new Collage(collageName);

        while (true) {
            System.out.println("------------");
            System.out.println("Welcome to " + c.getCollageName());
            System.out.println("This is the menu to your collage admin system");
            System.out.println("choose one of the next options (0-11):");
            System.out.println("------------");
            System.out.println("0 - Exit");
            System.out.println("1 - Add Lecturer To Collage");
            System.out.println("2 - Add Committee To Collage");
            System.out.println("3 - Add Lecturer to Committee");
            System.out.println("4 - Update CEO to Committee");
            System.out.println("5 - Remove Lecturer From Committee");
            System.out.println("6 - Add Department To Collage");
            System.out.println("7 - Add Lecturer to Department");
            System.out.println("8 - Show average of Lecturers wage");
            System.out.println("9 - Show average of Lecturers wage of specific Department");
            System.out.println("10 - Show Lecturers info");
            System.out.println("11 - Show Committees info");
            System.out.println("------------");
            System.out.println("Enter your choice: ");

            int userChoose = s.nextInt();

            if (userChoose == 0) {
                System.out.println("goodbye");
                break;

            } else if (userChoose == 1) {
                String lecturerName = "";
                boolean validName = false;
                Lecturer.eDegree selectedDegree = null;

                s.nextLine();
                while (!validName) {
                    System.out.println("enter lecturer name: ");
                    String input = s.nextLine();
                    if (!input.isEmpty() && !Tools.findLecturInArray(input, c.getLecturers(), c.getNumOfLecturers())) {
                        lecturerName = input;
                        validName = true;
                    } else {
                        System.out.println("invalid input!");
                    }
                }

                System.out.println("enter lecturer ID: ");
                String lecturerId = s.nextLine();

                while (selectedDegree == null) {
                    System.out.println("choose lecturer degree from list (first_degree, masters, phd, professor):");
                    String input = s.next();
                    if (input.equals("first_degree") || input.equals("masters") || input.equals("phd") || input.equals("professor")) {
                        selectedDegree = Lecturer.eDegree.valueOf(input);
                    } else {
                        System.out.println("invalid input!");
                    }
                }

                s.nextLine();
                System.out.println("enter degree name: ");
                String degreeName = s.nextLine();

                System.out.println("Enter Salary: ");
                int salary = s.nextInt();

                Lecturer l1 = new Lecturer(lecturerName, lecturerId, selectedDegree, degreeName, salary);
                c.addLecturer(l1);
                System.out.println(l1.toString());

            } else if (userChoose == 2) {
                if (c.getNumOfLecturers() < 1) {
                    System.out.println("You have to add at least one lecturer!");
                    continue;
                }

                String committeeName;
                s.nextLine();
                do {
                    System.out.println("Enter committee name: ");
                    committeeName = s.nextLine();
                    if (!Tools.findCommitteeInArray(committeeName, c.getCommittees(), c.getNumOfCommittees())) {
                        break;
                    }
                    System.out.println("committee already exists!");
                } while (true);

                boolean validCeo = false;
                Lecturer l = null;
                do {
                    System.out.println("Enter ceo name: ");
                    String ceoName = s.nextLine();
                    if (Tools.findLecturInArray(ceoName, c.getLecturers(), c.getNumOfLecturers())) {
                        l = Tools.getLecturer(ceoName, c.getLecturers());
                        if (l.getDegree() == Lecturer.eDegree.phd || l.getDegree() == Lecturer.eDegree.professor) {
                            validCeo = true;
                        } else {
                            System.out.println("invalid lecturer degree! must be phd or professor. try again!");
                        }
                    } else {
                        System.out.println("invalid lecturer name! try again!");
                    }
                } while (!validCeo);

                Committee com = new Committee(committeeName, l);
                if (c.addCommittee(com)) {
                    System.out.println("committee added successfully!");
                }

            } else if (userChoose == 3) {
                if (c.getNumOfCommittees() < 1) {
                    System.out.println("You have to add at least one Committee!");
                    continue;
                }

                String committeeName;
                String lecturerName;

                s.nextLine();
                do {
                    System.out.println("Enter committee name: ");
                    committeeName = s.nextLine();
                    if (Tools.findCommitteeInArray(committeeName, c.getCommittees(), c.getNumOfCommittees())) {
                        break;
                    }
                    System.out.println("invalid committee name! try again!");
                } while (true);

                do {
                    System.out.println("Enter lecturer name: ");
                    lecturerName = s.nextLine();
                    if (Tools.findLecturInArray(lecturerName, c.getLecturers(), c.getNumOfLecturers())) {
                        break;
                    }
                    System.out.println("invalid lecturer name! try again!");
                } while (true);

                c.addLecturerToCommittee(Tools.getLecturer(lecturerName, c.getLecturers()),
                        Tools.getCommittee(committeeName, c.getCommittees()));

            } else if (userChoose == 4) {
                if (c.getNumOfCommittees() < 1) {
                    System.out.println("You have to add at least one Committee!");
                    continue;
                }

                Committee com = null;
                Lecturer l = null;

                s.nextLine();
                do {
                    System.out.println("Enter committee name: ");
                    String committeeName = s.nextLine();
                    if (Tools.findCommitteeInArray(committeeName, c.getCommittees(), c.getNumOfCommittees())) {
                        com = c.getCommitteeByName(committeeName);
                        break;
                    }
                    System.out.println("invalid committee name! try again!");
                } while (true);

                do {
                    System.out.println("Enter lecturer name: ");
                    String lecturerName = s.nextLine();
                    if (Tools.findLecturInArray(lecturerName, c.getLecturers(), c.getNumOfLecturers())) {
                        l = c.getLecturerByName(lecturerName);
                        break;
                    }
                    System.out.println("invalid lecturer name! try again!");
                } while (true);

                if (c.setCeo(com, l)) {
                    System.out.println("CEO updated successfully!");
                } else {
                    System.out.println("failed to update CEO!");
                }

            } else if (userChoose == 5) {
                if (c.getNumOfCommittees() < 1) {
                    System.out.println("You have to add at least one Committee!");
                    continue;
                }

                Committee com = null;
                Lecturer l = null;

                s.nextLine();
                do {
                    System.out.println("Enter committee name: ");
                    String committeeName = s.nextLine();
                    if (Tools.findCommitteeInArray(committeeName, c.getCommittees(), c.getNumOfCommittees())) {
                        com = c.getCommitteeByName(committeeName);
                        break;
                    }
                    System.out.println("invalid committee name! try again!");
                } while (true);

                do {
                    System.out.println("Enter lecturer name: ");
                    String lecturerName = s.nextLine();
                    if (Tools.findLecturInArray(lecturerName, com.getLecturers(), com.getNumOfLecturers())) {
                        l = c.getLecturerByName(lecturerName);
                        break;
                    }
                    System.out.println("invalid lecturer name! try again!");
                } while (true);

                if (c.removeLecturerFromCommittee(com, l)) {
                    System.out.println("lecturer removed from committee successfully!");
                } else {
                    System.out.println("failed to remove lecturer from committee!");
                }

            } else if (userChoose == 6) {
                s.nextLine();
                String departmentName;
                do {
                    System.out.print("Enter department name: ");
                    departmentName = s.nextLine();
                    if (!Tools.findDepartmentInArray(departmentName, c.getDepartments(), c.getNumOfDepartments())) {
                        break;
                    }
                    System.out.println("department already exists!");
                } while (true);

                System.out.println("Enter number of students: ");
                int numOfStudents = s.nextInt();
                Department d1 = new Department(numOfStudents, departmentName);
                c.addDepartment(d1);
                System.out.println("Name: " + d1.getName() + " | Number of students: " + d1.getNumOfStudents());

            } else if (userChoose == 7) {
                if (c.getNumOfLecturers() < 1 || c.getNumOfDepartments() < 1) {
                    System.out.println("You need at least one lecturer and one department!");
                    continue;
                }
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

            } else if (userChoose == 8) {
                System.out.println("average salary of all lecturers: " + c.getWageAve());

            } else if (userChoose == 9) {
                if (c.getNumOfDepartments() < 1) {
                    System.out.println("You have to add at least one Department!");
                    continue;
                }
                System.out.println("choose department number for avg");
                System.out.println(Tools.showDepartmentsByIndex(c.getDepartments(), c.getNumOfDepartments()));
                int numOfDep = s.nextInt();
                if (numOfDep < 0 || numOfDep >= c.getNumOfDepartments()) {
                    System.out.println("invalid department number!");
                } else {
                    System.out.println(c.getDepartments()[numOfDep].getWageAve());
                }

            } else if (userChoose == 10) {
                System.out.println(c.getLecturersInfo());

            } else if (userChoose == 11) {
                System.out.println(c.getCommitteesInfo());

            } else {
                System.out.println("invalid value!");
            }
        }
    }
}
