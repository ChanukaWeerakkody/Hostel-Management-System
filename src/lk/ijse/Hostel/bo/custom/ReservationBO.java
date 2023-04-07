package lk.ijse.Hostel.bo.custom;

import lk.ijse.Hostel.bo.SuperBO;
import lk.ijse.Hostel.dto.ReserveDTO;
import lk.ijse.Hostel.dto.RoomDTO;
import lk.ijse.Hostel.dto.StudentDTO;
import lk.ijse.Hostel.entity.Room;
import lk.ijse.Hostel.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ReservationBO extends SuperBO {
    Room getRoom(String id) throws IOException;

    Student getStudent(String id) throws IOException;

    ArrayList<StudentDTO> getAllStudent() throws IOException;

    ArrayList<RoomDTO> getAllRoom() throws IOException;

    List<ReserveDTO> searchReservedRoomById(String id) throws IOException;

    boolean registerStudent(ReserveDTO dto) throws IOException;
}
