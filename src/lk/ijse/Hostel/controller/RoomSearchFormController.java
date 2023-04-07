package lk.ijse.Hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Hostel.bo.BOFactory;
import lk.ijse.Hostel.bo.custom.impl.RoomBOImpl;
import lk.ijse.Hostel.dto.RoomDTO;
import lk.ijse.Hostel.dto.StudentDTO;
import lk.ijse.Hostel.util.Navigation;
import lk.ijse.Hostel.util.Routes;

import java.io.IOException;
import java.sql.SQLException;

public class RoomSearchFormController {
    public AnchorPane subPane;
    public JFXButton btnClear;
    public JFXComboBox cmbRoomType;
    public JFXTextField txtQty;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtRoomId;
    public JFXTextField txtRoomType;
    private RoomBOImpl roomBO = (RoomBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ROOM);

    public void clearOnAction(ActionEvent actionEvent) {
        txtRoomId.clear();
        txtRoomType.clear();
        txtKeyMoney.clear();
        txtQty.clear();
    }

    private void fillData(RoomDTO roomDTO) {
        txtRoomId.setText(roomDTO.getRoomTypeId());
        txtKeyMoney.setText(String.valueOf(roomDTO.getKeyMoney()));
        txtQty.setText(String.valueOf(roomDTO.getQty()));
        txtRoomType.setText(roomDTO.getType());
    }

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.ROOM, subPane);
    }

    public void searchOnAction(ActionEvent actionEvent) {
        String id = txtRoomId.getText();
        try {
            RoomDTO roomDTO = roomBO.searchRoom(id);
            if(roomDTO != null) {
                fillData(roomDTO);
            } else {
                new Alert(Alert.AlertType.WARNING, "Room Not Found!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
