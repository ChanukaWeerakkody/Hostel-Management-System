package lk.ijse.Hostel.dao.custom;

import lk.ijse.Hostel.dao.CrudDAO;
import lk.ijse.Hostel.entity.Reserve;

import java.io.IOException;
import java.util.List;

public interface ReservationDAO extends CrudDAO<Reserve,String> {
    List<Reserve> searchReservedRoomById(String id) throws IOException;
}
