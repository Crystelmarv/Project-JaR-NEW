package gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JViewport;

import game.Handler;
import resManager.Assets;
import resManager.LevelFileReader;
import resManager.LevelFileWriter;
import resManager.Tastatur;
import states.StateManager;

public class PanelEditorLevel extends JPanel implements ActionListener
{
  private Handler handler;

  // Buttons
  private JButton[][] buttons = new JButton[25][300];
  private String[][] ids = new String[25][300];

  private int x = 0;
  private int y = 0;

  // Viewport
  private JPanel panelViewport;
  private JViewport viewport;
  private Point viewPoint;

  // MenüBar Frame
  private JMenuBar menuBar;
  private JMenu menuOptionen;
  private JMenuItem menuItemSpeichern;
  private JMenuItem menuItemOeffnen;
  private JMenuItem menuItemConfig;
  private JMenuItem menuItemHauptMenue;
  private JMenuItem menuItemLevelTesten;

  // TastaturEingabe
  private boolean left = false;
  private boolean right = false;
  private boolean down = false;
  private boolean up = false;
  private boolean shift = false;

  // Shift Help
  private int letzterButtonXTemp;
  private int letzterButtonYTemp;
  private int letzterButtonX;
  private int letzterButtonY;
  private boolean shiftGestezt = false;
  private boolean shiftHelpAktiv = false;
  private boolean zweiterButtonGedrueckt = false;

  // Frame Config
  private FrameEditorConfig frameConfig;
  private boolean focus = true;

  // Frame BlockAusWahl
  private FrameLevelEditorBlockAuswahl frameBlockAuswahl;

  public PanelEditorLevel(Handler handler)
  {
    this.handler = handler;

    handler.setPanelLevelEditor(this);

    setSize(FrameMain.FRAMEBREITE, FrameMain.FRAMEHOEHE);
    setLayout(null);
    setVisible(false);
  }

  public void PanelErstellen()
  {
    

    frameBlockAuswahl = new FrameLevelEditorBlockAuswahl();
    frameConfig = new FrameEditorConfig(this);

    int i, j;
    int x = 20;
    int y = 20;

    // Vieport
    panelViewport = new JPanel();
    panelViewport.setSize(1000000, 1000000);
    panelViewport.setLayout(null);
    panelViewport.setFocusable(true);

    viewPoint = new Point(0, 0);

    viewport = new JViewport();
    viewport.add(panelViewport);
    viewport.setViewPosition(viewPoint);

    // MenüBar Frame
    menuBar = new JMenuBar();
    menuOptionen = new JMenu("Optionen");
    menuItemSpeichern = new JMenuItem("Speichern");
    menuItemOeffnen = new JMenuItem("Öffnen");
    menuItemConfig = new JMenuItem("Congig");
    menuItemHauptMenue = new JMenuItem("zurück zum Hauptmenü");
    menuItemLevelTesten = new JMenuItem("Level testen");

    menuOptionen.add(menuItemSpeichern);
    menuOptionen.add(menuItemOeffnen);
    menuOptionen.add(menuItemConfig);
    menuOptionen.add(menuItemLevelTesten);
    menuOptionen.add(menuItemHauptMenue);

    menuBar.add(menuOptionen);

    menuItemSpeichern.addActionListener(this);
    menuItemOeffnen.addActionListener(this);
    menuItemConfig.addActionListener(this);
    menuItemHauptMenue.addActionListener(this);
    menuItemLevelTesten.addActionListener(this);

    handler.getFrameMain().add(menuBar);
    handler.getFrameMain().setJMenuBar(menuBar);

    // Buttons
    for (i = 0; i < buttons.length; i++)
    {
      for (j = 0; j < buttons[1].length; j++)
      {
        buttons[i][j] = new JButton();
        buttons[i][j].setBounds(x, y, 64, 64);
        ids[i][j] = "20";
        buttons[i][j].addActionListener(this);
        panelViewport.add(buttons[i][j]);

        x = x + 64;

      }
      x = 20;
      y = y + 64;
    }

    panelViewport.setVisible(true);
    // this.add(panelViewport);
    // handler.getFrameMain().add(this);
    // this.setVisible(true);
    handler.getFrameMain().add(panelViewport);
    handler.getFrameMain().requestFocus();
  }

