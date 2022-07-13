package tictactoe;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    private LabelStatus labelStatus = new LabelStatus("");
    StatusPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(getWidth(), 16));

        add(labelStatus, BorderLayout.WEST);
    }

    void setStatusPanelText(String text) {
        this.labelStatus.setText(text);
    }
}
