package states;

import java.awt.Graphics;

import game.Handler;
import gui.PanelEditorLevel;

public class StateEditor extends State
{
  private PanelEditorLevel panelEditorLevel;
  private boolean frameErstellt = false;

  public StateEditor(Handler handler)
  {
    super(handler);
    panelEditorLevel = new PanelEditorLevel(handler);
    handler.getFrameMain().add(panelEditorLevel);
  }

  @Override
  public void update()
  {
    if(frameErstellt == true)
    {
      panelEditorLevel.update();
    }
  
  
  }

  @Override
  public void paint(Graphics g)
  {
    panelEditorLevel.paint(g);
    
  }

  @Override
  public void stateUpdate()
  {
    panelEditorLevel.setVisible(true);
    panelEditorLevel.PanelErstellen();
    frameErstellt = true;
   
    
  }

}
