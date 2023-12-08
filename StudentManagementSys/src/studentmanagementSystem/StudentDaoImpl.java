package studentmanagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao{
    @Override
    public void addStudent(Student student) throws SomethingWentWrongException {
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            String query = "INSERT INTO student (name, age, city,roll_no) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getCity());
            ps.setInt(4, student.getRoll_no());
            ps.executeUpdate();
        }catch(ClassNotFoundException | SQLException ex) {
            throw new SomethingWentWrongException("Unable to insert the record now, try again later");
        }finally {
            try {
                DBUtils.closeConnection(conn);
            }catch(SQLException ex) {

                ex.printStackTrace();
            }
        }
    }

    @Override
    public void updateStudent(StudentDto student,Integer roll_no) throws SomethingWentWrongException {

        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            String query = "UPDATE student set name = ?, age = ?, city = ? WHERE roll_no = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getCity());
            ps.setInt(4, roll_no);
            ps.executeUpdate();
        }catch(ClassNotFoundException | SQLException ex) {
            throw new SomethingWentWrongException("Unable to update the record now, try again later");

        }finally {
            try {
                DBUtils.closeConnection(conn);
            }catch(SQLException ex) {

                ex.printStackTrace();
            }
        }
    }

    @Override
    public void deleteStudent(Integer roll_no) throws SomethingWentWrongException {

        Connection conn = null;
        try {
            conn = DBUtils.getConnection();

            String query = "DELETE FROM student WHERE roll_no = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, roll_no);
            ps.executeUpdate();
        }catch(ClassNotFoundException | SQLException ex) {
            throw new SomethingWentWrongException("Unable to delete the record now, try again later");
        }finally {
            try {
                DBUtils.closeConnection(conn);
            }catch(SQLException ex) {

                ex.printStackTrace();
            }
        }

    }

    @Override
    public List<Student> getStudentList() throws SomethingWentWrongException, NoRecordFoundException {
        Connection conn = null;
        List<Student> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            String query = "SELECT roll_no, name, age, city FROM student";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(DBUtils.isResultSetEmpty(rs)) {
                throw new NoRecordFoundException("No Student found");
            }
            while(rs.next()) {
                list.add(new Student(rs.getString(2), rs.getInt(3), rs.getInt(1), rs.getString(4)));
            }

        }catch(ClassNotFoundException | SQLException ex) {
            throw new SomethingWentWrongException("Unable to find the record now, try again later");
        }finally {
            try {
                DBUtils.closeConnection(conn);
            }catch(SQLException ex) {

                ex.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<Student> searchStudentByCity(String city) throws SomethingWentWrongException, NoRecordFoundException {
        Connection conn = null;
        List<Student> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            String query = "SELECT roll_no, name, age, city FROM student where city LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,"%"+city+"%");
            ResultSet rs = ps.executeQuery();
            if(DBUtils.isResultSetEmpty(rs)) {
                throw new NoRecordFoundException("No Student found");
            }
            while(rs.next()) {
                list.add(new Student(rs.getString(2), rs.getInt(3), rs.getInt(1), rs.getString(4)));
            }

        }catch(ClassNotFoundException | SQLException ex) {
            throw new SomethingWentWrongException("Unable to find the record now, try again later");
        }finally {
            try {
                DBUtils.closeConnection(conn);
            }catch(SQLException ex) {

                ex.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public boolean findStudentByRoll(Integer roll_no) {
        Connection conn = null;

        try {
            conn = DBUtils.getConnection();
            String query = "SELECT id FROM student where roll_no = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,roll_no);
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next();


        }catch(ClassNotFoundException | SQLException ex) {

            return false;
        }finally {
            try {
                DBUtils.closeConnection(conn);
            }catch(SQLException ex) {

                ex.printStackTrace();
            }
        }

    }
}
