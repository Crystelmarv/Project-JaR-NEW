package items;

import game.Interactables;
import gui.Handler;

public abstract class Item extends Interactables
{
  protected Item item;

  public Item(int xp, int yp, int blockID, Handler handler)
  {
    super(xp,yp, blockID, handler);
    item = this;
  }

}
