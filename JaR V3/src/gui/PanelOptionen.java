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
  private PanelTastaturbelegung panelTastaturbelegung;
  
  private JButton buttonZurueck;
  private JButton buttonTastaturbelegung;

  public PanelOptionen(Handler handler)
  {
    this.handler = handler;

    setSize(FrameMain.FRAMEBREITE, FrameMain.FRAMEHOEHE);
    setLayout(null);
    
    panelTastaturbelegung = new PanelTastaturbelegung(handler);
    handler.getFrameMain().add(panelTastaturbelegung);
    
    
    buttonZurueck = new JButton("Zurück");
    buttonTastaturbelegung = new JButton("Tastaturbelegung");
    
    buttonZurueck.setBounds(50,750, 100, 70);
    buttonTastaturbelegung.setBounds(50, 100, 200, 70);
    
    this.add(buttonTastaturbelegung);
    this.add(buttonZurueck);
    
   buttonZurueck.addActionListener(this);
   buttonTastaturbelegung.addActionListener(this);
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
    else if(e.getSource().equals(buttonTastaturbelegung))
    { 
      
      setVisible(false);
      panelTastaturbelegung.keyButtonAnzeigeUpdate();
      panelTastaturbelegung.setVisible(true);
     handler.getFrameMain().repaint();
    }
    
  }
 
}
