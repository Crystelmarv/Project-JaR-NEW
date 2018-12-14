package blocke;

import java.awt.Graphics;

import game.Entity;
import gui.Handler;
import items.Item;
import items.ItemApfel;
import items.ItemFeuer;
import items.ItemLebenPlus;
import resManager.Assets;

public class BlockItem extends Entity
{
  private boolean getroffen = false;
  private boolean itemGespawned = false;
  private Item item;

  public BlockItem(int xp, int yp, int blockID, Handler handler)
  {
    super(xp, yp, blockID, handler);
  }

  @Override
  public void paint(Graphics g)
  {
    if (getroffen == true)
    {
      g.drawImage(Assets.blauerBlock, x, y, null);
    } else
    {
      g.drawImage(Assets.orangerBlock, x, y, null);
    }

  }

  @Override
  public void update()
  {
    collission();

    if (itemGespawned == false && getroffen == true)
    {
      switch (blockID)
      {
      case 51:

        item = new ItemLebenPlus(x, y-63, blockID, handler);

        break;

      case 52:
        item = new ItemApfel(x, y-63, blockID, handler);
        break;
      case 54:
        item = new ItemFeuer(x, y-63, blockID, handler);
        break;

      }
      handler.getLevel().setEntity(item);

      itemGespawned = true;
      isGegnerOnTop();
    }

  }

  public void collission()
  {
    if (handler.getPlayer().getX() > x - 59 && handler.getPlayer().getX() < x + 59
        && handler.getPlayer().getY() == y + 64 && getroffen == false)
    {
      handler.getPlayer().setFall();
      getroffen = true;

      isGegnerOnTop();
    }
  }

  private void isGegnerOnTop()
  {
    int i, gegnerX, gegnerY;

    for (i = 0; i < handler.getLevel().getEntityListSize(); i++)
    {
      gegnerX = handler.getLevel().getEntity(i).getX();
      gegnerY = handler.getLevel().getEntity(i).getY();

      if (gegnerX > x - 59 && gegnerX < x + 59 && gegnerY == y - 64)
      {
        handler.getLevel().removeEntity(handler.getLevel().getEntity(i));
      }
    }
  }

}
