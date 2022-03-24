package Controller;

import App.App;
import Infrastructure.PlayerData;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegisterController extends Controller{

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String userName;
    private String password1;
    private String password2;

    // pw2 = confirm pw
    @FXML
    private TextField tfUserName;
    @FXML
    private PasswordField tfPassword1;
    @FXML
    private TextField tfShowPassword1;
    @FXML
    private PasswordField tfPassword2;
    @FXML
    private TextField tfShowPassword2;
    @FXML
    private CheckBox cbShowPassword;
    @FXML
    private Button btnMenu;
    @FXML
    private Button btnRegister;

    private void sceneReload(ActionEvent e) {
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchScene(ActionEvent e) throws IOException {
        if (e.getSource() == btnMenu){
            root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Menu.fxml")));
        } else if (e.getSource() == btnRegister){
            root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Lobby.fxml")));
        }
        sceneReload(e);
    }

    public void showPassword(ActionEvent e){
        // TextField <-> PasswordField
        String tempString1 = "";
        String tempString2 = "";
        if(cbShowPassword.isSelected()){
            tempString1 = tfPassword1.getText();
            tempString2 = tfPassword2.getText();
            tfShowPassword1.setText(tempString1);
            tfShowPassword1.setVisible(true);
            tfShowPassword2.setText(tempString2);
            tfShowPassword2.setVisible(true);
        } else {
            tempString1 = tfShowPassword1.getText();
            tempString2 = tfShowPassword2.getText();
            tfPassword1.setText(tempString1);
            tfPassword2.setText(tempString2);
            tfShowPassword1.setVisible(false);
            tfShowPassword2.setVisible(false);
        }
    }

    public void registerAction(ActionEvent e) throws IOException {
        if(cbShowPassword.isSelected()){
            password1 = tfShowPassword1.getText();
            password2 = tfShowPassword1.getText();
        } else {
            password1 = tfPassword1.getText();
            password2 = tfPassword2.getText();
        }
        if (checkRegister()){
            switchScene(e);
        }
        // check scene and pass the player object into that scene
    }

    public boolean checkRegister(){
        // todo - fail register
        PlayerData pd = new PlayerData();
        userName = tfUserName.getText();
        if(!password1.isBlank() && !password2.isBlank()){
            if(password1.equals(password2)){
                return pd.registerValidation(userName, password1);
            } else {
                Alert registerAlert = new Alert(Alert.AlertType.WARNING);
                registerAlert.setContentText("The password and confirm password do not match");
            }

        } else {
            Alert registerAlert = new Alert(Alert.AlertType.WARNING);
            registerAlert.setContentText("The password or confirm password cannot be blank or just whitespace");
        }
        return false;
    }


}