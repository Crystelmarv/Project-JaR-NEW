package gegner;

import java.awt.Graphics;

import gui.FrameMain;
import gui.Handler;
import resManager.Assets;

public class GegnerMarienKaefer extends Gegner
{
  // Collision
  private boolean topLeft;
  private boolean topRight;
  private boolean midLeft;
  private boolean midRight;
  private boolean bottomLeft;
  private boolean bottomRight;

  public GegnerMarienKaefer(int xp, int yp, int blockID, Handler handler)
  {
    
    super(xp, yp, blockID, handler);
    HOEHE = 32;
  }

  @Override
  public void paint(Graphics g)
  {
    if(left == true)
    {
      g.drawImage(Assets.marienKaeferLinks, x, y+3, null);
    }
    if(right == true)
    {
      g.drawImage(Assets.marienKaeferRechts, x, y+3, null);
    }

  }

  @Override
  public void update()
  {
    calculateMovement();
    calculateCollision();
    move();
    playerGetroffen();

  }

  @Override
  public void playerGetroffen()
  {
    // TODO Auto-generated method stub

  }
  
  private void calculateMovement()
  {
    if (left == true)
    {
      dx = -speed;
    }
    if (right == true)
    {
      dx = speed;
    }
  }
  
  private void calculateCollision()
  {
    float toX = x + dx;
    float toY = y + dy ;

    // Collision Top and Bottom
    calculateCorners(x, toY);

    // Hin und Her, Fällt nicht runter oder ähnliches

    if (bottomRight == false)
    {
      right = false;
      left = true;
    }
    if (bottomLeft == false)
    {
      right = true;
      left = false;
    }

    // Collision Left and Right
    calculateCorners(toX, y - 1);
    

    if (dx < 0)// links
    {
      if (topLeft == true || midLeft == true || bottomLeft == true)
      {
        left = false;
        right = true;
        dx =0;
      }
    }

    if (dx > 0)
    {
      if (topRight == true || midRight == true || bottomRight == true)
      {
        left = true;
        right = false;
        dx =0;
      }
    }
  }
  
  private void move()
  {
    x += dx;
    
    dx = 0;
  }
  
  private void calculateCorners(float x, float y)
  {
      int leftTile = getBlockKordinateX((int) x);
      int rightTile = getBlockKordinateX((int) x + BREITE - 1);
      int topTile = getBlockKordinateY((int) y);
      int midTile = getBlockKordinateY((int) y + HOEHE / 2);
      int bottomTile = getBlockKordinateY((int) y + HOEHE);
      
      if (handler.getLevelCreator().levelObjects[topTile][leftTile].isGegnerInteract() == false)
      {
        topLeft = false;
      } else
      {
        topLeft = true;
      }

      if (handler.getLevelCreator().levelObjects[topTile][rightTile].isGegnerInteract() == false)
      {
        topRight = false;
      } else
      {
        topRight = true;
      }

      if (handler.getLevelCreator().levelObjects[midTile][leftTile].isGegnerInteract() == false)
      {
        midLeft = false;
      } else
      {
        midLeft = true;
      }

      if (handler.getLevelCreator().levelObjects[midTile][rightTile].isGegnerInteract() == false)
      {
        midRight = false;
      } else
      {
        midRight = true;
      }

      if (handler.getLevelCreator().levelObjects[bottomTile][leftTile].isGegnerInteract() == false)
      {
        bottomLeft = false;
      } else
      {
        bottomLeft = true;
      }

      if (handler.getLevelCreator().levelObjects[bottomTile][rightTile].isGegnerInteract() == false)
      {

        bottomRight = false;
      } else
      {
        bottomRight = true;
      }
  }
  
  public int getBlockKordinateY(int y)
  {
    return  y / FrameMain.BLOCKHOEHE;
  }

  public int getBlockKordinateX(int x)
  {
    return  x / FrameMain.BLOCKBREITE;
  }

}
