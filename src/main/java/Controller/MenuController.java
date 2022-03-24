package Controller;

import Infrastructure.Game;
import App.App;
import Infrastructure.PlayerData;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.*;
import javafx.stage.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Objects;

public class MenuController extends Controller{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button btnNewPlayer;

    @FXML
    private Button btnExistPlayer;

    @FXML
    private Button btnGuest;



    private void sceneReload(ActionEvent e) {
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    // TODO - switch according to button-id
    public void switchScene(ActionEvent e) throws IOException {
        if (e.getSource() == btnNewPlayer){
            root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Register.fxml")));
        } else if (e.getSource() == btnExistPlayer){
            root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Login.fxml")));
        } else if (e.getSource() == btnGuest){
            PlayerData.guestLogin();
            root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Lobby.fxml")));
        }
        sceneReload(e);
    }


}