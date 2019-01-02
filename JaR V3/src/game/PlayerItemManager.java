package game;

import items.Item;
import items.ItemFeuer;
import items.ItemFeuerKugel;
import items.ItemGeschwindigkeit;
import resManager.Tastatur;
import resManager.Timer;

public class PlayerItemManager
{
  private Handler handler;
  
  private String aktivesItem = "null";
  private Item akItem;
  private boolean timerGesetztItem = false;
  private double anfangsTimeItem;
  private Item item;
  

  public PlayerItemManager(Handler handler)
  {
   this.handler = handler;
  }
  
  public void update()
  {
    item();
  }
  
  public void item()
  {
   
    if (Tastatur.isUseItem() == true)
    {
      if(aktivesItem == "geschwindigkeit")
      {
        handler.getPlayer().setSpeed(10F);
      }
      
      else if (timerGesetztItem == false)
      {
        anfangsTimeItem = Timer.getTime() ;
        timerGesetztItem = true;

        switch (aktivesItem)
        {
        case "none":

          break;

        case "feuer":

          akItem = new ItemFeuerKugel(handler.getPlayer().getX(), handler.getPlayer().getY(), 0, handler, handler.getPlayer().isLastPositionRight());

          handler.getLevel().setEntity(akItem);
          break;
        
        }

      } else
      {

        if (Timer.getTime() - anfangsTimeItem > 500000000)
        {
          timerGesetztItem = false;

        }
      }
    }
    else if(aktivesItem == "geschwindigkeit")
    {
      handler.getPlayer().setSpeed(handler.getPlayer().getDEFAULT_SPEED());
    }
    
    

  }

  public void setAktivesItem(String aktivesItem)
  {
    dropAltesItem();
    this.aktivesItem = aktivesItem;
  }

  private void dropAltesItem()
  {
    
    
   if(aktivesItem != "null")
   {
     switch (aktivesItem)
    {
    case "feuer":
      item = new ItemFeuer(handler.getPlayer().getX()-128, handler.getPlayer().getY(), 0, handler);
      
      
      break;
    case "geschwindigkeit":
      handler.getPlayer().setSpeed(handler.getPlayer().getDEFAULT_SPEED());
      item = new ItemGeschwindigkeit(handler.getPlayer().getX()-128, handler.getPlayer().getY(), 0, handler);
      break;
      

    }
     handler.getLevel().setEntity(item);
   }
    
  }

  public String getAktivesItem()
  {
    return aktivesItem;
  }
  

}
