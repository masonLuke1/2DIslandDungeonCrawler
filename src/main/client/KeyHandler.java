package main.client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; //Reciver for keyboard events

public class KeyHandler implements KeyListener
{
    public boolean upPressed, rightPressed, leftPressed, downPressed;

    /**
     * The "key pressed" event. This event is generated when a key is pushed down.
     */
    @Override
    public void keyPressed(KeyEvent e) 
    {
        int keyCode = e.getKeyCode(); //returns int representation of key pressed.

        if(keyCode == KeyEvent.VK_W)
        {
            upPressed = true;
            //System.out.println("upPressed");
        }
        if(keyCode == KeyEvent.VK_A)
        {
            leftPressed = true;
            //System.out.println("leftPressed");
        }
        if(keyCode == KeyEvent.VK_S)
        {
            downPressed = true;
            //System.out.println("downPressed");
        }
        if(keyCode == KeyEvent.VK_D)
        {
            rightPressed = true;
            //System.out.println("rightPressed");
        }
    }

    /**
     * The "key released" event. This event is generated when a key is let up.
     */
    @Override
    public void keyReleased(KeyEvent e) 
    {
        int keyCode = e.getKeyCode(); //returns int representation of key pressed.

        if(keyCode == KeyEvent.VK_W)
        {
            upPressed = false;
            
        }
        if(keyCode == KeyEvent.VK_A)
        {
            leftPressed = false;
        }
        if(keyCode == KeyEvent.VK_S)
        {
            downPressed = false;
        }
        if(keyCode == KeyEvent.VK_D)
        {
            rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
        //Unused Method
    }

    /**
     * accessor for upPressed.
     * @returns value of upPressed.
     */
    public boolean getUpPressed()
    {
        return this.upPressed;
    }

    /**
     * accessor for leftPressed.
     * @returns value of leftPressed.
     */
    public boolean getLeftPressed()
    {
        return this.leftPressed;
    }

    /**
     * accessor for rightPressed.
     * @returns value of rightPressed.
     */
    public boolean getRightPressed()
    {
        return this.rightPressed;
    }

    /**
     * accessor for downPressed.
     * @returns value of downPressed.
     */
    public boolean getDownPressed()
    {
        return this.downPressed;
    }
}
