package lk.ijse.Hostel.bo.custom.impl;

import lk.ijse.Hostel.bo.custom.RoomBO;
import lk.ijse.Hostel.dao.DAOFactory;
import lk.ijse.Hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.Hostel.dto.RoomDTO;
import lk.ijse.Hostel.dto.StudentDTO;
import lk.ijse.Hostel.entity.Room;
import lk.ijse.Hostel.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    RoomDAOImpl roomDAO = (RoomDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    @Override
    public ArrayList<RoomDTO> getAllRoom() throws IOException {
        ArrayList<Room> all = roomDAO.getAll();
        ArrayList<RoomDTO> allRoom = new ArrayList<>();
        for (Room room : all) {
            allRoom.add(new RoomDTO(room.getRoomTypeId(), room.getType(), room.getKeyMoney(), room.getQty()));
        }
        return allRoom;
    }

    @Override
    public boolean saveRoom(RoomDTO dto) throws IOException {
        return roomDAO.save(new Room(dto.getRoomTypeId(), dto.getType(), dto.getKeyMoney(), dto.getQty()));
    }

    @Override
    public boolean updateRoom(RoomDTO dto) throws IOException {
        return roomDAO.update(new Room(dto.getRoomTypeId(), dto.getType(), dto.getKeyMoney(), dto.getQty()));
    }

    @Override
    public boolean deleteRoom(String id) throws IOException {
        return roomDAO.delete(id);
    }

    @Override
    public List<Room> getRoomDataFromType(String type) throws IOException {
        return roomDAO.getRoomDataFromType(type);
    }

    @Override
    public Room getRoom(String id) throws IOException {
        return roomDAO.getRoom(id);
    }

    @Override
    public RoomDTO searchRoom(String id) throws SQLException, ClassNotFoundException {
        Room ent = roomDAO.search(id);
        return new RoomDTO(ent.getRoomTypeId(), ent.getType(), ent.getKeyMoney(), ent.getQty());
    }
}
