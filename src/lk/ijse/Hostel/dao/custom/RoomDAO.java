package lk.ijse.Hostel.dao.custom;

import lk.ijse.Hostel.dao.CrudDAO;
import lk.ijse.Hostel.entity.Room;

import java.io.IOException;
import java.util.List;

public interface RoomDAO extends CrudDAO<Room,String> {
    List<Room> getRoomDataFromType(String type) throws IOException;
    Room getRoom(String id) throws IOException;
}
