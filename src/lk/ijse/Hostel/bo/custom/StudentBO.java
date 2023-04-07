package lk.ijse.Hostel.bo.custom;

import lk.ijse.Hostel.bo.SuperBO;
import lk.ijse.Hostel.dto.StudentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    ArrayList<StudentDTO> getAllStudent() throws IOException;

    boolean saveStudent(StudentDTO dto) throws IOException;

    boolean updateStudent(StudentDTO dto) throws IOException;

    boolean deleteStudent(String id) throws IOException;

    StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException;
}
