// Sagi Gilad - 324020825
// Or Haklay - 322307687


package sagiAndOr;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {
    // Sagi Gilad - 324020825
    // Or Haklay - 322307687
    private static final String SAVE_FILE = "collage.dat";

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        Collage c = loadCollage();
        if (c == null) {
            System.out.println("Enter Collage name: ");
            String collageName = s.next();
            c = new Collage(collageName);
        }

        while (true) {
            System.out.println("------------");
            System.out.println("Welcome to " + c.getCollageName());
            System.out.println("This is the menu to your collage admin system");
            System.out.println("choose one of the next options (0-16):");
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
            System.out.println("12 - Compare Doctors/Professors by number of articles");
            System.out.println("13 - Compare Committees by number of members");
            System.out.println("14 - Compare Committees by number of articles");
            System.out.println("15 - Duplicate Committee");
            System.out.println("16 - Add Article to Lecturer");
            System.out.println("------------");
            System.out.println("Enter your choice: ");

            int userChoose = s.nextInt();


            switch (userChoose) {
                case 0:
                    saveCollage(c);
                    System.out.println("goodbye");
                    return;

                case 1: {

                    String lecturerName = "";
                    boolean validName = false;
                    Lecturer.eDegree selectedDegree = null;

                    s.nextLine();
                    while (!validName) {
                        System.out.println("enter lecturer name: ");
                        String input = s.nextLine();
                        if (!c.includesLecturer(input)) {
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

                    Lecturer newLec;
                    if (selectedDegree == Lecturer.eDegree.phd) {
                        newLec = new Doctor(lecturerName, lecturerId, degreeName, salary);
                    } else if (selectedDegree == Lecturer.eDegree.professor) {
                        Prof p = new Prof(lecturerName, lecturerId, degreeName, salary);
                        s.nextLine();
                        System.out.println("enter the body that granted the professorship: ");
                        p.setDiplomGiver(s.nextLine());
                        newLec = p;
                    } else {
                        newLec = new Lecturer(lecturerName, lecturerId, selectedDegree, degreeName, salary);
                    }

                    try {
                        c.addLecturer(newLec);
                        System.out.println(newLec.toString());
                    } catch (CollegeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 2: {
                    if (c.getNumOfLecturers() < 1) {
                    System.out.println("You have to add at least one lecturer!");
                    continue;
                    }

                    String committeeName;
                    s.nextLine();
                    do {
                        System.out.println("Enter committee name: ");
                        committeeName = s.nextLine();
                        if (!c.includesCommittee(committeeName)) {
                            break;
                        }
                        System.out.println("committee already exists!");
                    } while (true);

                    boolean validCeo = false;
                    Lecturer l = null;
                    do {
                        System.out.println("Enter ceo name: ");
                        String ceoName = s.nextLine();
                        if (c.includesLecturer(ceoName)) {
                            l = c.getLecturerByName(ceoName);
                            if (l instanceof Doctor) {
                                validCeo = true;
                            } else {
                                System.out.println("invalid lecturer degree! must be phd or professor. try again!");
                            }
                        } else {
                            System.out.println("invalid lecturer name! try again!");
                        }
                    } while (!validCeo);

                    Committee.eCommitteeType selectedType = null;
                    while (selectedType == null) {
                        System.out.println("choose committee degree type (regular, doctors, professors): ");
                        String input = s.nextLine();
                        if (input.equals("regular") || input.equals("doctors") || input.equals("professors")) {
                            selectedType = Committee.eCommitteeType.valueOf(input);
                        } else {
                            System.out.println("invalid input!");
                        }
                    }

                    try {
                        Committee com = new Committee(committeeName, l, selectedType);
                        c.addCommittee(com);
                        System.out.println("committee added successfully!");
                    } catch (CollegeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 3: {
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
                        if (c.includesCommittee(committeeName)) {
                            break;
                        }
                        System.out.println("invalid committee name! try again!");
                    } while (true);

                    do {
                        System.out.println("Enter lecturer name: ");
                        lecturerName = s.nextLine();
                        if (c.includesLecturer(lecturerName)) {
                            break;
                        }
                        System.out.println("invalid lecturer name! try again!");
                    } while (true);

                    try {
                        c.addLecturerToCommittee(c.getLecturerByName(lecturerName),c.getCommitteeByName(committeeName));
                        System.out.println("lecturer added to committee successfully!");
                    } catch (AlreadyMemberException e) {
                        System.out.println(e.getMessage());
                    } catch (CollegeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 4: {
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
                        if (c.includesCommittee(committeeName)) {
                            com = c.getCommitteeByName(committeeName);
                            break;
                        }
                        System.out.println("invalid committee name! try again!");
                    } while (true);

                    do {
                        System.out.println("Enter lecturer name: ");
                        String lecturerName = s.nextLine();

                        if (c.includesLecturer(lecturerName)) {
                            l = c.getLecturerByName(lecturerName);
                            break;
                        }
                        System.out.println("invalid lecturer name! try again!");
                    } while (true);

                    try {
                        c.setCeo(com, l);
                        System.out.println("CEO updated successfully!");
                    } catch (CollegeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 5: {
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
                        if (c.includesCommittee(committeeName)) {
                            com = c.getCommitteeByName(committeeName);
                            break;
                        }
                        System.out.println("invalid committee name! try again!");
                    } while (true);

                    if (com.getNumOfLecturers() < 1) {
                        System.out.println("this committee has no members to remove!");
                        continue;
                    }

                    do {
                        System.out.println("Enter lecturer name: ");
                        String lecturerName = s.nextLine();
                        if (c.includesLecturer(lecturerName)) {
                            l = c.getLecturerByName(lecturerName);
                            break;
                        }
                        System.out.println("invalid lecturer name! try again!");
                    } while (true);

                    try {
                        c.removeLecturerFromCommittee(com, l);
                        System.out.println("lecturer removed from committee successfully!");
                    } catch (CollegeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 6: {
                    s.nextLine();
                    String departmentName;
                    do {
                        System.out.print("Enter department name: ");
                        departmentName = s.nextLine();
                        if (!c.includesDepartment(departmentName)) {
                            break;
                        }
                        System.out.println("department already exists!");
                    } while (true);

                    System.out.println("Enter number of students: ");
                    int numOfStudents = s.nextInt();
                    Department d1 = new Department(numOfStudents, departmentName);
                    try {
                        c.addDepartment(d1);
                        System.out.println("Name: " + d1.getName() + " | Number of students: " + d1.getNumOfStudents());
                    } catch (CollegeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 7: {
                    if (c.getNumOfLecturers() < 1 || c.getNumOfDepartments() < 1) {
                        System.out.println("You need at least one lecturer and one department!");
                        continue;
                    }
                    System.out.println("choose lecturer number");
                    System.out.println(Tools.showLecturerByIndex(c.getLecturers(), c.getNumOfLecturers()));
                    int lecIndex = s.nextInt();
                    if (lecIndex < 0 || lecIndex >= c.getNumOfLecturers()) {
                        System.out.println("invalid lecturer number!");
                        continue;
                    }
                    Lecturer lecToAdd = c.getLecturers().get(lecIndex);

                    System.out.println("choose department number");
                    System.out.println(Tools.showDepartmentsByIndex(c.getDepartments(), c.getNumOfDepartments()));
                    int depIndex = s.nextInt();
                    if (depIndex < 0 || depIndex >= c.getNumOfDepartments()) {
                        System.out.println("invalid department number!");
                        continue;
                    }
                    Department depToAdd = c.getDepartments().get(depIndex);

                    try {
                        c.addLecturerToDepartment(depToAdd, lecToAdd);
                        System.out.println("lecturer added to department successfully!");
                    } catch (CollegeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 8:
                    System.out.println("average salary of all lecturers: " + c.getWageAve());
                    break;

                case 9:
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
                        System.out.println(c.getDepartments().get(numOfDep).getWageAve());
                    }
                    break;

                case 10:
                    System.out.println(c.getLecturersInfo());
                    break;

                case 11:
                    System.out.println(c.getCommitteesInfo());
                    break;

                case 12: {
                    // compare Doctors/Professors by number of articles (descending)
                    ArrayList<Articelable> arr = c.getAricleableLecturers();
                    for (int i = 0; i < arr.size() - 1; i++) {
                        for (int j = 0; j < arr.size() - 1 - i; j++) {
                            if (arr.get(j).compareTo(arr.get(j + 1)) < 0) {
                                Articelable temp = arr.get(j);
                                arr.set(j, arr.get(j + 1));
                                arr.set(j + 1, temp);
                            }
                        }
                    }
                    for (int i = 0; i < arr.size(); i++) {
                        System.out.println(i + ". " + arr.get(i).getName() + " - articles: " + arr.get(i).getNumOfArticles());
                    }
                    break;
                }

                case 13: {
                    // compare committees by number of members
                    if (c.getNumOfCommittees() < 2) {
                        System.out.println("You need at least two committees!");
                        continue;
                    }
                    System.out.println(Tools.showCommitteesByIndex(c.getCommittees(), c.getNumOfCommittees()));
                    System.out.println("choose first committee number:");
                    int i1 = s.nextInt();
                    System.out.println("choose second committee number:");
                    int i2 = s.nextInt();
                    if (i1 < 0 || i1 >= c.getNumOfCommittees() || i2 < 0 || i2 >= c.getNumOfCommittees()) {
                        System.out.println("invalid committee number!");
                        continue;
                    }
                    Committee a = c.getCommittees().get(i1);
                    Committee b = c.getCommittees().get(i2);
                    int r = new CommitteeByMembersComparator().compare(a, b);
                    if (r > 0) {
                        System.out.println(a.getName() + " has more members");
                    } else if (r < 0) {
                        System.out.println(b.getName() + " has more members");
                    } else {
                        System.out.println("both committees have the same number of members");
                    }
                    break;
                }

                case 14: {
                    // compare committees by number of articles
                    if (c.getNumOfCommittees() < 2) {
                        System.out.println("You need at least two committees!");
                        continue;
                    }
                    System.out.println(Tools.showCommitteesByIndex(c.getCommittees(), c.getNumOfCommittees()));
                    System.out.println("choose first committee number:");
                    int i1 = s.nextInt();
                    System.out.println("choose second committee number:");
                    int i2 = s.nextInt();
                    if (i1 < 0 || i1 >= c.getNumOfCommittees() || i2 < 0 || i2 >= c.getNumOfCommittees()) {
                        System.out.println("invalid committee number!");
                        continue;
                    }
                    Committee a = c.getCommittees().get(i1);
                    Committee b = c.getCommittees().get(i2);
                    int r = new CommitteeByArticlesComparator().compare(a, b);
                    if (r > 0) {
                        System.out.println(a.getName() + " has more articles");
                    } else if (r < 0) {
                        System.out.println(b.getName() + " has more articles");
                    } else {
                        System.out.println("both committees have the same number of articles");
                    }
                    break;
                }

                case 15: {
                    // duplicate committee
                    if (c.getNumOfCommittees() < 1) {
                        System.out.println("You have to add at least one Committee!");
                        continue;
                    }
                    System.out.println(Tools.showCommitteesByIndex(c.getCommittees(), c.getNumOfCommittees()));
                    System.out.println("choose committee number to duplicate:");
                    int idx = s.nextInt();
                    if (idx < 0 || idx >= c.getNumOfCommittees()) {
                        System.out.println("invalid committee number!");
                        continue;
                    }
                    try {
                        Committee copy = c.getCommittees().get(idx).duplicate();
                        c.addCommittee(copy);
                        System.out.println("committee duplicated: " + copy.getName());
                    } catch (CollegeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 16: {
                    // add article to a Doctor/Professor
                    if (c.getNumOfLecturers() < 1) {
                        System.out.println("You have to add at least one lecturer!");
                        continue;
                    }
                    System.out.println(Tools.showLecturerByIndex(c.getLecturers(), c.getNumOfLecturers()));
                    System.out.println("choose lecturer number:");
                    int idx = s.nextInt();
                    if (idx < 0 || idx >= c.getNumOfLecturers()) {
                        System.out.println("invalid lecturer number!");
                        continue;
                    }
                    Lecturer lec = c.getLecturers().get(idx);
                    if (!(lec instanceof Articelable)) {
                        System.out.println("only Dr. or Prof. can have articles!");
                        continue;
                    }
                    s.nextLine();
                    System.out.println("enter article name:");
                    String articleName = s.nextLine();
                    try {
                        ((Articelable) lec).addArticel(articleName);
                        System.out.println("article added successfully!");
                    } catch (CollegeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                default:
                    System.out.println("invalid value!");
                    break;


            }

        }
    }

    private static Collage loadCollage() {
        File f = new File(SAVE_FILE);
        if (!f.exists()) return null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            return (Collage) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("could not load saved data: " + e.getMessage());
            return null;
        }
    }

    private static void saveCollage(Collage c) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            out.writeObject(c);
        } catch (IOException e) {
            System.out.println("could not save data: " + e.getMessage());
        }
    }
}
