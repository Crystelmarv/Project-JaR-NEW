package game;

import java.io.IOException;

import gui.PanelLevelSelect;
import resManager.LevelFileReader;

public class DreiApfelWertung
{
  public static int wertung[][] = new int[PanelLevelSelect.getAnzahllevel()][PanelLevelSelect.getAnzahllevel()];

  public static int aktuellesLevel = 1;

  // HIGHSCORE
  static String highScore1;
  static String highScore2;
  static String highScore3;

  private static String zeit = "";

  public static void levelGechafft()
  {
    wertung[aktuellesLevel][0] = 1;
    for (int h = 0; h < 3; h++)
    {
      for (int g = 0; g < 3; g++)
      {
        System.out.println(DreiApfelWertung.wertung[h][g]);
      }
    }
  }

  public static void alleAepfel()
  {
    wertung[aktuellesLevel][1] = 1;
    for (int h = 0; h < 3; h++)
    {
      for (int g = 0; g < 3; g++)
      {
        System.out.println(DreiApfelWertung.wertung[h][g]);
      }
    }
  }

  public static void zeitGeschafft(String z)
  {
    String zeit = "";
    String tem1;
    String tem2;
    String geschaffteZeit;
    int zeitMin = 0, zeitSek = 0, geschafftMin = 0, geschafftSek = 0;
    geschaffteZeit = z;
    try
    {
      zeit = LevelFileReader.zeitEinlesen();

    } catch (IOException e)
    {
      e.printStackTrace();
    }

    // Zeti
    tem1 = zeit.substring(0, 2);
    tem2 = zeit.substring(3, 5);

    zeitMin = Integer.parseInt(tem1);
    zeitSek = Integer.parseInt(tem2);

    // Zeit geschafft
    System.out.println(geschaffteZeit);
    tem1 = geschaffteZeit.substring(0, 2);
    tem2 = geschaffteZeit.substring(2, 4);

    geschafftMin = Integer.parseInt(tem1);
    geschafftSek = Integer.parseInt(tem2);
    if (geschafftSek == 60)
    {
      geschafftSek = 0;
    }
    System.out.println(zeitMin + ":" + zeitSek);
    System.out.println(geschafftMin + ":" + geschafftSek);

    if (zeitMin >= geschafftMin)
    {
      if (zeitMin == geschafftMin)
      {
        if (zeitSek >= geschafftSek)
        {
          wertung[aktuellesLevel][2] = 1;
        }

      } else
      {
        wertung[aktuellesLevel][2] = 1;
      }
    }
  }

  private static void highscoreEinlesen(int i)
  {
    try
    {
      zeit = LevelFileReader.highscoreEinlesen(i);
      switch (i)
      {
      case 1:
        highScore1 = zeit;
        break;
      case 2:
        highScore2 = zeit;
        break;
      case 3:
        highScore3 = zeit;
        break;

      }
    } catch (IOException e)
    {
      //
      e.printStackTrace();
    }
  }

  public static void highscoreVergleich(String z)
  {

    String tem1;
    String tem2;
    String geschaffteZeit;
    String zeitMitDoppelt;

    boolean abbruch = false;
    int i = 3;
    int zeitMin = 0, zeitSek = 0, geschafftMin = 0, geschafftSek = 0;
    geschaffteZeit = z;

    highscoreEinlesen(3);
    highscoreEinlesen(2);
    highscoreEinlesen(1);

    System.out.println("1: " + highScore1);
    System.out.println("2: " + highScore2);
    System.out.println("3: " + highScore3);
    /*
     * tem1 = geschaffteZeit.substring(0, 2); tem2 = geschaffteZeit.substring(2, 4);
     * zeitMitDoppelt = tem1 + ":" + tem2;
     * 
     * if (highScore1.equals("00:00")) { highscoreAnpassen(1, zeitMitDoppelt);
     * abbruch = true; } else if (highScore2.equals("00:00")) { highscoreAnpassen(2,
     * zeitMitDoppelt); abbruch = true; } else if (highScore3.equals("00:00")) {
     * highscoreAnpassen(3, zeitMitDoppelt); abbruch = true;
     * 
     * }
     */
    while (abbruch == false)
    {
      switch (i)
      {
      case 3:
        highscoreEinlesen(i);
        break;
      case 2:
        highscoreEinlesen(i);
        break;

      case 1:
        highscoreEinlesen(i);
        break;

      }

      // Zeit
      tem1 = zeit.substring(0, 2);
      tem2 = zeit.substring(3, 5);

      zeitMin = Integer.parseInt(tem1);
      zeitSek = Integer.parseInt(tem2);

      // Zeit geschafft
      System.out.println(geschaffteZeit);
      tem1 = geschaffteZeit.substring(0, 2);
      tem2 = geschaffteZeit.substring(2, 4);

      geschafftMin = Integer.parseInt(tem1);
      geschafftSek = Integer.parseInt(tem2);
      if (geschafftSek == 60)
      {
        geschafftSek = 0;
      }
      System.out.println("high zum vergleich " + zeitMin + ":" + zeitSek);
      System.out.println("geschafft zeitz" + geschafftMin + ":" + geschafftSek);

      zeitMitDoppelt = tem1 + ":" + tem2;
      if (zeitMin >= geschafftMin)
      {
        if (zeitMin == geschafftMin)
        {
          if (zeitSek >= geschafftSek)
          {
            // Besser als i Highscore
            highscoreAnpassen(i, zeitMitDoppelt);
            System.out.println("besser als " + i);
          } else
          {
            // schlechter
            System.out.println("schlechter als " + i + "seks");
            abbruch = true;
          }

        } else
        {
          // Besser als i Highscore
          highscoreAnpassen(i, zeitMitDoppelt);
          System.out.println("besser als " + i);
        }
      } else
      {
        // schlechter
        System.out.println("schlechter als " + i + "mins");
        abbruch = true;
      }

      i--;
      if (i == 0)
      {
        abbruch = true;
      }
    }
    try
    {
      LevelFileReader.writeHighscore(highScore1, highScore2, highScore3);
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private static void highscoreAnpassen(int i, String zeit)
  {

    switch (i)
    {
    case 1:
      highScore2 = highScore1;
      highScore1 = zeit;
      break;
    case 2:
      highScore3 = highScore2;
      highScore2 = zeit;

      break;

    case 3:
      highScore3 = zeit;
      break;

    }

    System.out.println("1. " + highScore1);
    System.out.println("2. " + highScore2);
    System.out.println("3. " + highScore3);
  }
}
