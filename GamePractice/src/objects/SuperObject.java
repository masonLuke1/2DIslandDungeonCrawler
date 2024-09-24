package objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.client.GamePanel;

/**
 * Parent Class of all objects
 */
public class SuperObject 
{
    public BufferedImage img;
    public String name;
    public boolean collision = false;
    public int worldX;
    public int worldY;

    public void draw(Graphics2D twoDg, GamePanel gamePanel)
    {
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX; //(worldX - gamePanel.player.worldX) ex: obj pos is 500 500, tile at 0 0, X and Y are -500, -500
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY; // Screen X and Y tell us where to draw in relation to screen

            if(worldX + gamePanel.getTileSize() > gamePanel.player.worldX - gamePanel.player.screenX && 
                worldX - gamePanel.getTileSize() < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.getTileSize() > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.getTileSize() < gamePanel.player.worldY + gamePanel.player.screenY) // create boundaries from screen edges and if within tile is drawn.
                {
                    twoDg.drawImage(img, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
                }
    }
}
