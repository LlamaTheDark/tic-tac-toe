package tictactoe;

import tictactoe.exception.TicTacToeException;
import tictactoe.npc.NPC;
import tictactoe.npc.RandomMoveNPC;

public
class TicTacToeGame {
    private final NPC           opponent   = new RandomMoveNPC();
    private       TicTacToeMark teamTurn;
    private       int           turnsTaken = 0;

    private TicTacToeBoard board = new TicTacToeBoard();

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

    public
    void makeMark(String location) throws TicTacToeException {
        var parsedLocation = parseUserInput(location);
        makeMark(location);
    }

    private
    int[] parseUserInput(String location) throws TicTacToeException {
        char[] parsedInput = new char[2];
        char[] splitLocation = location.toCharArray();
        parsedInput[0] = splitLocation[0];
        parsedInput[1] = splitLocation[1];

        var ttte = new TicTacToeException("Failed to make mark on board. Location given is not valid");
        int[] indexLocation = new int[2];
        switch (parsedInput[0]) {
            case 'T', 't' -> indexLocation[0] = 0;
            case 'M', 'm' -> indexLocation[0] = 1;
            case 'B', 'b' -> indexLocation[0] = 2;
            default -> throw ttte;
        }
        switch (parsedInput[1]) {
            case 'L', 'l' -> indexLocation[1] = 0;
            case 'M', 'm' -> indexLocation[1] = 1;
            case 'R', 'r' -> indexLocation[0] = 2;
            default -> throw ttte;
        }

        return indexLocation;
    }

    private
    void makeMark(int[] location) throws TicTacToeException {
        if (validateMark(location[0], location[1])) {
            board.mark(teamTurn, location[0], location[1]);
            teamTurn = teamTurn == TicTacToeMark.O ? TicTacToeMark.X : TicTacToeMark.O;
        } else {
            throw new TicTacToeException(
                    "Failed to make move. The requested spot is already taken or does not exist on the board");
        }
    }

    private
    boolean validateMark(int row, int col) {
        return true;
    }

    public
    void makeOpponentMark() throws TicTacToeException {
        var location = opponent.getNextMarkLocation(this.board);
        assert location.length == 2;

        makeMark(location);
    }

    public
    String getEndMessage() {
        assert turnsTaken == board.getBoardLength() * board.getBoardLength();

        return null;
    }

    private
    boolean threeInARow(TicTacToeMark teamMark) {

        // check columns
        for (int col = 0; col < board.getBoardLength(); col++) {
            for (int row = 0; row < board.getBoardLength(); row++) {
                if (board.getMark(row, col) != teamMark) {break;}
                if (row == 2) {return true;}
            }
        }

        // check rows
        for (int row = 0; row < board.getBoardLength(); row++) {
            for (int col = 0; col < board.getBoardLength(); col++) {
                if (board.getMark(row, col) != teamMark) {break;}
                if (col == 2) {return true;}
            }
        }

        // check tl-br diagonal
        for (int row = 0, col = 0; row < board.getBoardLength() && col < board.getBoardLength(); col++, row++) {
            if (board.getMark(row, col) != teamMark) {break;}
            if (row == 2 && col == 2) {return true;}
        }

        // check bl-tr diagonal
        for (int row = 2, col = 0; row < board.getBoardLength() && col < board.getBoardLength(); col++, row--) {
            if (board.getMark(row, col) != teamMark) {break;}
            if (row == 0 && col == 2) {return true;}
        }

        return false;
    }


    public
    int getTurnsTaken() {
        return turnsTaken;
    }
}
