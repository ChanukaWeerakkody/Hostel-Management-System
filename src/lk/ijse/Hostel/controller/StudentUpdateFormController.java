package lk.ijse.Hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Hostel.bo.BOFactory;
import lk.ijse.Hostel.bo.custom.impl.StudentBOImpl;
import lk.ijse.Hostel.dto.StudentDTO;
import lk.ijse.Hostel.util.Navigation;
import lk.ijse.Hostel.util.Routes;
import java.io.IOException;
import java.sql.SQLException;

public class StudentUpdateFormController {
    public AnchorPane subPane;
    public JFXTextField txtId;
    public JFXTextField txtContact;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public JFXButton btnClear;
    public JFXTextField txtDate;
    public JFXTextField txtGender;
    public JFXDatePicker dateDOB;
    public JFXComboBox cmbGender;
    private StudentBOImpl studentBO=(StudentBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.STUDENT);

    public void initialize(){
        cmbGender.getItems().addAll("Male", "Female");
    }
    public void txtIdOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            StudentDTO student = studentBO.searchStudent(id);
            if(student != null) {
                fillData(student);
            } else {
                new Alert(Alert.AlertType.WARNING, "Student Not Found!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void fillData(StudentDTO student) {
        txtId.setText(student.getStudentId());
        txtName.setText(student.getName());
        txtAddress.setText(student.getAddress());
        txtContact.setText(String.valueOf(student.getTelNo()));
        dateDOB.getValue();
        cmbGender.getValue();
    }

    public void clearOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDate.clear();
        cmbGender.setValue(null);
    }

    public void updateOnAction(ActionEvent actionEvent) {
        try {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String gender = String.valueOf(cmbGender.getValue());

        if (!txtId.getText().matches("^(S)[-]?[0-9]{3}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid id").show();
            txtId.requestFocus();
            return;
        } else if (!txtName.getText().matches("^[A-z ]{5,30}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            return;
        } else if (!txtAddress.getText().matches("^[A-z 0-9 \\/\\,]{2,50}[A-z 0-9]{1,50}$")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtAddress.requestFocus();
            return;
        } else if (!txtContact.getText().matches("^(07|03|01)[0-9]{8}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid contact number").show();
            txtContact.requestFocus();
            return;
        }
        studentBO.updateStudent(new StudentDTO(id,name,address,contact,dateDOB.getValue(),gender));
        new Alert(Alert.AlertType.CONFIRMATION, "Student Updated successfully!").show();

    } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.STUDENT, subPane);
    }
}
