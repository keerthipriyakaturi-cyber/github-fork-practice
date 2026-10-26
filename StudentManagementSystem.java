
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private String course;
    private double marks;

    public Student(int id, String name, String course, double marks) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getGrade() {
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        if (marks >= 70) return "B";
        if (marks >= 60) return "C";
        if (marks >= 40) return "D";
        return "Fail";
    }

    public void display() {
        System.out.println("--------------------------------");
        System.out.println("Student ID : " + id);
        System.out.println("Name       : " + name);
        System.out.println("Course     : " + course);
        System.out.println("Marks      : " + marks);
        System.out.println("Grade      : " + getGrade());
    }
}

public class StudentManagementSystem {

    private static final ArrayList<Student> students =
            new ArrayList<>();

    private static final Scanner sc = new Scanner(System.in);
    private static int nextId = 1;

    public static void main(String[] args) {
        int choice;

        do {
            showMenu();
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Enter a valid number: ");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    displayStatistics();
                    break;
                case 7:
                    System.out.println("Thank you for using the system!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 7);

        sc.close();
    }

    private static void showMenu() {
        System.out.println("\n================================");
        System.out.println("   STUDENT MANAGEMENT SYSTEM");
        System.out.println("================================");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. View Statistics");
        System.out.println("7. Exit");
        System.out.println("================================");
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        System.out.print("Enter course: ");
        String course = sc.nextLine();

        System.out.print("Enter marks (0-100): ");
        double marks = readMarks();

        Student student =
                new Student(nextId++, name, course, marks);

        students.add(student);

        System.out.println("Student added successfully!");
        System.out.println("Assigned ID: " + student.getId());
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\nSTUDENT RECORDS");

        for (Student student : students) {
            student.display();
        }
    }

    private static void searchStudent() {
        System.out.print("Enter student ID to search: ");
        int id = readInt();

        Student student = findStudent(id);

        if (student != null) {
            student.display();
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = readInt();

        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new name: ");
        student.setName(sc.nextLine());

        System.out.print("Enter new course: ");
        student.setCourse(sc.nextLine());

        System.out.print("Enter new marks (0-100): ");
        student.setMarks(readMarks());

        System.out.println("Student updated successfully!");
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = readInt();

        Student student = findStudent(id);

        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void displayStatistics() {
        if (students.isEmpty()) {
            System.out.println("No records available for statistics.");
            return;
        }

        double total = 0;
        double highest = students.get(0).getMarks();
        double lowest = students.get(0).getMarks();
        int passed = 0;

        for (Student student : students) {
            double marks = student.getMarks();

            total += marks;
            highest = Math.max(highest, marks);
            lowest = Math.min(lowest, marks);

            if (marks >= 40) {
                passed++;
            }
        }

        double average = total / students.size();

        System.out.println("\nSTUDENT STATISTICS");
        System.out.println("Total students : " + students.size());
        System.out.printf("Average marks  : %.2f%n", average);
        System.out.println("Highest marks  : " + highest);
        System.out.println("Lowest marks   : " + lowest);
        System.out.println("Passed         : " + passed);
        System.out.println("Failed         : " +
                (students.size() - passed));
    }

    private static Student findStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    private static int readInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid integer: ");
            sc.next();
        }

        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    private static double readMarks() {
        while (true) {
            while (!sc.hasNextDouble()) {
                System.out.print("Enter a valid number: ");
                sc.next();
            }

            double marks = sc.nextDouble();
            sc.nextLine();

            if (marks >= 0 && marks <= 100) {
                return marks;
            }

            System.out.print("Marks must be between 0 and 100: ");
        }
    }
}
