import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    // Count the number of lines in a file
    public static long countLines(String fileName) {
        int count = 0;
        Scanner fileScanner;
        // Get the file path
        try {
            fileScanner = new Scanner(new File(fileName));

            // Count the lines
            while (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
                count++;
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return count;

    }

    // Add a new item to the file
    public static void writeToFile(String fileName, String text) {
        PrintWriter writer = null;
        // Create new product and add it to the text file

        try {
            writer = new PrintWriter(new FileWriter(fileName, true));
            writer.println(text);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        if (writer != null)
            writer.close();
    }


    // Check if a text exists in a line
    public static boolean isExisted(String line, String text) {
        return line.toLowerCase().contains(text.toLowerCase());

    }

    public static void deleteContent(String fileName) {
        PrintWriter writer = null;
        // Create new product and add it to the text file

        try {
            writer = new PrintWriter(new FileWriter(fileName, false));
            writer.print("");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        if (writer != null)
            writer.close();
    }

    public static void updateContent(String fileName, ArrayList list) {
        // Delete the old content of the file
        FileManager.deleteContent(fileName);

        // Write the updated content to the file
        for (Object o : list) {
            FileManager.writeToFile(fileName, o.toString());
        }

        // Delete the elements in the ArrayList to remove duplicates
        list.clear();
    }

}