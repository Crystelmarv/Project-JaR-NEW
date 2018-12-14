package blocke;

import java.awt.Color;
import java.awt.Graphics;

import game.Entity;
import gui.Handler;
import items.Item;
import items.ItemApfel;
import resManager.Assets;

public class Block extends Entity
{
  private boolean gespawned = false;
  private Item item;


  public Block(int xp, int yp, int blockID, Handler handler)
  {
    super(xp,yp, blockID, handler);
    
  
  }

  @Override
  public void paint(Graphics g)
  {
    switch (blockID)
    {
    // Weiﬂ
    case 20:
      g.drawImage(Assets.weiss, x, y, null);
      walkable = true;
      gegnerWalkable = true;
      break;
    // hell Blau
    case 21:
      g.drawImage(Assets.blau, x, y, null);
      break;
      //Gras
    case 30:
      g.drawImage(Assets.gras, x, y, null);
      break;
      //Erde
    case 31:
      g.drawImage(Assets.erde, x, y, null);
      break;
      //Wasser
    case 33:
      g.drawImage(Assets.wasser, x, y, null);
      break;
      //Seerose
    case 34:
      g.drawImage(Assets.seerose, x, y, null);
      break;
      //DevBlock
    case 22:
      g.drawImage(Assets.devBlock, x, y, null);
      break;
    
    case 53:
      g.drawImage(Assets.weiss, x, y, null);
      walkable = true;
      gegnerWalkable = true;
      
      if(gespawned == false)
      {
        item = new ItemApfel(x, y, blockID, handler);
        handler.getLevel().setEntity(item);
        item.update();
        gespawned = true;
      }
      break;

    default:
      break;
    }
  
  }

  @Override
  public void update()
  {
    // TODO Auto-generated method stub
    
  }

}
