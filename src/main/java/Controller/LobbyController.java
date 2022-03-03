package Controller;

import App.App;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LobbyController extends Controller implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private int numOfGuesses;
    private int wordLength;

    @FXML
    private Button btnNewGame;
    @FXML
    private Button btnLeaderBoard;
    @FXML
    private Button btnSave;
    @FXML
    private Label lbNumOfGuesses;
    @FXML
    private Slider slideNumOfGuesses;
    @FXML
    private Label lbWordLength;
    @FXML
    private Slider slideWordLength;



    private void sceneReload(ActionEvent e) {
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    // TODO - switch according to button-id
    public void switchScene(ActionEvent e) throws IOException {
        if (e.getSource() == btnNewGame){
            root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Register.fxml")));
        } else if (e.getSource() == btnLeaderBoard){
            root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Login.fxml")));
        }
        sceneReload(e);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        numOfGuesses = (int)slideNumOfGuesses.getValue();
        lbNumOfGuesses.setText("Number of Guesses: " + Integer.toString(numOfGuesses));

        wordLength = (int)slideWordLength.getValue();
        lbWordLength.setText("Word Length: " + Integer.toString(wordLength));

        slideNumOfGuesses.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                numOfGuesses = (int)slideNumOfGuesses.getValue();
                lbNumOfGuesses.setText("Number of Guesses: " + Integer.toString(numOfGuesses));
            }
        });

        slideWordLength.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                wordLength = (int)slideWordLength.getValue();
                lbWordLength.setText("Word Length: " + Integer.toString(wordLength));
            }
        });
    }
}