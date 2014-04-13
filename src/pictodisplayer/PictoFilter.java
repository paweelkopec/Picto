
package pictodisplayer;
import java.io.File;

/**
 *
 * @author Pawel Kopec <paweelkopec@gmail.com>
 */
class PictoFilter extends javax.swing.filechooser.FileFilter {
        @Override
        public boolean accept(File file) {
            // Allow only directories, or files with ".jpeg" extension
            return file.isDirectory() || file.getAbsolutePath().endsWith(".jpeg") || file.getAbsolutePath().endsWith(".png") ||
                     file.getAbsolutePath().endsWith(".jpg") || file.getAbsolutePath().endsWith(".gif") || file.getAbsolutePath().endsWith(".bmp");
        }
        @Override
        public String getDescription() {
            // This description will be displayed in the dialog,
            // hard-coded = ugly, should be done via I18N
            return "Pliki Graficzne (*.png, *.jpeg, *.jpg, *.gif, *.bmp)";
        }
}