  public void update()
  {
    if (focus = true)
    {
      handler.getFrameMain().requestFocus();
    }

    shiftHelp();

    if (Tastatur.isMoveRight() == true)
    {
      x = x - 30;
    }

    if (Tastatur.isMoveLeft() == true)
    {
      x = x + 30;
    }

    if (Tastatur.isMoveUp() == true)
    {
      y = y + 30;
    }

    if (Tastatur.isMoveDown() == true)
    {
      y = y - 30;
    }

    viewPoint.move(x, y);
    panelViewport.setLocation(viewPoint);
  }

  public void paint(Graphics g)
  {

  }
/*
  public void keyReleased(KeyEvent e)
  {
    int key = e.getKeyCode();

    switch (key)
    {
    case KeyEvent.VK_RIGHT:
      right = false;
      break;
    case KeyEvent.VK_LEFT:
      left = false;
      break;
    case KeyEvent.VK_UP:
      up = false;
      break;
    case KeyEvent.VK_DOWN:
      down = false;
      break;
    case KeyEvent.VK_SHIFT:
      shift = false;
      break;

    }

  }

  public void keyPressed(KeyEvent e)
  {
    int key = e.getKeyCode();

    switch (key)
    {
    case KeyEvent.VK_RIGHT:
      right = true;
      break;
    case KeyEvent.VK_LEFT:
      left = true;
      break;
    case KeyEvent.VK_UP:
      up = true;
      break;
    case KeyEvent.VK_DOWN:
      down = true;
      break;
    case KeyEvent.VK_SHIFT:
      shift = true;
      break;

    }

  }
*/
  @Override
  public void actionPerformed(ActionEvent b)
  {
    int i, j;

    System.out.println("BUTTTON");

    if (menuItemSpeichern.equals(b.getSource()))
    {
      try
      {
        LevelFileWriter.speichern(handler, frameConfig);
      } catch (IOException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else if (menuItemOeffnen.equals(b.getSource()))
    {
      try
      {
        LevelFileReader.levelOeffnenEditor(handler, frameConfig);
      } catch (IOException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    } else if (menuItemConfig.equals(b.getSource()))
    {
      focus = false;
      frameConfig.setVisible(true);

    } else if (menuItemLevelTesten.equals(b.getSource()))
    {
      try
      {
        levelTesten();
      } catch (IOException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else if (menuItemHauptMenue.equals(b.getSource()))
    {
      panelViewport.setVisible(false);
      handler.getFrameMain().setJMenuBar(null);
      frameBlockAuswahl.setVisible(false);
      StateManager.setState(handler.getStateHauptMenue());
      StateManager.getState().stateUpdate();

    } else

    {

      for (i = 0; i < buttons.length; i++)
      {
        for (j = 0; j < buttons[1].length; j++)
        {
          if (buttons[i][j].equals(b.getSource()))
          {

            buttons[i][j].setIcon(new ImageIcon(resManager.Assets.texturen[frameBlockAuswahl.getTexture()]));
            ids[i][j] = resManager.Assets.getID(frameBlockAuswahl.getTexture());
            letzterButtonX = j;
            letzterButtonY = i;
            if (shiftGestezt == true && shiftHelpAktiv == true)
            {
              zweiterButtonGedrueckt = true;
            }
          }
        }
      }
    }
  }

  public void oeffnenButtonsUpdate()
  {
    int i, j, k;
    for (i = 0; i < buttons.length; i++)
    {

      for (j = 0; j < buttons[1].length; j++)
      {

        for (k = 0; k < Assets.texturen.length; k++)
        {
          // System.out.println("id " +ids[i][j]);
          // System.out.println(assets.getID(k));
          if (ids[i][j].equals(Assets.getID(k)))
          {
            buttons[i][j].setIcon(new ImageIcon(Assets.texturen[k]));
          }
        }

      }
    }

  }

  public void shiftHelp()
  {
    int i;
    if (Tastatur.isShiftHelp() == true && shiftGestezt == false)
    {
      letzterButtonXTemp = letzterButtonX;
      letzterButtonYTemp = letzterButtonY;
      shiftGestezt = true;
    }
    if (Tastatur.isShiftHelp() == false)
    {
      shiftHelpAktiv = true;
    }

    if (shiftGestezt == true && shiftHelpAktiv == true && zweiterButtonGedrueckt == true)
    {

      if (letzterButtonXTemp == letzterButtonX)
      {
        Tastatur.setShiftHelp(false);
        System.out.println("X GLEICH");

        if (letzterButtonYTemp > letzterButtonY)
        {
          for (i = letzterButtonYTemp; i >= letzterButtonY; i--)
          {
            buttons[i][letzterButtonX].setIcon(new ImageIcon(Assets.texturen[frameBlockAuswahl.getTexture()]));
            ids[i][letzterButtonX] = Assets.getID(frameBlockAuswahl.getTexture());
          }
        }

        if (letzterButtonYTemp < letzterButtonY)
        {
          for (i = letzterButtonYTemp; i <= letzterButtonY; i++)
          {
            buttons[i][letzterButtonX].setIcon(new ImageIcon(Assets.texturen[frameBlockAuswahl.getTexture()]));
            ids[i][letzterButtonX] = Assets.getID(frameBlockAuswahl.getTexture());
          }
        }
      }

      if (letzterButtonYTemp == letzterButtonY)
      {
        Tastatur.setShiftHelp(false);
        System.out.println("Y GLEICH");

        if (letzterButtonXTemp > letzterButtonX)
        {
          for (i = letzterButtonXTemp; i >= letzterButtonX; i--)
          {
            buttons[letzterButtonY][i].setIcon(new ImageIcon(Assets.texturen[frameBlockAuswahl.getTexture()]));
            ids[letzterButtonY][i] = Assets.getID(frameBlockAuswahl.getTexture());
          }
        }

        if (letzterButtonXTemp < letzterButtonX)
        {
          for (i = letzterButtonXTemp; i <= letzterButtonX; i++)
          {
            buttons[letzterButtonY][i].setIcon(new ImageIcon(Assets.texturen[frameBlockAuswahl.getTexture()]));
            ids[letzterButtonY][i] = Assets.getID(frameBlockAuswahl.getTexture());
          }
        }
      }
      shiftGestezt = false;
      zweiterButtonGedrueckt = false;

    }
  }

  private void levelTesten() throws IOException
  {
    File temp = LevelFileWriter.writeTempFile(handler, frameConfig);

    LevelFileReader.setLevelPfad(temp.getAbsolutePath());

    handler.getPanelGame().setLevelEditorTest(true);

    panelViewport.setVisible(false);
    handler.getFrameMain().setJMenuBar(null);
    frameBlockAuswahl.setVisible(false);

    StateManager.setState(handler.getStateGame());
    handler.getStateGame().stateUpdate();

  }

  public void levelTestenLaden() throws IOException
  {
    handler.getPanelGame().setVisible(false);
    handler.getFrameMain().repaint();
    StateManager.setState(handler.getStateEditor());
    StateManager.getState().stateUpdate();

    LevelFileReader.levelLesenEditor(handler);
    LevelFileReader.levelNameLaden(frameConfig);

  }

  public String getBlockIds(int i, int j)
  {
    return ids[i][j];
  }

  public void setBlockIds(int i, int j, String id)
  {
    ids[i][j] = id;
  }

  public int getButtonLaengeY()
  {
    return buttons.length;

  }

  public int getButtonLaengeX()
  {
    return buttons[0].length;

  }

  public void setFocus(boolean focus)
  {
    this.focus = focus;
  }
}
