package gegner;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import game.LevelCreator;
import resManager.Assets;

public class GegnerFisch extends Gegner
{
  private int ySpawn, xSpawn;
  private double timeStart;
  private boolean fall = false;
  private boolean sprung = false;
  private int yWasser;
  private int anzahlWasserBlocke;

  public GegnerFisch(int xp, int yp, int blockID, Handler handler)
  {
    super(xp, yp, blockID, handler);
    xSpawn = x;
    ySpawn = y;
    yWasser = y;
    timeStart = System.nanoTime();

    HITBOX_BREITE = 45;
    HITBOX_X = x + 10;

    wasserBlocke();
  }

  private void wasserBlocke()
  {

    while (LevelCreator.levelID[yWasser / 64][xSpawn / 64] == 33
        || LevelCreator.levelID[yWasser / 64][xSpawn / 64] == 73)
    {
      yWasser = yWasser - 64;
      anzahlWasserBlocke++;
    }
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
    int i;

    if (fall == true)
    {
      g.drawImage(Assets.fischUnten, x, y, null);

    } else
    {
      g.drawImage(Assets.fischOben, x, y, null);
    }

    yWasser = ySpawn;
    for (i = 0; i < anzahlWasserBlocke; i++)
    {
      g.drawImage(Assets.wasser, x, yWasser, null);
      yWasser = yWasser - 64;
    }

    // g.setColor(Color.red);
    // g.fillRect(HITBOX_X, HITBOX_Y, HITBOX_BREITE, HITBOX_HOEHE);

  }

  @Override
  public void update()
  {
    sprung();
    updateHitbox();
    playerGetroffen();
  }

  private void sprung()
  {
    double timeNow = System.nanoTime();
    if (timeNow - timeStart > 1500000000 && fall == false)
    {
      y = y - 4;
      sprung = true;

    }

    if (ySpawn - y > 384 || fall == true)
    {
      fall = true;
      sprung = false;
      y = y + 4;

      if (y >= ySpawn && sprung == false)
      {
        timeStart = timeNow;
        fall = false;
      }
    }
  }

  private void updateHitbox()
  {
    HITBOX_Y = y;
  }
}
