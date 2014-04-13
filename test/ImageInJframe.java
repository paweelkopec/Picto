
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ImageInJframe extends JFrame
{
public static void main(String args[])
{
   ImageInJframe I = new ImageInJframe();
I.start();

}
 
public void start()
{
    ImageImplement panel = new ImageImplement(new ImageIcon("C:\\Users\\Paweł\\Desktop\\praca dyp\\pitogram.jpg").getImage());
      ImageImplement panel2 = new ImageImplement(new ImageIcon("C:\\Users\\Paweł\\Desktop\\praca dyp\\pitogram.jpg").getImage());
           add(panel2);
      add(panel);
      setVisible(true);
      setSize(400,400);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
}
}