package game;



import java.awt.Graphics;
import java.io.IOException;

import resManager.LevelFileReader;
import resManager.Timer;

public class Stoppuhr
{
  private int x = 1200;
  private int y = 80;
  private float startZeit, zeit;
  private int sek; 
  private float mili;
  private int min = 0;
  String sekS, minS = "00";
  char ds, dsh;
 
  
  public Stoppuhr()
  {    
    startZeit = Timer.getTime();
  }
    
  
  public void paint(Graphics g)
  { 
    zeit = Timer.getTime()-startZeit;
    zeit = zeit /1000000000;
    
    sek = (int)zeit;
    sekS = String.valueOf(sek);
    
    if(sek < 10)
    {
      sekS = "0"+sekS;
    }
    if(sek >= 60)
    {
      startZeit = Timer.getTime();
      min++;
      minS = String.valueOf(min);
      if(min < 10)
      {
        minS = "0"+minS;
      }
    }
  
    
    //g.drawString(String.format("%1,2f", zeit),  x, y);
    try
    {
      g.drawString(minS + ":" + sekS + "/" + LevelFileReader.zeitEinlesen(),  x, y);
    } catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
//    System.out.println((String.format("%tM", zeit)));
   // System.out.println(zeit);
  //  System.out.println(min + " : " + sek);
    
  }
  public String getStoppUhrTime()
  {
    String zeit;
    
    zeit = minS+sekS;
    return zeit;
  }
 
  

}
  
  


