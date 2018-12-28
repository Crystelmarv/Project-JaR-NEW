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

  
  public LevelFileWriter()
  {
   
  }

  public static void speichern(Handler handler,  FrameEditorConfig frameConfig) throws IOException
  {
    JFileChooser fileChooser = new JFileChooser();
    
    fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
  
    int rueckgabeWert = fileChooser.showSaveDialog(null);
    
    //wurde speichern gedrückt?
    if(rueckgabeWert == JFileChooser.APPROVE_OPTION)
    {
         // Ausgabe der ausgewaehlten Datei
      speicherPfad = fileChooser.getSelectedFile().getPath();
      System.out.println(speicherPfad);
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
    try
    {
     

      bufferedWriter.write("LEVEL");
      bufferedWriter.newLine();
      
      for (i = 0; i < handler.getPanelLevelEditor().getButtonLaenge1(); i++)
      {
        for (j = 0; j < handler.getPanelLevelEditor().getButtonLaenge2(); j++)
        {
          bufferedWriter.write( handler.getPanelLevelEditor().getBlockIds(i, j)+ " ");
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

  public static String getSpeicherPfad()
  {
    return speicherPfad;
  }

  public static void setSpeicherPfad(String speicherPfad)
  {
    LevelFileWriter.speicherPfad = speicherPfad;
  }

 

}
