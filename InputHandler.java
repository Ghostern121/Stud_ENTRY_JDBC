// InputHandler.java
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class InputHandler {
    private final Scanner sc = new Scanner(System.in);
    private final StudentDAO dao = new StudentDAO();

    public void startMenu() {
        while (true) {
            System.out.println("\n1. Add Student\n2. Update Student\n3. Delete Student\n4. Search by PRN\n5. View All\n6. Exit");
            System.out.print("Choose option: ");
            int ch = sc.nextInt(); sc.nextLine();

            try {
                switch (ch) {
                    case 1 -> add();
                    case 2 -> update();
                    case 3 -> delete();
                    case 4 -> search();
                    case 5 -> viewAll();
                    case 6 -> System.exit(0);
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void add() throws SQLException {
        System.out.print("Enter PRN: ");
        String prn = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Batch: ");
        String batch = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        dao.addStudent(new Student(prn, name, batch, email));
        System.out.println("Student added.");
    }

    private void update() throws SQLException {
        System.out.print("Enter PRN to update: ");
        String prn = sc.nextLine();
        Student existing = dao.getStudent(prn);
        if (existing == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("New Name: ");
        String name = sc.nextLine();
        System.out.print("New Batch: ");
        String batch = sc.nextLine();
        System.out.print("New Email: ");
        String email = sc.nextLine();

        dao.updateStudent(prn, new Student(prn, name, batch, email));
        System.out.println("Student updated.");
    }

    private void delete() throws SQLException {
        System.out.print("Enter PRN to delete: ");
        String prn = sc.nextLine();
        dao.deleteStudent(prn);
        System.out.println("Student deleted.");
    }

    private void search() throws SQLException {
        System.out.print("Enter PRN to search: ");
        String prn = sc.nextLine();
        Student s = dao.getStudent(prn);
        if (s != null) {
            System.out.println("Name: " + s.getName() + ", Batch: " + s.getBatch() + ", Email: " + s.getEmail());
        } else {
            System.out.println("Student not found.");
        }
    }

    private void viewAll() throws SQLException {
        List<Student> list = dao.getAllStudents();
        for (Student s : list) {
            System.out.println(s.getPrn() + " | " + s.getName() + " | " + s.getBatch() + " | " + s.getEmail());
        }
    }
}