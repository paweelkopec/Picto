
package pictodisplayer;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Pawel Kopec <paweelkopec@gmail.com>
 */
public class PictoTreeNode extends DefaultMutableTreeNode{
    public int ID;

    /**
     * Set ID
     * @param ID 
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    /**
     * Create Object
     * @param title 
     */
    public PictoTreeNode(String title) {
        setUserObject(title);
    }
}
