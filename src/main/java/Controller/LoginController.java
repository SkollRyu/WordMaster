package Controller;

import App.App;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.layout.StackPane;
import javafx.stage.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Objects;

public class LoginController extends Controller{

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String password = "";


    @FXML
    private PasswordField tfPassword;
    @FXML
    TextField tfShowPassword;
    @FXML
    StackPane stackPane;
    @FXML
    CheckBox cbShowPassword;
    

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

    public void loginAction(ActionEvent e){
        password = tfPassword.getText();
        System.out.println(password);
    }

    public void showPassword(ActionEvent e){
        // Textfield <-> PasswordField
        if(cbShowPassword.isSelected()){
            password = tfPassword.getText();
            tfShowPassword.setText(password);
            tfShowPassword.setVisible(true);
        } else {
            password = tfShowPassword.getText();
            tfPassword.setText(password);
            tfShowPassword.setVisible(false);
        }
    }

}