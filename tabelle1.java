import javax.swing.*;

import java.util.Arrays;
import java.text.*;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableCellRenderer;
public class tabelle1 {

	public static void main (String[] args) {
		
	Object columnNames[] = {"lfd.Nr.","Land","PLZ","Ort","Destrikt","Straße","Hausnummer"};
	Object daten[] = {new Integer(1),"D","85604","Zorneding","Bayern","Ingelsberg","21c", new Integer(1),"F","93033","Nürnberg","Bayern","Müllstraße","1a"};
	Object tableDat[][] = {{daten[0],daten[1],daten[2],daten[3],daten[4],daten[5],daten[6]},
			{daten[7],daten[8],daten[9],daten[10],daten[11],daten[12],daten[13]}};
	
	Object plzort[][] = {{"80000","München"},{"80001","Nürnberg"},{"80002","Chemnitz"}};
	TableModel dataModel = new AbstractTableModel() {
	
	public int getColumnCount() {return columnNames.length;}
	public int getRowCount() {return tableDat.length;}
	public Object getValueAt (int row, int col){
		return tableDat[row][col];}
	public String getColumnName (int column){return (String)columnNames[column];}
	public boolean isCellEditable (int row, int col)
	{if (col == 2 ) return true; else return false;}

	public void setValueAt (Object aValue, int row, int column) {
		tableDat[row][column] = aValue;
		System.out.println(aValue);
		String text = "["+aValue;
	if (column==2) {
	for (int i = 0; i<plzort.length;i++){
		System.out.println(Arrays.toString(plzort[i]).startsWith(text));
	if (Arrays.toString(plzort[i]).startsWith(text)) {setValueAt(plzort[i][1],row,3);}
	}
	}
	
	
	
fireTableDataChanged();
	}
public Class getColumnClass (int c) {
return getValueAt(0,c).getClass();}
	
	
	};
	
	JTable adr = new JTable(dataModel);
	JFrame win = new JFrame();
	
	
	JScrollPane adrtab = new JScrollPane(adr);
	win.add(adrtab);
	win.setTitle("Erich's Adressenliste");
	win.pack();
	win.setVisible(true);
	
JComboBox PLZC = new JComboBox();
	for (int i = 0; i<plzort.length;i++)
	PLZC.addItem(plzort[i][0]);
	adr.getColumn("PLZ").setCellEditor(new DefaultCellEditor(PLZC));
	}
}
