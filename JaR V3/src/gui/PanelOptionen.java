package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import game.Handler;
import states.StateManager;

public class PanelOptionen extends JPanel implements ActionListener
{
  private Handler handler;
  
  private JButton buttonZurueck;

  public PanelOptionen(Handler handler)
  {
    this.handler = handler;

    setSize(FrameMain.FRAMEBREITE, FrameMain.FRAMEHOEHE);
    setLayout(null);
    
    
    buttonZurueck = new JButton("Zurück");
    buttonZurueck.setBounds(50,750, 100, 70);
    this.add(buttonZurueck);
   buttonZurueck.addActionListener(this);
   setVisible(false);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource().equals(buttonZurueck))
    {
      setVisible(false);
      StateManager.setState(handler.getStateHauptMenue());
      StateManager.getState().stateUpdate();
     
    }
    
  }
 
}
