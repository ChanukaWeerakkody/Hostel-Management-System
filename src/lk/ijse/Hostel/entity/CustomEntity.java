package lk.ijse.Hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomEntity {
    String studentId;
    String name;
    String address;
    String telNo;
    LocalDate dob;
    String gender;

    private String roomTypeId;
    private String type;
    private double keyMoney;
    private int qty;

    String resId;
    LocalDate date;
    Student regStudentId;
    Room regRoomId;
    String status;
}
