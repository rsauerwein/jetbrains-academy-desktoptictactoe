package tictactoe;

public enum AppState {
    NOT_STARTED("Game is not started"),
    IN_PROGRESS("Game in progress"),
    X_WINS("X wins"),
    O_WINS("O wins"),
    DRAW("Draw");
    private String title;

    AppState(String title) {
        this.title = title;
    }

    String getTitle() {
        return title;
    }
}
