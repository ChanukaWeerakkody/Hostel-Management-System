package lk.ijse.Hostel.bo.custom.impl;

import lk.ijse.Hostel.bo.custom.ReservationBO;
import lk.ijse.Hostel.dao.DAOFactory;
import lk.ijse.Hostel.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.Hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.Hostel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.Hostel.dto.ReserveDTO;
import lk.ijse.Hostel.dto.RoomDTO;
import lk.ijse.Hostel.dto.StudentDTO;
import lk.ijse.Hostel.entity.Reserve;
import lk.ijse.Hostel.entity.Room;
import lk.ijse.Hostel.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    RoomDAOImpl roomDAO = (RoomDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    StudentDAOImpl studentDAO = (StudentDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    ReservationDAOImpl reservationDAO = (ReservationDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.RESERVE);

    @Override
    public Room getRoom(String id) throws IOException {
        return roomDAO.getRoom(id);
    }

    @Override
    public Student getStudent(String id) throws IOException {
        return studentDAO.getStudent(id);
    }

    @Override
    public ArrayList<StudentDTO> getAllStudent() throws IOException {
        ArrayList<Student> all = studentDAO.getAll();

        ArrayList<StudentDTO> allStudent = new ArrayList<>();

        for (Student student : all) {
            allStudent.add(new StudentDTO(student.getStudentId(), student.getName(), student.getAddress(), student.getTelNo(), student.getDob(), student.getGender()));
        }

        return allStudent;
    }

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
    public List<ReserveDTO> searchReservedRoomById(String id) throws IOException {
        List<Reserve> reserves = reservationDAO.searchReservedRoomById(id);

        List<ReserveDTO> reserveDTOS = new ArrayList<>();

        for (Reserve reserve : reserves) {
            reserveDTOS.add(new ReserveDTO(reserve.getResId(), reserve.getDate(), reserve.getStudent(), reserve.getRoom(), reserve.getStatus()));
        }
        return reserveDTOS;
    }

    @Override
    public boolean registerStudent(ReserveDTO dto) throws IOException {
        return reservationDAO.save(new Reserve(dto.getResId(), dto.getDate(), dto.getStudentId(), dto.getRoomId(), dto.getStatus()));
    }
}
