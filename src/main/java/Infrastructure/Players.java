package Infrastructure;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Players {
    private final ArrayList<Player> players;

    /**
     * Initialises the players ArrayList
     */
    public Players(){
        players = new ArrayList<>();
    }


    /**
     * Adds a new player to the ArrayList
     * @param player - the player object
     */
    public void addPlayer(Player player){
        players.add(player);
    }

    /**
     * Polymorphism of addPlayer method
     * @param name - the name of the player
     * @param gamePlayed - the numbers of game that the player have played
     * @param highScore - the highest score that the player have achieved
     */
    public void addPlayer(String name, String password, int gamePlayed, int highScore){
        Player player = new Player (name, password, gamePlayed, highScore);
        players.add(player);
    }


    /**
     * reads the file players.txt and updates the players ArrayList
     */
    public void readPlayerFile(){
        ArrayList<String> players = FileIO.readDataFromFile(FileIO.DATA_FILE);
        //Use the ArrayList<String> to generate new register entries
        for (String str: players) {

            if (checkValidPlayerString(str)){
                // Split the string to pass the player value into the player class
                String[] strArr = str.split(",");
                String username = strArr[0];
                String password = strArr[1];
                int gamePlayed = Integer.parseInt(strArr[2]);
                int highScore = Integer.parseInt(strArr[3]);
                addPlayer(username, password, gamePlayed, highScore);
//            Player player = new Player(username, gamePlayed, highScore);
//            players.add(player);
            }
        }
    }

    /**
     * This method is to check if the data in the file is valid
     * Only read the data that is following the format "[username],[password],[integer],[integer]"
     * We assume the username can be a combination of characters and integer
     * And also this allows us to correct the file after player finish the game
     * Since it will only write the player in the players arraylist
     * @param line - each string line
     * @return true if it is related; false if it is not
     */
    public boolean checkValidPlayerString(String line){
        // TODO - Need to add validation for password
        return Pattern.matches("^[a-zA-Z0-9]+,\\d+,[0-9]+$", line);
    }


    /**
     * writes the ArrayList to the players.txt file
     */
    public void writePlayerFile(){
        ArrayList<String> playerData = new ArrayList<>();
        for(Player player: players){
            playerData.add(player.toString());
        }
        FileIO.writeDataToFile(FileIO.DATA_FILE, playerData);
    }


    /**
     * Finds a player in the ArrayList
     * @param name - the target username of the player
     * @return the Player object
     */
    public Player findPlayer(String name){
        for(Player player : players){
            if(name.equals(player.getUserName())){
                return player;
            }
        }
        return null;
    }

        
    /**
     * Update the data of the player with a matching username or add the player data to
     * the list
     * @param player - the current player
     */
    public void updatePlayerList(Player player){
        if (findPlayer(player.getUserName()) == null){
            addPlayer(player);
        } else {
            player.incGamesPlayed();
        }
    }

}
