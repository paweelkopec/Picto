/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pictodisplayer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import pictodisplayer.*;
import pictodisplayer.db.Pictodb;

/**
 *
 * @author Paweł
 */
public class PictoDisplayer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
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
