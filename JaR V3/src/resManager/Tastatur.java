package resManager;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class Tastatur
{
  public final static int ANZAHL_TASTEN = 10;

  private static boolean moveRight, moveLeft, moveUp, moveDown, shiftHelp, strHelp, pause, esc, useItem;

  private static int[] keyIds = new int[ANZAHL_TASTEN];

  public static void addKeyBinding(JComponent comp, int keyC, String idPress, String idRelease,
      ActionListener actionLPress, ActionListener actionLRelaese)
  {
    InputMap im = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap ap = comp.getActionMap();

    im.put(KeyStroke.getKeyStroke(keyC, 0, false), idPress);
    ap.put(idPress, new AbstractAction()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        actionLPress.actionPerformed(e);
      }
    });

    im.put(KeyStroke.getKeyStroke(keyC, 0, true), idRelease);
    ap.put(idRelease, new AbstractAction()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        actionLRelaese.actionPerformed(e);

      }
    });

  }

  public static void removeKeyBinding(JComponent comp, String idPress, String idRelease, int kE)
  {
    InputMap im = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap ap = comp.getActionMap();

    im.remove(KeyStroke.getKeyStroke(kE, 0, false));
    im.remove(KeyStroke.getKeyStroke(kE, 0, true));

    ap.remove(idPress);
    ap.remove(idRelease);

  }

  public static void standartKeyBinding(JComponent comp)
  {

    addKeyBinding(comp, keyIds[3] = KeyEvent.VK_UP, "moveUpPress", "moveUpRelease", (pre) -> {
      moveUp = true;

    }, (rel) -> {
      moveUp = false;
    });

    addKeyBinding(comp, keyIds[2] = KeyEvent.VK_DOWN, "moveDownPress", "moveDownRelease", (pre) -> {
      moveDown = true;
    }, (rel) -> {
      moveDown = false;
    });

    addKeyBinding(comp, keyIds[0] = KeyEvent.VK_RIGHT, "moveRightPress", "moveRightRelease", (pre) -> {
      moveRight = true;

    }, (rel) -> {
      moveRight = false;

    });

    addKeyBinding(comp, keyIds[1] = KeyEvent.VK_LEFT, "moveLeftPress", "moveLeftRelease", (pre) -> {
      moveLeft = true;
    }, (rel) -> {
      moveLeft = false;
    });

    addKeyBinding(comp, keyIds[4] = KeyEvent.VK_SPACE, "useItemPress", "useItemRelease", (pre) -> {
      useItem = true;
    }, (rel) -> {
      useItem = false;
    });

    addKeyBinding(comp, keyIds[5] = KeyEvent.VK_ESCAPE, "pausePress", "pauseRelease", (pre) -> {
      pause = true;
    }, (rel) -> {
      pause = false;
    });

    addKeyBinding(comp, keyIds[6] = KeyEvent.VK_Q, "shiftHelpPress", "shiftHelpRelease", (pre) -> {
      shiftHelp = true;
    }, (rel) -> {
      shiftHelp = false;
    });

    addKeyBinding(comp, keyIds[7] = KeyEvent.VK_CONTROL, "strHelpPress", "strRelease", (pre) -> {
      strHelp = true;
    }, (rel) -> {
      strHelp = false;
    });
  }

  public static boolean isMoveRight()
  {
    return moveRight;
  }

  public static boolean isMoveLeft()
  {
    return moveLeft;
  }

  public static boolean isMoveUp()
  {
    return moveUp;
  }

  public static boolean isMoveDown()
  {
    return moveDown;
  }

  public static void setMoveRight(boolean moveRight)
  {
    Tastatur.moveRight = moveRight;
  }

  public static void setMoveLeft(boolean moveLeft)
  {
    Tastatur.moveLeft = moveLeft;
  }

  public static void setMoveUp(boolean moveUp)
  {
    Tastatur.moveUp = moveUp;
  }

  public static void setMoveDown(boolean moveDown)
  {
    Tastatur.moveDown = moveDown;
  }

  public static boolean isUseItem()
  {
    return useItem;
  }

  public static void setUseItem(boolean useItem)
  {
    Tastatur.useItem = useItem;
  }

  public static int[] getKeyIds()
  {
    return keyIds;
  }

  public static void setKeyIds(int[] keyIds)
  {
    Tastatur.keyIds = keyIds;
  }

  public static boolean isShiftHelp()
  {
    return shiftHelp;
  }

  public static boolean isStrHelp()
  {
    return strHelp;
  }

  public static boolean isPause()
  {
    return pause;
  }

  public static void setShiftHelp(boolean shiftHelp)
  {
    Tastatur.shiftHelp = shiftHelp;
  }

  public static void setStrHelp(boolean strHelp)
  {
    Tastatur.strHelp = strHelp;
  }

  public static void setPause(boolean pause)
  {
    Tastatur.pause = pause;
  }

}
