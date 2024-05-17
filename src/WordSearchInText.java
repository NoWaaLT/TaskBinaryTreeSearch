import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class WordSearchInText {

    private static final Logger logger = Logger.getLogger("MyLog");

    private static void init() {

        FileHandler fileHandler;

        try {

            fileHandler = new FileHandler("C:\\Users\\vjelis\\IdeaProjects\\TaskBinaryTreeSearch\\src\\log.txt");

            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.info("Logger Initialized");

        } catch (IOException e) {
            logger.log(Level.WARNING, "Exception ::", e);
        }
    }

    public static void main(String[] args) {

        init();

        String fileName = "C:\\Users\\vjelis\\IdeaProjects\\TaskBinaryTreeSearch\\src\\lorem.txt";

        BinarySearchTree bsc = new BinarySearchTree();

        String specificWord;

        try {

            System.out.println("Input the word you want to search in a text");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            specificWord = bufferedReader.readLine();
            bufferedReader.close();

            logger.info("Search keyword: " + specificWord);

            FileReader fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            String line;

            int position = 0;       // to store position of duplicates

            while ((line = bufferedReader.readLine()) != null) {        // reads a line of text till null

                String[] words = line.split("\\W");

                for (String word : words) {
                    if (!word.isEmpty()) {
                        position++;
                        bsc.insert(word, position);
                    }
                }
            }

            boolean searchStatus = bsc.search(specificWord);

            if (searchStatus) {
                logger.info("A Word was founded " + bsc.displayCount(specificWord) + " times in " + bsc.displayPositions(specificWord) + "positions in text.");
            } else {
                logger.info("Word wasn't founded.");
            }

        } catch (IOException e) {
            logger.log(Level.WARNING, "Exception ::", e);
        }
    }
}