package blocke;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;

import game.DreiApfelWertung;
import game.Entity;
import game.Handler;
import gui.FrameMain;
import resManager.Assets;
import resManager.LevelFileWriter;
import states.StateManager;

public class LevelZiel extends Entity
{

  public LevelZiel(int xp, int yp, int blockID, Handler handler)
  {
    super(xp, yp, blockID, handler);
    walkable = true;
    gegnerWalkable = true;
  }

  @Override
  public void paint(Graphics g)
  {
    g.drawImage(Assets.ziel, x, y, null);
    g.drawImage(Assets.haus, x, y-FrameMain.BLOCKHOEHE*3, null);

  }

  public void update()
  {
    if (ziel() == true)
    {
      handler.getPanelGame().setVisible(false);
      handler.getFrameMain().repaint();
      if (handler.getPanelGame().isLevelEditorTest() == false)
      {
        DreiApfelWertung.levelGechafft();
        DreiApfelWertung.zeitGeschafft(handler.getStoppuhr().getStoppUhrTime());
        if(handler.getLevelCreator().getMoeglicheAepfel()==handler.getAnzeigeApfel().getApfel())
        {
          DreiApfelWertung.alleAepfel();
        }
        try
        {
          LevelFileWriter.wertungSpeichern();
        } catch (IOException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        DreiApfelWertung.highscoreVergleich(handler.getStoppuhr().getStoppUhrTime());
        StateManager.setState(handler.getStateLevelSelect());
        StateManager.getState().stateUpdate();
      } else
      {
        try
        {
          handler.getPanelLevelEditor().levelTestenLaden();
        } catch (IOException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }

    }
  }

  public boolean ziel()
  {
    if (handler.getPlayer().getBounds().intersects(getBounds()) == true)
    {
      return true;
    }
    return false;
  }

}
