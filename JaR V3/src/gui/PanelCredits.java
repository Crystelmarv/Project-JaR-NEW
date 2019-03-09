package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.Handler;
import resManager.Timer;
import states.StateManager;

public class PanelCredits extends JPanel implements ActionListener
{

  private Handler handler;
  private JButton btZurück;
  private String[] strings =
  { "Ein Danke an: ", "Christian", "Flow", "Markus", "Philipp", "Luca" };
  private int [] x = new int[6];
  private int [] y = new int[6];

  public PanelCredits(Handler handler)
  {
    this.handler = handler;

    setSize(FrameMain.FRAMEBREITE, FrameMain.FRAMEHOEHE);
    setLayout(null);

    btZurück = new JButton("Zurück");
    btZurück.setBounds(50, FrameMain.FRAMEHOEHE - 150, 100, 50);

    add(btZurück);
    btZurück.addActionListener(this);
    randomCords();
    
    setVisible(false);
   
  }

  @Override
  public void paint(Graphics g)
  {

    super.paint(g);
    g.setColor(Color.black);
    g.setFont(new Font("Arial", Font.BOLD, 25));

    for (int i = 0; i < strings.length; i++)
    {
      g.drawString(strings[i], x[i], y[i]);
    }

  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    setVisible(false);
    StateManager.setState(handler.getStateHauptMenue());
    StateManager.getState().stateUpdate();
    randomCords();

  }
  
  private void randomCords()
  {
    for (int i = 0; i < strings.length; i++)
    {
      x[i] =  (int) ((Math.random() * FrameMain.FRAMEBREITE + 1));
      y[i] =  (int) ((Math.random() * FrameMain.FRAMEHOEHE + 1));
      
     
    }
  }

}
