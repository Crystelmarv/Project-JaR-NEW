package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTree;

public class FrameEditorConfig extends JFrame implements ActionListener
{
  private JLabel labelLevelName, labelZeit;
  private JTextField textFieldLevelName, textFieldZeit;
  private JButton buttonOK;
  
  private String levelName = "";
  private String levelZeit = "";
  
  private PanelEditorLevel panelEditor;
  

  public FrameEditorConfig(PanelEditorLevel panelEditor)
  {
    this.panelEditor = panelEditor;
    
    setSize(500, 700);
    setTitle("JaR Level Editor Config");
    setResizable(false);
    setLocationRelativeTo(null);
    setLayout(null);
    setVisible(false);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    
    labelLevelName = new JLabel("Level Name:");
    textFieldLevelName = new JTextField(20);
    
    labelZeit = new JLabel("Zu schlagende Zeit (z.B. 05:15):");
    textFieldZeit = new JTextField(10);
    
    buttonOK = new JButton("OK");
    
    labelLevelName.setBounds(40, 40, 100, 60);
    textFieldLevelName.setBounds(130, 50, 200, 40);
    labelZeit.setBounds(40, 100, 200, 60);
    textFieldZeit.setBounds(220, 110, 200, 40);
    buttonOK.setBounds(200, 550, 100, 60);
     
    add(labelZeit);
    add(textFieldZeit);
    add(labelLevelName);
    add(textFieldLevelName);
    add(buttonOK);
    
   buttonOK.addActionListener(this);
    
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    levelName = textFieldLevelName.getText();
    levelZeit = textFieldZeit.getText();
    setVisible(false);
    panelEditor.setFocus(true);
    
    
  }
  
  public void setLevelName(String name)
  {
    levelName = name;
  }
  
  public void setTextFieldLevelName(String name)
  {
    textFieldLevelName.setText(name);
  }
  
  public void setLevelZeit(String zeit)
  {
    levelZeit = zeit;
  }
  
  public void setTextFieldLevelZeit(String zeit)
  {
    textFieldZeit.setText(zeit);
  }
  
  public String getLevelName()
  {
    return levelName;
  }
  
  public String getLevelZeit()
  {
    return levelZeit;
  }
  

}
