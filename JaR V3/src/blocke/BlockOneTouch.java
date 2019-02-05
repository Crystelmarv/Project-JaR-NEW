package blocke;

import java.awt.Graphics;

import game.Entity;
import game.Handler;
import resManager.Assets;
import resManager.Timer;

public class BlockOneTouch extends Entity
{
  private boolean getroffen = false;
  private double timeStart;

  public BlockOneTouch(int xp, int yp, int blockID, Handler handler)
  {
    super(xp, yp, blockID, handler);
  }

  @Override
  public void paint(Graphics g)
  {
    if (walkable == false)
    {
      g.drawImage(Assets.blauerBlock, x, y, null);

    }
    else
    {
      g.drawImage(Assets.weiss, x, y, null);
    }
    

  }

  @Override
  public void update()
  {
    collision();
    auflösen();

  }

  private void collision()
  {
    if (handler.getPlayer().getX() > x - 59 && handler.getPlayer().getX() < x + 59
        && handler.getPlayer().getY() == y - 60 && getroffen == false)
    {

     
      getroffen = true;
      timeStart = Timer.getTime();

    }
  }

  private void auflösen()
  {
    if (getroffen == true)
    {
      if (Timer.getTime() - timeStart > 1900000000)
      {
        walkable = true;
      }
    }
  }
}
