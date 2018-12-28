package resManager;

public class Timer
{
  private static long timeVorPause, timeNachPause, pauseTime;
  
  public static long getTime()
  {
   
    return System.nanoTime() - pauseTime ;
  }



  public static void setTimeVorPause(long timeVorPause)
  {
    Timer.timeVorPause = timeVorPause;
  }

  public static void setTimeNachPause(long timeNachPause)
  {
    Timer.timeNachPause = timeNachPause;
  }
  
  public static void setPauseTime()
  {
    pauseTime = pauseTime + (timeNachPause-timeVorPause);
  }
  
  
  
  
  
 
  


}
