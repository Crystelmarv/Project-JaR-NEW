package blocke;

import java.awt.Color;
import java.awt.Graphics;

import game.Entity;
import game.Handler;

public class LevelSpawn extends Entity
{

  public LevelSpawn(int xp, int yp, int blockID, Handler handler)
  {
    super(xp,yp, blockID, handler);
    walkable = true;
    gegnerWalkable = true;
  }

  @Override
  public void paint(Graphics g)
  {
    g.setColor(Color.white);
   g.fillRect(x, y, 64, 64);
    
  }

  @Override
  public void update()
  {
    // TODO Auto-generated method stub
    
  }

}
