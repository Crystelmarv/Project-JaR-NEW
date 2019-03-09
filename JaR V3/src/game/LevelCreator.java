package game;

import java.awt.Graphics;
import java.io.IOException;

import blocke.Block;
import blocke.BlockBaumStamm;
import blocke.BlockItem;
import blocke.BlockLeiter;
import blocke.BlockOneTouch;
import blocke.BlockSchild;
import blocke.BlockTrampolin;
import blocke.BlockZerstoerbar;
import blocke.GegnerSpawn;
import blocke.LevelCheckpoint;
import blocke.LevelSpawn;
import blocke.LevelTod;
import blocke.LevelZiel;
import gui.FrameMain;
import resManager.LevelFileReader;

public class LevelCreator
{
  public static int levelID[][];
  public Entity levelObjects[][];

  private Handler handler;

  // Counter mögliche Äpfel
  private int moeglicheAepfel = 0;

  // Player Spawn Koordinaten
  private int playerSpawnY, playerSpawnX;
  
 

  public LevelCreator(Handler handler) throws IOException
  {
    this.handler = handler;
    setGroesseLevel(LevelFileReader.arrayGroesseY(handler), LevelFileReader.arrayGroesseX(handler));
   
  }

  public void levelErstellen()
  {
    int iy, ix;
    int x = 0;
    int y = 0;

    for (iy = 0; iy < levelID.length; iy++)
    {
      for (ix = 0; ix < levelID[0].length; ix++)
      {
        switch (levelID[iy][ix])
        {
        // normale Blöcke
        case 20:
        case 21:
        case 30:
        case 31:
        case 33:
        case 34:
        case 22:
        case 53:
        
          levelObjects[iy][ix] = new Block(x, y, levelID[iy][ix], handler);
          
          if (levelID[iy][ix] == 53)
          {
            moeglicheAepfel++;
          }
          break;

        // Schild
        case 19:
          // levelObjects[iy][ix] = new BlockSchild(x, y, levelID[iy][ix]);
          break;
        // Block Item
        case 10:
        case 51:
        case 52:
        case 54:
        case 55:
           levelObjects[iy][ix] = new BlockItem(x, y, levelID[iy][ix], handler);

          if (levelID[iy][ix] == 52)
          {
            moeglicheAepfel++;
          }
          break;
        // Level Spawn
        case 15:
          levelObjects[iy][ix] = new LevelSpawn(x, y, levelID[iy][ix], handler);
          playerSpawnY = y;
          playerSpawnX = x;
          break;
        // Level Ziel
        case 16:
          levelObjects[iy][ix] = new LevelZiel(x, y, levelID[iy][ix], handler);
          break;
        // LevelCheckpoint
        case 17:
           levelObjects[iy][ix] = new LevelCheckpoint(x, y, levelID[iy][ix], handler);
          break;
        // Level Tod
        case 18:
          levelObjects[iy][ix] = new LevelTod(x, y, levelID[iy][ix], handler);
          break;
          
          //Block Zerstörbar
        case 32:
          levelObjects[iy][ix] = new BlockZerstoerbar(x, y, levelID[iy][ix], handler);
         break;

        // Gegner Spawn
        case 70:
        case 71:
        case 72:
        case 73:
        case 74:
        case 75:
          
         
           levelObjects[iy][ix] = new GegnerSpawn(x, y, levelID[iy][ix], handler);
           
          break;
          
          //Trampolin
        case 35:
          levelObjects[iy][ix] = new BlockTrampolin(x, y, levelID[iy][ix], handler);
         break;
         
         //BaumStamm
        case 36:
          levelObjects[iy][ix] = new BlockBaumStamm(x, y, levelID[iy][ix], handler);
         break;
         //One Touch
        case 38:
          levelObjects[iy][ix] = new BlockOneTouch(x, y, levelID[iy][ix], handler);
          break;

          //Leiter
        case 39:
          levelObjects[iy][ix] = new BlockLeiter(x, y, levelID[iy][ix], handler);
          break;

        default:
          break;
        }
        x = x + FrameMain.BLOCKBREITE;
      }
      x = 0;
      y = y + FrameMain.BLOCKHOEHE;
    }
    
  }

  public int getPlayerSpawnY()
  {
    return playerSpawnY;
  }

  public int getPlayerSpawnX()
  {
    return playerSpawnX;
  }

  public void setGroesseLevel(int y, int x)
  {
    levelID = new int[y][x];
    levelObjects = new Entity[y][x];

  }

  public int getMoeglicheAepfel()
  {
    return moeglicheAepfel;
  }
  

}
