package lk.ijse.Hostel.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentTM {
    String studentId;
    String name;
    String address;
    String telNo;
    LocalDate dob;
    String gender;
}
