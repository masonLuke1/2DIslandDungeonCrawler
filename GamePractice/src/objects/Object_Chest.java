package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Object_Chest extends SuperObject
{
    public Object_Chest()
    {
        this.name = "Chest";
        try
        {
            img = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png/"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
}
