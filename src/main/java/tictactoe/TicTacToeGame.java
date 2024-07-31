package tictactoe;

import tictactoe.exception.TicTacToeException;
import tictactoe.npc.NPC;
import tictactoe.npc.RandomMoveNPC;

/**
 * A class to store the states and behaviors of a TicTacToe game.
 */
public
class TicTacToeGame {
    private final NPC           OPPONENT   = new RandomMoveNPC();
    private       TicTacToeMark teamTurn;
    private       int           turnsTaken = 0;

    private final TicTacToeBoard BOARD = new TicTacToeBoard();

    /**
     * Initializes the TicTacToeGame with the requested starting player.
     *
     * @param startingPlayer The requested starting player. Either "x" or "o" (upper or lowercase).
     *
     * @throws TicTacToeException if the player requests to start with something other than "x" or "o".
     */
    TicTacToeGame(String startingPlayer) throws TicTacToeException {
        switch (startingPlayer) {
            case "X", "x" -> this.teamTurn = TicTacToeMark.X;
            case "O", "o" -> this.teamTurn = TicTacToeMark.O;
            default -> throw new TicTacToeException(String.format(
                    "Failed to start game. Unknown requested starting player: " +
                    "\"%s\". Please request either \"x\" or " +
                    "\"o\".",
                    startingPlayer
            ));
        }
    }

    /**
     * Makes a mark based off a string location
     *
     * @param location The location to be marked
     *
     * @throws TicTacToeException if the location is in the wrong format.
     * @see tictactoe.TicTacToeGame#parseUserInput
     */
    public
    void makeMark(String location) throws TicTacToeException {
        var parsedLocation = parseUserInput(location);
        makeMark(parsedLocation);
    }

    /**
     * Parses <code>String</code> user input to array indices.
     *
     * @param location The <code>String</code> to be parsed.
     *
     * @return An integer array of size <code>2</code> in the form {row, col} that describes the location specified by
     * user input.
     *
     * @throws TicTacToeException if the user input is not valid.
     */
    private
    int[] parseUserInput(String location) throws TicTacToeException {
        char[] parsedInput = new char[2];
        char[] splitLocation = location.toCharArray();
        parsedInput[0] = splitLocation[0];
        parsedInput[1] = splitLocation[1];

        var ttte = new TicTacToeException("Failed to make mark on board. Location given is not valid.");
        int[] indexLocation = new int[2];
        switch (parsedInput[0]) {
            case 'T', 't' -> {} // variable already assigned
            case 'M', 'm' -> indexLocation[0] = 1;
            case 'B', 'b' -> indexLocation[0] = 2;
            default -> throw ttte;
        }
        switch (parsedInput[1]) {
            case 'L', 'l' -> {}
            case 'M', 'm' -> indexLocation[1] = 1;
            case 'R', 'r' -> indexLocation[1] = 2;
            default -> throw ttte;
        }

        return indexLocation;
    }

    /**
     * "Marks" a location on the board of the game. Uses the team whose turn it is to mark.
     *
     * @param location The location to make the mark.
     */
    private
    void makeMark(int[] location) {
        BOARD.mark(teamTurn, location[0], location[1]);
        teamTurn = teamTurn == TicTacToeMark.O ? TicTacToeMark.X : TicTacToeMark.O;
        turnsTaken++;
    }

    /**
     * Uses an <code></code> to generate a move location, and then makes the move.
     *
     * @throws TicTacToeException
     */
    public
    void makeOpponentMark() throws TicTacToeException {
        var location = OPPONENT.getNextMarkLocation(this.BOARD);
        assert location.length == 2;

        makeMark(location);
    }

    /**
     * @param teamMark The mark of the team whose 3-in-a-row state should be tested.
     *
     * @return Whether the specified team has 3 marks in a row on the board at this time.
     */
    public
    boolean threeInARow(TicTacToeMark teamMark) {

        // check columns
        for (int col = 0; col < BOARD.getBoardLength(); col++) {
            for (int row = 0; row < BOARD.getBoardLength(); row++) {
                if (BOARD.getMark(row, col) != teamMark) {break;}
                if (row == 2) {return true;}
            }
        }

        // check rows
        for (int row = 0; row < BOARD.getBoardLength(); row++) {
            for (int col = 0; col < BOARD.getBoardLength(); col++) {
                if (BOARD.getMark(row, col) != teamMark) {break;}
                if (col == 2) {return true;}
            }
        }

        // check tl-br diagonal
        for (int row = 0, col = 0; row < BOARD.getBoardLength() && col < BOARD.getBoardLength(); col++, row++) {
            if (BOARD.getMark(row, col) != teamMark) {break;}
            if (row == 2 && col == 2) {return true;}
        }

        // check bl-tr diagonal
        for (int row = 2, col = 0; row < BOARD.getBoardLength() && col < BOARD.getBoardLength(); col++, row--) {
            if (BOARD.getMark(row, col) != teamMark) {break;}
            if (row == 0 && col == 2) {return true;}
        }

        return false;
    }

    public
    boolean isGameOver() {
        if (getTurnsTaken() == BOARD.getBoardLength() * BOARD.getBoardLength()) {return true;}
        if (threeInARow(TicTacToeMark.X)) {return true;}
        return threeInARow(TicTacToeMark.O);
    }

    // ACCESSORS

    public
    int getTurnsTaken() {
        return turnsTaken;
    }

    public
    TicTacToeMark getTeamTurn() {
        return this.teamTurn;
    }

    public
    TicTacToeBoard getBoard() {return this.BOARD;}
}
