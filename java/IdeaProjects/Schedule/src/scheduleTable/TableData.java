package scheduleTable;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class TableData implements TableModel {
    private static final String[] titles = {
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday"
    };
    private String[][] keys = new String[8][7];

    public TableData(){
        for(int i = 0; i < keys.length; i++){
            for(int j = 0; j < keys[0].length; j++){
                keys[i][j] = "";
            }
        }
    }

    @Override
    public int getRowCount() {
        return keys.length;
    }

    @Override
    public int getColumnCount() {
        return keys[0].length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return titles[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;//返回string类告知其储存数据类型
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;//单元格是否可编辑
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return keys[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        keys[rowIndex][columnIndex] = (String) aValue;
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
