package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity
{
  protected int x;
  protected int y;
  protected int BREITE = 64;
  protected int HOEHE = 64;
  protected int blockID;
  protected boolean walkable = false;
  protected boolean gegnerWalkable = false;
  protected Handler handler;
  
    
  

  public Entity(int xp, int yp, int blockID, Handler handler)
  {
    x = xp;
    y = yp;
    this.blockID = blockID;
    this.handler = handler;
    
  }
  
  abstract public void paint(Graphics g);
  abstract public void update();
  
  public Rectangle getBounds()
  {
    return new Rectangle(x, y, BREITE, HOEHE);
  }
  
  public boolean isWalkable()
  {
    return walkable;
  }
  
  public boolean isGegnerWalkable()
  {
    return gegnerWalkable;
  }

  public int getX()
  {
    return x;
  }

  public int getY()
  {
    return y;
  }
  
  

}
