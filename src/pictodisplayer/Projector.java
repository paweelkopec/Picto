package pictodisplayer;

/*
 * Displays images.
 */
import pictodisplayer.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import pictodisplayer.db.Page;
import pictodisplayer.db.Picto;
import pictodisplayer.db.Pictodb;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import pictodisplayer.db.Category;
import pictodisplayer.socket.Server;

/**
 * Image Projector
 *
 * @author Pawel Kopec <paweelkopec@gmail.com>
 */
public class Projector extends javax.swing.JFrame {

    Page page;
    Picto pictos[];
    Page pages[];
    Category categorys[];
    Integer currentImage=0;
    int currentCategory=0;
    int currentPage=0;
    Timer timer;
    Timer serverTimer;
    ImagePanel imagePane;
    Server server;
    Boolean isServerActived = false;
    Boolean isDispalyCategorys = false;
    Boolean isDispalyPages = false;
    /**
     * Creates new form Projector
     */
    public Projector() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
        }
        initComponents();
        this.loadImages(3);
        this.currentImage = 0;
        init();
        imagePane = new ImagePanel();
        this.slectedPictos.add(new JScrollPane(imagePane));
    }
    /**
     * Creates new form Projector
     */
    public Projector(Page page) {
        this.page = page;
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
        }
        this.loadCategorys();
        try {
            this.currentCategory=Category.searchKey(page.categoryId, categorys);
            this.pages = Page.list(this.categorys[this.currentCategory].id);
        } catch (Exception ex) {
            Logger.getLogger(Projector.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        this.loadImages(this.page.id);
        this.currentImage = 0;
        imagePane = new ImagePanel();
        this.slectedPictos.add(new JScrollPane(imagePane));
        init();
    }
    /**
     * Construct from category
     * @param categoryID
     */
    public Projector(int categoryID) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
        }
        this.loadCategorys();
        initComponents();
        imagePane = new ImagePanel();
        this.currentCategory=Category.searchKey(categoryID, categorys);
        this.slectedPictos.add(new JScrollPane(imagePane));
        this.isDispalyCategorys = true;
        init();
    }
    /**
     * Run Timers
     */
    public void init() {
        try {
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    start();
                }
            };
            start();
            timer = new Timer(2000, taskPerformer);
            timer.start();
        } catch (Exception e) {
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

        jPanel3 = new javax.swing.JPanel();
        panelDisplay = new javax.swing.JPanel();
        labelDisplay = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        pauze = new javax.swing.JToggleButton();
        reset = new javax.swing.JButton();
        speed = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        slectedPictos = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        port = new javax.swing.JTextField();
        serverSocket = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        panelDisplay.setBorder(javax.swing.BorderFactory.createTitledBorder("Wybór Obrazka"));
        panelDisplay.setPreferredSize(new java.awt.Dimension(15, 300));
        panelDisplay.setLayout(new java.awt.BorderLayout());

        labelDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDisplay.setAlignmentY(0.0F);
        labelDisplay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelDisplayMouseClicked(evt);
            }
        });
        panelDisplay.add(labelDisplay, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Operacje"));

        pauze.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pauze.setText("Pauza");
        pauze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauzeActionPerformed(evt);
            }
        });

        reset.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        reset.setText("Wyczyść");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        speed.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "300", "400", "500", "600", "700", "800", "900", "1000", "1500", "2000", "2500", "3000", "3500", "4000", "4500", "5000" }));
        speed.setSelectedIndex(9);
        speed.setToolTipText("");
        speed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speedActionPerformed(evt);
            }
        });

        jLabel1.setText("Szybkość Wyświetlania [ms]");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(speed, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(reset, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pauze, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pauze, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(speed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        slectedPictos.setBorder(javax.swing.BorderFactory.createTitledBorder("Wybrane Piktogramy"));
        slectedPictos.setToolTipText("");
        slectedPictos.setPreferredSize(new java.awt.Dimension(12, 600));
        slectedPictos.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Serwer"));

        jLabel2.setText("Port:");

        port.setText("3568");

        serverSocket.setText("Start");
        serverSocket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverSocketActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(serverSocket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(port)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(serverSocket)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(slectedPictos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(slectedPictos, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panelDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Stop display timer
     *
     * @param evt
     */
    private void pauzeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauzeActionPerformed
        if (this.pauze.isSelected()) {
            this.timer.stop();
        } else {
            this.timer.start();
        }
    }//GEN-LAST:event_pauzeActionPerformed
    /**
     * Changw display speed
     *
     * @param evt
     */
    private void speedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speedActionPerformed
        String value = (String) this.speed.getSelectedItem();
        this.timer.setDelay(Integer.parseInt(value));
    }//GEN-LAST:event_speedActionPerformed

    /**
     * Adds image to the projection
     *
     * @param evt
     */
    private void labelDisplayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDisplayMouseClicked
        try {
            if(this.isDispalyCategorys){
                int i = this.currentCategory>0 ?  this.currentCategory-1 : 0;
                this.pages = Page.list(this.categorys[i].id);
                this.loadImages(this.pages[0].id);
                this.isDispalyCategorys = false;
                this.isDispalyPages = true;
                return;
            }else{
                int i = this.currentImage>0 ?  this.currentImage-1 : 0;
                ImageIcon icon = new ImageIcon("C:\\PictoDisplayer\\" + this.pictos[i].fileName);
                JLabel label = new JLabel(icon);
                String name = this.pictos[i].name.equals("") ? this.pictos[i].fileName : this.pictos[i].name;                
                label.setText(name);
                label.setHorizontalTextPosition(JLabel.CENTER);
                label.setVerticalTextPosition(JLabel.BOTTOM);
                label.setBorder(new LineBorder(Color.WHITE));
                imagePane.add(label);
                imagePane.revalidate();
                this.pictos[i].increaseStats();
            }
        } catch (Exception e) {
            System.out.println("Error Label display - " + e.toString());
        }
    }//GEN-LAST:event_labelDisplayMouseClicked
    /**
     * Clear images on windows
     *
     * @param evt
     */
    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        this.imagePane.removeAll();
        this.imagePane.revalidate();
        this.imagePane.repaint();
    }//GEN-LAST:event_resetActionPerformed

    /**
     * Run server socket
     *
     * @param evt
     */
    private void serverSocketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverSocketActionPerformed
        try {
            Integer port = Integer.parseInt(this.port.getText());
            server = new Server(port);

            ActionListener taskPerformer2 = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    getServerCommand();
                }
            };
            this.serverTimer = new Timer(100, taskPerformer2);
            serverTimer.start();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "ERROR: " + ex.getMessage());
        }
    }//GEN-LAST:event_serverSocketActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Projector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Projector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Projector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Projector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Projector().setVisible(true);
            }
        });
    }

    /**
     * Displays the current image
     */
    private void start() {
        try {
            //categorys
            if(this.isDispalyCategorys){
                if (this.currentCategory >= this.categorys.length) {
                    this.currentCategory = 0;
                }
                ImageIcon icon = new ImageIcon("C:\\PictoDisplayer\\" + this.categorys[this.currentCategory].file_name);
                this.labelDisplay.removeAll();
                String name = this.categorys[this.currentCategory].name;
                this.labelDisplay.setText(name);
                this.labelDisplay.setHorizontalTextPosition(JLabel.CENTER);
                this.labelDisplay.setVerticalTextPosition(JLabel.BOTTOM);
                this.labelDisplay.setIcon(icon);
                this.labelDisplay.revalidate();
                this.currentCategory ++;
            // pictos    
            }else{
                if (this.currentImage >= this.pictos.length) {
                    this.currentImage = 0;
                    // next page
                    this.currentPage++;
                    if(this.currentPage<this.pages.length){
                        this.loadImages(this.pages[this.currentPage].id);
                    }
                    if (this.currentPage >= this.pages.length) {
                         this.currentPage = 0;
                         this.isDispalyCategorys = true;
                         this.isDispalyPages = false;
                         this.currentCategory++;
                         return;
                    }
                }                
                ImageIcon icon = new ImageIcon("C:\\PictoDisplayer\\" + this.pictos[this.currentImage].fileName);
                String name = this.pictos[this.currentImage].name.equals("") ? this.pictos[this.currentImage].fileName : this.pictos[this.currentImage].name;
                this.labelDisplay.removeAll();
                this.labelDisplay.setIcon(icon);
                this.labelDisplay.setText(name);
                this.labelDisplay.setHorizontalTextPosition(JLabel.CENTER);
                this.labelDisplay.setVerticalTextPosition(JLabel.BOTTOM);
                this.labelDisplay.revalidate();
                this.currentImage ++;
            }
        } catch (Exception e) {
            System.out.println("Start - " + e.toString());
        }
    }

    /**
     * Command from socket
     */
    private void getServerCommand() {
        server.run();
        String cmd = this.server.getCommand();
        System.out.println("cmd - " + cmd);
        if (cmd.equalsIgnoreCase("show")) {
            java.awt.event.MouseEvent evt = new MouseEvent(
                    labelDisplay,
                    MouseEvent.MOUSE_CLICKED,
                    1,
                    MouseEvent.BUTTON1,
                    0, 0,
                    2,
                    false);
            this.labelDisplayMouseClicked(evt);

        }else if(cmd.equalsIgnoreCase("reset")){
            java.awt.event.ActionEvent evt = new ActionEvent(this,78898, "");
            this.resetActionPerformed(evt);
        }else if(cmd.equalsIgnoreCase("pause")){
            java.awt.event.ActionEvent evt = new ActionEvent(this,782434898, "");
            this.pauze.setSelected(true);
            this.pauzeActionPerformed(evt);
        }else if(cmd.equalsIgnoreCase("start")){
            java.awt.event.ActionEvent evt = new ActionEvent(this,782234898, "");
            this.pauze.setSelected(false);
            this.pauzeActionPerformed(evt);
        }
    }

    /**
     * Load images from database table page
     *
     * @param PageId
     */
    private void loadImages(Integer PageId){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());

            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rec = st.executeQuery("SELECT * FROM pictos WHERE pid=" + PageId + " ORDER BY stats DESC");
            int rows = 0;
            if (rec.last()) {
                rows = rec.getRow();
                rec.beforeFirst();
            }
            this.pictos = new Picto[rows];
            int i = 0;
            while (rec.next()) {
                pictos[i] = new Picto(rec);
                i++;
            }
            st.close();
        } catch (Exception e) {
            System.out.println("Error Load Images - " + e.toString());
        }
    }
    private void loadCategorys(){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());

            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rec = st.executeQuery("SELECT * FROM categories ORDER BY stats DESC");
            int rows = 0;
            if (rec.last()) {
                rows = rec.getRow();
                rec.beforeFirst();
            }
            this.categorys = new Category[rows];
            int i = 0;
            while (rec.next()) {
                categorys[i] = new Category(rec);
                i++;
            }
            st.close();
        } catch (Exception e) {
            System.out.println("Error Load Category - " + e.toString());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelDisplay;
    private javax.swing.JPanel panelDisplay;
    private javax.swing.JToggleButton pauze;
    private javax.swing.JTextField port;
    private javax.swing.JButton reset;
    private javax.swing.JToggleButton serverSocket;
    private javax.swing.JPanel slectedPictos;
    private javax.swing.JComboBox speed;
    // End of variables declaration//GEN-END:variables
}
