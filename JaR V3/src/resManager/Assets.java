package resManager;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Assets
{
  static SpriteSheet sheet, sheet_Buttons, sheet_Animation, sheet_Baum;

  private static int breite = 64;
  private static int hoehe = 64;
  
  private static int breiteB = 90;
  private static int hoeheB = 90;

  public static BufferedImage[] texturen = new BufferedImage[24];

  public static BufferedImage gras, erde, blauerBlock, orangerBlock, schild, orangerBlockOhneKreuz, apfel, 
                              marienKaeferLinks, marienKaeferRechts, checkPointUnten, checkPointObenAus, 
                              checkPointObenAn, bieneLinks, bieneRechts, wasser, seerose, devBlock, herz,
                              fischOben, fischUnten, tod, spawn, ziel, weiss, blau, itemFeuerKugel, itemFeuer,
                              itemSchuh, itemLebenPlus, anzeigeAktuellesItem,
                              eichhoernchenRechts, eichhoernchenLinks, nuss, stern, baum, ast, haus, oneTouch, wildschweinRechts, wildschweinLinks,
                              //Buttons
                              buttonLevelSelect000, buttonLevelSelect100, buttonLevelSelect101, buttonLevelSelect110, buttonLevelSelect111;
                              
  //Trampolin Animation
  public static BufferedImage[] trampolinARunter = new BufferedImage[8];
  public static BufferedImage[] trampolinAHoch = new BufferedImage[8];
  
  //Baum
  public static BufferedImage baumS;
      
  public void init()
  {
    try
    {
      sheet = new SpriteSheet(ImageIO.read(getClass().getResource("/textures/TilesSheet.png")));
      sheet_Buttons = new SpriteSheet(ImageIO.read(getClass().getResource("/textures/TileSheet_Buttons.png")));
      sheet_Animation = new SpriteSheet(ImageIO.read(getClass().getResource("/textures/TileSheet_Animation.png")));
      sheet_Baum = new SpriteSheet(ImageIO.read(getClass().getResource("/textures/Baum.png")));
    } catch (IOException e)
    {
      e.printStackTrace();
      System.exit(1);
    }

    gras = sheet.crop(0, 0, breite, hoehe);
    erde = sheet.crop(breite, 0, breite, hoehe);
    blauerBlock = sheet.crop(breite * 2, 0, breite, hoehe);
    orangerBlock = sheet.crop(breite * 3, 0, breite, hoehe);
    schild = sheet.crop(breite * 4, 0, breite, hoehe);
    orangerBlockOhneKreuz = sheet.crop(0, hoehe, breite, hoehe);
    apfel = sheet.crop(breite, hoehe, breite, hoehe);
    marienKaeferLinks = sheet.crop(breite*2, hoehe, breite, hoehe);
    marienKaeferRechts = sheet.crop(breite*3, hoehe, breite, hoehe);
    checkPointObenAn = sheet.crop(breite*4, hoehe, breite, hoehe);
    checkPointObenAus = sheet.crop(breite*4, hoehe*2, breite, hoehe);
    checkPointUnten = sheet.crop(breite*4, hoehe*3, breite, hoehe);
    bieneLinks = sheet.crop(breite*3, hoehe*2, breite, hoehe);
    bieneRechts = sheet.crop(breite*2, hoehe*2, breite, hoehe);
    wasser  = sheet.crop(breite, hoehe*2, breite, hoehe);
    seerose  = sheet.crop(0, hoehe*2, breite, hoehe);
    devBlock  = sheet.crop(0, hoehe*3, breite, hoehe);
    herz  = sheet.crop(breite, hoehe*3, breite, hoehe);
    fischOben = sheet.crop(breite*2, hoehe*3, breite, hoehe);
    fischUnten = sheet.crop(breite*3, hoehe*3, breite, hoehe);
    tod = sheet.crop(0, hoehe*4, breite, hoehe);
    spawn = sheet.crop(breite, hoehe*4, breite, hoehe);
    ziel = sheet.crop(breite*2, hoehe*4, breite, hoehe);
    weiss = sheet.crop(breite*3, hoehe*4, breite, hoehe);
    blau = sheet.crop(breite*4, hoehe*4, breite, hoehe);
    itemFeuerKugel = sheet.crop(breite*5, 0, breite, hoehe);
    itemFeuer = sheet.crop(breite*6, 0, breite, hoehe);
    itemSchuh = sheet.crop(breite*7, 0, breite, hoehe);
    itemLebenPlus = sheet.crop(breite*8, 0, breite, hoehe);
    anzeigeAktuellesItem = sheet.crop(breite*5, hoehe, breite, hoehe);
    eichhoernchenRechts = sheet.crop(breite*5, hoehe*3, breite, hoehe);
    eichhoernchenLinks = sheet.crop(breite*5, hoehe*2, breite, hoehe);
    stern = sheet.crop(breite*2, hoehe*5, breite, hoehe);
    nuss = sheet.crop(breite*5, hoehe*4, breite, hoehe);
    baum = sheet.crop(breite*7, hoehe, breite*3, hoehe*4);
    ast = sheet.crop(0, hoehe*5, breite*2, hoehe);
    haus = sheet.crop(breite*6, hoehe*6, breite*4, hoehe*4);
    oneTouch = sheet.crop(breite*9, 0, breite, hoehe);
    wildschweinLinks = sheet.crop(breite*2, hoehe*5, breite*2, hoehe);
    wildschweinRechts = sheet.crop(breite*4, hoehe*5, breite*2, hoehe);
    
    //Baum
    baumS =  sheet_Baum.crop(0, 0, 900, 900);
    
    //Buttons LevelSelect
    buttonLevelSelect000 = sheet_Buttons.crop(0, 0, breiteB, hoeheB);
    buttonLevelSelect100 = sheet_Buttons.crop(breiteB, 0, breiteB, hoeheB);
    buttonLevelSelect101 = sheet_Buttons.crop(breiteB*2, 0, breiteB, hoeheB);
    buttonLevelSelect110 = sheet_Buttons.crop(breiteB*3, 0, breiteB, hoeheB);
    buttonLevelSelect111 = sheet_Buttons.crop(breiteB*4, 0, breiteB, hoeheB);
    
    //Trampolin Animation
    
    for(int i = 0; i < 8; i++)
    {
      trampolinARunter[i] = sheet_Animation.crop(breite * i, 0, breite, hoehe);
    }
    
    int j = 0;
    for(int i = 7; i >= 0; i--)
    {
      trampolinAHoch[j] = sheet_Animation.crop(breite * i, 0, breite, hoehe);
      j++;
    }
    
    
    

    texturen[0] = gras;
    texturen[1] = erde;
    texturen[2] = checkPointObenAn;
    texturen[3] = orangerBlock;
    texturen[4] = orangerBlockOhneKreuz;
    texturen[5] = wildschweinLinks;
    texturen[6] = devBlock;
    texturen[7] = wasser;
    texturen[8] = seerose;
    texturen[9] = apfel;
    texturen[10] = marienKaeferLinks;
    texturen[11] = bieneLinks;
    texturen[12] = fischOben;
    texturen[13] = tod;
    texturen[14] = spawn;
    texturen[15] = ziel;
    texturen[16] = weiss;
    texturen[17] = blau;
    texturen[18] = itemFeuer;
    texturen[19] = itemSchuh;
    texturen[20] = itemLebenPlus;
    texturen[21] = trampolinARunter[0];
    texturen[22] = oneTouch;
    texturen[23] = eichhoernchenLinks;
  
   
    
    

    

  }

  public static String getID(int texture)
  {
    String id = "";
    switch (texture)
    {
    case 0:
      id = "30";
      break;
    case 1:
      id = "31";
      break;
    case 2:
      id = "17";
      break;
    case 3:
      id = "52";
      break;
    case 4:
      id = "32";
      break;
    case 5:
      id = "75";
      break;
    case 6:
      id = "22";
      break;
    case 7:
      id = "33";
      break;
    case 8:
      id = "34";
      break;
    case 9:
      id = "53";
      break;
    case 10:
      id = "71";
      break;
    case 11:
      id = "72";
      break;
    case 12:
      id = "73";
      break;
    case 13:
      id = "18";
      break;
    case 14:
      id = "15";
      break;
    case 15:
      id = "16";
      break;
    case 16:
      id = "20";
      break;
    case 17:
      id = "21";
      break;
    case 18:
      id = "54";
      break;
    case 19:
      id = "55";
      break;
    case 20:
      id = "51";
      break;
    case 21:
      id = "35";
      break;
    case 22:
      id = "38";
      break;
    case 23:
      id = "74";
      break;

 
      
      
      
      
      
     

    }
    return id;

  }

}
