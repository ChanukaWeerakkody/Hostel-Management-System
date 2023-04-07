package lk.ijse.Hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.Hostel.bo.BOFactory;
import lk.ijse.Hostel.bo.custom.impl.ReservationDetailBOImpl;
import lk.ijse.Hostel.dto.CustomDTO;
import lk.ijse.Hostel.dto.ReserveDTO;
import lk.ijse.Hostel.dto.RoomDTO;
import lk.ijse.Hostel.dto.StudentDTO;
import lk.ijse.Hostel.entity.Room;
import lk.ijse.Hostel.entity.Student;
import lk.ijse.Hostel.view.tm.ReserveDetailTM;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationDetailController {
    public AnchorPane subPane;
    public Label lblTime;
    public Label lblDate;
    public JFXTextField txtUpdateStatus;
    public JFXComboBox cmbUpdateSelectStudent;
    public JFXComboBox cmbUpdateSelectRoom;
    public JFXTextField txtReserveID;
    public TableView tblReserve;
    public JFXComboBox cmbSearchRoomId;
    public JFXComboBox cmbRoomType;
    public JFXButton btnUpdate;
    public TableColumn colResId;
    public TableColumn colDate;
    public TableColumn colStudentId;
    public TableColumn colRoomId;
    public TableColumn colStatus;
    public TableColumn colKeymoney;
    LocalDate date;

    private ReservationDetailBOImpl reservationDetailBO = (ReservationDetailBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.RESERVE_DETAIL);

    public void initialize() throws IOException {
        loadAllReservation();
        setDate();

        colResId.setCellValueFactory(new PropertyValueFactory<>("resId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colKeymoney.setCellValueFactory(new PropertyValueFactory<>("remainKeyMoney"));

        loadCmbData();
        loadSearchReserve();

        txtReserveID.setDisable(true);
        cmbUpdateSelectStudent.setDisable(true);
        cmbUpdateSelectRoom.setDisable(true);
        txtUpdateStatus.setDisable(true);
    }

    public void loadAllReservation() throws IOException {
        tblReserve.getItems().clear();

        ArrayList<CustomDTO> allRes = reservationDetailBO.getAllReservationDetails();
        for (CustomDTO all : allRes) {

            String remain = "";
            String status = all.getStatus();

            if (status.equalsIgnoreCase("Paid")) {
                remain = "---";
            } else if (status.equalsIgnoreCase("Non-Paid")) {
                remain = String.valueOf(all.getKeyMoney());
            } else {
                if (!status.equals("")) {
                    double paid = Double.parseDouble(status);
                    remain = String.valueOf(all.getKeyMoney() - paid);
                }
            }
            tblReserve.getItems().add(new ReserveDetailTM(all.getResId(), all.getDate(), all.getRegStudentId().getStudentId(), all.getRegRoomId().getRoomTypeId(), all.getStatus(), remain));
        }
    }

    public void menuEditOnAction(ActionEvent actionEvent) {
        txtReserveID.setDisable(false);
        cmbUpdateSelectStudent.setDisable(false);
        cmbUpdateSelectRoom.setDisable(false);
        txtUpdateStatus.setDisable(false);

        ReserveDetailTM selectedItem = (ReserveDetailTM) tblReserve.getSelectionModel().getSelectedItem();

        txtReserveID.setText(selectedItem.getResId());
        txtReserveID.setEditable(false);
        date = selectedItem.getDate();
        cmbUpdateSelectStudent.getSelectionModel().select(selectedItem.getStudentId());
        cmbUpdateSelectRoom.getSelectionModel().select(selectedItem.getRoomId());

        txtUpdateStatus.setText(selectedItem.getStatus());
    }

    public void btnReserveUpdateOnAction(ActionEvent actionEvent) throws IOException {
        txtReserveID.setDisable(true);
        cmbUpdateSelectStudent.setDisable(true);
        cmbUpdateSelectRoom.setDisable(true);
        txtUpdateStatus.setDisable(true);

        Student student = new Student();
        student.setStudentId(String.valueOf(cmbUpdateSelectStudent.getValue()));

        Room room = new Room();
        room.setRoomTypeId(String.valueOf(cmbUpdateSelectRoom.getValue()));

        boolean b = reservationDetailBO.updateReservation(new ReserveDTO(txtReserveID.getText(), date, student, room, txtUpdateStatus.getText()));

        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "Reservation Updated!!").show();
            loadAllReservation();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Something Went Wrong").show();
        }
    }

    public void RoomClearOnAction(ActionEvent actionEvent) {

    }

    private void loadCmbData() throws IOException {
        for (RoomDTO roomDTO : reservationDetailBO.getAllRoom()) {
            cmbUpdateSelectRoom.getItems().add(roomDTO.getRoomTypeId());
            cmbSearchRoomId.getItems().add(roomDTO.getRoomTypeId());
            cmbRoomType.getItems().add(roomDTO.getType());
        }
        for (StudentDTO dto : reservationDetailBO.getAllStudent()) {
            cmbUpdateSelectStudent.getItems().add(dto.getStudentId());
        }
    }

    private void loadSearchReserve() {
        cmbSearchRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                tblReserve.getItems().clear();
                try {
                    List<ReserveDTO> reserveDTOS = reservationDetailBO.searchReservedRoomById(String.valueOf(newValue));

                    for (ReserveDTO reserveDTO : reserveDTOS) {
                        cmbRoomType.getSelectionModel().select(reserveDTO.getRoomId().getType());

                        String remain = "";
                        String status = reserveDTO.getStatus();

                        if (status.equalsIgnoreCase("Paid")) {
                            remain = "---";
                        } else if (status.equalsIgnoreCase("Non-Paid")) {
                            remain = String.valueOf(reserveDTO.getRoomId().getKeyMoney());
                        } else {
                            if (!status.equals("")) {
                                double paid = Double.parseDouble(status);
                                remain = String.valueOf(reserveDTO.getRoomId().getKeyMoney() - paid);
                            }
                        }
                        tblReserve.getItems().add(new ReserveDetailTM(reserveDTO.getResId(), reserveDTO.getDate(), reserveDTO.getStudentId().getStudentId(), reserveDTO.getRoomId().getRoomTypeId(), reserveDTO.getStatus(), remain));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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
