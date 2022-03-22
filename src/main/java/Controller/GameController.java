package Controller;

import Infrastructure.Player;
import Infrastructure.PlayerData;
import Infrastructure.WordList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.util.Scanner;
import java.util.regex.Pattern;

public class GameController extends Controller{
    private Stage stage;
    private Scene scene;
    private Parent root;

    private int numTurns;
    private int estimatedTurns;
    private int wordLength;
    private String secretWord;
    private WordList wordList;

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

    public GameController(){
        wordList = new WordList();
        numTurns = PlayerData.getNumTurns();
        estimatedTurns = numTurns;
        wordLength = PlayerData.getWordLength();
        wordList.setSecretWord(wordLength);
        secretWord = wordList.getSecretWord();
    }


    public void playTurn(){
        boolean flag;
        if (numTurns != 0 ){
            String playerGuess = getPlayerGuess();
            // todo - return null and break
            if(playerGuess != null){
                flag = wordList.compareWords(playerGuess);
                displayGuessWord();
                displayTurns();
                if(flag){
                    int score = getScore(estimatedTurns, estimatedTurns - numTurns, wordLength);
                }
                numTurns--;
            }
        } else {
            // player doesn't get the correct answer
            System.out.println("you lose");
        }
        // TODO - wrong type doesn't turns--
    }

    private void displayGuessWord(){
        wordList.printDisplayWordArray();
    }

    private void displayTurns(){

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
        System.out.println(playerGuess); // checking
        if(isPlayerGuessValidString(playerGuess)){
            if (playerGuess.length() == wordLength){
                flag = true;
            } else {
                System.out.println("Error: Invalid Word Length ->> Word Length: " + wordLength);
            }
        }else{
            System.out.println("Error: Invalid String Value: ->> (a-z/A-Z)");
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
