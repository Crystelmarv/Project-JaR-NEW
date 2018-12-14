package items;

import java.awt.Color;
import java.awt.Graphics;

import gui.Handler;
import resManager.Assets;

public class ItemFeuerKugel extends Item
{
  private boolean feuerDown = true;
  private int yTemp;
  private int test = 1;
  private float velocityY = (float) (Math.sin(10) * 2);
  private float velocityX = (float) (Math.cos(10) * -5);

  public ItemFeuerKugel(int xp, int yp, int blockID, Handler handler)
  {
    super(xp, yp, blockID, handler);
    handler.getLevel().setEntity(item);
    HITBOX_BREITE = 30;
    HITBOX_HOEHE = 30;
  }

  @Override
  public void paint(Graphics g)
  {
    // g.setColor(Color.black);
    // g.fillOval(x, y, 30, 30);
    g.drawImage(Assets.itemFeuerKugel, x, y, 30, 30, null);

  }

  @Override
  public void update()
  {

    move();
    hitboxUpdate();

  }

  private void hitboxUpdate()
  {
    HITBOX_X = x;
    HITBOX_Y = y;
  }

  public void move()
  {
    int blockX, blockY;

    velocityY += 0.1;

    blockX = x / 64;
    blockY = y / 64;

    if (handler.getLevelCreator().levelObjects[blockY + 1][blockX].getBounds().intersects(getBounds())
        && handler.getLevelCreator().levelObjects[blockY + 1][blockX].isWalkable() == false && feuerDown == true)
    {

      feuerDown = false;
      yTemp = y;
    } else
    {
      if (handler.getLevelCreator().levelObjects[blockY][blockX + 1].getBounds().intersects(getBounds())
          && handler.getLevelCreator().levelObjects[blockY][blockX + 1].isWalkable() == false)
      {
        handler.getLevel().removeEntity(item);
      }
      if (feuerDown == true)
      {
        x += velocityX;
        y += velocityY;
      } else
      {
        if (yTemp - 64 < y)
        {

          x += velocityX;
          y -= velocityY;
          test++;
        } else
        {
          feuerDown = true;
          velocityY = (float) (Math.sin(10) * 2);
        }

      }
    }
  }

}
