package main.client;

import entity.Entity;

public class CollsionChecker 
{
    GamePanel gamePanel;

    public CollsionChecker(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }

    public void checktile(Entity entity)
    {
        int entityLeftWorldX = entity.worldX + entity.collisionBox.x;
        int entityRightWorldX = entity.worldX + entity.collisionBox.x + entity.collisionBox.width;
        int entityTopWorldY = entity.worldY + entity.collisionBox.y;
        int entityBottomWorldY = entity.worldY + entity.collisionBox.y + entity.collisionBox.height;

        int entityLeftColumn = entityLeftWorldX/gamePanel.getTileSize();
        int entityRightColumn = entityRightWorldX/gamePanel.getTileSize();
        int entityTopRow = entityTopWorldY/gamePanel.getTileSize();
        int entityBottomRow = entityBottomWorldY/gamePanel.getTileSize();

        int tileNum1, tileNum2;

        switch (entity.getDirection()) 
        {
            case "up":

                entityTopRow = (entityTopWorldY - entity.getSpeed())/gamePanel.getTileSize();
                tileNum1 = gamePanel.tileMan.mapTileNum[entityLeftColumn][entityTopRow];
                tileNum2 = gamePanel.tileMan.mapTileNum[entityRightColumn][entityTopRow];
                if (gamePanel.tileMan.tiles[tileNum1].getCollision() == true || gamePanel.tileMan.tiles[tileNum2].getCollision() == true)
                {
                    entity.setCollision(true);
                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed())/gamePanel.getTileSize();
                tileNum1 = gamePanel.tileMan.mapTileNum[entityLeftColumn][entityBottomRow];
                tileNum2 = gamePanel.tileMan.mapTileNum[entityRightColumn][entityBottomRow];
                if (gamePanel.tileMan.tiles[tileNum1].getCollision() == true || gamePanel.tileMan.tiles[tileNum2].getCollision() == true)
                {
                    entity.setCollision(true);
                }
                break;
            case "left":
                entityLeftColumn = (entityLeftWorldX - entity.getSpeed())/gamePanel.getTileSize();
                tileNum1 = gamePanel.tileMan.mapTileNum[entityLeftColumn][entityTopRow];
                tileNum2 = gamePanel.tileMan.mapTileNum[entityLeftColumn][entityBottomRow];
                if (gamePanel.tileMan.tiles[tileNum1].getCollision() == true || gamePanel.tileMan.tiles[tileNum2].getCollision() == true)
                {
                    entity.setCollision(true);
                }
                break;
            case "right":
                entityRightColumn = (entityRightWorldX + entity.getSpeed())/gamePanel.getTileSize();
                tileNum1 = gamePanel.tileMan.mapTileNum[entityRightColumn][entityTopRow];
                tileNum2 = gamePanel.tileMan.mapTileNum[entityRightColumn][entityBottomRow];
                if (gamePanel.tileMan.tiles[tileNum1].getCollision() == true || gamePanel.tileMan.tiles[tileNum2].getCollision() == true)
                {
                    entity.setCollision(true);
                }
                break;
            default:
                break;
        }

    }
}
