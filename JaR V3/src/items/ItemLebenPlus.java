package items;

import java.awt.Graphics;

import gui.Handler;
import resManager.Assets;

public class ItemLebenPlus extends Item
{

  public ItemLebenPlus(int xp, int yp, int blockID, Handler handler)
  {
    super(xp, yp, blockID, handler);
  }

  @Override
  public void paint(Graphics g)
  {
    g.drawImage(Assets.itemLebenPlus, x, y, null);


    
  }

  @Override
  public void update()
  {
    intersect();
    
  }

  private void intersect()
  {
    if(getBounds().intersects(handler.getPlayer().getBounds()))
    {
      handler.getLeben().lebenPlus();
      handler.getLevel().removeEntity(item);
    }
    
  }

}
