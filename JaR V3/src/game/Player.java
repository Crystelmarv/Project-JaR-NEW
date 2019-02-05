package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gui.FrameMain;
import items.Item;
import items.ItemFeuerKugel;
import resManager.Tastatur;

public class Player
{
  // Position
  private int y = 900;
  private int x = 200;

  // Größe
  public static final int BREITE = 60;
  public static final int HOEHE = 60;

  // Handler
  private Handler handler;

  // Movement

  private boolean jump = false;
  private boolean fall = false;
  private boolean doNotFall = false;

  private boolean lastPositionRight = false;

  private final float DEFAULT_SPEED = 4.75f;
  private float speed = 20f; // DEFAULT_SPEED;
  private float gravity = 0.22f;
  private float maxFallingSpeed = 5.5f;
  private float jumpStart = -8.7f;

  // Status
  private boolean tot = false;

  private boolean interaction = false;

  // Collision
  private boolean topLeft;
  private boolean topRight;
  private boolean midLeft;
  private boolean midRight;
  private boolean bottomLeft;
  private boolean bottomRight;
  private float dy;
  private float dx;
  private int blockKorX;
  private int blockKorY;

  // Checkpoint
  private int checkpointY;
  private int checkpointX;

  // Items
  private PlayerItemManager itemManager;

  // Angreifbar
  private boolean angreifbar = true;
  private boolean timerSetzen = false;
  private boolean timerGesetzt = false;
  double anfangsTime;

  public Player(Handler handler)
  {
    this.handler = handler;

    handler.setPlayer(this);

    itemManager = new PlayerItemManager(handler);
    handler.setItemManager(itemManager);

    x = handler.getLevelCreator().getPlayerSpawnX();
    y = handler.getLevelCreator().getPlayerSpawnY();

    checkpointX = x;
    checkpointY = y;

  }

  public void update()
  {

    inLeiterRange();
    jumpUpdate();
    calculateMovement();
    calculateCollision();
    move();
    itemManager.update();
    nichtAngreifbar();

  }

  private void jumpUpdate()
  {
    if (fall == false && Tastatur.isMoveUp() == true)
    {

      jump = true;
    }
  }

  public void paint(Graphics2D g2d)
  {
    if (angreifbar == true)
    {
      g2d.setColor(Color.BLACK);
    }

    if (angreifbar == false)
    {
      g2d.setColor(Color.ORANGE);
    }
    g2d.fillRect(x, y, BREITE, HOEHE);
  }
  /*
   * public void keyPressed(KeyEvent e) { int key = e.getKeyCode();
   * 
   * switch (key) { case KeyEvent.VK_UP: if (fall == false) { jump = true; }
   * break; case KeyEvent.VK_LEFT: left = true; lastPositionRight = false; break;
   * case KeyEvent.VK_RIGHT: right = true; lastPositionRight = true; break; case
   * KeyEvent.VK_SPACE: interaction = true; break; case KeyEvent.VK_SHIFT:
   * interaction = true; break; case KeyEvent.VK_W: if (fall == false) { jump =
   * true; } break; case KeyEvent.VK_A: left = true; break; case KeyEvent.VK_D:
   * right = true; break; } }
   * 
   * 
   * public void keyReleased(KeyEvent e) { int key = e.getKeyCode();
   * 
   * switch (key) { case KeyEvent.VK_LEFT: left = false; break; case
   * KeyEvent.VK_RIGHT: right = false; break; case KeyEvent.VK_SPACE: interaction
   * = false; break; case KeyEvent.VK_SHIFT: interaction = false; break; case
   * KeyEvent.VK_A: left = false; break; case KeyEvent.VK_D: right = false; break;
   * } }
   * 
   * 
   * public void keyTyped(KeyEvent arg0) {
   * 
   * }
   */

  private void inLeiterRange()
  {
    boolean abbruch = false;

    if (handler.getLevel().getLeiterListSize() > 0)
    {

      for (int i = 0; i < handler.getLevel().getLeiterListSize() && abbruch == false; i++)
      {
        if (getBounds().intersects(handler.getLevel().getLeiter(i).getBounds()) && Tastatur.isMoveUp())
        {
          doNotFall = true;
          abbruch = true;
        } else
        {
          doNotFall = false;
        }

      }
    } else
    {
      doNotFall = false;
    }
  }

  private void calculateCollision()
  {
    float toX = x + dx;
    float toY = y + dy;

    // Collision Top and Bottom
    calculateCorners(x, toY);
    // System.out.println("00"+handler.getLevelCreator().levelObjects[getBlockKordinateY((int)
    // toY + HOEHE)][ getBlockKordinateX((int) x + BREITE - 1)].walkable);
    if (topLeft == true || topRight == true)
    {
      dy = 0;
      fall = true;
      int playerY = getBlockKordinateY((int) toY);
      y = (playerY + 1) * FrameMain.BLOCKHOEHE;

    }
    if (bottomLeft == true && fall == true || bottomRight == true && fall == true)
    {
      if (doNotFall == false)
      {
        fall = false;
        dy = 0;

        int playerY = getBlockKordinateY((int) toY + HOEHE);

        y = playerY * FrameMain.BLOCKHOEHE - HOEHE;

      }

    }

    if (bottomLeft == false && bottomRight == false)
    {
      if (doNotFall == false)
      {
        fall = true;
      }

    }

    // Collision Left and Right
    calculateCorners(toX, y - 1);
    if (dx < 0)// links
    {
      if (topLeft == true || midLeft == true || bottomLeft == true)
      {

        dx = 0;
      }

    }

    if (dx > 0)
    {
      if (topRight == true || midRight == true || bottomRight == true)
      {
        dx = 0;
      }
    }

  }

