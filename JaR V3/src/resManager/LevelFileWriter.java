package resManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import game.Handler;
import gui.FrameEditorConfig;
import gui.FrameMain;

public class LevelFileWriter
{

  private static String speicherPfad;

  private static Integer levelAnfangX, levelEndeX, levelAnfangY, levelEndeY;

  public LevelFileWriter()
  {

  }

  public static void speichern(Handler handler, FrameEditorConfig frameConfig) throws IOException
  {
    JFileChooser fileChooser = new JFileChooser();

    fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);

    int rueckgabeWert = fileChooser.showSaveDialog(null);

    // wurde speichern gedrückt?
    if (rueckgabeWert == JFileChooser.APPROVE_OPTION)
    {
      // Ausgabe der ausgewaehlten Datei
      speicherPfad = fileChooser.getSelectedFile().getPath();
      writeFileWithStaticPath(handler, frameConfig);
    }
  }

  public static File writeTempFile(Handler handler, FrameEditorConfig frameConfig) throws IOException
  {
    File temp = File.createTempFile("levelTemp", ".txt");
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(temp));

    writeFile(handler, frameConfig, bufferedWriter);

    return temp;

  }

  public static void writeFileWithStaticPath(Handler handler, FrameEditorConfig frameConfig) throws IOException
  {
    FileWriter writer = new FileWriter(speicherPfad + ".txt", false);
    BufferedWriter bufferedWriter = new BufferedWriter(writer);

    writeFile(handler, frameConfig, bufferedWriter);

  }

  public static void writeFile(Handler handler, FrameEditorConfig frameConfig, BufferedWriter bufferedWriter)
  {

    int i, j;
    
    beschneider(handler);
    try
    {

      bufferedWriter.write("LEVEL");
      bufferedWriter.newLine();

      for (i = levelAnfangY; i <=levelEndeY; i++)
      {
        for (j = levelAnfangX; j <= levelEndeX; j++)
        {
          bufferedWriter.write(handler.getPanelLevelEditor().getBlockIds(i, j) + " ");
        }
        bufferedWriter.newLine();
      }

      bufferedWriter.write("NAME");
      bufferedWriter.newLine();
      bufferedWriter.write(frameConfig.getLevelName());

      bufferedWriter.close();
    } catch (IOException e)
    {
      e.printStackTrace();
    }

  
  }

  private static void beschneider(Handler handler)
  {

    // Erste Reihe Testen: Wenn min. ein normaler Block -> LevelAnfangX = 0
    // wenn nicht -> erste Reihe suchen in der nicht alles Luft

    for (int ix = 0; ix < handler.getPanelLevelEditor().getButtonLaengeX(); ix++)
    {
      for (int iy = 0; iy < handler.getPanelLevelEditor().getButtonLaengeY() && levelAnfangX == null; iy++)
      {
        if (Integer.parseInt(handler.getPanelLevelEditor().getBlockIds(iy, ix)) != 20)
        {
          levelAnfangX = ix;
        }
      }
    }

    System.out.println("ANFANG " + levelAnfangX);
    
    // Erste Reihe nach levelAnfangX suchen die ganz mit Luft gefüllt ist
    int y = 0;
    for (int ix = levelAnfangX; ix < handler.getPanelLevelEditor().getButtonLaengeX() && levelEndeX == null; ix++)
    {
      while (y < handler.getPanelLevelEditor().getButtonLaengeY() &&Integer.parseInt(handler.getPanelLevelEditor().getBlockIds(y, ix)) == 20)
         
      {
        if (handler.getPanelLevelEditor().getButtonLaengeY()-1 == y)
        {
          levelEndeX = ix-1;
        }
        y++;
      }
      y = 0;

    }

    System.out.println("Ende" +levelEndeX);
    
    //Jetzt das gleiche mit der Y-Achse
    
    for (int iy = 0; iy < handler.getPanelLevelEditor().getButtonLaengeY(); iy++)
    {
      for (int ix = 0; ix < handler.getPanelLevelEditor().getButtonLaengeX() && levelAnfangY == null; ix++)
      {
        if (Integer.parseInt(handler.getPanelLevelEditor().getBlockIds(iy, ix)) != 20)
        {
          levelAnfangY = iy;
        }
      }
    }

    System.out.println("ANFANG Y " + levelAnfangY);
    
    // Erste Reihe nach levelAnfangX suchen die ganz mit Luft gefüllt ist
    int x = 0;
    for (int iy = levelAnfangY; iy < handler.getPanelLevelEditor().getButtonLaengeY() && levelEndeY == null; iy++)
    {
      while (x < handler.getPanelLevelEditor().getButtonLaengeX() &&Integer.parseInt(handler.getPanelLevelEditor().getBlockIds(iy, x)) == 20)
         
      {
        if (handler.getPanelLevelEditor().getButtonLaengeY()-1 == x)
        {
          levelEndeY = iy-1;
        }
        x++;
      }
      x = 0;

    }

    System.out.println("Ende Y " +levelEndeY);

  }

  public static String getSpeicherPfad()
  {
    return speicherPfad;
  }

  public static void setSpeicherPfad(String speicherPfad)
  {
    LevelFileWriter.speicherPfad = speicherPfad;
  }

}
