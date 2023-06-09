package lk.ijse.Hostel.dao;

import lk.ijse.Hostel.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.Hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.Hostel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.Hostel.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    //factory method
    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case USER:
                return new UserDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVE:
                return new ReservationDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        USER,STUDENT,ROOM,RESERVE
    }
}
