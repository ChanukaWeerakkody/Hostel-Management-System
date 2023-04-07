package lk.ijse.Hostel.bo.custom;

import lk.ijse.Hostel.bo.SuperBO;
import lk.ijse.Hostel.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {
    ArrayList<UserDTO> getAllUser() throws IOException;
    boolean updateUser(UserDTO dto) throws IOException;
    void saveUser(UserDTO dto) throws SQLException, ClassNotFoundException, IOException;
    UserDTO searchUser(String id) throws SQLException, ClassNotFoundException;
    boolean deleteUser(String id) throws SQLException, ClassNotFoundException, IOException;
}
