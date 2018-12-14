package game;

import java.awt.Rectangle;

import gui.Handler;

public abstract class Interactables extends Entity
{
  protected int HITBOX_X = x;
  protected int HITBOX_Y = y;
  protected int HITBOX_BREITE = BREITE;
  protected int HITBOX_HOEHE = HOEHE;

  public Interactables(int xp, int yp, int blockID, Handler handler)
  {
    super(xp,yp, blockID, handler);
  }
  
  @Override
  public Rectangle getBounds()
  {
    return new Rectangle(HITBOX_X, HITBOX_Y, HITBOX_BREITE, HITBOX_HOEHE);
  }
}
