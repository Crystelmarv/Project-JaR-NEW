package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.DreiApfelWertung;
import game.Handler;
import resManager.Assets;
import resManager.LevelFileReader;
import resManager.LevelFileWriter;
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
      bts[i].setVerticalTextPosition(0);
      bts[i].setHorizontalTextPosition(0);
      setTextLevelNr(i);
      bts[i].setIconTextGap(0);
      bts[i].setBounds(x, y, 90, 90);
      setTexture(i);
      
       setMouseListener(i);
      
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
        if (e.getSource().equals(bts[i]))
        {
          setVisible(false);

          LevelFileReader.setLevelPfad("/level/" + (i + 1) + ".Level.txt");

          DreiApfelWertung.aktuellesLevel = i;

          StateManager.setState(handler.getStateGame());
          StateManager.getState().stateUpdate();
        }
      }

    }

  }
  public void updateTexture()
  {
    for (int i = 0; i < ANZAHLLEVEL; i++)
    {
      setTexture(i);
    }
  }
  
  private void setTextLevelNr(int btNr)
  {
    bts[btNr].setText("Level " + (btNr + 1));
  }
 
  
  private void setTextLevelHighscore(int btNr)
  {
    try
    {
      LevelFileReader.setLevelPfad("/level/" + (btNr + 1) + ".Level.txt");
      bts[btNr].setText("<html> " + LevelFileReader.highscoreEinlesen(1) +"<br />"
          + LevelFileReader.highscoreEinlesen(2) + "<br />"+ 
          LevelFileReader.highscoreEinlesen(3)+" </html>");
    } catch (IOException e)
    {
 
      e.printStackTrace();
    }
  }


  private void setTexture(int buttonNr)
  {
    int levelGeschafft = 0;
    int alleAepfel = 0;
    int zeit = 0;

    if (DreiApfelWertung.wertung[buttonNr][0] == 0 && DreiApfelWertung.wertung[buttonNr][1] == 0
        && DreiApfelWertung.wertung[buttonNr][2] == 0)
    {
      bts[buttonNr].setIcon(new ImageIcon(Assets.buttonLevelSelect000));
    } 
    else if (DreiApfelWertung.wertung[buttonNr][0] == 1 && DreiApfelWertung.wertung[buttonNr][1] == 0
        && DreiApfelWertung.wertung[buttonNr][2] == 0)
    {
      bts[buttonNr].setIcon(new ImageIcon(Assets.buttonLevelSelect100));
    }
    else if (DreiApfelWertung.wertung[buttonNr][0] == 1 && DreiApfelWertung.wertung[buttonNr][1] == 0
        && DreiApfelWertung.wertung[buttonNr][2] == 1)
    {
      bts[buttonNr].setIcon(new ImageIcon(Assets.buttonLevelSelect101));
    }
    else if (DreiApfelWertung.wertung[buttonNr][0] == 1 && DreiApfelWertung.wertung[buttonNr][1] == 1
        && DreiApfelWertung.wertung[buttonNr][2] == 0)
    {
      bts[buttonNr].setIcon(new ImageIcon(Assets.buttonLevelSelect110));
    }
    else if (DreiApfelWertung.wertung[buttonNr][0] == 1 && DreiApfelWertung.wertung[buttonNr][1] == 1
        && DreiApfelWertung.wertung[buttonNr][2] == 1)
    {
      bts[buttonNr].setIcon(new ImageIcon(Assets.buttonLevelSelect111));
    }

  }
  
  private void setMouseListener(int buttNr)
  {
    bts[buttNr].addMouseListener(new MouseAdapter()
    {
      public void mouseExited(MouseEvent me) {
        setTexture(buttNr);
        setTextLevelNr(buttNr);
     }
      public void mouseEntered(MouseEvent me) {
        bts[buttNr].setIcon(new ImageIcon(Assets.buttonLevelSelect000));
        setTextLevelHighscore(buttNr);
      }
      
    });
  }

}
