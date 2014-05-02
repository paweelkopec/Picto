
package pictodisplayer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import pictodisplayer.*;
import pictodisplayer.db.Pictodb;

/**
 *
 * @author Pawel Kopec <paweelkopec@gmail.com>
 */
public class PictoDisplayer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Pictodb  db = new Pictodb("db_picto");
            db.createDatabase();
            new Index().setVisible(true);
        }catch (Exception e) {
           Index index = new Index();
           index.setVisible(true);
            JOptionPane.showMessageDialog(index,
            e.toString(),
            "Inane error",
            JOptionPane.ERROR_MESSAGE);
            System.out.println("Error - " + e.toString());
        }
    }

}
