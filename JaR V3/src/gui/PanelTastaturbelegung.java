package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent.KeyBinding;

import game.Handler;
import resManager.Tastatur;

public class PanelTastaturbelegung extends JPanel implements ActionListener
{
  private Handler handler;
  private boolean record = false;
  private KeyEvent recordKey;

  // Keys

  private int whichKey;

  // Buttons
  private JButton buttonZurueck;

  private JButton[] buttons = new JButton[Tastatur.ANZAHL_TASTEN];

  // Labels
  private JLabel labelMoveRight;
  private JLabel labelMoveLeft;
  private JLabel labelMoveUp;
  private JLabel labelMoveDown;
  private JLabel labelUseItem;
  private JLabel labelPause;
  private JLabel labelShiftHelp;
  private JLabel labelStrHelp;

  private KeyListener keyL;

  private String idRelease, idPress;
  private ActionListener alRelease, alPress;

  public PanelTastaturbelegung(Handler handler)
  {
    this.handler = handler;

    setSize(FrameMain.FRAMEBREITE, FrameMain.FRAMEHOEHE);
    setLayout(null);

    // Buttons erstellen

    buttonZurueck = new JButton("Zurück");
    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i] = new JButton();
    }

    // Label erstellen
    labelMoveRight = new JLabel("nach rechts bewegen");
    labelMoveLeft = new JLabel("nach links bewegen");
    labelMoveDown = new JLabel("nach unten bewegen");
    labelMoveUp = new JLabel("nach oben bewegen/hüpfen");
    labelUseItem = new JLabel("Item benutzen");
    labelPause = new JLabel("Pause");
    labelShiftHelp = new JLabel("Shift-Hilfe");
    labelStrHelp = new JLabel("Str-Hilfe");

    // Buttons Position
    buttonZurueck.setBounds(50, 750, 100, 70);
    int y = 100;
    int x = 250;
    int k = 0;

    for (int j = 0; j < 2; j++)
    {
      for (int i = 0; i < 5; i++)
      {
        buttons[k].setBounds(x, y, 200, 70);
        y = y + 100;
        k++;
      }
      x = x + 500;
      y = 100;
    }

    // Label Position
    labelMoveRight.setBounds(50, 100, 200, 70);
    labelMoveLeft.setBounds(50, 200, 200, 70);
    labelMoveDown.setBounds(50, 300, 200, 70);
    labelMoveUp.setBounds(50, 400, 200, 70);
    labelUseItem.setBounds(50, 500, 200, 70);
    labelPause.setBounds(550, 100, 200, 70);
    labelShiftHelp.setBounds(550, 200, 200, 70);
    labelStrHelp.setBounds(550, 300, 200, 70);

    // Buttons zum Panel hinzufügen
    this.add(buttonZurueck);

    for (int i = 0; i < buttons.length-2; i++)
    {
      this.add(buttons[i]);
    }

    // Label zu Panel hinzufügen
    this.add(labelMoveLeft);
    this.add(labelMoveRight);
    this.add(labelMoveDown);
    this.add(labelMoveUp);
    this.add(labelUseItem);
    this.add(labelPause);
    this.add(labelShiftHelp);
    this.add(labelStrHelp);

    // Buttons und ActionListner verknüpfen
    buttonZurueck.addActionListener(this);
    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i].addActionListener(this);
    }

    keyButtonAnzeigeUpdate();
    setVisible(false);

    handler.getFrameMain().addKeyListener(keyL = new KeyListener()
    {
      @Override
      public void keyTyped(KeyEvent e)
      {
      }

      @Override
      public void keyPressed(KeyEvent e)
      {
        if (record == true)
        {
          recordKey = e;
          record = false;
          keyBinden();
        }
      }

      @Override
      public void keyReleased(KeyEvent e)
      {
      }
    });
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource().equals(buttonZurueck))
    {
      setVisible(false);
      handler.getPanelOptionne().setVisible(true);
    } else
    {
      handler.getFrameMain().requestFocus();
      if (e.getSource().equals(buttons[0]))
      {
        whichKey = 0;
        idPress = "moveRightPress";
        idRelease = "moveRightRelease";
        alPress = (pre) -> {
          Tastatur.setMoveRight(true);
        };
        alRelease = (rel) -> {
          Tastatur.setMoveRight(false);
        };
      } else if (e.getSource().equals(buttons[1]))
      {
        whichKey = 1;
        idPress = "moveLeftPress";
        idRelease = "moveLeftRelease";
        alPress = (pre) -> {
          Tastatur.setMoveLeft(true);
        };
        alRelease = (rel) -> {
          Tastatur.setMoveLeft(false);
        };
      } else if (e.getSource().equals(buttons[2]))
      {
        whichKey = 2;
        idPress = "moveDownPress";
        idRelease = "moveDownRelease";
        alPress = (pre) -> {
          Tastatur.setMoveDown(true);
        };
        alRelease = (rel) -> {
          Tastatur.setMoveDown(false);
        };
      } else if (e.getSource().equals(buttons[3]))
      {
        whichKey = 3;
        idPress = "moveUpPress";
        idRelease = "moveUpRelease";
        alPress = (pre) -> {
          Tastatur.setMoveUp(true);
        };
        alRelease = (rel) -> {
          Tastatur.setMoveUp(false);
        };
      } else if (e.getSource().equals(buttons[4]))
      {
        whichKey = 4;
        idPress = "useItemPress";
        idRelease = "useItemRelease";
        alPress = (pre) -> {
          Tastatur.setUseItem(true);
        };
        alRelease = (rel) -> {
          Tastatur.setUseItem(false);
        };
      } else if (e.getSource().equals(buttons[5]))
      {
        whichKey = 5;
        idPress = "pausePress";
        idRelease = "pauseRelease";
        alPress = (pre) -> {
          Tastatur.setPause(true);
        };
        alRelease = (rel) -> {
          Tastatur.setPause(false);
        };
      } else if (e.getSource().equals(buttons[6]))
      {
        whichKey = 6;
        idPress = "shiftHelpPress";
        idRelease = "shiftHelpRelease";
        alPress = (pre) -> {
          Tastatur.setShiftHelp(true);
        };
        alRelease = (rel) -> {
          Tastatur.setShiftHelp(false);
        };
      } else if (e.getSource().equals(buttons[7]))
      {
        whichKey = 7;
        idPress = "strHelpPress";
        idRelease = "strHelpRelease";
        alPress = (pre) -> {
          Tastatur.setStrHelp(true);
        };
        alRelease = (rel) -> {
          Tastatur.setStrHelp(false);
        };
      }
      Tastatur.removeKeyBinding(handler.getPanelGame(), idPress, idRelease, Tastatur.getKeyIds()[whichKey]);
      Tastatur.removeKeyBinding(handler.getPanelLevelEditor(), idPress, idRelease, Tastatur.getKeyIds()[whichKey]);
      record = true;

    }

  }

  private void keyBinden()
  {
    Tastatur.addKeyBinding(handler.getPanelGame(), recordKey.getKeyCode(), idPress, idRelease, alPress, alRelease);
    Tastatur.addKeyBinding(handler.getPanelLevelEditor(), recordKey.getKeyCode(), idPress, idRelease, alPress, alRelease);
    Tastatur.getKeyIds()[whichKey] = recordKey.getKeyCode();
    keyButtonAnzeigeUpdate();

  }

  public void keyButtonAnzeigeUpdate()
  {
    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i].setText(KeyEvent.getKeyText(Tastatur.getKeyIds()[i]));
    }

  }

}
