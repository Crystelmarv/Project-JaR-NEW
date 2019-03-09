package resManager;

import java.awt.image.BufferedImage;

public class Animation
{
  private int speed, index;
  private long lastTime, timer;
  private BufferedImage[] frames;

  public Animation(int speed, BufferedImage[] frames)
  {
   this.speed = speed;
   this.frames = frames;
   index = 0;
   timer = 0;
   lastTime = Timer.getTime();
  }
  
  public boolean update()
  {
    timer += Timer.getTime() - lastTime;
    lastTime = Timer.getTime();
    
    if(timer > speed)
    {
      index++;
      timer = 0;
      if(index >= frames.length)
      {
        index = 0;
        return true;
      }
    }
    return false;
  }
  
  public BufferedImage getFrame()
  {
    return frames[index];
  }

  public int getIndex()
  {
    return index;
  }

  public void setIndex(int index)
  {
    this.index = index;
  }
  
  
  
  
  
  

}
