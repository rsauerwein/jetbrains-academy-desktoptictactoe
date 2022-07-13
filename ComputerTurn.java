package tictactoe;

import java.util.Arrays;

public class ComputerTurn implements TurnMethod{
    @Override
    public void performTurn(TicTacToe ticTacToe, Player player) {
        Cell[][] cells = ticTacToe.getBoard().getCells();

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (!cells[i][j].isOccupied()) {
                    ticTacToe.newTurn(cells[i][j]);
                    return;
                }
            }
        }
    }
}
