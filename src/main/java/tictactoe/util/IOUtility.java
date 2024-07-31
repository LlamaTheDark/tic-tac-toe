package tictactoe.util;

import java.util.Scanner;

public
class IOUtility {
    private static Scanner scanner = new Scanner(System.in);

    public
    String getNextWord() {
        return scanner.next();
    }
}
