package studentmanagementSystem;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {

     void addStudent(Student emp) throws SomethingWentWrongException;
     void updateStudent(StudentDto emp,Integer id) throws SomethingWentWrongException;
     void deleteStudent(Integer id) throws SomethingWentWrongException;
     List<Student> getStudentList() throws SomethingWentWrongException, NoRecordFoundException, SQLException;
     List<Student> searchStudentByCity(String city) throws SomethingWentWrongException, NoRecordFoundException;

     boolean findStudentByRoll(Integer roll_no) throws NoRecordFoundException;
}

