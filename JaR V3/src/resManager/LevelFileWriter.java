package resManager;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import game.Handler;
import gui.FrameEditorConfig;



public class LevelFileWriter
{

  static String speicherPfad;
  
  public LevelFileWriter()
  {
   
  }

  public static void speichern(Handler handler,  FrameEditorConfig frameConfig)
  {
    JFileChooser fileChooser = new JFileChooser();
    
    fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
  
    int rueckgabeWert = fileChooser.showSaveDialog(null);
    
    //wurde speichern gedrückt?
    if(rueckgabeWert == JFileChooser.APPROVE_OPTION)
    {
         // Ausgabe der ausgewaehlten Datei
      speicherPfad = fileChooser.getSelectedFile().getPath();
      writeFile(handler, frameConfig);
    }
  }
  public static void writeFile(Handler handler, FrameEditorConfig frameConfig)
  {
   
    int i, j;
    try
    {
      FileWriter writer = new FileWriter(speicherPfad + ".txt", false);
      BufferedWriter bufferedWriter = new BufferedWriter(writer);

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

}
