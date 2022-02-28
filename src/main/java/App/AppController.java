package App;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.*;
import javafx.stage.*;

import java.io.IOException;
import java.util.Objects;

public class AppController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void exit(ActionEvent e){
        Platform.exit();
        System.exit(0);
    }

    public void switchRegisterScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Register.fxml")));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchMenuScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Menu.fxml")));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}