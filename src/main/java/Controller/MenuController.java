package Controller;

import App.App;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.*;
import javafx.stage.*;

import java.io.IOException;
import java.util.Objects;

public class MenuController extends Controller{

    private Stage stage;
    private Scene scene;
    private Parent root;

    private void sceneReload(ActionEvent e) {
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
    // TODO - can do a switch case by the use of button-id
    public void switchRegisterScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Register.fxml")));
        sceneReload(e);
    }


    public void switchMenuScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Menu.fxml")));
        sceneReload(e);
    }
}