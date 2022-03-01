package Infrastructure;

public class Player {
    private final String userName;
    private String password;
    private int gamesPlayed;
    private int highScore;

    /**
     * Constructor initialises all the fields
     * @param userName - the username of player
     * @param gamesPlayed - the numbers of times that the player have played
     * @param highScore - the highest score that the player have achieved
     */
    public Player(String userName, String password, int gamesPlayed, int highScore){
        this.userName = userName;
        this.password = password;
        this.gamesPlayed = gamesPlayed;
        this.highScore = highScore;
    }

    /**
     * Return the userName
     * @return userName
     */
    public String getUserName(){
        return userName;
    }

    public void changePassword(String password){
        this.password = password;
    }

    public String getPassword(){ return password; }

    /**
     * Increment the number of games played each time the player has a turn
     */
    public void incGamesPlayed(){
        gamesPlayed += 1;
    }

    /**
     * Sets the current highScore if it is higher than the previous one
     * @param score - the score that player get in the current game
     * @return true if score is larger than high score
     */
    public boolean setHighScore(int score){
        if (score > highScore){
            highScore = score;
            return true;
        } else {
            return false;
        }
    }
    /**
     * toString writes all the fields of the class as csv e.g. fred,1,80
     * The userName,gamesPlayed,highScore
     * @return formatted String
     */
    public String toString(){
        return userName + "," + gamesPlayed + "," + highScore;
    }

    public int getHighScore() { return highScore; }
}
