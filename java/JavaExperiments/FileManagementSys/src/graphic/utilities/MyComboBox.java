package graphic.utilities;

import consts.GUI_CONST;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class MyComboBox<T> extends JComboBox<T> {
    public MyComboBox(T[] contents) {
        super(contents);

        setBackground(GUI_CONST.BG_COLOR);
        setBorder(GUI_CONST.TF_BORDER);
        setFont(GUI_CONST.FONT);
        setForeground(GUI_CONST.FONT_COLOR);

        setRenderer(new CustomComboBoxRenderer());
        setEditor(new BasicComboBoxEditor());
        setUI(new CustomComboBoxUI());


    }


    static class CustomComboBoxRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
            return this;
        }
    }

    static class CustomComboBoxUI extends BasicComboBoxUI {
        @Override
        protected void installDefaults() {
            super.installDefaults();
            comboBox.setBackground(GUI_CONST.BG_COLOR);
            comboBox.setForeground(GUI_CONST.FONT_COLOR);
        }

        @Override
        protected JButton createArrowButton() {
            return new JButton() {
                @Override
                public int getWidth() {
                    return 0; // Remove the arrow button
                }
            };
        }

        @Override
        public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
            super.paintCurrentValueBackground(g, bounds, hasFocus); // Call the super method first
            g.setColor(GUI_CONST.BG_COLOR); // Set your desired highlight color
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }
}
