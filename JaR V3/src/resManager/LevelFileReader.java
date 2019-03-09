package resManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;

import game.DreiApfelWertung;
import game.Handler;
import game.Level;
import game.LevelCreator;
import gui.FrameEditorConfig;
import gui.FrameMain;

public class LevelFileReader
{
  // level
  private static String levelPfad = "/level/1.Level.txt";

  // Drei Apfel Wertung
  private static String dreiApfelPfad = "/saves/3ApfelWertung.txt";

  // Umrahmung des Levels
  private static Integer anfangX;

  private static Integer endeX;
  private static int levelIDTemp[][];

  // FilePartTrenner
  private static String trennerName = "NAME";
  private static String trennerLevel = "LEVEL";
  private static String trennerSchild = "SCHILD";
  private static String trennerZeit = "ZEIT";
  private static String trennerHighscore = "HIGHSCORE";

  // Reader
  static InputStream inputStream;
  static BufferedReader bufferedReader;

  // Editor Reader
  static InputStream eInputStream;
  static BufferedReader eBufferedReader;

  // Editor
  private static String levelName;

  public LevelFileReader()
  {

  }

  public static void levelDateiLesen(Handler handler) throws IOException
  {

    levelRead(handler);

  }

  public static void readerErstellen() throws IOException
  {

    inputStream = FrameMain.class.getResourceAsStream(levelPfad);
    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

  }

  public static void readerErstellenWithStaticPath() throws FileNotFoundException
  {
    FileReader fileR = new FileReader(levelPfad);
    bufferedReader = new BufferedReader(fileR);
  }

