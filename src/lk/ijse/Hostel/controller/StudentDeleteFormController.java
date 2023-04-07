package lk.ijse.Hostel.controller;

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

public class StudentDeleteFormController {
    public AnchorPane subPane;
    public JFXTextField txtContact;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public JFXTextField txtDate;
    public JFXTextField txtGender;
    public JFXTextField txtId;
    private StudentBOImpl studentBO=(StudentBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.STUDENT);

    public void clearOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDate.clear();
        txtGender.clear();
    }

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.STUDENT, subPane);
    }

    private void fillData(StudentDTO student) {
        txtId.setText(student.getStudentId());
        txtName.setText(student.getName());
        txtAddress.setText(student.getAddress());
        txtContact.setText(String.valueOf(student.getTelNo()));
        txtDate.setText(String.valueOf(student.getDob()));
        txtGender.setText(student.getGender());
    }
    public void txtIdOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            StudentDTO customer = studentBO.searchStudent(id);
            if(customer != null) {
                fillData(customer);
            } else {
                new Alert(Alert.AlertType.WARNING, "Student Not Found!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            boolean delete = studentBO.deleteStudent(id);
            if (delete){
                new Alert(Alert.AlertType.INFORMATION,"Student Deleted!").show();
            }else{
                new Alert(Alert.AlertType.WARNING,"Deleted Fail !").show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
