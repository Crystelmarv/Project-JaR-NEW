package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import game.Handler;
import states.StateGame;
import states.StateManager;

public class PanelHauptMenue extends JPanel implements ActionListener
{

  private Handler handler;
  
  private JButton buttonSpielen;
  private JButton buttonLevelEditor;
  private JButton buttonOptionen;
  private JButton buttonBeenden;
  private JButton buttonCredits;
  private JButton buttonAchievements;
  
  

  public PanelHauptMenue(Handler handler)
  {
    this.handler = handler;

    setSize(FrameMain.FRAMEBREITE, FrameMain.FRAMEHOEHE);
    setLayout(null);

    buttonSpielen = new JButton("Spielen");
    buttonLevelEditor = new JButton("LevelEditor");
    buttonOptionen = new JButton("Optionen");
    buttonBeenden = new JButton("Beenden");
    buttonCredits = new JButton("Credits");
    buttonAchievements = new JButton("Achievements");

    buttonSpielen.setBounds((FrameMain.FRAMEBREITE + 150) / 3, 100, 300, 100);
    buttonLevelEditor.setBounds((FrameMain.FRAMEBREITE + 150) / 3, 250, 300, 100);
    buttonOptionen.setBounds((FrameMain.FRAMEBREITE + 150) / 3, 400, 300, 100);
    buttonBeenden.setBounds((FrameMain.FRAMEBREITE + 150) / 3, 550, 300, 100);
    buttonCredits.setBounds(50, FrameMain.FRAMEHOEHE-150, 100, 50);
    buttonAchievements.setBounds(FrameMain.FRAMEBREITE - 250, 50, 150, 50);
    

    this.add(buttonSpielen);
    this.add(buttonLevelEditor);
    this.add(buttonOptionen);
    this.add(buttonBeenden);
    this.add(buttonAchievements);
    this.add(buttonCredits);

    buttonSpielen.addActionListener(this);
    buttonLevelEditor.addActionListener(this);
    buttonOptionen.addActionListener(this);
    buttonBeenden.addActionListener(this);
    buttonAchievements.addActionListener(this);
    buttonCredits.addActionListener(this);

    setVisible(false);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource().equals(buttonSpielen))
    {
      setVisible(false);
      handler.getFrameMain().repaint();
      StateManager.setState(handler.getStateLevelSelect());
      StateManager.getState().stateUpdate();

    } else if (e.getSource().equals(buttonLevelEditor))
    {
      setVisible(false);
      StateManager.setState(handler.getStateEditor());
      StateManager.getState().stateUpdate();

    } else if (e.getSource().equals(buttonOptionen))
    {
      setVisible(false);
      StateManager.setState(handler.getStateOptionen());
      StateManager.getState().stateUpdate();

    } else if (e.getSource().equals(buttonBeenden))
    {
      beenden();

    }

  }

  public void beenden()
  {
    System.exit(0);
  }

}
