package blocke;

import java.awt.Graphics;

import game.Entity;
import gui.Handler;
import items.Item;
import resManager.Assets;

public class BlockItem extends Entity
{
  private boolean getroffen = false;
  private boolean itemGespawned = false;
  private Item item;

  public BlockItem(int xp, int yp, int blockID, Handler handler)
  {
    super(xp,yp, blockID, handler);
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
    collision();
    
    if (itemGespawned == false && getroffen == true)
    {
      switch (blockID)
      {
      case 51:
        
      //  item = new ItemLebenPlus(game, x, y);
        
        break;
        
      case 52:
//        item = new ItemApfel;
        break;
      case 54:
     //   aktuellesItem = new ItemFeuer(game, x, y);
        break;
        
      }
      game.items.add(aktuellesItem);
      aktuellesItem.setItem(aktuellesItem);
      aktuellesItem.update();
      
      itemGespawned = true;
    }
    
  }

}
