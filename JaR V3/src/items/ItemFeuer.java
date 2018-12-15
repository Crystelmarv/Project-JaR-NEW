package items;

import java.awt.Graphics;

import game.Handler;
import resManager.Assets;

public class ItemFeuer extends Item
{

  public ItemFeuer(int xp, int yp, int blockID, Handler handler)
  {
    super(xp, yp, blockID, handler);
  }

  @Override
  public void paint(Graphics g)
  {
    g.drawImage(Assets.itemFeuer, x, y, null);
    
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
      handler.getLevel().removeEntity(item);
      handler.getItemManager().setAktivesItem("feuer");
    }
    
  }

}
