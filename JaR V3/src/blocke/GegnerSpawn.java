package blocke;

import java.awt.Color;
import java.awt.Graphics;

import game.Entity;
import gegner.Gegner;
import gegner.GegnerBiene;
import gegner.GegnerFisch;
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
    gegnerWalkable = true;
    init();
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
     
      break;
      
    case 72:
      gegner = new GegnerBiene(x, y, blockID, handler);
      
      break;
    case 73:
      
      gegner = new GegnerFisch(x, y, blockID, handler);
      break;

    }
    handler.getLevel().setEntity(gegner);

  }

}
