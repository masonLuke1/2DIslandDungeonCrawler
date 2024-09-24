package main.client;

import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

/**
 * Game's Main Method 
 * JFrame represents a framed window.
 * 
 * @author Mason Luke
 * @version 0 (9/20/2024)
 */
public class Main 
{
    public static void main(String[] args) throws Exception 
    {
        JFrame gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("2D Island Dungeon Crawler");

        GamePanel gamePanel = new GamePanel();
        gameWindow.add(gamePanel);
        gameWindow.pack();

        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);

        gamePanel.setUpGameObj();
        gamePanel.startGameThread();
    }
}
