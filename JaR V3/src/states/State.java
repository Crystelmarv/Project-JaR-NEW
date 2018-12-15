package states;

import java.awt.Graphics;
import java.io.IOException;

import game.Handler;

public abstract class State
{
  protected Handler handler;
  
  public State(Handler handler)
  {
    this.handler = handler;
  }

  public abstract void update() throws IOException;
  
  public abstract void paint(Graphics g);
  
  public abstract void stateUpdate();

 
  
}
