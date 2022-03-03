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

/**
 * This class is to implement some global functions for other sub controller to inherit from it
 */
public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void exit(ActionEvent e) {
        Platform.exit();
        System.exit(0);
    }


}
