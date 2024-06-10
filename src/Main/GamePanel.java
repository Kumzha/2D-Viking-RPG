package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.ArrayList;

import javax.swing.JPanel;

import Entity.*;
import Tile.TileManager;


public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16; // 16x16 pixel tiles
    final int SCALE = 5;            

    final int tileSize = originalTileSize * SCALE; // 80x80 pixel tiles
    final int maxColumns = 16;                     
    final int maxRows = 11;
    final int screenWidth = tileSize * maxColumns;
    final int screenHeight = tileSize * maxRows;

    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Bullet> bullets = new ArrayList<>();

    final int FPS = 120;
    long timer = 0;
    int drawCount = 0;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    
    Player player = new Player(this, keyHandler);
    TileManager tm = new TileManager(this);


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }


    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; // 0.0166666seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            long startLoopTime = System.nanoTime();

            update();

            repaint();
            drawCount++;            

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

            } catch (IllegalArgumentException e) {
            } catch (InterruptedException e) {
            }
            long endLoopTime = System.nanoTime();
            timer += endLoopTime - startLoopTime;
            
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }

        
    
    }

    public void update(){
        if(keyHandler.plusPressed==true){
            
            enemies.add(new Enemy(this, 1));
        }



        // if(keyHandler.spacePressed==true){

        //     bullets.add(new Bullet(this, player));

        // }

        player.update();

        for(Enemy e : enemies){
            e.update();
        }
        for(Bullet b : bullets){
            b.update();
        }

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tm.draw(g2);

        player.draw(g2);

        for(Enemy e : enemies){
            e.draw(g2);
        }
        for(Bullet b : bullets){
            b.draw(g2);
        }

        g2.dispose();
    }


    //Getters
    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight() {
        return screenHeight;
    }
    public int getTileSize() {
        return tileSize;
    }
    public int getMaxColumns() {
        return maxColumns;
    }
    public int getMaxRows() {
        return maxRows;
    }



}
