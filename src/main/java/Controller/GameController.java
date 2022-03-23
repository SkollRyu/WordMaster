package Controller;

import App.App;
import Infrastructure.Player;
import Infrastructure.PlayerData;
import Infrastructure.WordList;
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
import java.util.Scanner;
import java.util.regex.Pattern;

public class GameController extends Controller{
    private Stage stage;
    private Scene scene;
    private Parent root;

    private int numTurns;
    private int currTurns;
    private int estimatedTurns;
    private int wordLength;
    private int score;
    private String secretWord;
    private WordList wordList;
    private Player currPlayer;

    @FXML
    private Label lbTurns;
    @FXML
    private Label lbFeedback;
    @FXML
    private Label lbSecretWord;
    @FXML
    private TextField tfInput;
    @FXML
    private CheckBox cbShowSecretWord;
    @FXML
    private Button btnLobby;

    public GameController(){
        wordList = new WordList();
        numTurns = PlayerData.getNumTurns();
        estimatedTurns = numTurns;
        wordLength = PlayerData.getWordLength();
        wordList.setSecretWord(wordLength);
        secretWord = wordList.getSecretWord();
        currPlayer = PlayerData.getCurrPlayer();
        currTurns = 0;
    }

    private void sceneReload(ActionEvent e) {
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // TODO - switch according to button-id
    public void switchScene(ActionEvent e) throws IOException {
        if (e.getSource() == btnLobby){
            root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Lobby.fxml")));
        }
        sceneReload(e);
    }

    public void playTurn(){
        boolean flag;
        if (numTurns != 0){
            String playerGuess = getPlayerGuess();
            if(playerGuess != null){
                flag = wordList.compareWords(playerGuess);
                displayGuessWord();
                displayTurns();
                if(flag){
                    score = getScore(estimatedTurns, estimatedTurns - numTurns, wordLength);
                    if (currPlayer.setHighScore(score)){
                        System.out.println("New high Score");
                    }
                }
                numTurns--;
                currTurns++;
                displayTurns();
            }
        } else {
            // player doesn't get the correct answer
            // todo - alert you lose and go back lobby or try again
            tfInput.setDisable(true);
            System.out.println("you lose");
        }
    }

    private void displayGuessWord(){
        lbFeedback.setText(wordList.printDisplayWordArray());
    }

    private void displayTurns(){
        lbTurns.setText("Turn: " + currTurns + "/" + estimatedTurns);
    }

    private int getScore(int estimatedTurns, int actualTurns, int wordLength){
        return 100 / estimatedTurns * (estimatedTurns - actualTurns + 1) * wordLength;
    }

    public void showSecretWord(ActionEvent e){
        if(cbShowSecretWord.isSelected()){
            lbSecretWord.setText("Secret word " + secretWord);
        } else if(!cbShowSecretWord.isSelected()){
            lbSecretWord.setText("");
        }
    }

    public String getPlayerGuess(){
        // TODO - get from the text field
        String playerGuess;
        boolean flag = false;
        playerGuess = tfInput.getText();
        Alert inputAlert = new Alert(Alert.AlertType.WARNING);
        if(isPlayerGuessValidString(playerGuess)){
            if (playerGuess.length() == wordLength){
                flag = true;
            } else {
                inputAlert.setContentText("Error: Invalid Word Length ->> Word Length: " + wordLength);
                inputAlert.showAndWait();
            }
        }else{
            inputAlert.setContentText("Error: Invalid String Value: ->> (a-z/A-Z)");
        }

        if(flag){
            return playerGuess;
        } else{
            return null;
        }
    }

    private boolean isPlayerGuessValidString(String playerGuess){
        return Pattern.matches("^[a-z]+$", playerGuess);
    }
}
