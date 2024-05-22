import java.io.*;

import java.util.logging.Level;

public class WordSearchInText {

    static boolean filePathChecker(String pathName) {
        File fileName = new File(pathName);
        return fileName.exists();
    }

    public static void main(String[] args) {

        final String fileName = ".\\src\\main\\java\\lorem.txt";
        final String logFilePath = ".\\src\\main\\java\\log.txt";

        LoggerInit logO = new LoggerInit(logFilePath);
        BinarySearchTree bsc = new BinarySearchTree();

        String specificWord = "";
        BufferedReader bufferedReader;

        try {
            System.out.println("Input the word you want to search in a text");

            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            specificWord = bufferedReader.readLine();
            bufferedReader.close();

            logO.logger.info("Search keyword: " + specificWord);

            if (filePathChecker(fileName)) {
                FileReader fileReader = new FileReader(fileName);
                bufferedReader = new BufferedReader(fileReader);
                String line;

                int position = 0;                                           // to store position of duplicates

                while ((line = bufferedReader.readLine()) != null) {        // reads a line of text till null

                    String[] words = line.split("[.,:/\\n\\s\\t]+");

                    for (String word : words) {
                        position++;
                        bsc.insert(word, position);
                    }
                }

                boolean searchStatus = bsc.search(specificWord);

                if (searchStatus) {
                    logO.logger.info("Word was found " + bsc.displayCount(specificWord) + " times in " + bsc.displayPositions(specificWord) + " positions in text.");
                } else {
                    logO.logger.info("Word wasn't found.");
                }

            } else {
                logO.logger.log(Level.WARNING, "File is not found.");
            }

        } catch (IOException e) {
            logO.logger.log(Level.WARNING, "Exception ::", e);
        }
    }
}