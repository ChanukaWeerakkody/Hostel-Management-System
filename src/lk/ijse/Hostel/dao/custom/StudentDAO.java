package lk.ijse.Hostel.dao.custom;

import lk.ijse.Hostel.dao.CrudDAO;
import lk.ijse.Hostel.entity.Student;

import java.io.IOException;

public interface StudentDAO extends CrudDAO<Student,String> {
    Student getStudent(String id) throws IOException;
}
