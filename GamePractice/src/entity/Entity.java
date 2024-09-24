package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * @author Mason Luke
 * @version 0 (9/21/2024)
 */
public class Entity 
{
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, left1, left2, right1, right2, down1, down2; //Used to store image files
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle collisionBox;
    public boolean collisionOn = false;

    /**
     * 
     * @param collision boolean for collision
     */
    public void setCollision(boolean collision)
    {
        this.collisionOn = collision;
    }

    /**
     * Accessor for collision.
     * @return collisionOn collision on/off
     */
    public boolean getCollison()
    {
        return collisionOn;
    }

    /**
     * Accessor for direction.
     * @return direction the direction i.e. "up", "down", ect.
     */
    public String getDirection()
    {
        return direction;
    }

    /**
     * Accessor for Speed.
     * @return speed the speed value i.e. 3, 4, ect.
     */
    public int getSpeed()
    {
        return speed;
    }
}
