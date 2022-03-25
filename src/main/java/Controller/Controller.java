package Controller;

import Infrastructure.PlayerData;
import javafx.application.Platform;
import javafx.event.ActionEvent;

/**
 * This class is to implement some global functions for other sub controller to inherit from it
 */
public class Controller {

    public void exit(ActionEvent e) {
        PlayerData.getPlayerList().writePlayerFile();
        Platform.exit();
        System.exit(0);
    }
}
