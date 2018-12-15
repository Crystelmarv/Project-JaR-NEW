package resManager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;

import game.Handler;
import game.Level;
import game.LevelCreator;
import gui.FrameEditorConfig;
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
  
  //Editor Reader
  static InputStream eInputStream;
  static BufferedReader eBufferedReader;
  
  //Editor
  private static String levelName;

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
  
  //EDITOR////////////
  
  public static void editorReaderErstellen() throws FileNotFoundException
  {
    eInputStream = new FileInputStream(levelPfad);
    eBufferedReader  = new BufferedReader(new InputStreamReader(eInputStream));
  }
  
  public static void levelOeffnenEditor(Handler handler, FrameEditorConfig frameConfig) throws IOException
  {
 JFileChooser fileChooser = new JFileChooser();
    
    fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
  
    int rueckgabeWert = fileChooser.showOpenDialog(null);
    
    //wurde speichern gedrückt?
    if(rueckgabeWert == JFileChooser.APPROVE_OPTION)
    {
         // Ausgabe der ausgewaehlten Datei
      levelPfad = fileChooser.getSelectedFile().getPath();
;
    }
    levelLesenEditor(handler);
    levelNameLaden(frameConfig);
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
  }
  
  public static void levelNameLaden(FrameEditorConfig frameConfig) throws IOException
  {
editorReaderErstellen();
    String currentLine;
   
    do
    {
      currentLine = eBufferedReader.readLine();
      System.out.println(currentLine);
    
    } while (!currentLine.regionMatches(0, trennerName, 0, trennerName.length()));
    
    levelName = eBufferedReader.readLine();
   
    frameConfig.setTextFieldLevelName(levelName);
    
    frameConfig.setLevelName(levelName);
    
  }
  
  public static void setLevelPfad(String levelP)
  {
    levelPfad = levelP;
  }

  

}
