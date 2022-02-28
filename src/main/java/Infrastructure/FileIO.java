package Infrastructure;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileIO {
    //Stores the file name that can be used anywhere in the program
    public static final String DATA_FILE = "src/main/resources/database/players.txt";

    private static void fileIOErrorMessage(String fileName, String errorType, IOException e) {
        System.out.println(fileName
                        + " cannot be written to at "
                        + Paths.get("").toAbsolutePath().normalize().toString()
                        + ". Error message "
                        + e.getMessage()
                        + errorType);
    }

    /**
     * Saves the Class data to a text file
     * @param fileName
     * @param data
     */
    public static void writeDataToFile(String fileName, ArrayList<String> data) {
        // Creates a BufferedWriter using try with resources so no need to close the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String str: data) {
                //Add carriage return & line feed to the String
                writer.write(str + System.lineSeparator());
            }
        } catch (IOException e) {
            //Output a detailed error message if there are any problems
            fileIOErrorMessage(fileName, "Save file error", e);
        }
    }

    /**
     * Reads a text file if it exists using BufferedReader
     * @param fileName
     * @return
     */
    public static ArrayList<String> readDataFromFile(String fileName) {
        ArrayList<String> data = new ArrayList<>();
        File dataFile = new File(fileName);
        //Check the file exists
        if (dataFile.exists()) {
            // Creates a BufferedReader using try with resources so no need to close the file
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String lineStr;
                //While there are lines in the file
                while ((lineStr = reader.readLine()) != null)
                    //Add lines to the ArrayList
                    data.add(lineStr);
            } catch (IOException e) {
                //Will throw an error if the file exists but there is a problem reading the file
                fileIOErrorMessage(fileName, "Read file error", e);
            }
        }
        return data;
    }

}
