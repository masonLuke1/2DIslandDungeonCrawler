package main.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import objects.SuperObject;
//import tiles.Tile;
import tiles.TileManager;

/**
 * JPanel represents some area in which controls (e.g., buttons, checkboxes, and textfields) 
 * and visuals (e.g., figures, pictures, and even text) can appear.
 *
 * @author Mason Luke
 * @version 0 (9/20/2024)
 */
public class GamePanel extends JPanel implements Runnable
{
    public final int originalTileSize = 32;
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale; //64*64 Tile size (scaled)
    public final int maxScreenColumns = 16;
    public final int maxScreenRows = 9; //  aspect ratio 16:9 
    public final int screenTileHeight = tileSize * maxScreenRows; // 786 
    public final int screenTileWidth = tileSize * maxScreenColumns; // 1024

    public final int maxWorldColumns = 50;
    public final int maxWorldRows = 50;
    public final int worldTileHeight = tileSize * maxWorldRows; //  3200
    public final int worldTileWidth = tileSize * maxWorldColumns; // 3200

    final int FPS = 60;

    TileManager tileMan = new TileManager(this);

    KeyHandler keyHandle = new KeyHandler();

    Thread gameThread;

    public CollsionChecker collsionChecker = new CollsionChecker(this);

    public AssetSetter assetSetter = new AssetSetter(this);

    public Player player = new Player(this, keyHandle);

    public SuperObject objects[] = new SuperObject[10]; // 10 objects displayed max at once

    //Default Player Location
    int player_X = 100;
    int player_Y = 100;
    int player_speed = 4;

    /**
     * No-Arg Constructor.
     */
    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenTileWidth, screenTileHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(false); //Improves Performance?
        this.addKeyListener(keyHandle);
        this.setFocusable(true); //The focusable flag indicates whether a component can gain the focus if it is requested to do so. Ex: text box/focus would need true (Redundant?)
    }

    public void setUpGameObj()
    {
        assetSetter.setObject();
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Game Loop (Running);
     */
    @Override
    public void run()
    {
        //long curTime = System.nanoTime(); //returns current time of the program in nanoseconds(1,000,000,000ns = 1s)
        //long curTime = System.currentTimeMillis() 1,000ms = 1s nano is more accurate.

        double drawInterval = 1000000000/FPS; //nanoSeconds Between Frames (0.016667s)
        double nextDrawTime = System.nanoTime() + drawInterval; //next time that we will loop again.
        long currentTime; //FPS
        long timer = 0; //FPS
        long drawCount = 0; //FPS
        long lastTime = System.nanoTime(); //FPS
        while(this.gameThread != null)
        {
            update();
            repaint();

            try 
            {
                currentTime = System.nanoTime();
                double remainingTime = nextDrawTime - currentTime;

                timer += (System.nanoTime() - lastTime); //FPS
                lastTime = currentTime; //FPS

                remainingTime = remainingTime/1000000;
                if(remainingTime < 0)
                {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime); //Pause game loop until 0.016667s have passed total.
                nextDrawTime += drawInterval;
                drawCount++; //FPS
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
            if (timer >= 1000000000) //FPS
            {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    /**
     * Updates infromation like player/obj location
     */
    public void update() 
    {
        player.update();
    }

    /**
     * Draw to screen with updated info
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D twoDg = (Graphics2D)g; //Graphics2D provides more sophistocated geometry control.

        //Tiles
        tileMan.draw(twoDg);

        //Objects
        for (int i = 0; i < objects.length; i++) //loop through 10 objects
        {
            if (objects[i] != null) //if obj exists, draw
            {
                objects[i].draw(twoDg, this);
            }
        }

        //Player
        player.draw(twoDg);



        // Draw the collision box
        twoDg.setColor(Color.RED);

        
        int collisionBoxX = player.screenX + player.collisionBox.x;
        int collisionBoxY = player.screenY + player.collisionBox.y;

        // Draw the collision box at the adjusted position
        twoDg.drawRect(collisionBoxX, collisionBoxY, player.collisionBox.width, player.collisionBox.height);
        twoDg.dispose();

    }

    /**
     * Accessor for tileSize.
     * @return tileSize 64x64 Tile size (scaled)
     */
    public int getTileSize()
    {
        return tileSize;
    }

    /**
     * Accessor for screenTileHeight.
     * @return screenTileHeight the height in px i.e. 786
     */
    public int getScreenTileHeight()
    {
        return screenTileHeight;
    }

    /**
     * Accessor for screenTileWidth.
     * @return screenTileWidth the height in px i.e. 1024
     */
    public int getScreenTileWidth()
    {
        return screenTileWidth;
    }

    /** 
     * Accessor for maxScreenColumns.
     * @return maxScreenColumns no of screen columns i.e. 16
     */
    public int getMaxScreenColumns()
    {
        return maxScreenColumns;
    }

    /**
     * Accessor for maxScreenRows.
     * @return maxScreenRows no of screen rows i.e. 12
     */
    public int getMaxScreenRows()
    {
        return maxScreenRows;
    }

    /**
     * Accessor for maxWorldColumns.
     * @return maxWorldColumns no of screen columns i.e. 50
     */
    public int getMaxWorldColumns()
    {
        return maxWorldColumns;
    }

    /**
     * Accessor for maxWorldRows.
     * @return maxWorldRows no of screen columns i.e. 50
     */
    public int getMaxWorldRows()
    {
        return maxWorldRows;
    }

}
