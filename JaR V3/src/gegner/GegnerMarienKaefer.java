package gegner;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import gui.FrameMain;
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
    left = true;

    HITBOX_HOEHE = 32;
    HITBOX_Y = y + 32;

  }

  @Override
  public void paint(Graphics g)
  {

    if (left == true)
    {
      g.drawImage(Assets.marienKaeferLinks, x, y + 3, null);
    }
    if (right == true || left == false && right == false)
    {
      g.drawImage(Assets.marienKaeferRechts, x, y + 3, null);
    }

    // show Hitbox
    /*
     * g.setColor(Color.red); g.fillRect(HITBOX_X, HITBOX_Y, HITBOX_BREITE,
     * HITBOX_HOEHE);
     */
  }

  @Override
  public void update()
  {
    calculateMovement();
    calculateCollision();
    move();
    hitboxUpdate();
    playerGetroffen();

  }

  private void hitboxUpdate()
  {
    HITBOX_X = x;

  }

  @Override
  public void playerGetroffen()
  {

    if (getBounds().intersects(handler.getPlayer().getBounds()))
    {
      if (handler.getPlayer().isFall() == true)
      {
        handler.getPlayer().setJump();
        handler.getLevel().removeEntity(gegner);
      } else
      {
        handler.getPlayer().setNichtAngreifbarTimerSet();
        handler.getLeben().lebenMinus();
      }

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

    if (handler.getLevelCreator().levelObjects[getBlockKordinateY((int) toY + HOEHE)][getBlockKordinateX(
        (int) x + 64 + BREITE - 1)].isGegnerWalkable() == true
        && handler.getLevelCreator().levelObjects[getBlockKordinateY((int) toY + HOEHE)][getBlockKordinateX(
            (int) x - 64)].isGegnerWalkable() == true)
    {
      left = false;
      right = false;
    } else if (bottomRight == false)
    {
      right = false;
      left = true;

      // System.out.println("ri");
    } else if (bottomLeft == false)
    {

      left = false;
      right = true;
      // System.out.println("le");

    }

    if (bottomLeft == false && bottomRight == false)
    {
      left = false;
      right = false;
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
