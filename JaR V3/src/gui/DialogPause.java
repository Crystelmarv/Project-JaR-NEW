package gui;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import game.Handler;
import states.StateGame;

public class DialogPause extends JDialog implements ActionListener
{
  private Handler handler;
  private Button fortsezen, optionen, levelSelect, levelNeustart;

  public DialogPause(Handler handler)
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
    levelSelect = new Button("zur Levelauswahl");
    levelNeustart = new Button("Level neustarten");

    fortsezen.setBounds(75, 50, 150, 50);
    optionen.setBounds(75, 125, 150, 50);
    levelNeustart.setBounds(75, 200, 150, 50);
    levelSelect.setBounds(75, 275, 150, 50);

    add(fortsezen);
    add(optionen);
    add(levelNeustart);
    add(levelSelect);
    
    fortsezen.addActionListener(this);

  }

  private void unpause()
  {
    StateGame stg = (StateGame) handler.getStateGame();
    stg.setPause(false);
    setVisible(false);

  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    unpause();

  }

}
