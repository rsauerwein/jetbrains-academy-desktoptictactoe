package tictactoe;

import javax.swing.*;

public class MenuGame extends JMenuBar {
    private JMenu gameMenu;
    private JMenuItem humanVsHuman;
    private JMenuItem humanVsRobot;
    private JMenuItem robotVsHuman;
    private JMenuItem robotVsRobot;
    private JMenuItem exit;

    MenuGame() {
        super();

        gameMenu = new JMenu("Game");
        gameMenu.setName("MenuGame");

        humanVsHuman = new JMenuItem("Human vs Human");
        humanVsHuman.setName("MenuHumanHuman");

        humanVsRobot = new JMenuItem("Human vs Robot");
        humanVsRobot.setName("MenuHumanRobot");

        robotVsHuman = new JMenuItem("Robot vs Human");
        robotVsHuman.setName("MenuRobotHuman");

        robotVsRobot = new JMenuItem("Robot vs Robot");
        robotVsRobot.setName("MenuRobotRobot");

        exit = new JMenuItem("Exit");
        exit.setName("MenuExit");

        gameMenu.add(humanVsHuman);
        gameMenu.add(humanVsRobot);
        gameMenu.add(robotVsHuman);
        gameMenu.add(robotVsRobot);
        gameMenu.addSeparator();
        gameMenu.add(exit);

        add(gameMenu);
    }

    public JMenu getGameMenu() {
        return gameMenu;
    }

    public JMenuItem getHumanVsHuman() {
        return humanVsHuman;
    }

    public JMenuItem getHumanVsRobot() {
        return humanVsRobot;
    }

    public JMenuItem getRobotVsHuman() {
        return robotVsHuman;
    }

    public JMenuItem getRobotVsRobot() {
        return robotVsRobot;
    }

    public JMenuItem getExit() {
        return exit;
    }
}
