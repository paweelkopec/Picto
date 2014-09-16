
package pictodisplayer;

import java.io.File;
import java.nio.file.Files;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import pictodisplayer.*;
import pictodisplayer.db.Category;
import pictodisplayer.db.Page;
import pictodisplayer.db.Picto;
import pictodisplayer.db.Pictodb;

/**
 *
 * @author Pawel Kopec <paweelkopec@gmail.com>
 * @version 1.0.1
 */
public class PictoDisplayer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Pictodb  db = new Pictodb("db_picto");
            db.createDatabase();

            //Install some pictos
            try{
                //dir exist?
                File dir = new File("C:\\PictoDisplayer\\");
                dir.mkdir();
                
                // get current dir
                String workingDirectory = System.getProperty("user.dir");
                // get category
                Category[] categorys = Category.all();
                System.out.println("C:\\PictoDisplayer\\" +categorys.length );
                //get pages
                for(int i=0; i<categorys.length; i++){
                    File file = new File("C:\\PictoDisplayer\\" +categorys[i].file_name );
                    if(!file.exists()){
                         // copy
                        File fileCopy = new File(workingDirectory +"\\img\\" +categorys[i].file_name );
                        if(fileCopy.exists()){
                            Files.copy(fileCopy.toPath(), file.toPath());
                        }
                    }
                    
                    Page[] pages = Page.list(categorys[i].id);
                    //get pictos
                    for(int j=0; j<pages.length; j++){
                        Picto[] pictos = Picto.list(pages[j].id);
                        for(int x=0; x<pictos.length; x++){
                            File pictoFile = new File("C:\\PictoDisplayer\\" +pictos[x].fileName );
                            if(!pictoFile.exists()){
                                // copy
                                File fileCopy = new File(workingDirectory +"\\img\\" +pictos[x].fileName);
                                if (fileCopy.exists()) {
                                    Files.copy(fileCopy.toPath(), pictoFile.toPath());
                                }
                            }
                        }
                    }
                }
            }catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            new Projector().setVisible(true);
            
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
