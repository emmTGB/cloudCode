package Consts;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public interface GUI_CONST {
    public static final Color BG_COLOR = new Color(0x262b33);
    public static final Color ALT_BG_COLOR = new Color(0x424b59);
    public static final Color ERR_COLOR = new Color(187, 29, 48);
    public static final Color FONT_COLOR = new Color(0xb0b6be);
    public static final Color ALT_FONT_COLOR = new Color(0x696c71);
    public static final int WIDTH = 800, HEIGHT = 600;
    public static final int FONT_SIZE = WIDTH / 40;
    public static final Font FONT = new Font("sans-serif", Font.PLAIN, FONT_SIZE);
    public static final Font FONT_ITALIC = new Font("sans-serif", Font.ITALIC, FONT_SIZE);
    public static final Border TF_BORDER = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ALT_BG_COLOR),
            BorderFactory.createEmptyBorder(0, 5, 0, 0)
    );
}
