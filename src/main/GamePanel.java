package main;

import entity.*;
import map.MapGrid;
import ui.UserInterface;

import javax.swing.JPanel;
import java.awt.*;

// TODO: eventually replace graphics handling to use a canvas and use a page-flip buffer strategy

public class GamePanel extends JPanel implements Runnable{

    public static final int baseTileSize = 16;
    public static final int scale = 3;
    public static final int tileSize = baseTileSize*scale;
    public static final int maxScreenCol = 34;
    public static final int maxScreenRow = 18;
    public static final int screenWidth = tileSize * maxScreenCol;
    public static final int screenHeight = tileSize * maxScreenRow;

    KeyHandler keyHandler = new KeyHandler();
    MouseHandler mouseHandler = new MouseHandler();
    Thread gameThread;
    EntityHandler entityHandler = new EntityHandler();
    MapGrid map = new MapGrid();
    Player player = new Player(this.keyHandler, this.mouseHandler, this.entityHandler);
    UserInterface ui = new UserInterface();


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        entityHandler.setPlayer(player);
        entityHandler.start();

        int FPS = 60;
        double drawInterval = 1000000000/(double)FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime = System.nanoTime();
        long timer = 0;
        int drawCount = 0;
        boolean gamePaused = false;

        while(gameThread != null){
            //System.out.println("The game loop is running");
            if(!gamePaused) {
                if(keyHandler.p)gamePaused=true;
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;
                if (delta > 0) {
                    if(!gamePaused) update();
                    repaint();
                    delta--;
                    drawCount++;
                }
                if (timer >= 2000000000) {
                    System.out.println("FPS: " + drawCount);
                    System.out.println("Player Row: " + player.getRow());
                    System.out.println("Player Col: " + player.getCol());
                    System.out.println("Player Money: " + player.getMoney() + ", xp: " + player.getXp());
                    drawCount = 0;
                    timer = 0;
                }
            }
        }
    }
    public void update(){
        entityHandler.update();
    }
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g2);
        // Draw map
        map.draw(g2, player);
        entityHandler.draw(g2);
        g2.dispose();
    }
}
