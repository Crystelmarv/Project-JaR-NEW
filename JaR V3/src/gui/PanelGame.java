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
import resManager.Tastatur;
import resManager.Timer;
import states.StateGame;
import states.StateManager;

public class PanelGame extends JPanel
{
  private Handler handler;
  private Level level;
  private JDialog pauseDialog;
  private JDialog pauseDialogEditor;
  
  private boolean levelEditorTest = false;

  


  private boolean levelErstellt = false;

  public PanelGame(Handler handler) throws IOException
  {
    this.handler = handler;
    setSize(FrameMain.FRAMEBREITE, FrameMain.FRAMEHOEHE);
    setLayout(null);
    setVisible(false);
    
    pauseDialog = new DialogPause(handler);
    pauseDialogEditor = new DialogPauseEditor(handler);

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
      isPause();
   
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
  
  private void isPause()
  {
    
    if(Tastatur.isPause() == true)
    {
      Timer.setTimeVorPause(System.nanoTime());
      
      
      StateGame  stg = (StateGame) handler.getStateGame();
      stg.setPause(true);
      
      Tastatur.setPause(false);
      if(levelEditorTest == true)
      {
        pauseDialogEditor.setVisible(true);
      }
      else
      {
        pauseDialog.setVisible(true);
      }
    
    }
   
  }
 
  public boolean isLevelEditorTest()
  {
    return levelEditorTest;
  }

  public void setLevelEditorTest(boolean levelEditorTest)
  {
    this.levelEditorTest = levelEditorTest;
  }



}
