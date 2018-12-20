package items;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import gegner.Gegner;
import gegner.GegnerFisch;
import gegner.GegnerMarienKaefer;
import resManager.Assets;

public class ItemFeuerKugel extends Item
{
  private boolean feuerDown = true;
  private int yTemp;
  private boolean lastPositionRight;
  private float velocityY = (float) (Math.sin(10) * 2);
  private float velocityX = (float) (Math.cos(10) * -5);
  
//private float velocityY = (float) (Math.sin(170) * 2);
//private float velocityX = (float) (Math.cos(170) * -5);

  public ItemFeuerKugel(int xp, int yp, int blockID, Handler handler, boolean lastPositionRight)
  {
    super(xp, yp, blockID, handler);
    this.lastPositionRight = lastPositionRight;
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
    gegnerGetroffen();
    

  }

  private void hitboxUpdate()
  {
    HITBOX_X = x;
    HITBOX_Y = y;
  }

  public void move()
  {
    if(lastPositionRight == true)
    {
      moveRight();
    }
    if(lastPositionRight == false)
    {
      moveLeft();
    }
  }
  
  private void moveRight()
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
        } else
        {
          feuerDown = true;
          velocityY = (float) (Math.sin(10) * 2);
        }

      }
    }
  }
  
  private void moveLeft()
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
      if (handler.getLevelCreator().levelObjects[blockY][blockX ].getBounds().intersects(getBounds())
          && handler.getLevelCreator().levelObjects[blockY][blockX ].isWalkable() == false)
      {
        handler.getLevel().removeEntity(item);
      }
      if (feuerDown == true)
      {
        x -= velocityX;
        y += velocityY;
      } else
      {
        if (yTemp - 64 < y)
        {

          x -= velocityX;
          y -= velocityY;
        } else
        {
          feuerDown = true;
          velocityY = (float) (Math.sin(170) * 2);
        }

      }
    }
  }
  
  private void gegnerGetroffen()
  {
    int i;
    
    for(i=0; i < handler.getLevel().getEntityListSize(); i++)
    {
      if(getBounds().intersects(handler.getLevel().getEntity(i).getBounds()))
      {
        if(handler.getLevel().getEntity(i) instanceof Gegner )
        {
          handler.getLevel().removeEntity(handler.getLevel().getEntity(i));
          handler.getLevel().removeEntity(item);
        }
      }
    }
  }

}
