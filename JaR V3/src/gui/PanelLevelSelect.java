package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.Handler;
import resManager.LevelFileReader;
import states.StateGame;
import states.StateManager;

public class PanelLevelSelect extends JPanel implements ActionListener
{
  private Handler handler;

  private final int ANZAHLLEVEL = 3;
  private JButton[] bts = new JButton[ANZAHLLEVEL];

  private JButton buttonZurueck;

  public PanelLevelSelect(Handler handler)
  {
    this.handler = handler;

    setSize(FrameMain.FRAMEBREITE, FrameMain.FRAMEHOEHE);
    setLayout(null);

    int i;
    int x = 50;
    int y = 50;
    for (i = 0; i < ANZAHLLEVEL; i++)
    {
      bts[i] = new JButton();
      this.add(bts[i]);

      bts[i].setBounds(x, y, 90, 90);
      bts[i].setText("Level " + (i + 1));
      bts[i].addActionListener(this);
      x = x + 90 + 25;
    }

    buttonZurueck = new JButton("Zurück");
    buttonZurueck.setBounds(50, 750, 100, 70);
    this.add(buttonZurueck);
    buttonZurueck.addActionListener(this);

    setVisible(false);
  }

  

  @Override
  public void actionPerformed(ActionEvent e)
  {
    int i;
    
    if (e.getSource().equals(buttonZurueck))
    {
      setVisible(false);
      StateManager.setState(handler.getStateHauptMenue());
      StateManager.getState().stateUpdate();
     

    } else
    {
      for (i = 0; i < ANZAHLLEVEL; i++)
      {
        if(e.getSource().equals(bts[i]))
        {
          setVisible(false);
        
          LevelFileReader.setLevelPfad("/level/"+(i+1)+".Level.txt");
        
          StateManager.setState(handler.getStateGame());
          StateManager.getState().stateUpdate();
        }
      }

    }

  }

}
