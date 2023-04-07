package lk.ijse.Hostel.bo.custom.impl;

import lk.ijse.Hostel.bo.custom.UserBO;
import lk.ijse.Hostel.dao.DAOFactory;
import lk.ijse.Hostel.dao.custom.impl.UserDAOImpl;
import lk.ijse.Hostel.dto.UserDTO;
import lk.ijse.Hostel.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    UserDAOImpl userDAO = (UserDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public ArrayList<UserDTO> getAllUser() throws IOException {
        ArrayList<User> all = userDAO.getAll();
        ArrayList<UserDTO> ud = new ArrayList<>();
        for (User user : all) {
            ud.add(new UserDTO(
                    user.getUserId(),
                    user.getName(),
                    user.getUserName(),
                    user.getPassword()
            ));
        }
        return ud;
    }

    @Override
    public boolean updateUser(UserDTO dto) throws IOException {
        return userDAO.update(new User(dto.getUserId(), dto.getName(), dto.getUserName(), dto.getPassword()));
    }

    @Override
    public void saveUser(UserDTO dto) throws SQLException, ClassNotFoundException, IOException {
        userDAO.save(new User(dto.getUserId(), dto.getName(), dto.getUserName(), dto.getPassword()));
    }

    @Override
    public UserDTO searchUser(String id) throws SQLException, ClassNotFoundException {
        User ent = userDAO.search(id);
        return new UserDTO(ent.getUserId(), ent.getName(), ent.getUserName(), ent.getPassword());
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException, IOException {
        return userDAO.delete(id);
    }
}
