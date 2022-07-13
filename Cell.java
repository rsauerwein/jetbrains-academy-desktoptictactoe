package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cell extends JButton {
    Cell(String name) {
        super(" ");
        setName("Button" + name);
        setFont(new Font("Arial", Font.BOLD, 40));
        setFocusPainted(false);
    }

    boolean isOccupied() {
        return !this.getText().isBlank();
    }
}
