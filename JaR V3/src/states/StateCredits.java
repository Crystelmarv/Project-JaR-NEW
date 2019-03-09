package states;

import java.awt.Graphics;
import java.io.IOException;

import game.Handler;
import gui.FrameMain;
import gui.PanelCredits;
import gui.PanelHauptMenue;

public class StateCredits extends State
{
  private PanelCredits panelCredits; 
  
  public StateCredits(Handler handler)
  {
    super(handler);
    panelCredits = new PanelCredits(handler);
    handler.getFrameMain().add(panelCredits);
  }

  @Override
  public void update() throws IOException
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void paint(Graphics g)
  {
   
    
  }

  @Override
  public void stateUpdate()
  {
    FrameMain.setFps(10000000000000.0);
    panelCredits.setVisible(true);
    
  }

}
