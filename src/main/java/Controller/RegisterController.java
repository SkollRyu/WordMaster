package Controller;

import App.App;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegisterController extends Controller{

    private Stage stage;
    private Scene scene;
    private Parent root;

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
}