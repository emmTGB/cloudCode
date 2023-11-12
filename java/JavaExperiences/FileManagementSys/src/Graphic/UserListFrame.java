package Graphic;

import Consts.GUI_CONST;
import Process.DataProcess;
import Users.User;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Enumeration;

public class UserListFrame extends JFrame {
    Enumeration<User> listUsers = DataProcess.getAllUsers();
    static final String[] columnNames = {"user name", "password", "role"};
    String[][] contents = new String[DataProcess.getLengthOfUserLists()][3];

    JTable tableUsers;
    JScrollPane scrollPane;

    public UserListFrame(JFrame fatherFrame) {
        super("User list");
        Point loc = fatherFrame.getLocationOnScreen();
        setBounds(loc.x, loc.y, GUI_CONST.WIDTH, GUI_CONST.HEIGHT);

        int i = 0;
        while (listUsers.hasMoreElements()) {
            User user = listUsers.nextElement();
            contents[i][0] = user.getUserName();
            contents[i][1] = user.getPassWord();
            contents[i][2] = user.getUserRole();
            i++;
        }

        tableUsers = new JTable(contents, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableUsers.setBackground(GUI_CONST.BG_COLOR);
        tableUsers.setForeground(GUI_CONST.FONT_COLOR);
        tableUsers.setRowHeight(GUI_CONST.FONT_SIZE * 5 / 4);
        tableUsers.setFont(GUI_CONST.FONT);

        JTableHeader tableHeaderUsers = tableUsers.getTableHeader();
        tableHeaderUsers.setBackground(GUI_CONST.ALT_BG_COLOR);
        tableHeaderUsers.setForeground(GUI_CONST.FONT_COLOR);
        tableUsers.setRowHeight(GUI_CONST.FONT_SIZE * 5 / 4);
        tableHeaderUsers.setFont(GUI_CONST.FONT);
        tableHeaderUsers.setBorder(GUI_CONST.TF_BORDER);

        tableUsers.getColumn(columnNames[0]).setMinWidth(100);
        tableUsers.getColumn(columnNames[1]).setMinWidth(100);
        tableUsers.getColumn(columnNames[2]).setMinWidth(100);

        scrollPane = new JScrollPane(tableUsers);
        scrollPane.setBackground(GUI_CONST.BG_COLOR);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        scrollPane.getVerticalScrollBar().setUI(new DIYScrollBar());

        add(scrollPane);
        for (Component c : scrollPane.getComponents()) {
            c.setBackground(GUI_CONST.BG_COLOR);
        }

        setVisible(true);
    }
}

//自定义滚动条UI
class DIYScrollBar extends BasicScrollBarUI {

    @Override
    protected void configureScrollBarColors() {

        // 把手

        // thumbColor = Color.GRAY;

        // thumbHighlightColor = Color.BLUE;

        // thumbDarkShadowColor = Color.BLACK;

        // thumbLightShadowColor = Color.YELLOW;

        // 滑道

        trackColor = GUI_CONST.ALT_BG_COLOR;

        setThumbBounds(0, 0, 3, 10);

        // trackHighlightColor = Color.GREEN;

    }

    /**
     * 设置滚动条的宽度
     */
    @Override
    public Dimension getPreferredSize(JComponent c) {

        // TODO Auto-generated method stub

        c.setPreferredSize(new Dimension(20, 0));

        return super.getPreferredSize(c);

    }


    // 重绘滑块的滑动区域背景

    public void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {

        Graphics2D g2 = (Graphics2D) g;

        GradientPaint gp = null;

        //判断滚动条是垂直的 还是水平的

        if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL) {

            //设置画笔

            gp = new GradientPaint(0, 0, GUI_CONST.ALT_BG_COLOR,

                    trackBounds.width, 0, GUI_CONST.ALT_BG_COLOR);

        }

        if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL) {

            gp = new GradientPaint(0, 0, GUI_CONST.ALT_BG_COLOR,

                    trackBounds.height, 0, GUI_CONST.ALT_BG_COLOR);

        }


        g2.setPaint(gp);

        //填充Track

        g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width,

                trackBounds.height);

        //绘制Track的边框
        /*       g2.setColor(new Color(175, 155, 95));
         g2.drawRect(trackBounds.x, trackBounds.y, trackBounds.width - 1,
                trackBounds.height - 1);
                */

        if (trackHighlight == BasicScrollBarUI.DECREASE_HIGHLIGHT)

            this.paintDecreaseHighlight(g);

        if (trackHighlight == BasicScrollBarUI.INCREASE_HIGHLIGHT)

            this.paintIncreaseHighlight(g);

    }


    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {

        // 把绘制区的x，y点坐标定义为坐标系的原点

        // 这句一定一定要加上啊，不然拖动就失效了

        g.translate(thumbBounds.x, thumbBounds.y);

        // 设置把手颜色

        g.setColor(GUI_CONST.FONT_COLOR);

        // 画一个圆角矩形

        // 这里面前四个参数就不多讲了，坐标和宽高

        // 后两个参数需要注意一下，是用来控制角落的圆角弧度

        // g.drawRoundRect(0, 0, 5, thumbBounds.height - 1, 5, 5);

        // 消除锯齿

        Graphics2D g2 = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,

                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.addRenderingHints(rh);

        // 半透明

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,

                0.5f));

        // 设置填充颜色，这里设置了渐变，由下往上

        // g2.setPaint(new GradientPaint(c.getWidth() / 2, 1, Color.GRAY,

        // c.getWidth() / 2, c.getHeight(), Color.GRAY));

        // 填充圆角矩形

        g2.fillRoundRect(0, 0, 40, thumbBounds.height - 1, 5, 5);

    }


    /**
     * 创建滚动条上方的按钮
     */
    @Override

    protected JButton createIncreaseButton(int orientation) {

        JButton button = new JButton();

        button.setBorderPainted(false);

        button.setContentAreaFilled(false);

        button.setBorder(null);

        return button;

    }

    /**
     * 创建滚动条下方的按钮
     */
    @Override

    protected JButton createDecreaseButton(int orientation) {

        JButton button = new JButton();

        button.setBorderPainted(false);

        button.setContentAreaFilled(false);

        button.setFocusable(false);

        button.setBorder(null);

        return button;

    }

}