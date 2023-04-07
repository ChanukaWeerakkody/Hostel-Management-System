package lk.ijse.Hostel.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomTM {
    private String roomTypeId;
    private String type;
    private double keyMoney;
    private int qty;
}
