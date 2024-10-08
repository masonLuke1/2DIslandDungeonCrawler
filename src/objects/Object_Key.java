package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Object_Key extends SuperObject
{
    public Object_Key()
    {
        this.name = "Key";
        try
        {
            img = ImageIO.read(getClass().getResourceAsStream("/objects/key.png/"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