  private void calculateCorners(float x, float y)
  {
    int leftTile = getBlockKordinateX((int) x);
    int rightTile = getBlockKordinateX((int) x + BREITE - 1);
    int topTile = getBlockKordinateY((int) y);
    int midTile = getBlockKordinateY((int) y + HOEHE / 2);
    int bottomTile = getBlockKordinateY((int) y + HOEHE);

    if (bottomRight == true)
    {
      // System.out.println("0"+bottomRight);
    }

    if (handler.getLevelCreator().levelObjects[topTile][leftTile].walkable == true)
    {
      topLeft = false;
    } else
    {
      topLeft = true;
    }

    if (handler.getLevelCreator().levelObjects[topTile][rightTile].walkable == true)
    {
      topRight = false;
    } else
    {
      topRight = true;
    }

    if (handler.getLevelCreator().levelObjects[midTile][leftTile].walkable == true)
    {
      midLeft = false;
    } else
    {
      midLeft = true;
    }

    if (handler.getLevelCreator().levelObjects[midTile][rightTile].walkable == true)
    {
      midRight = false;
    } else
    {
      midRight = true;
    }

    if (handler.getLevelCreator().levelObjects[bottomTile][leftTile].walkable == true)
    {
      bottomLeft = false;
    } else
    {
      bottomLeft = true;
    }

    if (handler.getLevelCreator().levelObjects[bottomTile][rightTile].walkable == true)
    {

      bottomRight = false;
    } else
    {

      bottomRight = true;
    }
    // System.out.println(Level.map[bottomTile][leftTile]);

  }

  public void respawnAtCheckpoint()
  {
    x = checkpointX;
    y = checkpointY;
  }

  public void setFall()
  {
    dy = 0;
    fall = true;
  }

  private void calculateMovement()
  {
    if (Tastatur.isMoveLeft() == true)
    {
      lastPositionRight = false;
      dx = -speed;
    }
    if (Tastatur.isMoveRight() == true)
    {
      lastPositionRight = true;
      dx = speed;
    }

    if (fall == true && jump == false && doNotFall == false)
    {
      dy += gravity;
      if (dy > maxFallingSpeed)
      {
        dy = maxFallingSpeed;
      }
    }

    if (jump == true && fall == false && doNotFall == false)
    {
      dy = jumpStart;
      jump = false;
      fall = true;
    }

  }

  public void move()
  {

    x += dx;
    if (doNotFall == false)
      y += dy;

    dx = 0;

  }

  public void setNichtAngreifbarTimerSet()
  {
    timerSetzen = true;
  }

  public void nichtAngreifbar()
  {
    double timeNow = System.nanoTime() / 1000000000;

    if (timerGesetzt == false && timerSetzen == true)
    {
      anfangsTime = System.nanoTime() / 1000000000;
      timerGesetzt = true;
      angreifbar = false;

    } else
    {
      if (timeNow - anfangsTime > 5)
      {
        angreifbar = true;
        timerGesetzt = false;
        timerSetzen = false;
      }
    }
  }

  public void sterben()
  {

  }

  public void setCheckpointY(int checkpointY)
  {
    this.checkpointY = checkpointY;
  }

  public void setCheckpointX(int checkpointX)
  {
    this.checkpointX = checkpointX;
  }

  public int getBlockKordinateY(int y)
  {
    return blockKorY = y / FrameMain.BLOCKHOEHE;
  }

  public int getBlockKordinateX(int x)
  {
    return blockKorX = x / FrameMain.BLOCKBREITE;
  }

  public int getX()
  {

    return x;
  }

  public int getY()
  {

    return y;
  }

  public boolean isAngreifbar()
  {
    return angreifbar;
  }

  public void setAngreifbar(boolean angreifbar)
  {
    this.angreifbar = angreifbar;
  }

  public Rectangle getBounds()
  {
    return new Rectangle(x, y, BREITE, HOEHE);
  }

  public void setJump()
  {
    fall = false;
    jump = true;
  }

  public boolean isFall()
  {
    return fall;
  }

  public boolean isInteraction()
  {
    return interaction;
  }

  public float getDEFAULT_SPEED()
  {
    return DEFAULT_SPEED;
  }

  public void setSpeed(float speed)
  {
    this.speed = speed;
  }

  public boolean isLastPositionRight()
  {
    return lastPositionRight;
  }

  public void setLastPositionRight(boolean lastPositionRight)
  {
    this.lastPositionRight = lastPositionRight;
  }

  public void setY(int y)
  {
    this.y = y;
  }

  public boolean isDoNotFall()
  {
    return doNotFall;
  }

  public void setDoNotFall(boolean doNotFall)
  {
    this.doNotFall = doNotFall;
  }

}
