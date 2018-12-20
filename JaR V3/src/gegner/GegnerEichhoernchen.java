package gegner;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;

public class GegnerEichhoernchen extends Gegner
{
  private double timeStart;
  private boolean blickRichtungRechts = false;

  public GegnerEichhoernchen(int xp, int yp, int blockID, Handler handler)
  {
    super(xp, yp, blockID, handler);
    
    timeStart = System.nanoTime();
  }

  @Override
  public void playerGetroffen()
  {
    if (getBounds().intersects(handler.getPlayer().getBounds()))
    {
      handler.getPlayer().setNichtAngreifbarTimerSet();
      handler.getLeben().lebenMinus();
    }
    
  }

  @Override
  public void paint(Graphics g)
  {
   g.setColor(Color.orange);
   g.fillRect(x, y, HOEHE, BREITE);
    
  }

  @Override
  public void update()
  {
    playerSuche();
    schiessen();
    playerGetroffen();
    
  }
  
  private void playerSuche()
  {
    if(x < handler.getPlayer().getX())
    {
      blickRichtungRechts = true;
    }
    else
    {
      blickRichtungRechts = false;
    }
  }
  
  private void schiessen()
  {
    
    double timeNow = System.nanoTime();
    if (timeNow - timeStart > 1900000000)
    {
      Gegner geg = new GegnerEichhoernchenNuss(x, y, 0, handler, blickRichtungRechts);
      
      handler.getLevel().setEntity(geg);
      
      timeStart = timeNow;
    }
   
  }

}
