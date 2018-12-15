
package states;

import java.awt.Graphics;

import game.Handler;
import gui.PanelLevelSelect;

public class StateLevelSelect extends State
{
  private PanelLevelSelect panelLevelSelect;

  public StateLevelSelect(Handler handler)
  {
    super(handler);
    panelLevelSelect = new PanelLevelSelect(handler);
    handler.getFrameMain().add(panelLevelSelect);
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
    panelLevelSelect.setVisible(true);
    
  }

}
