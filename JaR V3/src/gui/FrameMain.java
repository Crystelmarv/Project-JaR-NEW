package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.undo.StateEdit;

import game.Handler;
import resManager.Tastatur;
import states.State;
import states.StateCredits;
import states.StateEditor;
import states.StateGame;
import states.StateHauptMenue;
import states.StateLevelSelect;
import states.StateManager;
import states.StateOptionen;
import states.StatePause;

public class FrameMain extends JFrame
{

  // Frame
  public final static int FRAMEHOEHE = 900;
  public final static int FRAMEBREITE = 1450;

  // Größe der Blöcke
  public final static int BLOCKBREITE = 64;
  public final static int BLOCKHOEHE = 64;

  // States
  private State stateGame;
  private State stateHauptMenue;
  private State stateEditor;
  private State stateLevelSelect;
  private State stateOptionen;
  private State statePause;
  private State stateCredits;

  // Handler
  private Handler handler;

  // booleans für Init
  private boolean frameInit = false;
  
  //FPS
  private static double fps = 100.0;

  public FrameMain() throws InterruptedException, IOException
  {

    // Frame
    setSize(FRAMEBREITE, FRAMEHOEHE);
    setTitle("JaR - BETA 1.2 - Entwicklerversion");
    setResizable(false);
    setLocationRelativeTo(null);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);
    setFocusable(true);
    requestFocus();

    // Handler
    handler = new Handler(this);

    // States
    stateGame = new StateGame(handler);
    stateHauptMenue = new StateHauptMenue(handler);
    stateEditor = new StateEditor(handler);
    stateLevelSelect = new StateLevelSelect(handler);
    stateOptionen = new StateOptionen(handler);
    statePause = new StatePause(handler);
    stateCredits = new StateCredits(handler);

    handler.setStateGame(stateGame);
    handler.setStateHauptMenue(stateHauptMenue);
    handler.setStateEditor(stateEditor);
    handler.setStateLevelSelect(stateLevelSelect);
    handler.setStateOptionen(stateOptionen);
    handler.setStatePause(statePause);
    handler.setStateCredits(stateCredits);

    Tastatur.standartKeyBinding(handler.getPanelGame());
    Tastatur.standartKeyBinding(handler.getPanelLevelEditor());

    StateManager.setState(stateHauptMenue);
    StateManager.getState().stateUpdate();

    addKeyListener(new KeyListener()
    {

      @Override
      public void keyTyped(KeyEvent e)
      {
        // TODO Auto-generated method stub

      }

      @Override
      public void keyReleased(KeyEvent e)
      {
        if (StateManager.getState().equals(handler.getStateGame()))
        {
          // handler.getPlayer().keyReleased(e);
          // handler.getPanelGame().keyReleased(e);

        }
        if (StateManager.getState().equals(handler.getStateEditor()))
        {
          // handler.getPanelLevelEditor().keyReleased(e);
        }

      }

      @Override
      public void keyPressed(KeyEvent e)
      {
        if (StateManager.getState().equals(handler.getStateGame()))
        {
          // handler.getPlayer().keyPressed(e);
          // handler.getPanelGame().keyPressed(e);

        }
        if (StateManager.getState().equals(handler.getStateEditor()))
        {
          // handler.getPanelLevelEditor().keyPressed(e);
        }
      }
    });

    setVisible(true);
    frameInit = true;
    gameLoop();
  }

  public static void main(String[] args) throws InterruptedException, IOException
  {
    new FrameMain();
  }

  public void gameLoop() throws InterruptedException, IOException
  {
    // Timer
    
    double timePerTick = 1000000000.0 / fps;
    double delta = 0.0;
    long now;
    long lastTime = System.nanoTime();
    int ticks = 0;
    long timer = 0;

    while (true)
    {
      now = System.nanoTime();
      delta += (now - lastTime) / timePerTick;
      timer += now - lastTime;
      lastTime = now;

      if (delta >= 1.0)
      {
        StateManager.getState().update();
        repaint();
        ticks++;
        delta--;
      }
      Thread.sleep(1);
      if (timer >= 1000000000)
      {
        System.out.println("FPS: " + ticks);
        ticks = 0;
        timer = 0;
      }
    }
  }

  public static double getFps()
  {
    return fps;
  }

  public static void setFps(double fps)
  {
    FrameMain.fps = fps;
  }

}
