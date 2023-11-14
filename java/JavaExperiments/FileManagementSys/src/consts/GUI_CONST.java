package consts;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public interface GUI_CONST {
    Color BG_COLOR = new Color(0x262b33);
    Color ALT_BG_COLOR = new Color(0x424b59);
    Color ERR_COLOR = new Color(187, 29, 48);
    Color FONT_COLOR = new Color(0xb0b6be);
    Color ALT_FONT_COLOR = new Color(0x696c71);

    int WIDTH = 800, HEIGHT = 600;
    int FONT_SIZE = WIDTH / 40;
    int TF_LENGTH = 24;

    Font FONT = new Font("sans-serif", Font.PLAIN, FONT_SIZE);
    Font FONT_ITALIC = new Font("sans-serif", Font.ITALIC, FONT_SIZE);
    Font FONT_SMALL = new Font("sans-serif", Font.PLAIN, FONT_SIZE * 2 / 3);
    Border TF_BORDER = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ALT_BG_COLOR),
            BorderFactory.createEmptyBorder(0, 5, 0, 0)
    );
}
