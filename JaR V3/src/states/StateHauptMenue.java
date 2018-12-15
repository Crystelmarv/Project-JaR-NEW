package states;

import java.awt.Graphics;

import game.Handler;
import gui.PanelHauptMenue;

public class StateHauptMenue extends State
{
  private PanelHauptMenue panelHauptMenue;

  public StateHauptMenue(Handler handler)
  {
    super(handler);
    panelHauptMenue = new PanelHauptMenue(handler);
    handler.getFrameMain().add(panelHauptMenue);
    
  }

  @Override
  public void update()
  {
    
    
  }

  @Override
  public void paint(Graphics g)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void stateUpdate()
  {
    panelHauptMenue.setVisible(true);
    
  }

}
