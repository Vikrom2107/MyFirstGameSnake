package ru.romanovvb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;

public class GUI extends JFrame{
    private static JMenuBar menuBar = new JMenuBar();
    private static JPanel mainPanel = new JPanel();
    private static Background background = new Background("/images/BigBack.jpg");
    private static GameField gameField = GameField.getInstance();

    private static boolean stopGame = false;

    public GUI() {
        setTitle("MyFirstGame - Snake");
        setFocusableWindowState(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(710,550);
        setLocation(200,100);
        setResizable(false);
        getContentPane().add(mainPanel);
        getContentPane().add(background);
        getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.X_AXIS));
        setBackground(Color.WHITE);
        setGameFieldPanel();
        setMainPanel();

        setMenuBar();
        setJMenuBar(menuBar);


        setVisible(true);
    }
    public void setMainPanel() {
        mainPanel.setSize(250,400);
        setMinimumSize(new Dimension(250, 400));
        setMaximumSize(new Dimension(250, 400));
        mainPanel.setOpaque(false);

        ArrayList<JComponent> components = new ArrayList<>();
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new StartGameListener());
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new StopGameListener());
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ExitGameListener());
        JLabel controlName = new JLabel("Управление");
        controlName.setFont(new Font("Управление", Font.BOLD, 20));
        URL controlUrl = GUI.class.getResource("/images/control.png");
        JLabel control = new JLabel(new ImageIcon(controlUrl));
        JLabel goal = new JLabel("<html><center>Для победы необходимо<br> собрать 30 яблок</center></html>");
        goal.setFont(new Font("goal", Font.BOLD, 18));
        JCheckBox infiniteMode = new JCheckBox("Включить бесконечный режим");
        infiniteMode.addItemListener(e -> {
            if (infiniteMode.isSelected()) {
                gameField.setInfiniteMode(true);
                gameField.requestFocus();
            } else {
                gameField.setInfiniteMode(false);
                gameField.requestFocus();
            }
        });
        components.add(startButton);
        components.add(stopButton);
        components.add(exitButton);
        components.add(controlName);
        components.add(control);
        components.add(goal);
        components.add(infiniteMode);

        for(JComponent component : components) {
            component.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(component);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
    }
    public void setGameFieldPanel() {
        background.setSize(500,500);
        background.setBackground(Color.BLACK);
        background.add(gameField);
    }
    public void setMenuBar() {
        JMenu fileMenu = new JMenu("Game");
        JMenuItem startMenuItem = new JMenuItem("Start     'F2'");
        startMenuItem.addActionListener(new StartGameListener());

        JMenuItem stopGame = new JMenuItem("Stop    'Space'");
        stopGame.addActionListener(new StopGameListener());

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ExitGameListener());
        fileMenu.add(startMenuItem);
        fileMenu.add(stopGame);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        JMenu levelMenu = new JMenu("Levels");
        JMenuItem level1MenuItem = new JMenuItem("Level 1");
        level1MenuItem.addActionListener(new Level1MenuListener());
        JMenuItem level2MenuItem = new JMenuItem("Level 2");
        level2MenuItem.addActionListener(new Level2MenuListener());
        JMenuItem level3MenuItem = new JMenuItem("Level 3");
        level3MenuItem.addActionListener(new Level3MenuListener());
        JMenuItem level4MenuItem = new JMenuItem("Level 4");
        level4MenuItem.addActionListener(new Level4MenuListener());
        JMenuItem level5MenuItem = new JMenuItem("Level 5");
        level5MenuItem.addActionListener(new Level5MenuListener());

        levelMenu.add(level1MenuItem);
        levelMenu.add(level2MenuItem);
        levelMenu.add(level3MenuItem);
        levelMenu.add(level4MenuItem);
        levelMenu.add(level5MenuItem);
        menuBar.add(levelMenu);

        JMenu speedMenu = new JMenu("Speed");
        JMenuItem lowSpeedMenuItem = new JMenuItem("Low speed          '1'");
        lowSpeedMenuItem.addActionListener(new LowSpeedMenuListener());
        JMenuItem mediumSpeedMenuItem = new JMenuItem("Medium speed   '2'");
        mediumSpeedMenuItem.addActionListener(new MediumSpeedMenuListener());
        JMenuItem fastSpeedMenuItem = new JMenuItem("Fast speed          '3'");
        fastSpeedMenuItem.addActionListener(new FastSpeedMenuListener());
        speedMenu.add(lowSpeedMenuItem);
        speedMenu.add(mediumSpeedMenuItem);
        speedMenu.add(fastSpeedMenuItem);
        menuBar.add(speedMenu);
    }

    // Здесь идут различные Listener-ы
    public static class StartGameListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            gameField.initGame();
        }
    }
    public static class StopGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (gameField.getTimer().isRunning()) {
                gameField.getTimer().stop();
                gameField.getPause().setVisible(true);
                gameField.requestFocus();
            } else {
                gameField.getTimer().start();
                gameField.getPause().setVisible(false);
                gameField.requestFocus();
            }
        }
    }
    public static class ExitGameListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            System.exit(1);
        }
    }
    public static class Level1MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent eve) {
            gameField.getWall().createWall("Level 1");
            gameField.initGame();
        }
    }
    public static class Level2MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent eve) {
            gameField.getWall().createWall("Level 2");
            gameField.initGame();
        }
    }
    public static class Level3MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent eve) {
            gameField.getWall().createWall("Level 3");
            gameField.initGame();
        }
    }
    public static class Level4MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent eve) {
            gameField.getWall().createWall("Level 4");
            gameField.initGame();
        }
    }
    public static class Level5MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent eve) {
            gameField.getWall().createWall("Level 5");
            gameField.initGame();
        }
    }
    public static class LowSpeedMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameField.setSpeedSnake("Low speed");
        }
    }
    public static class MediumSpeedMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameField.setSpeedSnake("Medium speed");
        }
    }
    public static class FastSpeedMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameField.setSpeedSnake("Fast speed");
        }
    }
}
