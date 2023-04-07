package lk.ijse.Hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.Hostel.bo.BOFactory;
import lk.ijse.Hostel.bo.custom.impl.RoomBOImpl;
import lk.ijse.Hostel.dto.RoomDTO;
import lk.ijse.Hostel.dto.StudentDTO;
import lk.ijse.Hostel.util.Navigation;
import lk.ijse.Hostel.util.Routes;
import lk.ijse.Hostel.view.tm.RoomTM;
import lk.ijse.Hostel.view.tm.StudentTM;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class RoomsFormController {
    public AnchorPane subPane;
    public JFXComboBox cmbRoomType;
    public JFXTextField txtQty;
    public JFXButton btnAdd;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtRoomId;
    public TableView tblRoom;
    public TableColumn colId;
    public TableColumn colType;
    public TableColumn colKeyMoney;
    public TableColumn colQty;
    public Label lblTime;
    public Label lblDate;
    private RoomBOImpl roomBO = (RoomBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ROOM);

    public void initialize() throws IOException {
        colId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        cmbRoomType.getItems().addAll("Non-AC", "Non-AC / Food","AC","AC / Food");
        getAllRooms();
        setDate();
    }

    public void searchOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCH_ROOM, subPane);
    }

    public void clearOnAction(ActionEvent actionEvent) {
        txtRoomId.clear();
        cmbRoomType.setValue(null);
        txtKeyMoney.clear();
        txtQty.clear();
    }

    public void addOnAction(ActionEvent actionEvent) throws IOException {
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
        if (btnAdd.getText().equalsIgnoreCase("ADD")) {
            roomBO.saveRoom(new RoomDTO(txtRoomId.getText(), type,keyMoney,qty));
            new Alert(Alert.AlertType.CONFIRMATION, "Save Room successfully").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION, "Unsuccessfully").show();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.UPDATE_ROOM, subPane);
    }

    public void deleteOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DELETE_ROOM, subPane);
    }

    public void refreshOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ROOM, subPane);
    }
    public void setDate() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalDate currentDate = LocalDate.now();

            //Displaying current time in 12hour format with AM/PM
            DateFormat dateFormat = new SimpleDateFormat("hh : mm : ss aa");
            String dateString = dateFormat.format(new Date()).toString();
            lblTime.setText(dateString);



            String month = "";
            switch (currentDate.getMonthValue()) {
                case 1:
                    month = "Jan";
                    break;
                case 2:
                    month = "Feb";
                    break;
                case 3:
                    month = "March";
                    break;
                case 4:
                    month = "April";
                    break;
                case 5:
                    month = "May";
                    break;
                case 6:
                    month = "June";
                    break;
                case 7:
                    month = "July";
                    break;
                case 8:
                    month = "August";
                    break;
                case 9:
                    month = "Sep";
                    break;
                case 10:
                    month = "Oct";
                    break;
                case 11:
                    month = "Nov";
                    break;
                case 12:
                    month = "Dec";
                    break;

            }
            lblDate.setText(currentDate.getYear() + "-" + month + "-" + currentDate.getDayOfMonth());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void getAllRooms() throws IOException {
        ArrayList<RoomDTO> allRoom = roomBO.getAllRoom();
        tblRoom.getItems().clear();
        for (RoomDTO roomDTO : allRoom) {
            tblRoom.getItems().add(new RoomTM(roomDTO.getRoomTypeId(), roomDTO.getType(), roomDTO.getKeyMoney(), roomDTO.getQty()));
        }
    }
}
