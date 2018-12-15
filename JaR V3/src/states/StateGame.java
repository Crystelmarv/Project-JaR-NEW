package states;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;

import game.Handler;
import gui.PanelGame;
import gui.PanelLevelSelect;

public class StateGame extends State
{
  private PanelGame panelGame;
  private int aktuelesLevel;

  public StateGame(Handler handler) throws IOException
  {
    super(handler);
    panelGame = new PanelGame(handler);
    handler.getFrameMain().add(panelGame);
  }

  @Override
  public void update() throws IOException
  {
    panelGame.update();

    
  }

  @Override
  public void paint(Graphics g)
  {
 
    panelGame.paint(g);

    
  }

  @Override
  public void stateUpdate()
  {
   
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
  
  

 
}
