package gegner;

import java.awt.Color;
import java.awt.Graphics;

import gui.FrameMain;
import gui.Handler;
import resManager.Assets;

public class GegnerBiene extends Gegner
{
  // Collision
  private boolean topLeft;
  private boolean topRight;
  private boolean midLeft;
  private boolean midRight;
  private boolean bottomLeft;
  private boolean bottomRight;
  
  //Timer Stachel
  private double timeStart;

  public GegnerBiene(int xp, int yp, int blockID, Handler handler)
  {

    super(xp, yp, blockID, handler);

    left = true;

   
    HITBOX_HOEHE = 45;
    HITBOX_Y = y +18;
    
    timeStart = System.nanoTime();
  }

  @Override
  public void playerGetroffen()
  {
    if (getBounds().intersects(handler.getPlayer().getBounds()))
    {

      handler.getPlayer().setNichtAngreifbarTimerSet();
      handler.getLeben().lebenMinus();

    }

  }

  @Override
  public void paint(Graphics g)
  {
    walkable = true;
    gegnerWalkable = true;

    if (left == true)
    {
      g.drawImage(Assets.bieneLinks, x, y + 3, null);
    }
    if (right == true)
    {
      g.drawImage(Assets.bieneRechts, x, y + 3, null);
    }
    
   // g.setColor(Color.red);
 //   g.fillRect(HITBOX_X, HITBOX_Y, HITBOX_BREITE, HITBOX_HOEHE);

  }

  @Override
  public void update()
  {
    calculateMovement();
    calculateCollision();
    move();
    hitboxUpdate();
    playerGetroffen();
    stachel();

  }

  private void hitboxUpdate()
  {
    HITBOX_X = x;

  }
  
  public void stachel()
  {
   double timeNow = System.nanoTime();

    if (timeNow - timeStart > 1500000000) //4000000)
    {
      GegnerBieneStachel stachl = new GegnerBieneStachel(x, y, blockID, handler, right);
      handler.getLevel().setEntity(stachl);
     
     timeStart = timeNow;
    }
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
    float toY = y + 32;

    // Collision Top and Bottom
    calculateCorners(x, toY);

    // Hin und Her, Fällt nicht runter oder ähnliches

    if (bottomRight == false)
    {
     

      // System.out.println("ri");
    }
    if (bottomLeft == false)
    {

     
      // System.out.println("le");

    }

    if (bottomLeft == false && bottomRight == false)
    {
      
    }
    // Collision Left and Right
    calculateCorners(toX, y - 1);

    if (dx < 0)// links
    {
      if (topLeft == true || midLeft == true || bottomLeft == true)
      {
        left = false;
        right = true;
        dx = 0;
      }
    }

    if (dx > 0)
    {
      if (topRight == true || midRight == true || bottomRight == true)
      {
        left = true;
        right = false;
        dx = 0;
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

    if (bottomRight == true)
    {
    }

    if (handler.getLevelCreator().levelObjects[topTile][leftTile].isGegnerWalkable() == true)
    {
      topLeft = false;
    } else
    {
      topLeft = true;
    }

    if (handler.getLevelCreator().levelObjects[topTile][rightTile].isGegnerWalkable() == true)
    {
      topRight = false;
    } else
    {
      topRight = true;
    }

    if (handler.getLevelCreator().levelObjects[midTile][leftTile].isGegnerWalkable() == true)
    {
      midLeft = false;
    } else
    {
      midLeft = true;
    }

    if (handler.getLevelCreator().levelObjects[midTile][rightTile].isGegnerWalkable() == true)
    {
      midRight = false;
    } else
    {
      midRight = true;
    }

    if (handler.getLevelCreator().levelObjects[bottomTile][leftTile].isGegnerWalkable() == true)
    {
      bottomLeft = false;
    } else
    {
      bottomLeft = true;
    }

    if (handler.getLevelCreator().levelObjects[bottomTile][rightTile].isGegnerWalkable() == true)
    {

      bottomRight = false;
    } else
    {
      bottomRight = true;
    }
  }

  public int getBlockKordinateY(int y)
  {
    return y / FrameMain.BLOCKHOEHE;
  }

  public int getBlockKordinateX(int x)
  {
    return x / FrameMain.BLOCKBREITE;
  }

}
