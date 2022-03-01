package Controller;

import App.App;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegisterController extends Controller{

    private Stage stage;
    private Scene scene;
    private Parent root;

    private void sceneReload(ActionEvent e) {
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchMenuScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Menu.fxml")));
        sceneReload(e);
    }
}