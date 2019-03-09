package game;

import java.awt.Graphics;
import java.io.IOException;

import resManager.Assets;
import states.StateManager;

public class Leben
{
  private final int MAXLEBEN = 5;
  private int aktuelleLeben = 3;
  private int[] x;
  private int y=50;
  
  private Handler handler;

  public Leben(Handler handler)
  {
    this.handler = handler;
    x = new int[MAXLEBEN];
    x[0]=0;
    update();
  }
  
  public void paint(Graphics g)
  {
    int i;
    
    
    for (i = 0; i < aktuelleLeben; i++)
    {
      g.drawImage(Assets.herz, x[i], y, null);
    }
  }
  
  public void update()
  {
    int i;
    this.x[0] = 100;
    
  
    
    for(i = 1; i< aktuelleLeben; i++)
    {
      this.x[i] = this.x[i-1] + 100;
    }
    
    isTod();
  }
  
  
  public void isTod()
  {
    if(aktuelleLeben <= 0)
    {
      handler.getPanelGame().setVisible(false);
      handler.getFrameMain().repaint();
      if(handler.getPanelGame().isLevelEditorTest() == false)
      {
        
        StateManager.setState(handler.getStateLevelSelect());
        StateManager.getState().stateUpdate();
      }
      else
      {
          try
          {
            handler.getPanelLevelEditor().levelTestenLaden();
            
          } catch (IOException e)
          {
            e.printStackTrace();
          }
      }
    }
  }
  
  public void lebenMinus()
  {
    if(handler.getPlayer().isAngreifbar() == true)
    {
      aktuelleLeben--;
      handler.getPlayer().setNichtAngreifbarTimerSet();
    }
  }
  
  public void lebenPlus()
  {
    if(aktuelleLeben < MAXLEBEN)
    {
      aktuelleLeben++;
    }
  }
  

}
