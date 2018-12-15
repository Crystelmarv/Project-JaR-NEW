package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import resManager.Assets;

public class AnzeigeApfel
{
  private Handler handler;
  
  private int apfel = 0;
  private int x,y;

  public AnzeigeApfel(Handler handler)
  {
    this.handler = handler;
  }
  
  public void paint(Graphics g)
  {
    g.drawImage(Assets.apfel, x, y, null);
    
    Font font = new Font("Arial", Font.BOLD, 30);
    g.setFont(font);
    g.setColor(Color.black);
    g.drawString(Integer.toString(apfel) + "/" + 
    (Integer.toString(handler.getLevelCreator().getMoeglicheAepfel())),
        x+80, y+45);
  }
  
  public void update()
  {
    x = handler.getPlayer().getX() + 600;
    y = handler.getPlayer().getY() - 500;
  }

  public void apfelPlus()
  {
    apfel++;
  }
}
