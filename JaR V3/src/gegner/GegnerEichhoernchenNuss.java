package gegner;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import resManager.Assets;

public class GegnerEichhoernchenNuss extends Gegner
{
  private float velocityY = (float) (Math.sin(10) * 2);
  private float velocityX = (float) (Math.cos(10) * -3.4);
  private int maxY;
  private boolean fall = false;
  private boolean blickRichtungRechts;

  public GegnerEichhoernchenNuss(int xp, int yp, int blockID, Handler handler, boolean right)
  {
    super(xp, yp, blockID, handler);
    this.blickRichtungRechts = right;

    if (right == true)
    {
      x = x + 84;
    } else
    {
      x = x - 20;
    }
    y = y - 20;
    maxY = y - 32;

    HITBOX_BREITE = 22;
    HITBOX_HOEHE = 22;
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
    g.drawImage(Assets.nuss, x, y , null);

  }

  @Override
  public void update()
  {
    hitboxUpdate();
    playerGetroffen();
    umweltGetroffen();

  }

  private void hitboxUpdate()
  {
    HITBOX_X = x;
    HITBOX_Y = y;
  }

  private void umweltGetroffen()
  {
    int blockX, blockY;

    blockX = x / 64;
    blockY = y / 64;

    velocityY += 0.05;

    if (handler.getLevelCreator().levelObjects[blockY + 1][blockX].getBounds().intersects(getBounds())
        && handler.getLevelCreator().levelObjects[blockY + 1][blockX].isWalkable() == false)
    {

      handler.getLevel().removeEntity(gegner);

    } else
  

    if (blickRichtungRechts == true)
    {

      if (handler.getLevelCreator().levelObjects[blockY][blockX + 1].getBounds().intersects(getBounds())
          && handler.getLevelCreator().levelObjects[blockY][blockX + 1].isWalkable() == false)
      {
        handler.getLevel().removeEntity(gegner);
      }
     
        if (maxY < y && fall == false)
        {
          x += velocityX;
          y += velocityY;
        } else
        {
          fall = true;
          x += velocityX;
          y -= velocityY;
        }
    }

    else
    {
      if (handler.getLevelCreator().levelObjects[blockY][blockX].getBounds().intersects(getBounds())
          && handler.getLevelCreator().levelObjects[blockY][blockX].isWalkable() == false)
      {
        handler.getLevel().removeEntity(gegner);
      }

      if (maxY < y && fall == false)
      {
        x -= velocityX;
        y += velocityY;
      } else
      {
        fall = true;
        x -= velocityX;
        y -= velocityY;
      }

    }
  }

}
