package blocke;

import java.awt.Color;
import java.awt.Graphics;

import game.Entity;
import game.Handler;
import game.Interactables;
import game.LevelCreator;
import resManager.Assets;
import resManager.Tastatur;

public class BlockLeiter extends Interactables
{

  private boolean leiterOben, leiterUnten, leiterOUGeladen = false;
  
  public BlockLeiter(int xp, int yp, int blockID, Handler handler)
  {
    super(xp, yp, blockID, handler);
    walkable = true;
  }

  @Override
  public void paint(Graphics g)
  {
    g.drawImage(Assets.gras, x, y, null);
    g.setColor(Color.red);
    g.drawRect(HITBOX_X, HITBOX_Y, HITBOX_BREITE, HITBOX_HOEHE);
  }

  @Override
  public void update()
  {
    if(leiterOUGeladen == false)
    {
      leiterOUCheck();
      System.out.println(leiterOben);
      System.out.println(leiterUnten);
    }
   ////  System.out.println("PLA" + handler.getPlayer().getY());
    isPlayerInRange();

  }

  private void isPlayerInRange()
  {

   /* if (Tastatur.isMoveUp() == true && handler.getPlayer().getX() > x - 59 && handler.getPlayer().getX() < x + 59
        && (handler.getPlayer().getY() + 59 < y + 64 && handler.getPlayer().getY() + 59 >= y)
        || (handler.getPlayer().getY() < y + 64 && handler.getPlayer().getY() >= y && Tastatur.isMoveUp() == true
            && handler.getPlayer().getX() > x - 59 && handler.getPlayer().getX() < x + 59))
    {
*/
    
    if(Tastatur.isMoveUp() == true && getBounds().intersects(handler.getPlayer().getBounds()) )
        {
      
      
      handler.getPlayer().setY(handler.getPlayer().getY() - 3);

    }
   
   
  }
  
  private void leiterOUCheck()
  {
    if(LevelCreator.levelID[(y/64)+1][x/64] == blockID)
    {
      leiterUnten = true;
    }
    if(LevelCreator.levelID[(y/64)-1][x/64] == blockID)
    {
      leiterOben = true;
    }
    
    if(leiterUnten == false && leiterOben == true)
    {
      int i = 0;
      while(LevelCreator.levelID[y/64-i][x/64] == blockID)
      {
        i++;
        System.out.println(i);
      }
      
      HITBOX_Y = HITBOX_Y - ((i-1)*64);
      HITBOX_HOEHE = i * 64;
      
      handler.getLevel().setLeiter(this);
      
    }
    else if(leiterOben == false && leiterUnten == true || leiterOben == true && leiterUnten == true)
    {
      HITBOX_BREITE = 0;
      HITBOX_HOEHE = 0;
    }

    leiterOUGeladen = true;
  }

}
