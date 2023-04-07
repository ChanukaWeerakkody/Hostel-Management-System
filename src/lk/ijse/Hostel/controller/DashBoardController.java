package lk.ijse.Hostel.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Hostel.util.Navigation;
import lk.ijse.Hostel.util.Routes;

import java.io.IOException;
import java.util.Optional;

public class DashBoardController {
    public AnchorPane mainPane;
    public AnchorPane subPane;

    public void btnAboutUsOnAction(ActionEvent actionEvent) {
    }

    public void btnContactOnAction(ActionEvent actionEvent) {
    }

    public void logOutOnAction(MouseEvent mouseEvent) throws IOException {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure want to logOut?");
        Optional<ButtonType> option=alert.showAndWait();

        if(option.get().equals(ButtonType.OK)){
            Navigation.navigate(Routes.LOG,mainPane);
        }else return;
    }

    public void dashBoardOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD, mainPane);
    }

    public void studentOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.STUDENT, subPane);
    }

    public void roomsOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.ROOM, subPane);
    }

    public void reservationOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.RESERVE, subPane);
    }

    public void detailsOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.RESERVE_DETAIL, subPane);
    }
}
