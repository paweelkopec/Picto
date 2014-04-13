import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public class PaintImage extends JPanel
{
  public static BufferedImage image;
    public static BufferedImage image2;
  public PaintImage ()
  {
    super();
    try 
    {                
      image = ImageIO.read(new File("C:\\Users\\Paweł\\Desktop\\praca dyp\\pitogram.jpg"));
      
       image2 = ImageIO.read(new File("C:\\Users\\Paweł\\Desktop\\praca dyp\\pitogram.jpg"));
    } 
    catch (IOException e)
    {
      //Not handled.
    }
  }

  public void paintComponent(Graphics g) 
  {
    g.drawImage(image, 0, 0, null);
   
    g.drawImage(image2, 0, 0, null);
    repaint();
  }

  public static void main(String [] args)
  {
    JFrame f = new JFrame("Window");
    f.getContentPane().add(new PaintImage());
    f.setSize(image.getWidth(), image.getHeight() + 30);
    f.setVisible(true);
  }
}
