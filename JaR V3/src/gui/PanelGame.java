package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.Handler;
import game.Kamera;
import game.Level;
import game.LevelCreator;
import resManager.Assets;
import states.StateGame;
import states.StateManager;

public class PanelGame extends JPanel
{
  private Handler handler;
  private Level level;
  private JDialog pauseDialog;
  


  private boolean levelErstellt = false;

  public PanelGame(Handler handler) throws IOException
  {
    this.handler = handler;
    setSize(FrameMain.FRAMEBREITE, FrameMain.FRAMEHOEHE);
    setLayout(null);
    setVisible(false);
    
    pauseDialog = new DialogPause(handler);

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

  public void setLevelErstellt(boolean levelErstellt)
  {
    this.levelErstellt = levelErstellt;
  }
  
  public void keyReleased(KeyEvent e)
  {
    
  }
  
  public void keyPressed(KeyEvent e)
  {
    if( e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_P)
    {
      pauseDialog.setVisible(true);
     
      StateGame  stg = (StateGame) handler.getStateGame();
      stg.setPause(true);
      
    }
  }



}
