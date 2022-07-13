package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicTacToe extends JFrame {
    private Logger logger;

    // App fields
    private AppState appState;
    private Player[] players;
    private Player currentPlayer;
    private int turnCount;

    // View fields
    private Board board;
    private StatusPanel statusPanel;
    private MenuBar menuBar;

    {
        logger = Logger.getLogger("TicTacToe");
        appState = AppState.NOT_STARTED;
        players = new Player[]{new Player('X'), new Player('O')};
        currentPlayer = players[0];
        turnCount = 0;

        // Initialize view fields
        statusPanel = new StatusPanel();
        menuBar = new MenuBar();
        board = new Board();
    }

    public TicTacToe() {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 450);
        setLayout(new BorderLayout());

        MenuGame menuGame = new MenuGame();
        setJMenuBar(menuGame);

        menuGame.getHumanVsHuman().addActionListener(e -> {
            resetBoard();
            startNewGame();

            logger.log(Level.INFO, "Clicked");
            players[0].setHuman(true);
            menuBar.getBtnP1().setText("Human");
            players[1].setHuman(true);
            menuBar.getBtnP2().setText("Human");
        });

        menuGame.getHumanVsRobot().addActionListener(e -> {
            resetBoard();
            startNewGame();

            logger.log(Level.INFO, "Clicked");
            players[0].setHuman(true);
            menuBar.getBtnP1().setText("Human");
            players[1].setHuman(false);
            menuBar.getBtnP2().setText("Robot");
        });

        menuGame.getRobotVsHuman().addActionListener(e -> {
            resetBoard();
            startNewGame();

            logger.log(Level.INFO, "Clicked");
            players[0].setHuman(false);
            menuBar.getBtnP1().setText("Robot");
            players[1].setHuman(true);
            menuBar.getBtnP2().setText("Human");
        });

        menuGame.getRobotVsRobot().addActionListener(e -> {
            resetBoard();
            startNewGame();

            logger.log(Level.INFO, "Clicked");
            players[0].setHuman(false);
            menuBar.getBtnP1().setText("Robot");
            players[1].setHuman(false);
            menuBar.getBtnP2().setText("Robot");
        });

        menuGame.getExit().addActionListener(e -> {
            logger.log(Level.INFO, "Clicked");
            System.exit(0);
        });

        BtnSelectPlayer btnP1 = menuBar.getBtnP1();
        BtnSelectPlayer btnP2 = menuBar.getBtnP2();
        btnP1.addActionListener(e -> playerBtnClicked(e));
        btnP2.addActionListener(e -> playerBtnClicked(e));

        menuBar.getBtnP2();
        add(menuBar, BorderLayout.NORTH);

        generateCells();
        add(board);

        statusPanel.setStatusPanelText(appState.getTitle());
        add(statusPanel, BorderLayout.SOUTH);

        menuBar.getBtnReset().addActionListener(e -> btnStartResetClicked(e));

        setVisible(true);
        setLayout(null);
    }

    public Board getBoard() {
        return board;
    }

    private void setAppState(AppState appState) {
        this.appState = appState;
        refreshStatusBar();
        logger.log(Level.INFO, "New App state: " + appState);
    }

    private void refreshStatusBar() {
        String state;
        switch (appState) {
            case IN_PROGRESS:
                state = MessageFormat.format("The turn of {0} Player ({1})", currentPlayer.getPlayerTypeAsString(), currentPlayer.getSign());
                break;
            case O_WINS:
            case X_WINS:
                state = MessageFormat.format("The {0} Player ({1}) wins", currentPlayer.getPlayerTypeAsString(), currentPlayer.getSign());
                break;
            default:
                state = appState.getTitle();
                break;
        }

        statusPanel.setStatusPanelText(state);
    }

    private void generateCells() {
        Cell[][] cells = new Cell[3][3];
        for (int i = 3; i >= 1; i--) {
            for (char j = 'A'; j <= 'C'; j++) {
                Cell cell = new Cell("" + j + i);
                cell.addActionListener(e -> cellClicked(e));
                cell.setEnabled(false);

                int res = (i - 3) % 3;
                cells[Math.abs((i - 3) % 3)][j - 'A'] = cell;
                board.add(cell);
            }
        }

        board.setCells(cells);
    }

    private void cellClicked(ActionEvent e) {
        if (appState != AppState.IN_PROGRESS) {
            String msg = String.format("Current AppState: %s - skipping action", appState.getTitle());
            logger.log(Level.INFO, msg);
            return;
        }
        Cell cell = (Cell) e.getSource();
        logger.log(Level.INFO, currentPlayer + " clicked cell " + cell.getName());
        newTurn(cell);
    }

    /**
     * Starts a new Game or resets a running game
     *
     * @param e
     */
    private void btnStartResetClicked(ActionEvent e) {
        if (appState == AppState.NOT_STARTED) {
            startNewGame();
        } else {
            resetBoard();
        }
    }

    private void resetBoard() {
        Cell[][] cells = board.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].setText(" ");
            }
        }

        logger.log(Level.INFO, "Resetting game");
        setAppState(AppState.NOT_STARTED);
        menuBar.getBtnReset().setText("Start");
        menuBar.getBtnP1().setEnabled(true);
        menuBar.getBtnP2().setEnabled(true);
        board.toggleCellsEnabled(appState);
        currentPlayer = players[0];
        turnCount = 0;
    }

    private void startNewGame() {
        logger.log(Level.INFO, "Starting new game");
        setAppState(AppState.IN_PROGRESS);
        menuBar.getBtnReset().setText("Reset");
        menuBar.getBtnP1().setEnabled(false);
        menuBar.getBtnP2().setEnabled(false);
        board.toggleCellsEnabled(appState);
        currentPlayer.performTurn(this);
    }

    /**
     * Toggle Human/Computer player
     *
     * @param e
     */
    private void playerBtnClicked(ActionEvent e) {
        Player player;
        if (e.getSource() == menuBar.getBtnP1()) {
            player = players[0];
        } else {
            player = players[1];
        }

        BtnSelectPlayer btnSelectPlayer = (BtnSelectPlayer) e.getSource();
        if (player.isHuman()) {
            player.setHuman(false);
            btnSelectPlayer.setText("Robot");
        } else {
            player.setHuman(true);
            btnSelectPlayer.setText("Human");
        }

        String msg = String.format("Player %c is now a %s player", player.getSign(), btnSelectPlayer.getText());
        logger.log(Level.INFO, msg);
    }

    /**
     * Checks if the game is finished (player won or draw)
     *
     * @return
     */
    private boolean isFinished() {

        // Check rows
        Cell[][] cells = board.getCells();
        for (int i = 0; i < cells.length; i++) {
            int rowStreak = 0;
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].getText().equals(currentPlayer.getSignAsString())) {
                    rowStreak++;
                } else {
                    continue;
                }
            }

            if (rowStreak == 3) {
                currentPlayerHasWon();
                return true;
            }
        }


        // Check cols
        for (int i = 0; i < cells[0].length; i++) {
            int colStreak = 0;
            for (int j = 0; j < cells.length; j++) {
                if (cells[j][i].getText().equals(currentPlayer.getSignAsString())) {
                    colStreak++;
                } else {
                    continue;
                }
            }

            if (colStreak == 3) {
                currentPlayerHasWon();
                return true;
            }
        }

        // Check diagonals
        if (cells[0][0].getText().equals(currentPlayer.getSignAsString()) &&
                cells[1][1].getText().equals(currentPlayer.getSignAsString()) &&
                cells[2][2].getText().equals(currentPlayer.getSignAsString())) {
            currentPlayerHasWon();
            return true;
        }

        if (cells[2][0].getText().equals(currentPlayer.getSignAsString()) &&
                cells[1][1].getText().equals(currentPlayer.getSignAsString()) &&
                cells[0][2].getText().equals(currentPlayer.getSignAsString())) {
            currentPlayerHasWon();
            return true;
        }

        // Check draw
        if (turnCount == 9) {
            setAppState(AppState.DRAW);
            board.toggleCellsEnabled(appState);
            return true;
        }

        return false;
    }

    void newTurn(Cell cell) {
        if (cell.isOccupied()) {
            logger.log(Level.INFO, "Cell already occupied - skipping");
            return;
        }

        turnCount++;
        logger.log(Level.INFO, "Turn #" + turnCount + ", Player" + currentPlayer.getSignAsString());

        cell.setText(currentPlayer.getSignAsString());
        if (isFinished()) {
            return;
        }

        if (currentPlayer == players[0]) {
            currentPlayer = players[1];
        } else {
            currentPlayer = players[0];
        }

        refreshStatusBar();
        currentPlayer.performTurn(this);

    }

    /**
     * Sets the current player as winner in
     */
    private void currentPlayerHasWon() {
        if (currentPlayer == players[0]) {
            setAppState(AppState.X_WINS);
        } else {
            setAppState(AppState.O_WINS);
        }

        board.toggleCellsEnabled(appState);
    }
}