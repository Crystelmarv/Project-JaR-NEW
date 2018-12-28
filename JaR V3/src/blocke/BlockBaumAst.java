package blocke;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import items.Item;

public class BlockBaumAst extends Item
{
  private boolean fall = false;

  public BlockBaumAst(int xp, int yp, int blockID, Handler handler)
  {
    super(xp, yp, blockID, handler);
    HITBOX_BREITE = BREITE*3;
  }

  @Override
  public void paint(Graphics g)
  {
    g.setColor(Color.DARK_GRAY);
    g.fillRect(x, y, BREITE * 3, HOEHE);

  }

  @Override
  public void update()
  {
    if (fall == false)
    {
      isPlayerInRange();
    } else
    {
      fall();
      hitboxUpdate();
    }
  }
  
  private void hitboxUpdate()
  {
    HITBOX_Y = y;
  }

  private void fall()
  {

    int blockX = x / 64;
    int blockY = y / 64;

    if (handler.getLevelCreator().levelObjects[blockY + 1][blockX].getBounds().intersects(getBounds())
        && handler.getLevelCreator().levelObjects[blockY + 1][blockX].isWalkable() == false)
    {

      handler.getLevel().removeEntity(item);

    }
    else
    {
      y++;
    }
  }

  private void isPlayerInRange()
  {
    if (handler.getPlayer().getX() > x - 64 && handler.getPlayer().getX() < x + BREITE * 3)
    {
      fall = true;
    }
  }

}
