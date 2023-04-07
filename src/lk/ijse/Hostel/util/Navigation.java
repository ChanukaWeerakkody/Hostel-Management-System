package lk.ijse.Hostel.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();
        switch (route){
            case LOG:
                window.setTitle("Log iN");
                initUI("LogInForm.fxml");
                break;
            case DASHBOARD:
                window.setTitle("DashBoard");
                initUI("DashBoard.fxml");
                break;
            case STUDENT:
                window.setTitle("Student");
                initUI("StudentForm.fxml");
                break;
            case SEARCH_STUDENT:
                window.setTitle("Student");
                initUI("StudentSearchForm.fxml");
                break;
            case DELETE_STUDENT:
                window.setTitle("Student");
                initUI("StudentDeleteForm.fxml");
                break;
            case UPDATE_STUDENT:
                window.setTitle("Student");
                initUI("StudentUpdateForm.fxml");
                break;
            case ROOM:
                window.setTitle("Room");
                initUI("RoomsForm.fxml");
                break;
            case SEARCH_ROOM:
                window.setTitle("Room");
                initUI("RoomSearchForm.fxml");
                break;
            case DELETE_ROOM:
                window.setTitle("Room");
                initUI("RoomDeleteForm.fxml");
                break;
            case UPDATE_ROOM:
                window.setTitle("Room");
                initUI("RoomUpdateForm.fxml");
                break;
            case RESERVE:
                window.setTitle("Room");
                initUI("Reservation.fxml");
                break;
            case RESERVE_DETAIL:
                window.setTitle("Room");
                initUI("ReservationDetail.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
        }
    }

    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/Hostel/view/" + location)));
    }
}
