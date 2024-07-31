package tictactoe;

/**
 * Models a tic-tac-toe board as a 3x3 square of tic-tac-toe mark objects.
 */
public
class TicTacToeBoard {
    private static final int               BOARD_LENGTH = 3;
    private final        TicTacToeMark[][] BOARD        = new TicTacToeMark[BOARD_LENGTH][BOARD_LENGTH];

    TicTacToeBoard() {
    }

    /**
     * Places the specified <code>TicTacToeMark</code> at the location on the board given by the parameters. This will
     * overwrite any value currently on the board at the given location, and will not check if there is already a value
     * there.
     *
     * @param mark The mark to be placed.
     * @param row  The row index of placement.
     * @param col  The column index of placement.
     */
    public
    void mark(TicTacToeMark mark, int row, int col) {
        assert BOARD[row][col] == null;
        BOARD[row][col] = mark;
    }

    /**
     * @param row The row index to be accessed.
     * @param col The column index to be accessed.
     *
     * @return The <code>TicTacToe</code> mark specified by the input parameters.
     */
    public
    TicTacToeMark getMark(int row, int col) {
        if (row < BOARD_LENGTH && col < BOARD_LENGTH) {return BOARD[row][col];}
        return null;
    }

    public
    int getBoardLength() {
        return BOARD_LENGTH;
    }

    @Override
    public
    String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (BOARD[row][col] == null) {
                    sb.append("- ");
                } else {
                    sb.append(String.format("%s ", BOARD[row][col]));
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
