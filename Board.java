package tictactoe;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private Cell[][] cells;
    Board() {
        setLayout(new GridLayout(3, 3));
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public void toggleCellsEnabled(AppState appState) {
        boolean cellsEnabled;
        if (appState == AppState.IN_PROGRESS) {
            cellsEnabled = true;
        } else {
            cellsEnabled = false;
        }
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].setEnabled(cellsEnabled);
            }
        }
    }
}
