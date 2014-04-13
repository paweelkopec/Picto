
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

    public class ImageBrowserPane extends JPanel {

        private JFileChooser fcImage = new JFileChooser();
        private ImagePane imagePane;

        public ImageBrowserPane() {
            setLayout(new BorderLayout());

            imagePane = new ImagePane();
            add(new JScrollPane(imagePane));

            JButton add = new JButton("Add");
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int state = fcImage.showOpenDialog(ImageBrowserPane.this);
                    switch (state) {

                        case JFileChooser.APPROVE_OPTION:
                            File file = fcImage.getSelectedFile();

                            try {
                                BufferedImage bi1 = ImageIO.read(file);
                                ImageIcon icon1 = new ImageIcon(bi1);
                                JLabel label = new JLabel(icon1);
                                label.setText(file.getPath());
                                label.setHorizontalTextPosition(JLabel.CENTER);
                                label.setVerticalTextPosition(JLabel.BOTTOM);
                                label.setForeground(Color.WHITE);
                                label.setBorder(new LineBorder(Color.WHITE));
                                imagePane.add(label);
                                imagePane.revalidate();
                            } catch (Exception exp) {
                                exp.printStackTrace();
                            }
                    }
                }
            });

            JPanel buttons = new JPanel();
            buttons.add(add);
            add(buttons, BorderLayout.NORTH);

        }
    }