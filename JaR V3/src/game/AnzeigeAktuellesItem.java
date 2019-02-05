package game;

import java.awt.Graphics;

import resManager.Assets;

public class AnzeigeAktuellesItem
{
  private Handler handler;


  private int x=1000;
  private int y=100;

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

  

}
