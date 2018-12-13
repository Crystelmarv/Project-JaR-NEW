package game;

import gui.Handler;

public abstract class Interactables extends Entity
{

  public Interactables(int xp, int yp, int blockID, Handler handler)
  {
    super(xp,yp, blockID, handler);
  }
}
