package tictactoe;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JPanel {
    private BtnSelectPlayer btnP1;
    private JButton btnReset;
    private  BtnSelectPlayer btnP2;
    MenuBar() {
        setLayout(new GridLayout(1, 3));

        btnReset = new JButton("Start");
        btnReset.setName("ButtonStartReset");

        btnP1 = new BtnSelectPlayer("Human");
        btnP1.setName("ButtonPlayer1");


        btnP2 = new BtnSelectPlayer("Human");
        btnP2.setName("ButtonPlayer2");


        add(btnP1);
        add(btnReset);
        add(btnP2);
    }

    public BtnSelectPlayer getBtnP1() {
        return btnP1;
    }

    public JButton getBtnReset() {
        return btnReset;
    }

    public BtnSelectPlayer getBtnP2() {
        return btnP2;
    }
}
