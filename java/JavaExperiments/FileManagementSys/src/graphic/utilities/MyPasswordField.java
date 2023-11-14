package graphic.utilities;

import consts.GUI_CONST;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MyPasswordField extends JPasswordField {
    protected final String HINT;
    protected boolean isShowed;

    public MyPasswordField(String HINT) {
        this.HINT = HINT;
        setColumns(GUI_CONST.TF_LENGTH);
        setEchoChar('\0');
        setText(HINT);
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(getPassword()).equals(HINT))
                    setText("");
                setEchoChar(isShowed ? '\0' : '*');
                setForeground(GUI_CONST.FONT_COLOR);
                setFont(GUI_CONST.FONT);
                setBackground(GUI_CONST.ALT_BG_COLOR);
                //todo
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getPassword().length == 0) {
                    setEchoChar('\0');
                    setText(HINT);
                    setForeground(GUI_CONST.ALT_FONT_COLOR);
                    setFont(GUI_CONST.FONT_ITALIC);
                } else
                    setEchoChar(isShowed ? '\0' : '*');
                setBackground(GUI_CONST.BG_COLOR);
            }
        });
        isShowed = false;
    }

    public void toggleShowPass(boolean isShowed) {
        this.isShowed = isShowed;
        if (!new String(getPassword()).equals(HINT))
            setEchoChar(isShowed ? '\0' : '*');
    }

    public boolean notTyped() {
        return (new String(getPassword()).equals(HINT)) || getPassword().length == 0;
    }
}
