package lk.ijse.Hostel.bo.custom;

import lk.ijse.Hostel.bo.SuperBO;
import lk.ijse.Hostel.dto.CustomDTO;
import lk.ijse.Hostel.dto.ReserveDTO;
import lk.ijse.Hostel.dto.RoomDTO;
import lk.ijse.Hostel.dto.StudentDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ReservationDetailBO extends SuperBO {
    ArrayList<CustomDTO> getAllReservationDetails() throws IOException;
    ArrayList<RoomDTO> getAllRoom() throws IOException;
    ArrayList<StudentDTO> getAllStudent() throws IOException;
    boolean updateReservation(ReserveDTO dto) throws IOException;
    List<ReserveDTO> searchReservedRoomById(String id) throws IOException;
}
