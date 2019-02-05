
package states;

import java.awt.Graphics;
import java.io.IOException;

import game.DreiApfelWertung;
import game.Handler;
import gui.PanelLevelSelect;
import resManager.LevelFileReader;

public class StateLevelSelect extends State
{
  private PanelLevelSelect panelLevelSelect;

  public StateLevelSelect(Handler handler) throws NumberFormatException, IOException
  {
    super(handler);
    panelLevelSelect = new PanelLevelSelect(handler);
    handler.getFrameMain().add(panelLevelSelect);
    LevelFileReader.dreiApfelEinlesen();
  }

  @Override
  public void update()
  {
 
    
  }

  @Override
  public void paint(Graphics g)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void stateUpdate()
  {
    panelLevelSelect.updateTexture();
    panelLevelSelect.setVisible(true);
    
  }

}
