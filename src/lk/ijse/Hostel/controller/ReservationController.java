package lk.ijse.Hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.Hostel.bo.BOFactory;
import lk.ijse.Hostel.bo.custom.impl.ReservationBOImpl;
import lk.ijse.Hostel.dto.ReserveDTO;
import lk.ijse.Hostel.dto.RoomDTO;
import lk.ijse.Hostel.dto.StudentDTO;
import lk.ijse.Hostel.entity.Room;
import lk.ijse.Hostel.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ReservationController {
    public AnchorPane subPane;
    public Label lblTime;
    public Label lblDate;
    public JFXTextField txtRegistrationId;
    public JFXComboBox cmbSelectStudent;
    public JFXTextField txtStudentName;
    public JFXTextField txtAddress;
    public JFXDatePicker dateDOB;
    public JFXComboBox cmbGender;
    public JFXComboBox cmbSelectRoom;
    public JFXTextField txtRoomType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;
    public JFXTextField txtStatus;
    public Label lblAvailable;
    public Label lblAllRooms;
    public Label lblUsedRooms;
    public Label lblRemainingRooms;
    public JFXButton btnRegister;
    private ReservationBOImpl reservationBO = (ReservationBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.RESERVE);

    public void initialize() throws IOException {
        setDate();
        lblAvailable.setText("................");
        cmbGender.getItems().addAll("Male","FeMale");
        //Get student id to combo box
        for (StudentDTO dto : reservationBO.getAllStudent()) {
            cmbSelectStudent.getItems().add(dto.getStudentId());
        }
        //Get room id to combo box
        for (RoomDTO roomDTO : reservationBO.getAllRoom()) {
            cmbSelectRoom.getItems().add(roomDTO.getRoomTypeId());
        }

        cmbSelectRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if(newValue!=null){
                Room room = null;
                try {
                    room = reservationBO.getRoom(String.valueOf(newValue));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                txtRoomType.setText(room.getType());
                txtKeyMoney.setText(String.valueOf(room.getKeyMoney()));
                txtQty.setText(String.valueOf(room.getQty()));
                lblAllRooms.setText(String.valueOf(room.getQty()));

                try {
                    List<ReserveDTO> reserveDTOS = reservationBO.searchReservedRoomById(String.valueOf(newValue));

                    int count=0;
                    for (ReserveDTO reserveDTO : reserveDTOS) {
                        count++;
                    }

                    int remainQty=Integer.parseInt(txtQty.getText())-count;
                    lblUsedRooms.setText(String.valueOf(count));
                    lblRemainingRooms.setText(String.valueOf(remainQty));

                    if(remainQty==0){
                        lblAvailable.setText("Un-Available");
                    }else{
                        lblAvailable.setText("Available");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        cmbSelectStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                Student student = null;
                try {
                    student = reservationBO.getStudent(String.valueOf(newValue));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                txtStudentName.setText(student.getName());
                txtAddress.setText(student.getAddress());
                cmbGender.getSelectionModel().select(student.getGender());
                dateDOB.setValue(student.getDob());
            }
        });
    }

    public void RegisterOnAction(ActionEvent actionEvent) throws IOException {
        if(lblAvailable.getText().equalsIgnoreCase("Available")) {
            Student student = new Student();
            student.setStudentId(String.valueOf(cmbSelectStudent.getValue()));

            Room room = new Room();
            room.setRoomTypeId(String.valueOf(cmbSelectRoom.getValue()));

            if(!txtRegistrationId.getText().matches("^(RG)[-]?[0-9]{3}$")) {
                new Alert(Alert.AlertType.ERROR, "Invalid id").show();
                txtRegistrationId.requestFocus();
                return;
            }

            reservationBO.registerStudent(new ReserveDTO(txtRegistrationId.getText(), LocalDate.now(), student, room, txtStatus.getText()));
            clear();

            lblAllRooms.setText("00");
            lblUsedRooms.setText("00");
            lblRemainingRooms.setText("00");

            new Alert(Alert.AlertType.CONFIRMATION,"Register Student successfully").show();
        }else{
            new Alert(Alert.AlertType.WARNING,"Register Student unsuccessfully").show();
        }
    }

    private void clear(){
        cmbSelectStudent.setValue(null);
        cmbSelectRoom.setValue(null);
        txtStatus.clear();
    }

    public void ClearOnAction(ActionEvent actionEvent) {
        cmbSelectStudent.setValue(null);
        cmbSelectRoom.setValue(null);
        txtStatus.clear();
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

}
