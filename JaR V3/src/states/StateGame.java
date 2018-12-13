package states;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;

import gui.Handler;
import gui.PanelGame;
import gui.PanelLevelSelect;

public class StateGame extends State
{
  private PanelGame panelGame;

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
    
  }

 
}
