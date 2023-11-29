package graphic.utilities;

import consts.GUI_CONST;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MyTextField extends JTextField {
    final String HINT;

    public MyTextField(String HINT) {
        super();
        this.HINT = HINT;
        setColumns(GUI_CONST.TF_LENGTH);
        setText(HINT);
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(HINT)) {
                    setText("");
                    setFont(GUI_CONST.FONT);
                }
                setForeground(GUI_CONST.FONT_COLOR);
                setBackground(GUI_CONST.ALT_BG_COLOR);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(HINT);
                    setForeground(GUI_CONST.ALT_FONT_COLOR);
                    setFont(GUI_CONST.FONT_ITALIC);
                }
                setBackground(GUI_CONST.BG_COLOR);
            }
        });
    }

    public boolean notTyped() {
        return getText().equals(HINT) || getText().isEmpty();
    }
}
