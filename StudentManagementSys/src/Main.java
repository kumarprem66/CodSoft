import studentmanagementSystem.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    static StudentDao studentDao = new StudentDaoImpl();

    public static void main(String[] args) throws NoRecordFoundException {

        Scanner sc = new Scanner(System.in);
        int choice;
        boolean again = true;
        while (again) {
            try {
                do {

                    System.out.println("1. Add Student");
                    System.out.println("2. View Students");
                    System.out.println("3. Update Student");
                    System.out.println("4. Delete Student");
                    System.out.println("5. Search Student By city");
                    System.out.println("0. Exit");
                    System.out.print("Enter Selection ");
                    choice = sc.nextInt();

                    switch (choice) {
                        case 1 -> addStudentUI(sc);
                        case 2 -> viewStudent();
                        case 3 -> updateStudentUI(sc);
                        case 4 -> deleteStudentUI(sc);
                        case 5 -> searchStudentByJoiningDateRange(sc);
                        case 0 -> System.out.println("Bye Bye");
                        default -> System.out.println("Invalid Selection please try again later");
                    }

                } while (choice != 0);
                sc.close();
                again = false;
            } catch (Exception e) {
                System.out.println("------------------- Please enter valid input---------------------");
                sc.nextLine();

            }
        }


    }

    private static void searchStudentByJoiningDateRange(Scanner sc) {

        boolean is_okay = true;
        while (is_okay) {
            try {
                System.out.print("Enter city name ");
                String city = sc.next();

                try {
                    List<Student> students = studentDao.searchStudentByCity(city);

                    Consumer<Student> con = student -> System.out.println(student.toString());
                    students.forEach(con);
                    is_okay = false;
                } catch (SomethingWentWrongException | NoRecordFoundException ex) {
                    System.out.println(ex.getMessage());
                    is_okay = false;
                }
            } catch (Exception e) {
                System.out.println("========================= Please input valid data ========================");
                sc.nextLine();
            }
        }
    }

    private static void deleteStudentUI(Scanner sc) {
        boolean is_okay = true;
        while (is_okay) {
            try {
                System.out.print("Enter Student roll_no ");
                int roll_no = sc.nextInt();

                if (!studentDao.findStudentByRoll(roll_no)) {

                    System.out.println("++++++++++++++++++ Student does exist with this roll no ++++++++++++++++++++");
                    continue;
                }
                try {

                    if (studentDao.findStudentByRoll(roll_no)) {
                        studentDao.deleteStudent(roll_no);
                        //print success message
                        System.out.println("++++++++++++++++++ Student deleted successfully ++++++++++++++++++++");
                    } else {
                        System.out.println("---------------- Student with roll no does not exist --------------");
                    }


                    is_okay = false;
                } catch (SomethingWentWrongException ex) {
                    System.out.println("------------------ " + ex.getMessage() + " ----------------");
                    is_okay = false;
                }
            } catch (Exception e) {
                System.out.println("========================= Please input valid data ========================");
                sc.nextLine();
            }
        }
    }

    private static void updateStudentUI(Scanner sc) {

        boolean is_okay = true;
        while (is_okay) {
            try {
                System.out.print("Enter Student roll_no ");
                int roll_no = sc.nextInt();

                if (!studentDao.findStudentByRoll(roll_no)) {

                    System.out.println("++++++++++++++++++ Student does exist with this roll no ++++++++++++++++++++");
                    continue;
                }

                System.out.print("Enter Student name ");
                String name = sc.next();

                System.out.print("Enter Age ");
                int age = sc.nextInt();

                System.out.print("Enter City ");
                String city = sc.next();

                StudentDto Student = new StudentDto(name, age, city);

                try {

                    studentDao.updateStudent(Student, roll_no);
                    //print success message
                    System.out.println("++++++++++++++++++++ Student updated successfully +++++++++++++++++++++++");
                    is_okay = false;
                } catch (SomethingWentWrongException ex) {
                    System.out.println("-------------------- " + ex.getMessage() + " ---------------------");
                    is_okay = false;
                }
            } catch (Exception e) {
                System.out.println("========================= Please input valid data =========================");
                sc.nextLine();

            }
        }
    }


    private static void viewStudent() {

        try {
            List<Student> empList = studentDao.getStudentList();
            Consumer<Student> con = student -> System.out.println(student.toString());
            empList.forEach(con);
        } catch (SomethingWentWrongException | NoRecordFoundException ex) {
            System.out.println("Student list is empty");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void addStudentUI(Scanner sc) {

        boolean is_okay = true;
        while (is_okay) {
            try {
                System.out.print("Enter Student roll_no ");
                int roll_no = sc.nextInt();

                if (studentDao.findStudentByRoll(roll_no)) {

                    System.out.println("++++++++++++++++++ Student Already exist with this roll no ++++++++++++++++++++");
                    continue;
                }

                System.out.print("Enter Student name ");
                String name = sc.next();

                System.out.print("Enter age ");
                int age = sc.nextInt();

                System.out.print("Enter City ");
                String city = sc.next();

                Student Student = new Student(name, age, roll_no, city);

                try {

                    studentDao.addStudent(Student);
                    System.out.println("+++++++++++++++++++ Student added successfully +++++++++++++++++++");
                    is_okay = false;
                } catch (SomethingWentWrongException ex) {
                    System.out.println("------------------ Roll no is already present --------------------");
                }
            } catch (Exception e) {

                System.out.println("========================= Please input valid data ========================");
                sc.nextLine();

            }
        }

    }
}