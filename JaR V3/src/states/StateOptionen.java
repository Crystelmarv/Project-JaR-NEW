package states;

import java.awt.Graphics;

import game.Handler;
import gui.PanelLevelSelect;
import gui.PanelOptionen;

public class StateOptionen extends State
{
  private PanelOptionen panelOptionen;
  
  public StateOptionen(Handler handler)
  {
    super(handler);
    panelOptionen = new PanelOptionen(handler);
    handler.setPanelOptionne(panelOptionen);
    handler.getFrameMain().add(panelOptionen);
  }

  @Override
  public void update()
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void paint(Graphics g)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void stateUpdate()
  {
    panelOptionen.setVisible(true);
    
  }

}
