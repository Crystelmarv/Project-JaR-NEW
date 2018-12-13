package blocke;

import java.awt.Color;
import java.awt.Graphics;

import game.Entity;
import gui.Handler;
import resManager.Assets;

public class BlockZerstoerbar extends Entity
{
  private boolean getroffen = false;

  public BlockZerstoerbar(int xp, int yp, int blockID, Handler handler)
  {
    super(xp, yp, blockID, handler);
  }

  @Override
  public void paint(Graphics g)
  {
    if (getroffen == true)
    {
      g.setColor(Color.WHITE);
      g.fillRect(x, y, BREITE, HOEHE);
    } else
    {
      g.drawImage(Assets.orangerBlockOhneKreuz, x, y, null);
    }
  }

  @Override
  public void update()
  {
    collission();
  }

  public void collission()
  {
    if (handler.getPlayer().getX() > x - 59 && handler.getPlayer().getX() < x + 59
        && handler.getPlayer().getY() == y + 64 && getroffen == false)
    {
      handler.getPlayer().setFall();
      getroffen = true;
      zerströren();
    }
  }

  private void zerströren()
  {
    walkable = true;
  }

}
