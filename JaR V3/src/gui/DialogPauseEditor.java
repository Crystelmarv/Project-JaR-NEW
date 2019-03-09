package gui;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JDialog;

import game.Handler;
import resManager.LevelFileReader;
import resManager.Timer;
import states.StateGame;
import states.StateManager;

public class DialogPauseEditor extends JDialog implements ActionListener
{
  private Handler handler;
  private Button fortsezen, optionen, zurueckZumEditor, levelNeustart;

  public DialogPauseEditor(Handler handler)
  {
    this.handler = handler;

    setSize(300, 400);
    setTitle("Pause");
    setResizable(false);
    setLocationRelativeTo(rootPane);
    setVisible(false);
    setLayout(null);
    setFocusable(true);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    // Button
    fortsezen = new Button("Fortsetzen");
    optionen = new Button("Optionen");
    zurueckZumEditor = new Button("zurück zum Editor");
    levelNeustart = new Button("Level neustarten");

    fortsezen.setBounds(75, 50, 150, 50);
    optionen.setBounds(75, 125, 150, 50);
    levelNeustart.setBounds(75, 200, 150, 50);
    zurueckZumEditor.setBounds(75, 275, 150, 50);

    add(fortsezen);
    add(optionen);
    add(levelNeustart);
    add(zurueckZumEditor);

    fortsezen.addActionListener(this);
    optionen.addActionListener(this);
    levelNeustart.addActionListener(this);
    zurueckZumEditor.addActionListener(this);
    

  }

  private void unpause()
  {
    Timer.setTimeNachPause(System.nanoTime());
    Timer.setPauseTime();
    StateGame stg = (StateGame) handler.getStateGame();
    stg.setPause(false);
    setVisible(false);

  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource().equals(fortsezen))
    {
      unpause();
    } else if (e.getSource().equals(optionen))
    {
      
    } else if (e.getSource().equals(levelNeustart))
    {
      setVisible(false);
      unpause();
      handler.getPanelGame().setVisible(false);
      StateManager.getState().stateUpdate();
    } else if (e.getSource().equals(zurueckZumEditor))
    {
      setVisible(false);
      unpause();
      try
      {
        handler.getPanelLevelEditor().levelTestenLaden();
      } catch (IOException e1)
      {
        e1.printStackTrace();
      }
    }
  }
}
