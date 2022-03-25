package Controller;

import App.App;
import Infrastructure.PlayerData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.stage.*;

import java.io.IOException;
import java.util.Objects;

public class LoginController extends Controller{

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String password = "";
    private String userName = "";
    private final PlayerData playerData = new PlayerData();

    @FXML
    private TextField tfUserName;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private TextField tfShowPassword;
    @FXML
    private CheckBox cbShowPassword;
    @FXML
    private Button btnBack;

    private void sceneReload(ActionEvent e) {
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchMenuScene(ActionEvent e) throws IOException {
        if (e.getSource() == btnBack){
            root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Menu.fxml")));
        } else {
            root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Lobby.fxml")));
        }
        sceneReload(e);
    }

    /**
     * Check login things
     * @return true if valid
     */
    private boolean checkLogin(){
        userName = tfUserName.getText();
        return playerData.loginValidation(userName, password);
    }

    /**
     * Login button event trigger
     * @param e - action listener
     * @throws IOException - if can't find or read the file
     */
    public void loginAction(ActionEvent e) throws IOException {
        if(cbShowPassword.isSelected()){
            password = tfShowPassword.getText();
        } else {
            password = tfPassword.getText();
        }
        if (checkLogin()){
            switchMenuScene(e);
        }
        // check scene and pass the player object into that scene
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