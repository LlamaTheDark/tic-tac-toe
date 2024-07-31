package tictactoe.npc;

import tictactoe.TicTacToeBoard;

import java.util.Random;

public
class RandomMoveNPC implements NPC {
    private static final Random RAND = new Random();

    /**
     * @param board The board being used.
     *
     * @return The indices of a random non-marked space on the board.
     */
    @Override
    public
    int[] getNextMarkLocation(TicTacToeBoard board) {
        int[] markLocation = {RAND.nextInt(board.getBoardLength()), RAND.nextInt(board.getBoardLength())};
        while (board.getMark(markLocation[0], markLocation[1]) != null) {
            markLocation[0] = RAND.nextInt(board.getBoardLength());
            markLocation[1] = RAND.nextInt(board.getBoardLength());
        }
        return markLocation;
    }
}
