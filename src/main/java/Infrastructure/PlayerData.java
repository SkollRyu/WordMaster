package Infrastructure;

import javafx.scene.control.Alert;

public class PlayerData {
    private final Players playerList = new Players();

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
        }else if(player.getPassword().equals(password)){
            System.out.println("It is valid"); // CAN BE COMMENT
            return true;
        } else {
            loginAlert.setContentText("WRONG PASSWORD");
            loginAlert.showAndWait();
        }
        return false;
    }

    public void registerValidation(){
        // TODO - check if exist, tell player to login; if not, then add to the playerlist
    }

    public void testPrint(){
        playerList.printScoreBoard();
    }
}
