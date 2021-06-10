import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
public class MyTable extends JPanel {
    public MyTable() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1700,700));
        //setBounds(800, 800, 900, 900);
        JTable table = new JTable(new ModelData());
        table.setRowMargin(4);
        table.setRowHeight(30);
        table.setFont(new Font("SansSerif", Font.HANGING_BASELINE + Font.PLAIN, 20));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        // table.setFillsViewportHeight(true);
        JScrollPane j=new JScrollPane(table);
        //j.setPreferedSize(new Dimension(800,600));
        //j.setSize(900,900);
        add(j);
        setVisible(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(160);
        table.getColumnModel().getColumn(1).setPreferredWidth(60);
        table.getColumnModel().getColumn(2).setPreferredWidth(140);
    }
/*
    public static void main(String[] args) {
        Traveller.initialize_travellers_from_file();
         new MyTable();
    }
 */
}
class ModelData extends AbstractTableModel {
    List<Traveller> data = new ArrayList<Traveller>();
    ArrayList<Traveller> unique_trav=Traveller.no_duplicate_arraylist_travellers();
    String colNames[] = { "NAME", "AGE", "RECOMM CITY","GR_0","GR_1","GR_2","GR_3","GR_4","GR_5","GR_6","GR_7","GR_8","GR_9"};
    Class<?>[] colClasses = { String.class, Integer.class, String.class ,Integer.class,Integer.class,Integer.class,Integer.class,Integer.class,Integer.class,Integer.class,Integer.class,Integer.class,Integer.class};
    ModelData() {
        for(int i =0; i<unique_trav.size();i++){
            data.add(unique_trav.get(i));
        }
    }
    @Override
    public int getRowCount() {
        return data.size();
    }
    @Override
    public int getColumnCount() {
        return colNames.length;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return data.get(rowIndex).getName();
        }
        if (columnIndex == 1) {
            return data.get(rowIndex).getAge();
        }
        if (columnIndex == 2) {
            return data.get(rowIndex).getVisit();
        }
        if (columnIndex == 3) {
            return data.get(rowIndex).getGrade(0);
        }
        if (columnIndex == 4) {
            return data.get(rowIndex).getGrade(1);
        }
        if (columnIndex == 5) {
            return data.get(rowIndex).getGrade(2);
        }
        if (columnIndex == 6) {
            return data.get(rowIndex).getGrade(3);
        }
        if (columnIndex == 7) {
            return data.get(rowIndex).getGrade(4);
        }
        if (columnIndex == 8) {
            return data.get(rowIndex).getGrade(5);
        }
        if (columnIndex == 9) {
            return data.get(rowIndex).getGrade(6);
        }
        if (columnIndex == 10) {
            return data.get(rowIndex).getGrade(7);
        }
        if (columnIndex == 11) {
            return data.get(rowIndex).getGrade(8);
        }
        if (columnIndex == 12) {
            return data.get(rowIndex).getGrade(9);
        }
        return null;
    }
    public String getColumnName(int columnIndex) {
        return colNames[columnIndex];
    }

    public Class<?> getColumnClass(int columnIndex) {
        return colClasses[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            data.get(rowIndex).setName((String) aValue);
        }
        if (columnIndex == 1) {
            data.get(rowIndex).setAge((int) aValue);

        }
        if (columnIndex == 2) {
            data.get(rowIndex).setVisit();
        }
        if(columnIndex == 3){
            data.get(rowIndex).setGrade(0, (Integer) aValue);
        }
        if(columnIndex == 4){
            data.get(rowIndex).setGrade(1, (Integer) aValue);
        }
        if(columnIndex == 5){
            data.get(rowIndex).setGrade(2, (Integer) aValue);
        }
        if(columnIndex == 6){
            data.get(rowIndex).setGrade(3, (Integer) aValue);
        }
        if(columnIndex == 7){
            data.get(rowIndex).setGrade(4, (Integer) aValue);
        }
        if(columnIndex == 8){
            data.get(rowIndex).setGrade(5, (Integer) aValue);
        }
        if(columnIndex == 9){
            data.get(rowIndex).setGrade(6, (Integer) aValue);
        }
        if(columnIndex == 10){
            data.get(rowIndex).setGrade(7, (Integer) aValue);
        }
        if(columnIndex == 11){
            data.get(rowIndex).setGrade(8, (Integer) aValue);
        }
        if(columnIndex == 12){
            data.get(rowIndex).setGrade(9, (Integer) aValue);
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}

