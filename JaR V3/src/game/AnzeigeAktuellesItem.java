package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import resManager.Assets;

public class AnzeigeAktuellesItem
{
  private Handler handler;

  private int apfel = 0;
  private int x, y;

  public AnzeigeAktuellesItem(Handler handler)
  {
    this.handler = handler;
  }

  public void paint(Graphics g)
  {
    g.drawImage(Assets.anzeigeAktuellesItem, x, y, 80, 80, null);

    switch (handler.getItemManager().getAktivesItem())
    {
    case "feuer":
      g.drawImage(Assets.itemFeuer, x + 10, y + 10, 70, 70, null);
      break;
    case "geschwindigkeit":
      g.drawImage(Assets.itemSchuh, x + 10, y - 15, 70, 70, null);
      break;

    }

  }

  public void update()
  {
    x = handler.getPlayer().getX() + 400;
    y = handler.getPlayer().getY() - 500;
  }

}
