package Controller;

import App.App;
import Infrastructure.Player;
import Infrastructure.PlayerData;
import Infrastructure.WordList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class GameController extends Controller implements Initializable {
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
    @FXML
    private Button btnSubmit;

    /**
     * Constructor to initialize fields
     */
    public GameController(){
        wordList = new WordList();
        numTurns = PlayerData.getNumTurns();
        estimatedTurns = numTurns;
        wordLength = PlayerData.getWordLength();
        wordList.setSecretWord(wordLength);
        secretWord = wordList.getSecretWord();
        currPlayer = PlayerData.getCurrPlayer();
        currTurns = 1;
    }

    private void sceneReload(ActionEvent e) {
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switch scene according to button-id
     * @param e - action listerner
     * @throws IOException - if can't read or find the fxml, throw it
     */
    public void switchScene(ActionEvent e) throws IOException {
        if (e.getSource() == btnLobby){
            root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Lobby.fxml")));
        }
        sceneReload(e);
    }

    public void playTurn(){
        boolean flag;
        String playerGuess = getPlayerGuess();
        if (currTurns != estimatedTurns){
            if(playerGuess != null){
                flag = wordList.compareWords(playerGuess);
                displayGuessWord();
                displayTurns();
                if(flag){
                    score = getScore(estimatedTurns, estimatedTurns - numTurns, wordLength);
                    if (currPlayer.setHighScore(score)){
                        displayHighScore(score);
                        updatePlayerTxt();
                    } else {
                        displayScore(score);
                        updatePlayerTxt();
                    }
                    setDisable();
                }
                numTurns--;
                currTurns++;
                displayTurns();
            }
        } else if (wordList.compareWords(playerGuess)){
            // last guess but correct
            score = getScore(estimatedTurns, estimatedTurns - numTurns, wordLength);
            if (currPlayer.setHighScore(score)){
                updatePlayerTxt();
                displayHighScore(score);
            } else {
                updatePlayerTxt();
                displayScore(score);
            }
            setDisable();
            updatePlayerTxt();
        } else {
            // player doesn't get the correct answer
            setDisable();
            displayLoseMessage();
            updatePlayerTxt();
        }
    }

    public void setDisable(){
        tfInput.setDisable(true);
        btnSubmit.setDisable(true);
    }

    private void updatePlayerTxt() {
        if(!currPlayer.getUserName().equals("Guest")){
            PlayerData.getPlayerList().updatePlayerList(currPlayer);
            PlayerData.getPlayerList().writePlayerFile();
        }
    }

    private void displayGuessWord(){
        lbFeedback.setText(wordList.printDisplayWordArray());
    }

    private void displayTurns(){
        lbTurns.setText("Turn: " + currTurns + "/" + estimatedTurns);
    }

    public void displayHighScore(int score){
        Alert scoreAlert = new Alert(Alert.AlertType.INFORMATION);
        scoreAlert.setContentText("High Score ! + Your Score is " + score);
        scoreAlert.showAndWait();
    }

    public void displayScore(int score){
        Alert scoreAlert = new Alert(Alert.AlertType.INFORMATION);
        scoreAlert.setContentText("Correct Guess ! + Your Score is " + score);
        scoreAlert.showAndWait();
    }

    public void displayLoseMessage(){
        Alert loseAlert = new Alert(Alert.AlertType.INFORMATION);
        loseAlert.setContentText("YOU LOSE ! THE WORD IS " + wordList.getSecretWord());
        loseAlert.showAndWait();
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


    // run-time checking
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbFeedback.setText(wordList.printDisplayWordArray());
        lbTurns.setText("Turns: " + currTurns + "/" + estimatedTurns);
    }
}
