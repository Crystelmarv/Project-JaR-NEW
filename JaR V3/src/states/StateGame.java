package states;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;

import javax.swing.JDialog;

import game.Handler;
import gui.DialogPause;
import gui.PanelGame;
import gui.PanelLevelSelect;
import resManager.Tastatur;
import resManager.Timer;

public class StateGame extends State
{
  private PanelGame panelGame;
 
  private int aktuelesLevel;
  
  private boolean pause = false;
 
  public StateGame(Handler handler) throws IOException
  {
    super(handler);
    panelGame = new PanelGame(handler);
    handler.getFrameMain().add(panelGame);
    
   
  }

  @Override
  public void update() throws IOException
  {
    if(pause == false)
    {
    
      panelGame.update();
    }
    

    
  }

  @Override
  public void paint(Graphics g)
  {
 
    panelGame.paint(g);

    
  }

  @Override
  public void stateUpdate()
  {
    Tastatur.allKeysNotPressed();
    panelGame.setVisible(true);
    panelGame.setLevelErstellt(false);
    
  }

  public int getAktuelesLevel()
  {
    return aktuelesLevel;
  }

  public void setAktuelesLevel(int aktuelesLevel)
  {
    this.aktuelesLevel = aktuelesLevel;
  }

  public void setPause(boolean pause)
  {
    this.pause = pause;
  }

  
  
  

 
}
