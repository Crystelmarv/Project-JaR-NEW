package game;

import gui.FrameMain;

public class Kamera
{
  
  private float x, y;
  private boolean anfang = true;
  private boolean ende = false;

  public Kamera(float x, float y)
  {
    this.x = x;
    this.y = y;
  }

  
  public float getX()
  {
    return x;
    
  }
  
  public float getY()
  {
    return y;
  }


  public void update(Player player)
  {
    if(anfang == true)
    {
      anfang(player);
    }
    else
    {
      x = player.getX()-600;
     
    }
  
    
  }
  
  private void anfang(Player player)
  {
    
    x = player.getX()-600;
    if(x < FrameMain.BLOCKBREITE)
    {
      x = FrameMain.BLOCKBREITE;
    }
    
    if(x > ((300 * FrameMain.BLOCKBREITE) + 600))
    {
      x = (300 * FrameMain.BLOCKBREITE) - 600;
          
    }
    y = player.getY()-600;
  }
}
