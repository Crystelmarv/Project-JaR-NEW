package blocke;

import java.awt.Color;
import java.awt.Graphics;

import game.Entity;
import game.Handler;
import gui.FrameMain;
import resManager.Assets;

public class BlockTrampolin extends Entity
{

  public BlockTrampolin(int xp, int yp, int blockID, Handler handler)
  {
    super(xp, yp, blockID, handler);
  }

  @Override
  public void paint(Graphics g)
  {
    g.setColor(Color.WHITE);
    g.fillRect(x, y, FrameMain.BLOCKBREITE, FrameMain.BLOCKHOEHE);
    g.drawImage(Assets.trampolin, x, y, null);

  }

  @Override
  public void update()
  {
    playerOnTop();

  }

  private void playerOnTop()
  {
    if (handler.getPlayer().getX() > x - 64 && handler.getPlayer().getX() < x + 64 && handler.getPlayer().getY() < y
        && handler.getPlayer().getY() >= y - 64)
    {
      handler.getPlayer().setJump();
    }
  }

}
