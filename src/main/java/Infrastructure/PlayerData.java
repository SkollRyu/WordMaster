package Infrastructure;

import javafx.scene.control.Alert;

import java.util.regex.Pattern;

public class PlayerData {
    private static Player currPlayer;
    private static Players playerList = new Players();
    private static int numTurns;

    public static int getNumTurns() {
        return numTurns;
    }

    public static void setNumTurns(int numTurns) {
        PlayerData.numTurns = numTurns;
    }

    public static int getWordLength() {
        return wordLength;
    }

    public static void setWordLength(int wordLength) {
        PlayerData.wordLength = wordLength;
    }

    private static int wordLength;

    public PlayerData(){
        playerList.readPlayerFile();
    }

    public boolean loginValidation(String userName, String password) {
        Player player = playerList.findPlayer(userName);
        Alert loginAlert = new Alert(Alert.AlertType.WARNING);
        if(userName.equals("")){
            loginAlert.setContentText("THE USERNAME FIELD CANNOT BE EMPTY");
            loginAlert.showAndWait();
        } else if (password.equals("")){
            loginAlert.setContentText("THE PASSWORD FIELD CANNOT BE EMPTY");
            loginAlert.showAndWait();
        } else if (player == null){
            loginAlert.setContentText("PLAYER NOT FOUND, YOU NEED TO REGISTER FIRST");
            loginAlert.showAndWait();
        } else if(player.getPassword().equals(password)){
            System.out.println("It is valid"); // CAN BE COMMENT
            currPlayer = player;
            return true;
        } else {
            loginAlert.setContentText("WRONG PASSWORD");
            loginAlert.showAndWait();
        }
        return false;
    }

    public boolean registerValidation(String userName, String password){
        // TODO - check if exist, tell player to login; if not, then add to the playerlist
        Alert registerAlert = new Alert(Alert.AlertType.WARNING);
        if(playerList.findPlayer(userName) != null){
            // player exists
            registerAlert.setContentText("The player is already exists. Please Log in instead");
            registerAlert.showAndWait();
            return false;
        }
        if(!check8characters(password)){
            registerAlert.setContentText("The password need to be at least 8 characters long");
            registerAlert.showAndWait();
            return false;
        }
        if(!checkUpperCharacters(password)){
            registerAlert.setContentText("You need at least 1 upper character");
            registerAlert.showAndWait();
            return false;
        }
        if(!checkLowerCharacters(password)){
            registerAlert.setContentText("You need at least 1 lower character");
            registerAlert.showAndWait();
            return false;
        }
        if(!checkSpecialCharacters(password)){
            registerAlert.setContentText("You need at least 1 special character");
            registerAlert.showAndWait();
            return false;
        }
        if(!checkNumericalCharacters(password)){
            registerAlert.setContentText("You need at least 1 numerical character");
            registerAlert.showAndWait();
            return false;
        }
        currPlayer = new Player(userName, password, 0,0);
        playerList.addPlayer(currPlayer);
        return true;
    }

    public boolean check8characters(String password){
        // characters except whitespace
        return Pattern.matches("^\\S{8,}$", password);
    }

    public boolean checkUpperCharacters(String password){
        return Pattern.matches("^\\w*[A-Z\\W]\\w*$", password);
    }

    public boolean checkLowerCharacters(String password){
        return Pattern.matches("^\\w*[a-z\\W]\\w*$", password);
    }

    public boolean checkSpecialCharacters(String password){
        return Pattern.matches("^\\w*[\\W]\\w*$", password);
    }

    public boolean checkNumericalCharacters(String password){
        return Pattern.matches("^\\w*[0-9\\W]\\w*$", password);
    }

    public static void guestLogin(){
        currPlayer = new Player("Guest", "",0,0);
    }

    public void testPrint(){
        playerList.printScoreBoard();
    }

    public static Player getCurrPlayer(){
        return currPlayer;
    }

    public static Players getPlayerList(){ return playerList; }


}
