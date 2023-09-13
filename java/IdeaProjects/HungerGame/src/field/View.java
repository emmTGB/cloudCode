package field;

import cell.Cell;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class View extends JPanel {
    @Serial
    private static final long serialVersionUID = -5258995676212660595L;
    private static final int GRID_SIZE = 16;
    private final Field thisField;

    public View(Field field){
        thisField = field;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(new Color(43, 43, 43));
        g.fillRect(0, 0, thisField.getWidth() * GRID_SIZE + 1, thisField.getHeight() * GRID_SIZE + 1);
        g.setColor(new Color(63, 63, 63));
        for (int row = 0; row < thisField.getHeight(); row++)
            g.drawLine(0, row * GRID_SIZE, thisField.getWidth() * GRID_SIZE, row * GRID_SIZE);
        for ( int col = 0; col<thisField.getWidth(); col++ )
            g.drawLine(col * GRID_SIZE, 0, col * GRID_SIZE, thisField.getHeight() * GRID_SIZE);
        for (int row = 0; row < thisField.getHeight(); row++) {
            for (int col = 0; col < thisField.getWidth(); col++) {
                Cell cell = thisField.get(row, col);
                if (cell != null)
                    cell.draw(g, col * GRID_SIZE, row * GRID_SIZE, GRID_SIZE);
            }
        }
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(thisField.getWidth() * GRID_SIZE + 1, thisField.getHeight() * GRID_SIZE + 1);
    }
}