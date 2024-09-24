package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Object_Door extends SuperObject
{
    public Object_Door()
    {
        this.name = "Door";
        try
        {
            img = ImageIO.read(getClass().getResourceAsStream("/objects/door.png/"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
