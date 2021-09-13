package controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InitPreloader implements Initializable {
    public Label lblLoading;
    public static Label lblloadingg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblloadingg = lblLoading;
    }
    public String checkFunctions(){
       final String[] message =  {""};

        Thread t1 = new Thread(() -> {
            message[0] = "Creating FXML...";
            Platform.runLater(() -> lblloadingg.setText(message[0]));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            message[0] = "Reading Controllers...";
            Platform.runLater(() -> lblloadingg.setText(message[0]));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            message[0] = "Loading Main Class...";
            Platform.runLater(() -> lblloadingg.setText(message[0]));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("../view/Home_window.fxml"));
                    Scene scene =new Scene(root);
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        });

        try {
            t1.start();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return message[0];
    }
}
