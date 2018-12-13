package items;

import java.awt.Graphics;

import gui.Handler;
import resManager.Assets;

public class ItemApfel extends Item
{
  private boolean aufgesammelt = false;

  public ItemApfel(int xp, int yp, int blockID, Handler handler)
  {
    super(xp, yp, blockID, handler);
    BREITE = 50;
    HOEHE = 50;
    walkable = true;
  }

  @Override
  public void paint(Graphics g)
  {
    g.drawImage(Assets.apfel, x, y, null);

  }

  @Override
  public void update()
  {
    intersect();
  }

  public void intersect()
  {
    if (handler.getPlayer().getBounds().intersects(getBounds()) && aufgesammelt == false)
    {
      if (aufgesammelt == false)
      {
        handler.getAnzeigeApfel().apfelPlus();
        handler.getLevel().removeEntity(item);
        aufgesammelt = true;
      }
    }
  }
}
