package tictactoe.util;

import java.util.Scanner;

public
class IOUtility {
    private static final Scanner SCANNER = new Scanner(System.in);

    public
    String getNextWord() {
        return SCANNER.next();
    }
}
