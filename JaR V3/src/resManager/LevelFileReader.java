package resManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import game.Level;
import game.LevelCreator;
import gui.FrameMain;

public class LevelFileReader
{
  // level
  private static String levelPfad = "/level/1.Level.txt";

  // FilePartTrenner
  private static String trennerName = "NAME";
  private static String trennerLevel = "LEVEL";
  private static String trennerSchild = "SCHILD";

  // Reader
  static InputStream inputStream;
  static BufferedReader bufferedReader;

  public LevelFileReader()
  {

  }

  public static void levelDateiLesen() throws IOException
  {

 
    levelRead();

  }

  public static void readerErstellen() throws IOException
  {
    inputStream = FrameMain.class.getResourceAsStream(levelPfad);
    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

    // System.out.println(bufferedReader.readLine());
  }

  public static void levelRead() throws IOException
  {
    readerErstellen();
    String currentLine;
    int i;
    int y = 0;

    do
    {
      currentLine = bufferedReader.readLine();
    } while (!currentLine.regionMatches(0, trennerLevel, 0, trennerLevel.length()));

    while ((currentLine = bufferedReader.readLine()) != null
        && !currentLine.regionMatches(0, trennerName, 0, trennerName.length())
        && !currentLine.regionMatches(0, trennerSchild, 0, trennerSchild.length()))
    {
      String[] zeile = currentLine.trim().split(" ");

      for (i = 0; i < zeile.length; i++)
      {
        LevelCreator.levelID[y][i] = Integer.parseInt(zeile[i]);
      }
      y++;
    }
  }

  public static int arrayGroesseX() throws IOException
  {
    readerErstellen();
    String currentLine;

    do
    {
      currentLine = bufferedReader.readLine();
    } while (!currentLine.regionMatches(0, trennerLevel, 0, trennerLevel.length()));
    currentLine = bufferedReader.readLine();
    String[] values = currentLine.trim().split(" ");
    return values.length;
  }

  public static int arrayGroesseY() throws IOException
  {
    readerErstellen();
    String currentLine;
    int i = 0;

    do
    {
      currentLine = bufferedReader.readLine();
    } while (!currentLine.regionMatches(0, trennerLevel, 0, trennerLevel.length()));
    while ((currentLine = bufferedReader.readLine()) != null
        && !currentLine.regionMatches(0, trennerName, 0, trennerName.length())
        && !currentLine.regionMatches(0, trennerSchild, 0, trennerSchild.length()))
    {
      i++;
    }
    return i;
  }

  

}
