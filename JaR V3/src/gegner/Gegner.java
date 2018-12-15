package gegner;

import game.Handler;
import game.Interactables;

public abstract class Gegner extends Interactables
{
  protected Gegner gegner;
  
  //Movement
  protected boolean right = false;
  protected boolean left = false;
  protected boolean jump = false;
  protected boolean fall = false;
  
  protected float speed = 1F;
  protected float dx, dy;
  
  

  public Gegner(int xp, int yp, int blockID, Handler handler)
  {
    super(xp, yp, blockID, handler);
    gegner = this;

  }
  
  public int getX()
  {
    return x;
  }
  
  public int getY()
  {
    return y;
  }
  
  abstract public void playerGetroffen();

}
