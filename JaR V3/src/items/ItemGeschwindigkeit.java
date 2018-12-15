package items;

import java.awt.Graphics;

import game.Handler;
import resManager.Assets;

public class ItemGeschwindigkeit extends Item
{

  public ItemGeschwindigkeit(int xp, int yp, int blockID, Handler handler)
  {
    super(xp, yp, blockID, handler);
  }

  @Override
  public void paint(Graphics g)
  {
    g.drawImage(Assets.itemSchuh, x, y, null);

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
      handler.getItemManager().setAktivesItem("geschwindigkeit");
      handler.getLevel().removeEntity(item);
    }
    
  }

}
