package graphic.panels;

import consts.GUI_CONST;
import graphic.utilities.MyTextField;
import process.DocProcess;
import users.User;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class UploadDocPanel extends MyPanel {
    User user;
    SpringLayout springLayout = new SpringLayout();
    JButton chooseFileButton;
    MyTextField chooseFileField;
    MyTextField descriptionField;
    JLabel chooseFileLabel;
    JLabel descriptionLabel;

    public UploadDocPanel(User user) {
        super();
        this.user = user;
        setLayout(springLayout);
        setPreferredSize(new Dimension(GUI_CONST.WIDTH, GUI_CONST.HEIGHT));

        chooseFileButton = new JButton("Browse");
        chooseFileLabel = new JLabel("Choose File");
        descriptionLabel = new JLabel("Description");
        chooseFileField = new MyTextField("choose your file");
        descriptionField = new MyTextField("input your description");

        chooseFileButton.setBackground(GUI_CONST.BG_COLOR);
        chooseFileButton.setForeground(GUI_CONST.FONT_COLOR);

        chooseFileButton.setPreferredSize(new Dimension(40, 20));
        chooseFileButton.addActionListener(e -> openFileChooser(chooseFileField));

        chooseFileField.setLayout(new BorderLayout());
        chooseFileField.add(chooseFileButton, BorderLayout.EAST);

        add(chooseFileField);
        add(descriptionField);
        add(chooseFileLabel);
        add(descriptionLabel);

        JPanel inputPane = new JPanel();
        {
            GroupLayout inputLayout = new GroupLayout(inputPane);
            inputPane.setLayout(inputLayout);
            inputPane.setBackground(GUI_CONST.BG_COLOR);

            GroupLayout.SequentialGroup hGroup = inputLayout.createSequentialGroup();
            hGroup.addGap(5);
            hGroup.addGroup(inputLayout.createParallelGroup().addComponent(chooseFileLabel).addComponent(descriptionLabel));
            hGroup.addGap(5);
            hGroup.addGroup(inputLayout.createParallelGroup().addComponent(chooseFileField).addComponent(descriptionField));
            hGroup.addGap(5);

            inputLayout.setHorizontalGroup(hGroup);

            GroupLayout.SequentialGroup vGroup = inputLayout.createSequentialGroup();
            vGroup.addGap(10);
            vGroup.addGroup(inputLayout.createParallelGroup().addComponent(chooseFileLabel).addComponent(chooseFileField));
            vGroup.addGap(10);
            vGroup.addGroup(inputLayout.createParallelGroup().addComponent(descriptionLabel).addComponent(descriptionField));
            vGroup.addGap(10);

            inputLayout.setVerticalGroup(vGroup);
        }
        add(inputPane);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, inputPane, 0, SpringLayout.HORIZONTAL_CENTER, this);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, inputPane, 0, SpringLayout.VERTICAL_CENTER, this);


        labelMsg = new JLabel();
        add(labelMsg);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelMsg, 0, SpringLayout.HORIZONTAL_CENTER, this);
        springLayout.putConstraint(SpringLayout.NORTH, labelMsg, 10, SpringLayout.SOUTH, inputPane);
        labelMsg.setVisible(false);
        labelMsg.setForeground(GUI_CONST.ERR_COLOR);

        for (Component c : inputPane.getComponents()) {
            c.setBackground(GUI_CONST.BG_COLOR);
            c.setForeground(GUI_CONST.FONT_COLOR);
            c.setFont(GUI_CONST.FONT);
            if (c instanceof JTextField tf) {
                tf.setForeground(GUI_CONST.ALT_FONT_COLOR);
                tf.setFont(GUI_CONST.FONT_ITALIC);
                tf.setBorder(GUI_CONST.TF_BORDER);
            }
            if (c instanceof JCheckBox cb) {
                cb.setFont(GUI_CONST.FONT_SMALL);
            }
        }
    }

    private static void openFileChooser(JTextField textField) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            textField.setText(selectedFile.getAbsolutePath());
        }
    }

    @Override
    public void confirmTriggered() {
        String filePath = chooseFileField.getText();
        String description = descriptionField.getText();
        DocProcess.uploadDoc(filePath, user.getUserName(), description);
    }
}
