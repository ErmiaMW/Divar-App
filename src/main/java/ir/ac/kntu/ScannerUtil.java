package ir.ac.kntu;

import java.util.Scanner;

/**
 * @author ErmiaMirzaei
 * ScannerUtil class provides a singleton Scanner instance for reading user input from the console.
 * It ensures that there is only one Scanner object created and used throughout the application.
 */
public class ScannerUtil {
    /**
     *Static method to get the singleton Scanner instance.
     * @return the singleton Scanner instance
     */
    private static Scanner scanner;
    public static Scanner getInstance() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
