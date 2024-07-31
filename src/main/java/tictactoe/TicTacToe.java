package tictactoe;

import tictactoe.exception.TicTacToeException;
import tictactoe.util.IOUtility;

/**
 * The class responsible for handling the game repl and some game logic
 */
public
class TicTacToe {
    private final IOUtility io = new IOUtility();

    /**
     * Runs the repl deciding:
     * <ul><li>Who will move first?</li><li>Will you play again?</li></ul>
     * <p>
     * Also runs the main game loop.
     */
    public
    void playGame() {
        while (true) {
            System.out.println("""
                               Welcome to Tic-Tac-Toe!
                               \s
                               You'll be playing against a computer, who will mark with 'O's. You'll be marking with 'X's.
                               Who would you like to go first? (X: you, O: the computer):
                               \s""");
            try {
                var game = new TicTacToeGame(io.getNextWord());
                runGameLoop(game);

                // display end messages

                if (game.threeInARow(TicTacToeMark.X)) {
                    System.out.println("""
                                        Tic-tac-toe, THREE IN A ROW. YEEEEEEEEAH.
                                        Congratulations! You won! Throw a party!\s
                                                                          \s
                                        or... play again? (type 'y' + Enter to play again! Enter any other key to quit.)
                                       \s""");
                } else if (game.threeInARow(TicTacToeMark.O)) {
                    System.out.println("""
                                        Honestly it's kind of embarrassing that you lost here... your opponent just picked squares randomly.
                                                                          \s
                                        Oh well... play again? (type 'y' + Enter to play again! Enter any other key to quit.)
                                       \s""");
                } else {
                    System.out.println("""
                                       Wooooooow a draw! What a tense game. Press 'y' + Enter to play again! Enter any other key to quit.
                                       """);
                }

                if (!io.getNextWord().toLowerCase().startsWith("y")) {
                    break;
                }
            } catch (TicTacToeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Loops through the game logic, including making moves, triggering the opponent to make moves, displaying
     * move-making instructions, and checking for if the game should end.
     *
     * @param game The game being played.
     */
    private
    void runGameLoop(TicTacToeGame game) {
        while (!game.isGameOver()) {
            try {
                if (game.getTeamTurn() == TicTacToeMark.X) {
                    System.out.println("""
                                        Make your mark! Input 2 characters to represent where you'd like to place your 'X' on the board.
                                        Key:
                                        \tStart with 't' (top), 'm' (middle), 'b' (bottom)
                                        \tThen 'l' (left), 'm' (middle), 'r' (right)
                                        \t(not case sensitive)
                                                                          \s
                                        e.g. 'tr' will place your "x" in the top right of the board.
                                       \s
                                       """);
                    System.out.println(game.getBoard());
                    System.out.print("mark location >>> ");
                    game.makeMark(io.getNextWord());
                } else {
                    System.out.println();
                    game.makeOpponentMark();
                }
            } catch (TicTacToeException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(game.getBoard());
    }
}
