package tictactoe.npc;

import tictactoe.TicTacToeBoard;

/**
 * Describes the behavior for a Tic-Tac-Toe non-player character.
 */
public
interface NPC {
    /**
     * @param board The board being used.
     *
     * @return An integer array whose first 2 values are the row and column indices of the Tic-Tac-Toe board at which
     * the NPC would like to place a mark.
     */
    int[] getNextMarkLocation(TicTacToeBoard board);
}
