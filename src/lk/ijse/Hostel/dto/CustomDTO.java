package lk.ijse.Hostel.dto;

import lk.ijse.Hostel.entity.Room;
import lk.ijse.Hostel.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomDTO {
    String studentId;
    String name;
    String address;
    String telNo;
    LocalDate dob;
    String gender;
    String resId;
    LocalDate date;
    Student regStudentId;
    Room regRoomId;
    String status;
    private String roomTypeId;
    private String type;
    private double keyMoney;
    private int qty;
}