  public static void levelRead(Handler handler) throws IOException
  {
    levelIDTemp = new int[arrayGroesseY(handler)][arrayGroesseX(handler)];
    if (handler.getPanelGame().isLevelEditorTest() == true)
    {
      readerErstellenWithStaticPath();
    } else
    {
      readerErstellen();
    }
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

        levelIDTemp[y][i] = Integer.parseInt(zeile[i]);
      }
      y++;
    }
    inputStream.close();
    bufferedReader.close();
  }

  public static int arrayGroesseX(Handler handler) throws IOException
  {
    if (handler.getPanelGame().isLevelEditorTest() == true)
    {
      readerErstellenWithStaticPath();
    } else
    {
      readerErstellen();
    }

    String currentLine;

    do
    {
      currentLine = bufferedReader.readLine();
    } while (!currentLine.regionMatches(0, trennerLevel, 0, trennerLevel.length()));
    currentLine = bufferedReader.readLine();
    String[] values = currentLine.trim().split(" ");
    bufferedReader.close();
    inputStream.close();
    return values.length;
  }

  public static int arrayGroesseY(Handler handler) throws IOException
  {
    if (handler.getPanelGame().isLevelEditorTest() == true)
    {
      readerErstellenWithStaticPath();
    } else
    {
      readerErstellen();
    }
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
    inputStream.close();
    bufferedReader.close();
    return i;
  }

  // Drei Apfel Wertung //////////

  public static void dreiApfelEinlesen() throws NumberFormatException, IOException
  {
    String currentLine;
    int i = 0;
    int y = 0;

    inputStream = FrameMain.class.getResourceAsStream(dreiApfelPfad);
    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

    while ((currentLine = bufferedReader.readLine()) != null)

    {
      String[] zeile = currentLine.trim().split(" ");

      for (i = 0; i < zeile.length; i++)
      {
        DreiApfelWertung.wertung[y][i] = Integer.parseInt(zeile[i]);

      }
      y++;
    }

    for (int h = 0; h < 3; h++)
    {
      for (int g = 0; g < 3; g++)
      {
        System.out.println(DreiApfelWertung.wertung[h][g]);
      }
    }
    inputStream.close();
    bufferedReader.close();
  }

  // Zeit//////
  public static String zeitEinlesen() throws IOException
  {
    readerErstellen();
    String currentLine;
    String zeit = "";

    do
    {
      currentLine = bufferedReader.readLine();
    } while (!currentLine.regionMatches(0, trennerZeit, 0, trennerZeit.length()));

    currentLine = bufferedReader.readLine();
    bufferedReader.close();
    inputStream.close();
    return currentLine;
  }

  // HIGHSCORE///////////

  public static String highscoreEinlesen(int i) throws IOException
  {
    readerErstellen();
    String currentLine;
    String zeit = "";

    do
    {
      currentLine = bufferedReader.readLine();
    } while (!currentLine.regionMatches(0, trennerHighscore, 0, trennerHighscore.length()));

    for (int j = 1; j < i; j++)
    {
      currentLine = bufferedReader.readLine();
    }
    currentLine = bufferedReader.readLine();
    bufferedReader.close();
    inputStream.close();
    return currentLine;
  }

  // HIGHSCORE/////

  public static void writeHighscore(String highscore1, String highscore2, String highscore3) throws IOException
  {
    BufferedReader br = null;
    FileReader reader = null;
    String currentLine;
    // Open a temporary file to write to.
    // FileWriter writer = new FileWriter(new BufferedWriter(new
    // FileWriter("d:\\book.temp")));
    // FileWriter writer = new FileWriter("res/level/1.LevelTemp.txt", false);
    FileWriter writer = new FileWriter("res/level/1.LevelTemp.txt", false);
    BufferedWriter bufferedWriter = new BufferedWriter(writer);

    //reader = new FileReader(levelPfad);
    //br = new BufferedReader(reader);
    readerErstellen();
    // ... then inside your loop ...

    while ((currentLine = bufferedReader.readLine()) != null)
    {
      if (currentLine.regionMatches(0, trennerHighscore, 0, trennerHighscore.length()))
      {
        bufferedWriter.write(currentLine);
        bufferedWriter.newLine();
        System.out.println(highscore1);
        bufferedWriter.write(highscore1);
        currentLine = bufferedReader.readLine();
        bufferedWriter.newLine();
        bufferedWriter.write(highscore2);
        currentLine = bufferedReader.readLine();
        bufferedWriter.newLine();
        bufferedWriter.write(highscore3);
        currentLine = bufferedReader.readLine();
        bufferedWriter.newLine();

      } else
      {
        System.out.println(currentLine);
        bufferedWriter.write(currentLine);
        bufferedWriter.newLine();
      }

    }
    bufferedWriter.close();
    writer.close();
    
    bufferedReader.close();
    inputStream.close();

    // ... and finally ...

    File realName = new File("res/level/1.Level.txt");
    System.out.println( realName.delete());
    System.out.println( realName.exists());
    // remove the old file
    new File("res/level/1.LevelTemp.txt").renameTo(realName); // Rename temp file
  }

  // EDITOR////////////

  public static void editorReaderErstellen() throws FileNotFoundException
  {
    eInputStream = new FileInputStream(levelPfad);
    eBufferedReader = new BufferedReader(new InputStreamReader(eInputStream));
  }

  public static void levelOeffnenEditor(Handler handler, FrameEditorConfig frameConfig) throws IOException
  {
    JFileChooser fileChooser = new JFileChooser();

    fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);

    int rueckgabeWert = fileChooser.showOpenDialog(null);

    // wurde speichern gedrückt?
    if (rueckgabeWert == JFileChooser.APPROVE_OPTION)
    {
      // Ausgabe der ausgewaehlten Datei
      levelPfad = fileChooser.getSelectedFile().getPath();
      ;
    }
    levelLesenEditor(handler);
    levelNameLaden(frameConfig);
    bufferedReader.close();
    inputStream.close();
  }

  public static void levelLesenEditor(Handler handler) throws IOException
  {
    editorReaderErstellen();

    String currentLine;
    int i;
    int y = 0;

    do
    {
      currentLine = eBufferedReader.readLine();
    } while (!currentLine.regionMatches(0, trennerLevel, 0, trennerLevel.length()));

    while ((currentLine = eBufferedReader.readLine()) != null
        && !currentLine.regionMatches(0, trennerName, 0, trennerName.length())
        && !currentLine.regionMatches(0, trennerSchild, 0, trennerSchild.length()))
    {
      String[] zeile = currentLine.trim().split(" ");

      for (i = 0; i < zeile.length; i++)
      {
        handler.getPanelLevelEditor().setBlockIds(y, i, zeile[i]);
      }
      y++;
    }
    handler.getPanelLevelEditor().oeffnenButtonsUpdate();
    bufferedReader.close();
    inputStream.close();
  }

  public static void levelNameLaden(FrameEditorConfig frameConfig) throws IOException
  {
    editorReaderErstellen();
    String currentLine;

    do
    {
      currentLine = eBufferedReader.readLine();

    } while (!currentLine.regionMatches(0, trennerName, 0, trennerName.length()));

    levelName = eBufferedReader.readLine();

    frameConfig.setTextFieldLevelName(levelName);

    frameConfig.setLevelName(levelName);
    
    do
    {
      currentLine = eBufferedReader.readLine();
    } while (!currentLine.regionMatches(0, trennerZeit, 0, trennerZeit.length()));

    
    currentLine = eBufferedReader.readLine();
    System.out.println(currentLine);
    
    frameConfig.setTextFieldLevelZeit(currentLine);
    frameConfig.setLevelZeit(currentLine);
    
    bufferedReader.close();
    inputStream.close();

  }
  
 

  public static void setLevelPfad(String levelP)
  {
    levelPfad = levelP;
  }

}
