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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.Hostel.bo.BOFactory;
import lk.ijse.Hostel.bo.custom.impl.StudentBOImpl;
import lk.ijse.Hostel.dto.StudentDTO;
import lk.ijse.Hostel.util.Navigation;
import lk.ijse.Hostel.util.Routes;
import lk.ijse.Hostel.view.tm.StudentTM;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class StudentFormController {
    public AnchorPane subPane;
    public JFXComboBox cmbGender;
    public JFXTextField txtStudentId;
    public JFXTextField txtTellNo;
    public JFXTextField txtAddress;
    public JFXTextField txtStudentName;
    public JFXButton btnClear;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnSearch;
    public JFXButton btnDelete;
    public Label lblTime;
    public JFXDatePicker dateDOB;
    public TableView tblStudent;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDob;
    public TableColumn colGender;
    public Label lblDate;
    private StudentBOImpl studentBO = (StudentBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.STUDENT);

    public void initialize() throws IOException {
        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("telNo"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        cmbGender.getItems().addAll("Male", "Female");
        getAllStudent();
        setDate();
    }

    public void clearOnAction(ActionEvent actionEvent) {
        txtStudentId.clear();
        txtStudentName.clear();
        txtAddress.clear();
        txtTellNo.clear();
        dateDOB.setValue(null);
        cmbGender.setValue(null);
    }

    public void addOnAction(ActionEvent actionEvent) throws IOException {
        String gender= String.valueOf(cmbGender.getValue());

        if (!txtStudentId.getText().matches("^(S)[-]?[0-9]{3}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid id").show();
            txtStudentId.requestFocus();
            return;
        } else if (!txtStudentName.getText().matches("^[A-z ]{5,30}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtStudentName.requestFocus();
            return;
        } else if (!txtAddress.getText().matches("^[A-z 0-9 \\/\\,]{2,50}[A-z 0-9]{1,50}$")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtAddress.requestFocus();
            return;
        } else if (!txtTellNo.getText().matches("^(07|03|01)[0-9]{8}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid contact number").show();
            txtTellNo.requestFocus();
            return;
        }
        if (btnAdd.getText().equalsIgnoreCase("ADD")) {
                studentBO.saveStudent(new StudentDTO(txtStudentId.getText(), txtStudentName.getText(), txtAddress.getText(), txtTellNo.getText(), dateDOB.getValue(), gender));
                new Alert(Alert.AlertType.CONFIRMATION, "Save student successfully").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION, "Unsuccessfully").show();
        }
    }

    public void getAllStudent() throws IOException {
        ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();
        tblStudent.getItems().clear();
        for (StudentDTO studentDTO : allStudent) {
            tblStudent.getItems().add(new StudentTM(studentDTO.getStudentId(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getTelNo(), studentDTO.getDob(), studentDTO.getGender()
            ));
        }
    }

    public void updateOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.UPDATE_STUDENT, subPane);
    }

    public void searchOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCH_STUDENT, subPane);
    }

    public void deleteOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DELETE_STUDENT, subPane);
    }

    public void refreshOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STUDENT, subPane);
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
