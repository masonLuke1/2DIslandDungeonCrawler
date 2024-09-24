package entity;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
//import java.io.IOError;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.client.GamePanel;
import main.client.KeyHandler;

/**
 * @author Mason Luke
 * @version 0 (9/21/2024)
 */
public class Player extends Entity
{
    public final int playerSpriteRefreshRate = 15; //updates player sprite every x frames.
    GamePanel gamePanel;
    KeyHandler keyHandle;

    public final int screenX;
    public final int screenY;

    /**
     * 
     * @param gamePanel
     * @param keyHandle
     */
    public Player(GamePanel gamePanel, KeyHandler keyHandle)
    {
        this.gamePanel = gamePanel;
        this.keyHandle = keyHandle;

        screenX = gamePanel.getScreenTileWidth()/2 - gamePanel.getTileSize()/2;
        screenY = gamePanel.getScreenTileHeight()/2 - gamePanel.getTileSize()/2;

        //Collision Settings
        collisionBox = new Rectangle();
        collisionBox.x = gamePanel.getTileSize()/4; //right 8pxa
        collisionBox.y = gamePanel.getTileSize()/3; //down 16px
        collisionBox.width = gamePanel.getTileSize() - collisionBox.x*2; 
        collisionBox.height = gamePanel.getTileSize() - collisionBox.y;
        
        

        setPlayerDefaults();
        getPlayerImage();
    }

    public void setPlayerDefaults()
    {
        worldX = gamePanel.getTileSize() * 24;
        worldY = gamePanel.getTileSize() * 24;
        speed = 4;
        direction = "down"; //default facing direction
    }

    public void getPlayerImage()
    {
        try
        {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
        }
        catch(IOException e)
        {
            System.out.println("Resource Not Found!!!");
            e.printStackTrace();
        }
    }

    public void update()
    {
        if(keyHandle.getDownPressed() == true || keyHandle.getUpPressed() == true ||
            keyHandle.getRightPressed() == true || keyHandle.getLeftPressed() == true)
        {
            if(keyHandle.getUpPressed() == true)
            {
                direction = "up";
                //System.out.println("this.player_Y = " + this.y);
            }
            if(keyHandle.getLeftPressed() == true)
            {
                direction = "left";
                //System.out.println("this.player_X = " + this.x);
            }
            if(keyHandle.getRightPressed() == true)
            {
                direction = "right";
                //System.out.println("this.player_X = " + this.x);
            }
            if(keyHandle.getDownPressed() == true)
            {
                direction = "down";
                //System.out.println("this.player_Y = " + this.y);
            }
            
            //Check tile collsion
            collisionOn = false;
            gamePanel.collsionChecker.checktile(this);

            if (getCollison() == false)
            {
                switch (getDirection()) {
                    case "up":
                        this.worldY -= speed;
                        break;
                    case "down":
                        this.worldY += speed;
                        break;
                    case "left":
                        this.worldX -= speed;
                        break;
                    case "right":
                        this.worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter > playerSpriteRefreshRate)
            {
                if(spriteNum == 1)
                {
                    spriteNum = 2;
                }
                else if (spriteNum == 2)
                {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D twoDg)
    {
        //twoDg.setColor(Color.white);
        //twoDg.fillRect(this.x, this.y, gamePanel.getTileSize(), gamePanel.getTileSize()); // (x, y, width, height)

        BufferedImage image = null;

        switch(direction)
        {
            case "up":
                if(spriteNum == 1)
                {
                    image = up1;
                }
                if(spriteNum == 2)
                {
                    image = up2;
                }
                break;

            case "left":
                if(spriteNum == 1)
                {
                    image = left1;
                }
                if(spriteNum == 2)
                {
                    image = left2;
                }
                break;

            case "right":
                if(spriteNum == 1)
                {
                    image = right1;
                }
                if(spriteNum == 2)
                {
                    image = right2;
                }
                break;

            case "down":
                if(spriteNum == 1)
                {
                    image = down1;
                }
                if(spriteNum == 2)
                {
                    image = down2;
                }
                break;

            //default:
            //    image = down1;
            //    break;
        }
        twoDg.drawImage(image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }
}
