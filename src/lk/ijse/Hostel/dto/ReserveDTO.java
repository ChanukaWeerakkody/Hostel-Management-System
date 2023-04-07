package lk.ijse.Hostel.dto;

import lk.ijse.Hostel.entity.Room;
import lk.ijse.Hostel.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveDTO {
    String resId;
    LocalDate date;
    Student studentId;
    Room roomId;
    String status;


}
