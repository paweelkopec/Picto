package pictodisplayer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import pictodisplayer.db.Pictodb;
import pictodisplayer.db.Category;
import pictodisplayer.db.Page;
import pictodisplayer.db.Setting;
import pictodisplayer.PictoFilter;
import pictodisplayer.db.Picto;
import pictodisplayer.ImagePanel;
import pictodisplayer.WrapLayout;

/**
 *
 * @author Pawel Kopec <paweelkopec@gmail.com>
 */
public class PagePanel extends javax.swing.JPanel {

    private String pageName;
    private Integer categoryID;
    private Page page;
    private static Index index;
    private Integer currentImageId;
    JLabel display;
    ImagePanel imagePane;

    /**
     * Creates new form category
     */
    public PagePanel(String pageName) {
        this.pageName = pageName;
        try {
            page = new Page(pageName);
            initComponents();
        } catch (Exception e) {
            System.out.println("Error - " + e.toString());
        }
    }
    /**
     * Creates new form category
     */
    public PagePanel(int id) {
        try {
            page = new Page(id);
            initComponents();
        } catch (Exception e) {
            System.out.println("Error - " + e.toString());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        popupImageMenu = new javax.swing.JPopupMenu();
        menuDelete = new javax.swing.JMenuItem();
        menuEdit = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        addPicto = new javax.swing.JButton();
        editPage = new javax.swing.JButton();
        deletePage = new javax.swing.JButton();
        projector = new javax.swing.JButton();
        srodekPanel = new javax.swing.JPanel();

        fileChooser.setDialogTitle("Wybierz piktogram");
        fileChooser.setFileFilter(new PictoFilter());

        menuDelete.setText("Usuń");
        menuDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuDeleteMousePressed(evt);
            }
        });
        menuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDeleteActionPerformed(evt);
            }
        });
        popupImageMenu.add(menuDelete);

        menuEdit.setText("Edytuj");
        menuEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEditActionPerformed(evt);
            }
        });
        popupImageMenu.add(menuEdit);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Opcje"));

        addPicto.setText("Dodaj Pitogram");
        addPicto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPictoActionPerformed(evt);
            }
        });

        editPage.setText("Edytuj  Stronę");
        editPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPageActionPerformed(evt);
            }
        });

        deletePage.setText("Usuń Strone");
        deletePage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePageActionPerformed(evt);
            }
        });

        projector.setText("Przeglądaj");
        projector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(addPicto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editPage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deletePage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(projector)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addPicto)
                    .addComponent(editPage)
                    .addComponent(deletePage)
                    .addComponent(projector))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        srodekPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(srodekPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(srodekPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        showImages();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Press delete event
     *
     * @param evt
     */
    private void deletePageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePageActionPerformed
        // TODO add your handling code here:
        int n = JOptionPane.showConfirmDialog(
                this,
                "Strona " + page.name + " zostanie usunięta",
                "An Inane Question",
                JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            page.delete();
            this.setVisible(false);
            index.reLoadTree();
        }
    }//GEN-LAST:event_deletePageActionPerformed
    /**
     * Press edit event
     *
     * @param evt
     */
    private void editPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPageActionPerformed
        EditPage eP = new EditPage(page);
        eP.setCategPanel(this);
        eP.setVisible(true);
    }//GEN-LAST:event_editPageActionPerformed
    /**
     * Press add event
     *
     * @param evt
     */
    private void addPictoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPictoActionPerformed
        
        
        Setting defaultDir = new Setting("defaultDir");
        if(defaultDir.value != null){
            File dir2 = new File(defaultDir.value);
            fileChooser.setCurrentDirectory(dir2);
        }

        int returnVal = fileChooser.showOpenDialog(this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                defaultDir.setValue(file.getParent()+"\\");                  
                File dir = new File("C:\\PictoDisplayer\\");
                dir.mkdir();
                File dest = new File("C:\\PictoDisplayer\\" + file.getName());
                Files.copy(file.toPath(), dest.toPath());
                Picto.add(page.id, "", file.getName(), 0, 0);
                srodekPanel.removeAll();
                this.showImages();
                srodekPanel.revalidate();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        ex.toString(),
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);
                System.out.println("Error - " + ex.toString());
                System.out.println("problem accessing file" + file.getAbsolutePath());
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_addPictoActionPerformed
    /**
     * Press lable event
     *
     * @param evt
     */
    private void labelMousePressed(java.awt.event.MouseEvent evt) {
        System.out.println("labelMousePressed");
        JLabel label = (JLabel) evt.getComponent();
        this.currentImageId = Integer.parseInt(label.getName());
    }

    /**
     * Press menu delete event
     *
     * @param evt
     */
    private void menuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDeleteActionPerformed
        try {
            Picto picto = new Picto(this.currentImageId);
            int n = JOptionPane.showConfirmDialog(
                    this,
                    "Pitogram " + picto.fileName + " zostanie usunięty",
                    "An Inae Question",
                    JOptionPane.YES_NO_OPTION);
            if (n == 0) {
                picto.delete();
                srodekPanel.removeAll();
                this.showImages();
                srodekPanel.revalidate();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_menuDeleteActionPerformed
    /**
     * Press menu edit event
     *
     * @param evt
     */
    private void menuEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEditActionPerformed
        EditPicto ep = new EditPicto(this.currentImageId);
        ep.setPagePanel(this);
        ep.setVisible(true);
    }//GEN-LAST:event_menuEditActionPerformed
    /**
     * Press Delete event
     *
     * @param evt
     */
    private void menuDeleteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDeleteMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuDeleteMousePressed
    /**
     * Run window Projector
     *
     * @param evt
     */
    private void projectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectorActionPerformed
        Projector projector = new Projector(this.page);
        projector.setVisible(true);
    }//GEN-LAST:event_projectorActionPerformed
    /**
     * Set Index handle
     *
     * @param i
     */
    public static void setIndex(Index i) {
        index = i;
    }

    /**
     * Refresh current window
     */
    public void refresh() {
        index.reLoadTree();
    }

    /**
     * Show Image on the window
     */
    private void showImages() {
        imagePane = new ImagePanel();
        srodekPanel.add(new JScrollPane(imagePane));
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rec = st.executeQuery("SELECT * FROM pictos WHERE pid=" + page.id);
            int rows = 0;
            if (rec.last()) {
                rows = rec.getRow();
                rec.beforeFirst();
            }
            while (rec.next()) {
                System.out.println(rec.getString("file_name"));
                ImageIcon icon = new ImageIcon("C:\\PictoDisplayer\\" + rec.getString("file_name"));
                JLabel label = new JLabel(icon);
                String name = rec.getString("name").equals("") ? rec.getString("file_name") : rec.getString("name");
                label.setText(name);
                label.setHorizontalTextPosition(JLabel.CENTER);
                label.setVerticalTextPosition(JLabel.BOTTOM);
                label.setBorder(new LineBorder(Color.WHITE));
                Integer id = rec.getInt("id");
                label.setName(id.toString());
                label.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        labelMousePressed(evt);
                    }
                });
                label.setComponentPopupMenu(popupImageMenu);
                imagePane.add(label);
                imagePane.revalidate();
            }
            st.close();
        } catch (Exception e) {
            System.out.println("Error - " + e.toString());
        }
    }
    /**
     * Reload Images
     */
    public void reloadImages() {
        srodekPanel.removeAll();
        this.showImages();
        srodekPanel.revalidate();
    }
    private void setupListeners(){
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPicto;
    private javax.swing.JButton deletePage;
    private javax.swing.JButton editPage;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem menuDelete;
    private javax.swing.JMenuItem menuEdit;
    private javax.swing.JPopupMenu popupImageMenu;
    private javax.swing.JButton projector;
    private javax.swing.JPanel srodekPanel;
    // End of variables declaration//GEN-END:variables
}
