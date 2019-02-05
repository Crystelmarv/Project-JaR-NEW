package game;

import gui.FrameMain;

public class Kamera
{
  
  private int x, y;
  private int levelAnfangX, levelAnfangY, levelEndeX, levelEndeY;
 
  public Kamera()
  {

    levelAnfangX = 0;
    levelAnfangY = 0;
    
    levelEndeX = LevelCreator.levelID[0].length;
    levelEndeY = LevelCreator.levelID.length;
  }

  
  public float getX()
  {
    return x;
    
  }
  
  public float getY()
  {
    return y;
  }


  public void update(Handler handler)
  {
   
    y = handler.getPlayer().getY()-600;
    x = handler.getPlayer().getX()-600;
    
    if(x < (levelAnfangX +1)* FrameMain.BLOCKBREITE)
    {
      x = FrameMain.BLOCKBREITE;
    }
    
    if(x + FrameMain.FRAMEBREITE > (levelEndeX -1)* FrameMain.BLOCKBREITE)
    {
      x = ((levelEndeX-1) * FrameMain.BLOCKBREITE) - FrameMain.FRAMEBREITE;
    }
    
    if(y < (levelAnfangY +1)* FrameMain.BLOCKHOEHE)
    {
      y = FrameMain.BLOCKHOEHE;
    }
    
    
    if(y + FrameMain.FRAMEHOEHE > (levelEndeY -1)* FrameMain.BLOCKHOEHE)
    {
      y = ((levelEndeY-1) * FrameMain.BLOCKHOEHE) - FrameMain.FRAMEHOEHE;
    }
    
    //this.x = x ;
  //  this.y = y ;
   
  }
  

}
