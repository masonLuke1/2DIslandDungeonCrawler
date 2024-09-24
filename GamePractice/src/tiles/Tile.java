package tiles;

import java.awt.image.BufferedImage;

/**
 * @author Mason Luke
 * @version 0 (9/21/2024)
 */
public class Tile 
{
    public BufferedImage img;
    public boolean collision = false;

    public void setImage(BufferedImage img)
    {
        this.img = img;
    }

    public BufferedImage getImage()
    {
        return this.img;
    }

    public void setCollision(boolean collision)
    {
        this.collision = collision;
    }

    public boolean getCollision()
    {
        return this.collision;
    }
}
