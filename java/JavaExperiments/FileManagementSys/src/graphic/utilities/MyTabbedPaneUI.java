package graphic.utilities;


import consts.GUI_CONST;

import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

/**
 * 自定义JTabbedPane的UI
 */
public class MyTabbedPaneUI extends BasicTabbedPaneUI {
    @Override
    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
        //自定义选项卡的高：比默认的高度 高30
        return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight) + 10;
    }

    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
        //自定义选项卡的宽：比默认的宽度 宽50
        return super.calculateTabWidth(tabPlacement, tabIndex, metrics) + 50;
    }

    /**
     * 自定义选项卡的背景色
     *
     * @param g            图形设置
     * @param tabPlacement 标签位置
     * @param tabIndex     标签下标
     * @param x            x轴
     * @param y            y轴
     * @param w            宽
     * @param h            高
     * @param isSelected   是否被选中
     */
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex,
                                      int x, int y, int w, int h, boolean isSelected) {
        Color defaultColor = GUI_CONST.ALT_THEME_COLOR;
        Color selectedColor = GUI_CONST.THEME_COLOR;
        //设置选中时和未被选中时的颜色
        g.setColor(!isSelected ? defaultColor : selectedColor);
        //填充图形，即选项卡为矩形
        g.fillRect(x + 5, y - 2, w, h + 2);
    }

    @Override
    protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
        if (isSelected) {
            tabPane.setForeground(GUI_CONST.FONT_COLOR);
        } else {
            tabPane.setForeground(GUI_CONST.ALT_FONT_COLOR);
        }
        super.paintText(g, tabPlacement, font, metrics, tabIndex, title, textRect, isSelected);
    }

    /**
     * 绘制标签的边框
     *
     * @param g            图形
     * @param tabPlacement 标签位置
     * @param tabIndex     标签下标
     * @param x            x轴
     * @param y            y轴
     * @param w            宽
     * @param h            高
     * @param isSelected   标签是否被选中
     */
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex,
                                  int x, int y, int w, int h, boolean isSelected) {//自定义选项卡的边框色
        //注意：这个方法中的具体实现内容，可以写，也可以不写，根据自己实际情况决定。但这个方法定义必须有。否则会影响最终的显示效果
//        Color defaultColor = GUI_CONST.ALT_BG_COLOR;
//        Color selectedColor = GUI_CONST.ALT_THEME_COLOR;
//        //设置选中时和未被选中时的颜色
//        g.setColor(!isSelected ? defaultColor : selectedColor);
//        //绘制边框，即选项卡边框为矩形
//        g.drawRect(x + 5, y, w, h);
    }

    protected void paintFocusIndicator(Graphics g,
                                       int tabPlacement, Rectangle[] rects,
                                       int tabIndex, Rectangle iconRect, Rectangle textRect,
                                       boolean isSelected) {//这个方法定义如果没有的话，选项卡在选中时，内测会有虚线。
        //Do nothing
    }

    protected LayoutManager createLayoutManager() {// 设置Layout
        return new TabbedPaneLayout();
    }

    public class TabbedPaneLayout extends BasicTabbedPaneUI.TabbedPaneLayout {
        // 要想实现：1.选中选项卡时，选项卡突出显示 2.选项卡之间有间距。那么必须重写以下方法！！
        protected void calculateTabRects(int tabPlacement, int tabCount) {
            super.calculateTabRects(tabPlacement, tabCount);
            // 设置间距
            setRec(5);
        }

        public void setRec(int rec) {
            for (int i = 0; i < rects.length; i++) {
                rects[i].x = rects[i].x + rec * i;
            }
        }
    }

    @Override
    protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
        // 不调用父类的方法，以避免绘制默认边框

        // 在这里可以自定义边框的绘制
        // 例如，绘制一个红色的边框
        int width = tabPane.getWidth();
        Insets insets = tabPane.getInsets();
        int x = insets.left;
        int y = insets.bottom + 2;
        int w = width - insets.right - insets.left;

        switch (tabPlacement) {
            case LEFT:
                x += calculateTabAreaWidth(tabPlacement, runCount, maxTabWidth);

                w -= (x - insets.left);
                break;
            case RIGHT:
                w -= calculateTabAreaWidth(tabPlacement, runCount, maxTabWidth);

                break;
            case BOTTOM:
                break;
            case TOP:
            default:
                y += calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);
        }

        // Fill region behind content area
        g.setColor(GUI_CONST.ALT_BG_COLOR);
        g.fillRect(0, y - 1, w, 2);
        g.setColor(GUI_CONST.THEME_COLOR);
        g.drawLine(0, y - 2, w, y - 2);
    }
}