package lk.ijse.Hostel.bo.custom.impl;

import lk.ijse.Hostel.bo.custom.StudentBO;
import lk.ijse.Hostel.dao.DAOFactory;
import lk.ijse.Hostel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.Hostel.dto.StudentDTO;
import lk.ijse.Hostel.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {
    StudentDAOImpl studentDAO = (StudentDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    @Override
    public ArrayList<StudentDTO> getAllStudent() throws IOException {
        ArrayList<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudent = new ArrayList<>();
        for (Student student : all) {
            allStudent.add(new StudentDTO(student.getStudentId(), student.getName(), student.getAddress(), student.getTelNo(), student.getDob(), student.getGender()));
        }
        return allStudent;
    }

    @Override
    public boolean saveStudent(StudentDTO dto) throws IOException {
        return studentDAO.save(new Student(dto.getStudentId(), dto.getName(), dto.getAddress(), dto.getTelNo(), dto.getDob(), dto.getGender()));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws IOException {
        return studentDAO.update(new Student(dto.getStudentId(), dto.getName(), dto.getAddress(), dto.getTelNo(), dto.getDob(), dto.getGender()
        ));
    }

    @Override
    public boolean deleteStudent(String id) throws IOException {
        return studentDAO.delete(id);
    }

    @Override
    public StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException {
        Student ent = studentDAO.search(id);
        return new StudentDTO(ent.getStudentId(), ent.getName(), ent.getAddress(), ent.getTelNo(),ent.getDob(),ent.getGender());
    }
}
