package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import resManager.Assets;

public class AnzeigeApfel
{
  private LevelCreator levelCreator;
  
  private int apfel = 0;
  private int x=1200;
  private int y=100;

  public AnzeigeApfel(LevelCreator levelCreator)
  {
    this.levelCreator = levelCreator;
  }
  
  public void paint(Graphics g)
  {
    g.drawImage(Assets.apfel, x, y, null);
    
    Font font = new Font("Arial", Font.BOLD, 30);
    g.setFont(font);
    g.setColor(Color.black);
    g.drawString(Integer.toString(apfel) + "/" + 
    (Integer.toString(levelCreator.getMoeglicheAepfel())),
        x+80, y+45);
  }
  
 
  public void apfelPlus()
  {
    apfel++;
  }

  public int getApfel()
  {
    return apfel;
  }
  
}
