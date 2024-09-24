package main.client;

import objects.Object_Chest;
import objects.Object_Door;
import objects.Object_Key;

public class AssetSetter 
{
    GamePanel gamePanel;
    public AssetSetter(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }

    public void setObject()
    {
        gamePanel.objects[0] = new Object_Key();
        gamePanel.objects[0].worldX = 24 * gamePanel.getTileSize();
        gamePanel.objects[0].worldY = 21 * gamePanel.getTileSize();

        gamePanel.objects[1] = new Object_Key();
        gamePanel.objects[1].worldX = 24 * gamePanel.getTileSize();
        gamePanel.objects[1].worldY = 27 * gamePanel.getTileSize();

        gamePanel.objects[2] = new Object_Door();
        gamePanel.objects[2].worldX = 27 * gamePanel.getTileSize();
        gamePanel.objects[2].worldY = 25 * gamePanel.getTileSize();

        gamePanel.objects[3] = new Object_Chest();
        gamePanel.objects[3].worldX = 28 * gamePanel.getTileSize();
        gamePanel.objects[3].worldY = 25 * gamePanel.getTileSize();
    }
}
