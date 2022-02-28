package Infrastructure;// import org.jetbrains.annotations.Nullable;
// https://www.jetbrains.com/help/idea/nullable-and-notnull-annotations.html#nullable

import java.util.Scanner;
import java.util.regex.Pattern;

public class Game {

    private int numTurns;
    private int estimatedTurns;
    private Players players = new Players();

    /**
     * Main game loop
     * A game turn will loop for numTurns or until the player guesses the word correctly
     * The player can choose to play another round
     */
    public void playGame() {
        Player player = getPlayerData();
        WordList wordList = new WordList();
        do{
            playTurn(player,wordList);
            players.updatePlayerList(player);
            players.writePlayerFile();
        } while(continueGame());
    }

    /**
     * Prints the welcome message
     * Gets the userName
     * Checks the file players.txt to see if the player is in the file
     * use the userName to check
     * If the player is a new player make a new player object
     * @return the Player
     */
    private Player getPlayerData(){
        players.readPlayerFile();
        String userName = getUserName();
        String userPassword = getUserPassword();
        Player player = players.findPlayer(userName);
        if (player == null){
            player = new Player(userName, userPassword,0,0);
        }
        return player;
    }

    /**
     * Gets the player userName
     * Has error checking to ensure it's at least 2 characters
     * @return the userName
     */
    private String getUserName(){
        Scanner sc = new Scanner(System.in);
        String userName;
        do {
            System.out.println("Please enter your userName: ");
            userName = sc.next();
        }  while (userName.length() <= 2);
        return userName;
    }

    private String getUserPassword(){
        // TODO - regex need to be done
        Scanner sc = new Scanner(System.in);
        String userPassword;
        do {
            System.out.println("Please enter your userName: ");
            userPassword = sc.next();
        }  while (userPassword.length() <= 2);
        return userPassword;
    }

    /**
     * Asks the player if they want to continue
     * Has error checking on the input
     * @return true or false
     */
    public boolean continueGame(){
        Scanner sc = new Scanner(System.in);
        String choice;
        do{
            System.out.println("Continue? y/n ");
            choice = sc.next().toLowerCase();
            if (!choice.equals("y") && !choice.equals("n")){
                System.out.println("Wrong Input");
            }
        } while(!choice.equals("y") && !choice.equals("n"));

        return choice.equals("y");
    }

    /**
     * Plays a single turn of the game until the player has guessed the word or until t
     hey have run out of turns
     * @param player - the player that is playing the game
     * @param wordList - the list of world
     */
    public void playTurn(Player player, WordList wordList){
        numTurns = getNumTurns();
        estimatedTurns = numTurns;
        int wordLength = getWordLength();
        boolean flag;

        wordList.setSecretWord(wordLength);
//        System.out.println(wordList.getSecretWord());
        wordList.printDisplayWordArray();
        while (numTurns != 0){
             String playerGuess = getPlayerGuess(wordLength);
             flag = wordList.compareWords(playerGuess);
             wordList.printDisplayWordArray();
             System.out.println("Turn " + (estimatedTurns - numTurns + 1) + " of " + estimatedTurns);
             if (flag){
                 int score = getScore(estimatedTurns, estimatedTurns - numTurns, wordLength);
                 System.out.println("Congratulations " + player.getUserName() + " you guessed the word!" + "Score is " + score);
                 if (player.setHighScore(score)){
                     System.out.println("New high Score");
                 }
                 break;
             }
             numTurns--;
        }
        if (numTurns == 0){
            System.out.println("You lose");
        }
    }

    /**
     * To calculate the score
     * @param estimatedTurns - the turns that player estimate to finish
     * @param actualTurns - the actual turns that player finish the game
     * @param wordLength - the length of the word
     * @return the score
     */
    public int getScore(int estimatedTurns, int actualTurns, int wordLength){
        return 100 / estimatedTurns * (estimatedTurns - actualTurns + 1) * wordLength;
    }

    /**
     * Gets the length of the word the player wants to try and guess
     * Implemented validation
     * Has error checking for the input
     * @return - the word length
     */
    public int getWordLength(){
        int maxChar = 0;
        boolean flag = false;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.print("Enter word length (4 - 14) : ");
            if(sc.hasNextInt()){
                maxChar = sc.nextInt();
                if (maxChar >= 4 && maxChar <= 14){
                    flag = true;
                } else {
                    System.out.println("Enter a Integer between 4 and 14");
                }
            }else{
                sc.nextLine();
                System.out.println("Enter a valid Integer value");
            }
        }while(!flag);

        return maxChar;
    }

    /**
     * Asks the player the maximum number of turns they need to guess the word (2 - 10)
     * Has error checking
     * @return the number they entered
     */
    public int getNumTurns(){
        int numTurns = 0;
        boolean flag = false;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.print("Enter turns (2 - 10) : ");
            if(sc.hasNextInt()){
                numTurns = sc.nextInt();
                if (numTurns >= 2 && numTurns <= 10){
                    flag = true;
                } else {
                    System.out.println("Enter a Integer between 2 and 10");
                }
            }else{
                sc.nextInt();
                System.out.println("Enter a valid Integer value");
            }
        }while(!flag);

        return numTurns;
    }

    /**
     * Returns the guess the player makes (the word they type)
     * Has error checking to ensure the word is the correct length
     * @param wordLength - the length of the word they need to type
     * @return the word they typed
     */
    public String getPlayerGuess(int wordLength){
        Scanner sc = new Scanner(System.in);
        String playerGuess;
        boolean flag = false;
        do{
            System.out.println("\nEnter your guess. Word length is " + wordLength);
            playerGuess = sc.next().toLowerCase();
            if(isPlayerGuessValidString(playerGuess)){
                if (playerGuess.length() == wordLength){
                    flag = true;
                } else {
                    System.out.println("Error: Invalid Word Length ->> Word Length: " + wordLength);
                }
            }else{
                sc.nextLine();
                System.out.println("Error: Invalid String Value: ->> (a-z/A-Z)");
            }
        }while(!flag);
        return playerGuess;
    }


    /**
     * This method use regex to check if player guess only contains English letters
     * but not others like Integer, Special characters
     * @param playerGuess - the input guess by player
     * @return - true if valid input guess; false if invalid input guess
     */
    private boolean isPlayerGuessValidString(String playerGuess){
        return Pattern.matches("^[a-z]+$", playerGuess);
    }

}
