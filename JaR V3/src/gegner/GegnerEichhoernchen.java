package gegner;

import java.awt.Graphics;

import game.Handler;
import resManager.Assets;
import resManager.Timer;

public class GegnerEichhoernchen extends Gegner
{
  private double timeStart;
  private boolean blickRichtungRechts = false;

  public GegnerEichhoernchen(int xp, int yp, int blockID, Handler handler)
  {
    super(xp, yp, blockID, handler);
    
    timeStart = Timer.getTime();
  }

  @Override
  public void playerGetroffen()
  {
    if (getBounds().intersects(handler.getPlayer().getBounds()))
    {
      if (handler.getPlayer().isFall() == true)
      {
        handler.getPlayer().setJump();
        handler.getLevel().removeEntity(gegner);
      } else
      {
        handler.getPlayer().setNichtAngreifbarTimerSet();
        handler.getLeben().lebenMinus();
      }
    }
    
  }

  @Override
  public void paint(Graphics g)
  {
    if(blickRichtungRechts == false)
    {
      g.drawImage(Assets.eichhoernchenLinks, x, y+2, null);
    }
    else
    {
      g.drawImage(Assets.eichhoernchenRechts, x, y+2, null);
    }
    
    
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
    if (Timer.getTime() - timeStart > 1900000000)
    {
    
      Gegner geg = new GegnerEichhoernchenNuss(x, y, 0, handler, blickRichtungRechts);
      
      handler.getLevel().setEntity(geg);
      
      timeStart = Timer.getTime();
    }
   
  }

}
