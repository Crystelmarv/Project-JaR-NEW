package blocke;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.event.AncestorEvent;

import game.Entity;
import game.Handler;
import gui.FrameMain;
import resManager.Animation;
import resManager.Assets;

public class BlockTrampolin extends Entity
{
  private Animation animationRunter, animationHoch;
  private int blockHoehe = 64;
  private boolean runter = false;
  private boolean animate = false;
  private boolean animationAbgeschlossen = false;

  public BlockTrampolin(int xp, int yp, int blockID, Handler handler)
  {
    super(xp, yp, blockID, handler);
    walkable = true;
    // Animation
    animationRunter = new Animation(50000000, Assets.trampolinARunter);
    animationHoch = new Animation(50000000, Assets.trampolinAHoch);
  }

  @Override
  public void paint(Graphics g)
  {
    g.setColor(Color.WHITE);
    g.fillRect(x, y - blockHoehe + 64, FrameMain.BLOCKBREITE, blockHoehe);

    if (animate == true)
    {
      if (runter == true)
      {
        g.drawImage(animationRunter.getFrame(), x, y + 1, null);
      } else
      {
        g.drawImage(animationHoch.getFrame(), x, y + 1, null);
      }
    } else
    {
      g.drawImage(Assets.trampolinARunter[0], x, y + 1, null);
    }

  }

  @Override
  public void update()
  {
    if (animate == true)
    {
      if (runter == true)
      {
        if (animationRunter.update() == true)
        {
          runter = false;
        } else
        {
          updateHitboxRunter();
        }

      } else
      {
        if (animationHoch.update() == true)
        {
          runter = true;
        } else
        {
          updateHitboxHoch();
        }

      }
    }

    playerOnTop();

  }

  private void playerOnTop()
  {
    if (handler.getPlayer().getX() > x - 64 && handler.getPlayer().getX() < x + 64 && handler.getPlayer().getY() < y
        && handler.getPlayer().getY() >= y - blockHoehe)
    {
      // handler.getPlayer().setJump();
      animate = true;
      handler.getPlayer().setY(y - blockHoehe);
      if (runter == false && animationHoch.getIndex() == 7)
      {
        handler.getPlayer().setJumpStart(-12f);
        handler.getPlayer().setJump();
        runter = true;
        animationAbgeschlossen = false;
      }
    } else
    {
      animate = false;
     
      
    }
  }

  private void updateHitboxRunter()
  {
    switch (animationRunter.getIndex())
    {
    case 0:
      blockHoehe = 64;
      break;
    case 1:
      blockHoehe = 59;
      break;
    case 2:
      blockHoehe = 56;
      break;
    case 3:
      blockHoehe = 53;
      break;
    case 4:
      blockHoehe = 51;
      break;
    case 5:
      blockHoehe = 48;
      break;
    case 6:
      blockHoehe = 45;
      break;
    case 7:
      blockHoehe = 43;
      break;
    }
  }

  private void updateHitboxHoch()
  {
    switch (animationHoch.getIndex())
    {
    case 7:
      blockHoehe = 64;
      
      break;
    case 6:
      blockHoehe = 60;
      break;
    case 5:
      blockHoehe = 57;
      break;
    case 4:
      blockHoehe = 54;
      break;
    case 3:
      blockHoehe = 52;
      break;
    case 2:
      blockHoehe = 49;
      break;
    case 1:
      blockHoehe = 46;
      break;
    case 0:
      blockHoehe = 44;
      break;
    }
  }

}
