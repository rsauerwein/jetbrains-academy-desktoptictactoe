package tictactoe;

public class Player {
    private char sign;
    private boolean isHuman;
    private TurnMethod turnMethod;
    Player(char sign) {
        this.sign = sign;
        turnMethod = new HumanTurn();
        isHuman = true;
    }

    public char getSign() {
        return sign;
    }

    public String getSignAsString() {
        return String.valueOf(sign);
    }

    public String getPlayerTypeAsString() {
        return isHuman ? "Human" : "Robot";
    }

    public boolean isHuman() {
        return isHuman;
    }

    public void setHuman(boolean human) {
        turnMethod = human ? new HumanTurn() : new ComputerTurn();
        isHuman = human;
    }

    public void performTurn(TicTacToe ticTacToe) {
        turnMethod.performTurn(ticTacToe, this);
    }
}
