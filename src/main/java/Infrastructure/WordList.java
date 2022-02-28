package Infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class WordList {
    private ArrayList<String> allWords;
    private final String WORDFILE = "src/main/resources/database/1000EnglishWords.txt";
    private String secretWord;
    private char[] secretWordArray;
    private char[] displayWordArray;
    public final char SINGLE_CHAR = '_';
    public final char MATCHING_CHAR = '*';
    private int maxWordLength;


    /**
     * Reads all of the words from the file 1000EnglishWords.txt
     * Shuffles the list and then orders the shuffled list by list size
     * Finds the maximum length of the words in the list
     */
    public WordList() {
        allWords = FileIO.readDataFromFile(WORDFILE);
        // Shuffling the list
        Collections.shuffle(allWords);
        //Sort the shuffled list by length
        Collections.sort(allWords, Comparator.comparing(String::length));
        maxWordLength = allWords.get(allWords.size() - 1).length();
    }

    /**
     * Returns the maximum length of the words in the list
     * @return
     */
    public int getMaxWordLength() {
        return maxWordLength;
    }

    /**
     * Sets the Array used to display the word to empty
     */
    public void resetDisplayWordArray() {
        for (int i = 0; i < secretWord.length(); i++) {
            displayWordArray[i] = SINGLE_CHAR;
        }
    }

    /**
     * Sets the secret random word
     * @param wordLength
     */
    public void setSecretWord(int wordLength) {
        secretWord = getRandomWord(wordLength);
        secretWordArray = secretWord.toCharArray();
        displayWordArray = new char[secretWord.length()];
        resetDisplayWordArray();
    }

    /**
     * Returns the secret word - used for debugging
     * @return
     */
    public String getSecretWord() {
        return secretWord;
    }

    /**
     * Prints the current word Array
     */
    public void printDisplayWordArray() {
        for (int i = 0; i < displayWordArray.length; i++) {
            System.out.print(displayWordArray[i] + " ");
        }
        System.out.println();
    }

    /**
     * Used to compare the word the user has guessed with the current secret word
     * @param userWord
     * @return true if the words match
     */
    public boolean compareWords(String userWord) {
        char[] userWordArray = userWord.toCharArray();
        String lettersFound = "";
        resetDisplayWordArray();
        for (int i = 0; i < secretWordArray.length; i++) {
            if (secretWordArray[i] == userWordArray[i]) {
                displayWordArray[i] = userWordArray[i];
                lettersFound += userWordArray[i];
            }
        }
        if (lettersFound.equals(secretWord)) {
            return true;
        }
        for (int i = 0; i < secretWordArray.length; i++) {
            String subStr = Character.toString(userWordArray[i]);
            if (!lettersFound.contains(subStr) && secretWord.contains(subStr)) {
//                System.out.println(MATCHING_CHAR);
                    displayWordArray[i] = MATCHING_CHAR;
            }
        }
        return false;
    }

    /**
     * Gets a random word from the word list
     * @param length
     * @return
     */
    public String getRandomWord(int length) {
        ArrayList<String> currentWords = new ArrayList<>();
        for (String str: allWords) {
            if (str.length() == length) {
                currentWords.add(str);
            }
        }
        // Shuffling the list
        Collections.shuffle(currentWords);
        return currentWords.get(0);
    }
}
