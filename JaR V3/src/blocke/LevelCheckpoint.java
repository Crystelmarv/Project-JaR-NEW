package blocke;

import java.awt.Color;
import java.awt.Graphics;

import game.Entity;
import gui.Handler;
import resManager.Assets;


public class LevelCheckpoint extends Entity
{
  private boolean checkpointSet = false;

  public LevelCheckpoint(int xp, int yp, int blockID, Handler handler)
  {
    super(xp, yp, blockID, handler);
    walkable = true;
  }

  @Override
  public void paint(Graphics g)
  {
    g.setColor(Color.WHITE);
    g.fillRect(x, y, 64, 64);
    g.drawImage(Assets.checkPointUnten, x, y, null);
    
    if(checkpointSet == false)
    {
      g.drawImage(Assets.checkPointObenAus, x, y -64,null);
    }
    if(checkpointSet == true)
    {
      g.drawImage(Assets.checkPointObenAn, x, y -64,null);
    }
    
  }

  @Override
  public void update()
  {
    if(handler.getPlayer().getBounds().intersects(getBounds()) == true 
        && checkpointSet == false)
    {
      checkpointSet = true;
      handler.getPlayer().setCheckpointX(x);
      handler.getPlayer().setCheckpointY(y);
    }
    
  }

}
