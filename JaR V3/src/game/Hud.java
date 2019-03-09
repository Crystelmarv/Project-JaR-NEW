package game;

import java.awt.Graphics;

import gui.FrameMain;

public class Hud
{

  private int x, y;
  private Kamera kamera;
  private Leben leben;
  private AnzeigeApfel anzeigeApfel;
  private AnzeigeAktuellesItem anzeigeAktuellesItem;
  private Stoppuhr stoppuhr;

  private Handler handler;

  public Hud(LevelCreator levelCreator, Handler handler)
  {
    this.handler = handler;

    leben = new Leben(handler);
    anzeigeAktuellesItem = new AnzeigeAktuellesItem(handler);
    anzeigeApfel = new AnzeigeApfel(levelCreator);
    stoppuhr = new Stoppuhr();
    handler.setStoppuhr(stoppuhr);

    handler.setAnzeigeApfel(anzeigeApfel);
    handler.setLeben(leben);

    x = 0;
    y = 0;

  }
  public void update()
  {
    leben.update();
  }

  public Kamera getKamera()
  {
    return kamera;
  }

  public float getX()
  {
    return x;
  }

  public float getY()
  {
    return y;
  }

  public void paint(Graphics g)
  {
    
    leben.paint(g);
    anzeigeAktuellesItem.paint(g);
    anzeigeApfel.paint(g);
    stoppuhr.paint(g);
  }

  public void setX(int x)
  {
    this.x = x;
  }

  public void setY(int y)
  {
    this.y = y;
  }

}
