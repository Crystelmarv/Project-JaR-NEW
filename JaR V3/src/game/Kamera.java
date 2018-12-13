package game;

public class Kamera
{
  
  private float x, y;

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
  
    x = player.getX()-600;
    y = player.getY()-600;
    
  }
}
