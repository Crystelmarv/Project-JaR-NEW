package blocke;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.jar.Pack200.Unpacker;

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
    g.drawImage(Assets.weiss, x, y, null);
    g.drawImage(Assets.haus, x-FrameMain.BLOCKHOEHE*3, y-FrameMain.BLOCKHOEHE*3, null);
  }

  public void update()
  {
    if (ziel() == true)
    {
      
      if (handler.getPanelGame().isLevelEditorTest() == false)
      {
        handler.getPanelGame().setVisible(false);
        handler.getFrameMain().repaint();
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
          e.printStackTrace();
        }
        DreiApfelWertung.highscoreVergleich(handler.getStoppuhr().getStoppUhrTime());
        StateManager.setState(handler.getStateLevelSelect());
        StateManager.getState().stateUpdate();
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
