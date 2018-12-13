package blocke;

import java.awt.Graphics;

import game.Entity;
import gegner.Gegner;
import gegner.GegnerMarienKaefer;
import gui.Handler;
import resManager.Assets;

public class GegnerSpawn extends Entity
{
  private Gegner gegner;

  public GegnerSpawn(int xp, int yp, int blockID, Handler handler)
  {
    super(xp,yp, blockID, handler);
    walkable = true;
    gegnerInteract = false;
  }

  @Override
  public void paint(Graphics g)
  {
    g.drawImage(Assets.weiss, x, y, null);
    
  }

  @Override
  public void update()
  {
    // TODO Auto-generated method stub
    
  }
  
  public void init()
  {
    switch (blockID)
    {

    case 71:
      gegner = new GegnerMarienKaefer(x, y, blockID, handler);
      System.out.println("MARRR");
      break;
  /*    
    case 72:
      aktuellerGegner = new GegnerBiene(gameStates, x, y);
      break;
    case 73:
      
      aktuellerGegner = new GegnerFisch(gameStates, x, y);
      break;
*/
    }
    handler.getLevel().setEntity(gegner);

  }

}
