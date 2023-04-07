package lk.ijse.Hostel.controller;

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

public class RoomUpdateFormController {
    public AnchorPane subPane;
    public JFXTextField txtQty;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtRoomId;
    public JFXComboBox cmbRoomType;
    private RoomBOImpl roomBO = (RoomBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ROOM);
    public void initialize() throws IOException {
        cmbRoomType.getItems().addAll("Non-AC", "Non-AC / Food","AC","AC / Food");
    }

    public void clearOnAction(ActionEvent actionEvent) {
        txtRoomId.clear();
        cmbRoomType.setValue(null);
        txtKeyMoney.clear();
        txtQty.clear();
    }

    private void fillData(RoomDTO roomDTO) {
        txtRoomId.setText(roomDTO.getRoomTypeId());
        txtKeyMoney.setText(String.valueOf(roomDTO.getKeyMoney()));
        txtQty.setText(String.valueOf(roomDTO.getQty()));
        cmbRoomType.getValue();
    }
    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.ROOM, subPane);
    }

    public void txtIdOnAction(ActionEvent actionEvent) {
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

    public void updateOnAction(ActionEvent actionEvent) throws IOException {
        String type= String.valueOf(cmbRoomType.getValue());
        Double keyMoney= Double.valueOf(txtKeyMoney.getText());
        int qty= Integer.parseInt(txtQty.getText());
        if(!txtRoomId.getText().matches("^(R)[-]?[0-9]{3}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid id").show();
            txtRoomId.requestFocus();
            return;
        }else if(!txtKeyMoney.getText().matches("^[1-9][0-9]{1,9}$")){
            new Alert(Alert.AlertType.ERROR, "Invalid price").show();
            txtKeyMoney.requestFocus();
            return;
        }else if(!txtQty.getText().matches("[0-9]+")){
            new Alert(Alert.AlertType.ERROR, "Invalid qty").show();
            txtQty.requestFocus();
            return;
        }
        roomBO.updateRoom(new RoomDTO(txtRoomId.getText(),type,keyMoney,qty));
        new Alert(Alert.AlertType.CONFIRMATION, "Room Updated successfully!").show();

    }
}
