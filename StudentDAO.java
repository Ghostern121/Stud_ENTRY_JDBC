// StudentDAO.java
import java.sql.*;
import java.util.*;

public class StudentDAO {
    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (prn, name, batch, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getPrn());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getBatch());
            stmt.setString(4, student.getEmail());
            stmt.executeUpdate();
        }
    }

    public void updateStudent(String prn, Student student) throws SQLException {
        String sql = "UPDATE students SET name = ?, batch = ?, email = ? WHERE prn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getBatch());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, prn);
            stmt.executeUpdate();
        }
    }

    public void deleteStudent(String prn) throws SQLException {
        String sql = "DELETE FROM students WHERE prn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prn);
            stmt.executeUpdate();
        }
    }

    public Student getStudent(String prn) throws SQLException {
        String sql = "SELECT * FROM students WHERE prn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Student(rs.getString("prn"), rs.getString("name"), rs.getString("batch"), rs.getString("email"));
            }
            return null;
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Student(rs.getString("prn"), rs.getString("name"), rs.getString("batch"), rs.getString("email")));
            }
        }
        return list;
    }
}