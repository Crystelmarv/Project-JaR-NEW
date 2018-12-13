package game;

import java.awt.Graphics;

import gui.Handler;
import resManager.Assets;

public class Leben
{
  private final int MAXLEBEN = 5;
  private int aktuelleLeben = 3;
  private int[] x;
  private int y;
  
  private Handler handler;

  public Leben(Handler handler)
  {
    this.handler = handler;
    x = new int[MAXLEBEN];
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
    
    x[0] = handler.getPlayer().getX() - 500;
    y = handler.getPlayer().getY() -500;
    
    for(i = 1; i< aktuelleLeben; i++)
    {
      x[i] = x[i-1] + 100;
    }
    
    isTod();
  }
  
  
  public void isTod()
  {
    if(aktuelleLeben <= 0)
    {
      handler.getPlayer().sterben();
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
