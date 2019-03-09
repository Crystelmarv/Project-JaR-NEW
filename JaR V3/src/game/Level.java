package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.RepaintManager;

import resManager.LevelFileReader;
import states.StateGame;

public class Level
{
  private boolean levelGelesen = false;
  private Player player;
  private Handler handler;
  private LevelCreator levelCreator;
  private ArrayList<Entity> entityList = new ArrayList<>();
  private ArrayList<Entity> leiterList = new ArrayList<>();

  private Hud hud;
  private Kamera kamera;

  public Level(Handler handler) throws IOException
  {
    this.handler = handler;
    handler.setLevel(this);

    levelCreator = new LevelCreator(handler);
    LevelFileReader.levelDateiLesen(handler);

    levelCreator.levelErstellen();

    handler.setLevelCreator(levelCreator);

    levelGelesen = true;
    player = new Player(handler);

    hud = new Hud(levelCreator, handler);
    kamera = new Kamera();

  }

  public void paint(Graphics g)
  {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    kamera.update(handler);
    //////////////////////////////////////////
    g2d.translate((int) -kamera.getX(), (int) -kamera.getY());

    // Level Blöcke
    int iy, ix;
    if (levelGelesen == true)
    {
      // noch in klasse level verschieben
      for (iy = 0; iy < LevelCreator.levelID.length; iy++)
      {
        for (ix = 0; ix < LevelCreator.levelID[0].length; ix++)
        {
          levelCreator.levelObjects[iy][ix].paint(g2d);

        }
      }

      // EntityList

      int i;
      for (i = 0; i < entityList.size(); i++)
      {
        entityList.get(i).paint(g);
      }

      player.paint(g2d);

      ///////////////////////////////////

      g2d.translate((int) kamera.getX(), (int) kamera.getY());

      hud.paint(g2d);
    }
  }

  public void update()
  {
    int iy, ix;

    for (iy = 0; iy < LevelCreator.levelID.length; iy++)
    {
      for (ix = 0; ix < LevelCreator.levelID[0].length; ix++)
      {
        levelCreator.levelObjects[iy][ix].update();
      }
    }

    int i;
    for (i = 0; i < entityList.size(); i++)
    {
      entityList.get(i).update();
    }
   hud.update();
    player.update();
  }

  public boolean isLevelGelesen()
  {
    return levelGelesen;
  }

  public void setLevelGelesen(boolean levelGelesen)
  {
    this.levelGelesen = levelGelesen;
  }

  public Entity getEntity(int i)
  {
    return entityList.get(i);
  }

  public void setEntity(Entity ent)
  {
    entityList.add(ent);
  }

  public void removeEntity(Entity ent)
  {
    entityList.remove(ent);
  }

  public int getEntityListSize()
  {
    return entityList.size();
  }

  public int getLeiterListSize()
  {
    return leiterList.size();
  }

  public Entity getLeiter(int i)
  {
    return leiterList.get(i);
  }

  public void setLeiter(Entity ent)
  {
    leiterList.add(ent);
  }

  public void removeLeiter(Entity ent)
  {
    leiterList.remove(ent);
  }

}
