package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import resManager.Assets;

public class FrameLevelEditorBlockAuswahl extends JFrame implements ActionListener
{
  JButton[] buttons = new JButton[Assets.texturen.length];
  private int ausgewTexture = 2;

  public FrameLevelEditorBlockAuswahl()
  {

    int i, j;
    int x = 20;
    int y = 20;
    int k = 0;

    setSize(310, 800);
    setTitle("JaR Level Editor");
    setResizable(false);
    setLocation(0, 100);
    setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    for (i = 0; i < buttons.length / 3; i++)
    {
      for (j = 0; j < buttons.length / (buttons.length / 3); j++)
      {
        buttons[k] = new JButton();
        buttons[k].setBounds(x, y, 80, 80);
        buttons[k].setIcon(new ImageIcon(Assets.texturen[k]));
        buttons[k].addActionListener(this);
        add(buttons[k]);

        x = x + 90;
        k++;
      }
      x = 20;
      y = y + 90;
    }
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent b)
  {
    int i;

    for (i = 0; i < buttons.length; i++)
    {
      if (buttons[i].equals(b.getSource()))
      {
        ausgewTexture = i;
      }
    }
  }

  public int getTexture()
  {
    return ausgewTexture;
  }

}
