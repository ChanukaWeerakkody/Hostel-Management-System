package lk.ijse.Hostel.bo.custom.impl;

import lk.ijse.Hostel.bo.custom.ReservationDetailBO;
import lk.ijse.Hostel.dao.DAOFactory;
import lk.ijse.Hostel.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.Hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.Hostel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.Hostel.dto.CustomDTO;
import lk.ijse.Hostel.dto.ReserveDTO;
import lk.ijse.Hostel.dto.RoomDTO;
import lk.ijse.Hostel.dto.StudentDTO;
import lk.ijse.Hostel.entity.Reserve;
import lk.ijse.Hostel.entity.Room;
import lk.ijse.Hostel.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDetailBOImpl implements ReservationDetailBO {
    RoomDAOImpl roomDAO = (RoomDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    StudentDAOImpl studentDAO = (StudentDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    ReservationDAOImpl reservationDAO = (ReservationDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.RESERVE);

    @Override
    public ArrayList<CustomDTO> getAllReservationDetails() throws IOException {
        ArrayList<Reserve> all = reservationDAO.getAll();

        ArrayList<CustomDTO>arrayList=new ArrayList<>();

        for (Reserve res : all) {
            arrayList.add(new CustomDTO(
                    res.getStudent().getStudentId(), res.getStudent().getName(), res.getStudent().getAddress(), res.getStudent().getTelNo(), res.getStudent().getDob(),
                    res.getStudent().getGender(), res.getResId(), res.getDate(), res.getStudent(), res.getRoom(), res.getStatus(), res.getRoom().getRoomTypeId(),
                    res.getRoom().getType(), res.getRoom().getKeyMoney(), res.getRoom().getQty()));
        }
        return arrayList;
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
    public ArrayList<StudentDTO> getAllStudent() throws IOException {
        ArrayList<Student> all = studentDAO.getAll();

        ArrayList<StudentDTO> allStudent = new ArrayList<>();

        for (Student student : all) {
            allStudent.add(new StudentDTO(student.getStudentId(), student.getName(), student.getAddress(), student.getTelNo(), student.getDob(), student.getGender()));
        }
        return allStudent;
    }

    @Override
    public boolean updateReservation(ReserveDTO dto) throws IOException {
        return reservationDAO.update(new Reserve(dto.getResId(), dto.getDate(), dto.getStudentId(), dto.getRoomId(), dto.getStatus()));
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
}
