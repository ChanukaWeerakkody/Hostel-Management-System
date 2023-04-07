package lk.ijse.Hostel.bo.custom;

import lk.ijse.Hostel.bo.SuperBO;
import lk.ijse.Hostel.dto.RoomDTO;
import lk.ijse.Hostel.entity.Room;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RoomBO extends SuperBO {
    ArrayList<RoomDTO> getAllRoom() throws IOException;

    boolean saveRoom(RoomDTO dto) throws IOException;

    boolean updateRoom(RoomDTO dto) throws IOException;

    boolean deleteRoom(String id) throws IOException;

    List<Room> getRoomDataFromType(String type) throws IOException;

    Room getRoom(String id) throws IOException;

    RoomDTO searchRoom(String id) throws SQLException, ClassNotFoundException;
}
