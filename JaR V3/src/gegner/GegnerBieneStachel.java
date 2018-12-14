package gegner;

import java.awt.Color;
import java.awt.Graphics;

import gui.FrameMain;
import gui.Handler;

public class GegnerBieneStachel extends Gegner
{

  // Movement
  private float gravity = 0.22F;
  private float maxFallingSpeed = 5.5F;

  // Collision
  private boolean topLeft;
  private boolean topRight;
  private boolean midLeft;
  private boolean midRight;
  private boolean bottomLeft;
  private boolean bottomRight;

  public GegnerBieneStachel(int xp, int yp, int blockID, Handler handler, boolean right)
  {
    super(xp, yp, blockID, handler);

    fall = true;
    BREITE = 20;
    HOEHE = 20;

    HITBOX_BREITE = BREITE;
    HITBOX_HOEHE = HOEHE;

    if (right == true)
    {
      this.right = true;
    } else
    {
      this.left = true;
    }
  }

  @Override
  public void playerGetroffen()
  {
    if (getBounds().intersects(handler.getPlayer().getBounds()))
    {
      handler.getPlayer().setNichtAngreifbarTimerSet();
      handler.getLeben().lebenMinus();
      stachelRemove();
    }
  }

  @Override
  public void paint(Graphics g)
  {
    g.setColor(Color.BLACK);
    g.fillRect(x, y, 20, 20);

    // g.setColor(Color.red);
    // g.fillRect(HITBOX_X, HITBOX_Y, HITBOX_BREITE, HITBOX_HOEHE);

  }

  @Override
  public void update()
  {
    calculateCollision();
    calculateMovement();
    move();
    hitboxUpdate();
    playerGetroffen();

  }

  private void hitboxUpdate()
  {
    HITBOX_X = x;
    HITBOX_Y = y;
  }

  private void stachelRemove()
  {
    handler.getLevel().removeEntity(gegner);
  }

  public void move()
  {
    x += dx;
    y += dy;

    dx = 0;

  }

  private void calculateCollision()
  {
    float toX = x + dx;
    float toY = y + dy;

    // Collision Top and Bottom
    calculateCorners(x, toY);

    if (topLeft == true || topRight == true)
    {
      dy = 0;
      stachelRemove();
      fall = true;
      int playerY = getBlockKordinateY((int) toY);
      y = (playerY + 1) * FrameMain.BLOCKBREITE;
    }
    if (bottomLeft == true && fall == true || bottomRight == true && fall == true)
    {
      fall = false;
      dy = 0;
      stachelRemove();

      int playerY = getBlockKordinateY((int) toY + HOEHE);

      y = playerY * FrameMain.BLOCKBREITE - HOEHE;

    }

    if (bottomLeft == false && bottomRight == false)
    {
      fall = true;
    }

    // Collision Left and Right
    calculateCorners(toX, y - 1);
    if (dx < 0)// links
    {
      if (topLeft == true || midLeft == true || bottomLeft == true)
      {
        dx = 0;
        stachelRemove();
      }

    }

    if (dx > 0)
    {
      if (topRight == true || midRight == true || bottomRight == true)
      {
        dx = 0;
        stachelRemove();
      }
    }

  }

  private void calculateCorners(float x, float y)
  {
    int leftTile = getBlockKordinateX((int) x);
    int rightTile = getBlockKordinateX((int) x + BREITE - 1);
    int topTile = getBlockKordinateY((int) y);
    int midTile = getBlockKordinateY((int) y + HOEHE / 2);
    int bottomTile = getBlockKordinateY((int) y + HOEHE);

    if (handler.getLevelCreator().levelObjects[topTile][leftTile].isWalkable() == true)
    {
      topLeft = false;
    } else
    {
      topLeft = true;
    }

    if (handler.getLevelCreator().levelObjects[topTile][rightTile].isWalkable() == true)
    {
      topRight = false;
    } else
    {
      topRight = true;
    }

    if (handler.getLevelCreator().levelObjects[midTile][leftTile].isWalkable() == true)
    {
      midLeft = false;
    } else
    {
      midLeft = true;
    }

    if (handler.getLevelCreator().levelObjects[midTile][rightTile].isWalkable() == true)
    {
      midRight = false;
    } else
    {
      midRight = true;
    }

    if (handler.getLevelCreator().levelObjects[bottomTile][leftTile].isWalkable() == true)
    {
      bottomLeft = false;
    } else
    {
      bottomLeft = true;
    }

    if (handler.getLevelCreator().levelObjects[bottomTile][rightTile].isWalkable() == true)
    {

      bottomRight = false;
    } else
    {
      bottomRight = true;
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

    if (fall == true)
    {
      dy += gravity;
      if (dy > maxFallingSpeed)
      {
        dy = maxFallingSpeed;
      }
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
