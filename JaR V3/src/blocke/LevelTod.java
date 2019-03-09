package blocke;

import java.awt.Graphics;

import game.Entity;
import game.Handler;
import resManager.Assets;

public class LevelTod extends Entity
{

  public LevelTod(int xp, int yp, int blockID, Handler handler)
  {
    super(xp, yp, blockID, handler);
    walkable = true;
    gegnerWalkable = true;
  }

  @Override
  public void paint(Graphics g)
  {
   g.drawImage(Assets.erde, x, y, null);
    
  }

  @Override
  public void update()
  {
   intersect();
  }
  
  public void intersect()
  {
    if (handler.getPlayer().getBounds().intersects(getBounds()))
    {
      handler.getPlayer().respawnAtCheckpoint();
      handler.getLeben().lebenMinus();
    }
  }

}
