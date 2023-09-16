package ru.romanovvb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {

    private static GameField gameField;
    public static final int SIZE = 480;
    public static final int DOT_SIZE = 32;
    public static final int ALL_DOTS = 620;
    private JLabel pause;
    private JLabel fireworks;
    private Snake snake;
    private WallAndRoof wallAndRoof;
    private Image head;
    private Image body;
    private Image apple;

    private int appleX;
    private int appleY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private Timer timer = new Timer(250,this);
    private boolean inGame = true;
    private int appleScore;
    private int WIN_SCORE = 30;
    private boolean infiniteMode = false;
    private int infiniteWinScore;
    ControlGame controlGame;


    private GameField(){
        controlGame = new ControlGame();
        pause = new JLabel("Pause");
        pause.setFont(new Font("Pause", Font.BOLD,24));
        pause.setVisible(false);
        add(pause);
        URL fireworksUrl = GUI.class.getResource("/images/fireworks.gif");
        fireworks = new JLabel(new ImageIcon(fireworksUrl));
        add(fireworks);
        fireworks.setVisible(false);
        setSize(SIZE,SIZE);
        setMinimumSize(new Dimension(SIZE, SIZE));
        setMaximumSize(new Dimension(SIZE, SIZE));
        setOpaque(false);

        loadImages();
        addKeyListener(controlGame);
        addKeyListener(new StopAndSpeed());
        setFocusable(true);
        setBorder(BorderFactory.createLineBorder(Color.black, 3));

    }
    public static GameField getInstance() {
        if (gameField == null) {
            gameField = new GameField();
        }
        return gameField;
    }

    // Метод начала игры
    public void initGame(){
        inGame = true;
        pause.setVisible(false);
        fireworks.setVisible(false);
        controlGame.defaultControl();
        head = snake.getHeadRight();
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 32 - i*DOT_SIZE;
            y[i] = 128;
        }
        timer.start();
        createApple();
        appleScore = 0;
        requestFocus();

        infiniteWinScore = 225 - dots - wallAndRoof.getWallsCount()-1;

    }

    public void createApple(){
        boolean createAp = false;
        while (!createAp) {
            int xA = new Random().nextInt(15) * DOT_SIZE;
            int yA = new Random().nextInt(15) * DOT_SIZE;
            boolean checkSnake = false;
            for (int i = 0; i < dots; i++) {
                if (xA == x[i] && yA == y[i]) {
                    checkSnake = true;
                }
            }
            boolean checkWall = false;
            for(int i = 0; i < wallAndRoof.getWallsCount(); i++) {
                if (xA == wallAndRoof.getxWall()[i] && yA == wallAndRoof.getyWall()[i]) {
                    checkWall = true;
                }
            }
            if (!checkSnake && !checkWall) {
                appleX = xA;
                appleY = yA;
                createAp = true;
                break;
            }
        }
    }
    public void loadImages() {
        snake = new Snake();
        body = snake.getBody();
        head = snake.getHeadRight();
        wallAndRoof = new WallAndRoof();
        URL appleUrl = this.getClass().getResource("/images/apple.png");
        apple = Toolkit.getDefaultToolkit().getImage(appleUrl);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Прорисовка экрана победы для Бесконечного режима
        if (infiniteMode && appleScore == infiniteWinScore) {
            inGame = false;
            fireworks.setVisible(true);

            g.setColor(Color.white);
            String str = "СУПЕР ПОБЕДА!!!";
            g.setFont(new Font("win",Font.BOLD, 30));
            g.drawString(str, SIZE / 4, SIZE / 2);

            Font font = new Font("font", Font.BOLD, 16);
            g.setFont(font);
            String str2 = "Поздравляю Вас с Супер Победой!";
            g.drawString(str2, 96, 370);
            String str3 = "Это было действительно сложно сделать!";
            g.drawString(str3, 72, 400);
            String str4 = "Большое спасибо Вам, что играли в мою игру!";
            g.drawString(str4, 54, 430);
        }
        // Прорисовка экрана победы для обычного режима
        if (!infiniteMode && appleScore == WIN_SCORE) {
            inGame = false;
            String str = "Победа!!!";
            g.setColor(Color.white);
            g.setFont(new Font(str, Font.BOLD, 30));
            g.drawString(str, SIZE / 2 - 75, SIZE / 2);

            Font font = new Font("font", Font.BOLD, 16);
            g.setFont(font);
            String str2 = "Поздравляю Вас с Победой!";
            g.drawString(str2, 128, 370);
            String str4 = "Большое спасибо Вам, что играли в мою игру!";
            g.drawString(str4, 54, 430);

        }
        // Прорисовка самой игры
        if (inGame) {
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 0; i < dots; i++) {
                if (i == 0) {
                    g.drawImage(head, x[i], y[i], this);
                } else {
                    g.drawImage(body, x[i], y[i], this);
                }
            }
            if (wallAndRoof.getWallsCount() != 0) {
                for (int i = 0; i < wallAndRoof.getWallsCount(); i++) {
                    g.drawImage(wallAndRoof.getWallImage(), wallAndRoof.getxWall()[i], wallAndRoof.getyWall()[i], this);
                }
            }
            if (wallAndRoof.getRoofCount() != 0) {
                for (int i = 0; i < wallAndRoof.getRoofCount(); i++) {
                    g.drawImage(wallAndRoof.getRoofImage(), wallAndRoof.getxRoof()[i], wallAndRoof.getyRoof()[i], this);
                }
            }
        } else if ((appleScore < WIN_SCORE && !infiniteMode) || (appleScore < infiniteWinScore && infiniteMode)){
            String str = "Game Over";
            g.setColor(Color.white);
            g.setFont(new Font(str, Font.BOLD, 20));
            g.drawString(str, SIZE / 2 - 50, SIZE / 2);

        }
    }

    public void move(){
        for (int i = dots; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if(controlGame.isLeft()){
            x[0] -= DOT_SIZE;
            head = snake.getHeadLeft();
        }
        if(controlGame.isRight()){
            x[0] += DOT_SIZE;
            head = snake.getHeadRight();
        }
        if(controlGame.isUp()){
            y[0] -= DOT_SIZE;
            head = snake.getHeadUp();
        }
        if(controlGame.isDown()){
            y[0] += DOT_SIZE;
            head = snake.getHeadDown();
        }
    }
    // Три проверки коллизий змейки
    public void checkApple(){
        if(x[0] == appleX && y[0] == appleY){
            dots++;
            appleScore++;
            createApple();
        }
    }
    public void checkCollisions(){
        for (int i = dots; i >0 ; i--) {
            if(i>3 && x[0] == x[i] && y[0] == y[i]){
                inGame = false;
            }
        }

        if(x[0]>SIZE-32){
            inGame = false;
        }
        if(x[0]<0){
            inGame = false;
        }
        if(y[0]>SIZE-32){
            inGame = false;
        }
        if(y[0]<0){
            inGame = false;
        }
    }
    public void checkWallCollisions() {
        if (wallAndRoof.getWallsCount() != 0) {
            for (int i = 0; i < wallAndRoof.getWallsCount(); i++) {
                if (x[0] == wallAndRoof.getxWall()[i] && y[0] == wallAndRoof.getyWall()[i]) {
                    inGame = false;
                    break;
                }
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            checkApple();
            checkCollisions();
            checkWallCollisions();
            move();
        }
        repaint();
    }


    public void setSpeedSnake(String speed) {
        if (speed.equals("Low speed"))
            timer.setDelay(350);
        if (speed.equals("Medium speed"))
            timer.setDelay(250);
        if (speed.equals("Fast speed"))
            timer.setDelay(150);
    }

    public void setInfiniteMode(boolean infiniteMode) {
        this.infiniteMode = infiniteMode;
    }
    public WallAndRoof getWall() {
        return wallAndRoof;
    }

    public Timer getTimer() {
        return timer;
    }

    public JLabel getPause() {
        return pause;
    }

    public static class StopAndSpeed extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_SPACE) {
                if (gameField.getTimer().isRunning()) {
                    gameField.getTimer().stop();
                    gameField.getPause().setVisible(true);
                } else {
                    gameField.getTimer().start();
                    gameField.getPause().setVisible(false);
                }
            }
            // Клавиша для запуска(перезапуска) игры
            if (key == KeyEvent.VK_F2) {
                gameField.initGame();
            }
            // Клавиши управления скорость игры
            if (key == KeyEvent.VK_1) {
                gameField.setSpeedSnake("Low speed");
            }
            if (key == KeyEvent.VK_2) {
                gameField.setSpeedSnake("Medium speed");
            }
            if (key == KeyEvent.VK_3) {
                gameField.setSpeedSnake("Fast speed");
            }
        }
    }
}
