package lk.ijse.Hostel.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.Hostel.bo.BOFactory;
import lk.ijse.Hostel.bo.custom.impl.UserBOImpl;
import lk.ijse.Hostel.dto.UserDTO;
import lk.ijse.Hostel.util.Navigation;
import lk.ijse.Hostel.util.Routes;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class LogInFormController {
    public AnchorPane mainPane;
    public JFXTextField txtUsername;
    public Hyperlink linkNewAccount;
    public JFXPasswordField txtPassword;
    public Label lblTime;
    public Label lblDate;
    public JFXTextField txtShowPassword;
    public ImageView lblclose_Eye;
    public ImageView lblOpen_Eye;
    private UserBOImpl userBO = (UserBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.USER);

    String password;
    public void initialize(){
        setDate();
        txtShowPassword.setVisible(false);
        lblOpen_Eye.setVisible(false);
    }


    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        ArrayList<UserDTO> allUser = userBO.getAllUser();

        for (UserDTO userDTO : allUser) {
            if(userDTO.getUserName().equals(txtUsername.getText()) && userDTO.getPassword().equals(txtPassword.getText())){
                Navigation.navigate(Routes.DASHBOARD, mainPane);
            }else{
                new Alert(Alert.AlertType.ERROR,"Login Failed..").show();
            }
        }
    }

    public void linkNewAccountOnAction(ActionEvent actionEvent) {
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

    public void hidePasswordOnAction(KeyEvent keyEvent) {
        password=txtPassword.getText();
        txtShowPassword.setText(password);
    }

    public void showPasswordOnAction(KeyEvent keyEvent) {
        password=txtShowPassword.getText();
        txtPassword.setText(password);
    }

    public void close_Eye_OnAction(MouseEvent mouseEvent) {
        txtShowPassword.setVisible(true);
        lblOpen_Eye.setVisible(true);
        lblclose_Eye.setVisible(false);
        txtPassword.setVisible(false);
    }

    public void open_Eye_OnAction(MouseEvent mouseEvent) {
        txtShowPassword.setVisible(false);
        lblOpen_Eye.setVisible(false);
        lblclose_Eye.setVisible(true);
        txtPassword.setVisible(true);
    }
}
