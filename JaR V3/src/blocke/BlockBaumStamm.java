package blocke;

import java.awt.Color;
import java.awt.Graphics;

import game.Entity;
import game.Handler;

public class BlockBaumStamm extends Entity
{
  private BlockBaumAst ast;

  public BlockBaumStamm(int xp, int yp, int blockID, Handler handler)
  {
    super(xp,yp, blockID, handler);
    walkable = true;
    gegnerWalkable = true;
    
    ast = new BlockBaumAst(x+BREITE, y-HOEHE*5, blockID, handler);
    handler.getLevel().setEntity(ast);
  }

  @Override
  public void paint(Graphics g)
  {
   g.setColor(Color.MAGENTA);
   g.fillRect(x, y-HOEHE*6, BREITE, HOEHE*7);
    
  }

  @Override
  public void update()
  {
    // TODO Auto-generated method stub
    
  }

}
