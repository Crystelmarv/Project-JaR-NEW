package blocke;

import java.awt.Graphics;

import game.Entity;
import game.Handler;
import resManager.Assets;

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
    g.drawImage(Assets.baum, x, y-HOEHE*3, null);
    
  }

  @Override
  public void update()
  {
    // TODO Auto-generated method stub
    
  }

}
