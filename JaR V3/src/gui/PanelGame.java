package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.Kamera;
import game.Level;
import game.LevelCreator;
import resManager.Assets;
import states.StateManager;

public class PanelGame extends JPanel
{
  private Handler handler;
  private Level level;


  private boolean levelErstellt = false;

  public PanelGame(Handler handler) throws IOException
  {
    this.handler = handler;
    setSize(FrameMain.FRAMEBREITE, FrameMain.FRAMEHOEHE);
    setLayout(null);
    setVisible(false);

    Assets assets = new Assets();
    assets.init();
    requestFocus();
    
    handler.setPanelGame(this);
    
   

  }

  public void update() throws IOException
  {
    if (StateManager.getState().equals(handler.getStateGame()) && levelErstellt == false)
    {
      level = new Level(handler);
    
      levelErstellt = true;
    }
    if (StateManager.getState().equals(handler.getStateGame()) && levelErstellt == true)
    {
      level.update();
   
    }

  }


  public void paint(Graphics g)
  {

    if (levelErstellt == true)
    {

      level.paint(g);
      
    
    

    }
  }



}
