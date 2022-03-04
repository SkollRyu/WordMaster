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
    private String userName = "";

    @FXML
    private TextField tfUserName;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private TextField tfShowPassword;
    @FXML
    private CheckBox cbShowPassword;
    

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

    private void checkLogin(){
        userName = tfUserName.getText();
        if(userName == ""){
            Alert emptyAlert = new Alert(Alert.AlertType.WARNING);
            emptyAlert.setContentText("THE USERNAME FIELD CANNOT BE EMPTY");
            emptyAlert.showAndWait();
        }
    }

    public void loginAction(ActionEvent e){
        if(cbShowPassword.isSelected()){
            password = tfShowPassword.getText();
        } else {
            password = tfPassword.getText();
        }
        checkLogin();
        System.out.println(password);
    }

    public void showPassword(ActionEvent e){
        // TextField <-> PasswordField
        String tempString = "";
        if(cbShowPassword.isSelected()){
            tempString = tfPassword.getText();
            tfShowPassword.setText(tempString);
            tfShowPassword.setVisible(true);
        } else {
            tempString = tfShowPassword.getText();
            tfPassword.setText(tempString);
            tfShowPassword.setVisible(false);
        }
    }

}