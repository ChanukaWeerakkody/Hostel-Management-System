package lk.ijse.Hostel.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreenController implements Initializable {
    public Rectangle rctContainer;
    public Rectangle rctLoading;
    public Label lblLoading;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblLoading=lblLoading;
        Timeline timeline=new Timeline();
        KeyFrame keyFrame=new KeyFrame(Duration.millis(500), actionEvent ->{
            lblLoading.setText("Initializing Application....");
            rctLoading.setWidth(rctContainer.getWidth()*0.2);
        });

        KeyFrame keyFrame1=new KeyFrame(Duration.millis(1000),actionEvent ->{
            lblLoading.setText("Loading Internal Resources....");
            rctLoading.setWidth(rctContainer.getWidth()*0.4);
        });

        KeyFrame keyFrame2=new KeyFrame(Duration.millis(1500),actionEvent ->{
            lblLoading.setText("Loading Images....");
            rctLoading.setWidth(rctContainer.getWidth()*0.6);
        });

        KeyFrame keyFrame3=new KeyFrame(Duration.millis(2000),actionEvent ->{
            lblLoading.setText("Loading UIs....");
            rctLoading.setWidth(rctContainer.getWidth()*0.8);
        });

        KeyFrame keyFrame4=new KeyFrame(Duration.millis(2500),actionEvent ->{
            lblLoading.setText("Welcome to D24 Hostel Management System V4.0.0");
            rctLoading.setWidth(rctContainer.getWidth()*1);
        });

        KeyFrame keyFrame5=new KeyFrame(Duration.millis(3000),actionEvent ->{
            Stage stage=new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/lk/ijse/Hostel/view/LogInForm.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Stage window =(Stage) lblLoading.getScene().getWindow();
                window.hide();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        timeline.getKeyFrames().addAll(keyFrame,keyFrame1,keyFrame2,keyFrame3,keyFrame4,keyFrame5);
        timeline.playFromStart();
    }
}
