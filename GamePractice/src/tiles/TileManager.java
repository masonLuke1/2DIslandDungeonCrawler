package tiles;

import main.client.GamePanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;

public class TileManager 
{
    final int numOfTileTypes = 10;

    GamePanel gamePanel; 
    public Tile[] tiles;
    public int[][] mapTileNum;

    public TileManager(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;

        tiles = new Tile[numOfTileTypes]; 
        mapTileNum = new int[gamePanel.getMaxWorldColumns()][gamePanel.getMaxWorldRows()];

        getTileImage();

        loadMap("/maps/world01.txt");
    }

    public void getTileImage()
    {
        try
        {
            tiles[0] = new Tile();
            tiles[0].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png")));

            tiles[1] = new Tile();
            tiles[1].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/water.png")));
            tiles[1].setCollision(true);
        }
        catch(IOException e)
        {
            System.out.println("Resource Not Found!!!");
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath)
    {
        try
        {
            InputStream inputS = getClass().getResourceAsStream(filePath);
            BufferedReader bufferedR = new BufferedReader(new InputStreamReader(inputS));
         
            int col = 0;
            int row = 0;
            while (col < gamePanel.getMaxWorldColumns() && row < gamePanel.getMaxWorldRows())
            {
                String line = bufferedR.readLine();
                while (col < gamePanel.getMaxWorldColumns()) //Stores an entire row of a map file
                {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    System.out.println(num);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gamePanel.getMaxWorldColumns()) //shifts row to next
                {
                    col = 0;
                    row++;
                }
                
            }
            bufferedR.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        // for(int i = 0; i < gamePanel.getMaxScreenColumns(); i++)
        // {
        //     for(int j = 0; j < gamePanel.getMaxScreenRows(); j++)
        //     {
        //         System.out.println(mapTileNum[i][j]);
        //     }
        // }
    }

    public void draw(Graphics2D twoDg)
    {
        int worldRow = 0;
        int worldCol = 0;
        
        while (worldCol < gamePanel.getMaxWorldColumns() && worldRow < gamePanel.getMaxWorldRows())
        {
            int tileTypeIndex = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gamePanel.getTileSize(); //tile X location i.e. 0, 64, 128
            int worldY = worldRow * gamePanel.getTileSize(); //tile Y location i.e. 0, 64, 128

            //because screenX:0, screenY:0 top left of the screen we need to add offset (gamePanel.player.screenX) so we dont render outside the map.

            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX; //(worldX - gamePanel.player.worldX) ex: players pos is 500 500, tile at 0 0, X and Y are -500, -500
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY; // Screen X and Y tell us where to draw in relation to screen

            if(worldX + gamePanel.getTileSize() > gamePanel.player.worldX - gamePanel.player.screenX && 
                worldX - gamePanel.getTileSize() < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.getTileSize() > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.getTileSize() < gamePanel.player.worldY + gamePanel.player.screenY) // create boundaries from screen edges and if within tile is drawn.
                {
                    twoDg.drawImage(tiles[tileTypeIndex].getImage(), screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
                }

            worldCol++; //increase index to render next tile

            if (worldCol == gamePanel.getMaxWorldColumns())
            {
                worldCol = 0;
                worldRow++;
            }

            // ----------->
            //  V
            // ----------->
            //  V
            // ----------->

        }

        // for(int i = 0; i < gamePanel.getMaxScreenColumns(); i++)
        // {
        //     for(int j =0; j < gamePanel.getMaxScreenRows(); j++)
        //     {
        //         System.out.println(mapTileNum[i][j]);
        //     }
        // }



        // while (drawX < gamePanel.getWidthTileLength())
        // {
        //     twoDg.drawImage(tiles[0].getImage(), drawX, drawY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        //     while(drawY < gamePanel.getHeightTileLength())
        //     {
        //         twoDg.drawImage(tiles[0].getImage(), drawX, drawY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        //         drawY += gamePanel.getTileSize();
        //     }
        //     drawY = 0;
        //     drawX += gamePanel.getTileSize();
        // }
    }
}
