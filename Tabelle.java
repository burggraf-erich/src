import javax.swing.*;

import java.text.*;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class Tabelle {
	public static void main (String[] args) {
	Object weinliste[][] = {
			{"Scheurebe", new Double(21.10)}, {"Riesling", new Double(27.50)},{"Weissburgunder", new Double(26.60)}
	};
	Object columnNames[] = {"Bestellnr.","Rebsorte","Abbildung","Sektpreis/Liter","Flaschengr.","Verkaufte Flaschen","Gesamtpreis","Lieferung"};
	int columnWidths[] = {70,140,70,100,90,120,130,70};
	ImageIcon weinIcons[] = new ImageIcon[weinliste.length];
	weinIcons[0] = new ImageIcon("C:/Users/burggraf/Downloads/gif1.gif");
	weinIcons[1] = new ImageIcon("C:/Users/burggraf/Downloads/gif2.gif");
	weinIcons[2] = new ImageIcon("C:/Users/burggraf/Downloads/gif3.gif");
Object tableData[][] = {
		{new Integer(1), weinliste[0][0],weinIcons[0],weinliste[0][1],new Double(0.2), new Integer(200), new Double(0.2*200), new Boolean(true)},
		{new Integer(2), weinliste[1][0],weinIcons[1],weinliste[1][1],new Double(0.2), new Integer(200), new Double(0), new Boolean(false)},
		{new Integer(3), weinliste[2][0],weinIcons[2],weinliste[2][1],new Double(0.2), new Integer(200), new Double(0), new Boolean(true)},
};
/*JTable weinTableGr= new JTable(tableData, columnNames);
JFrame f = new JFrame();
JScrollPane sc = new JScrollPane(weinTableGr);
f.add(sc);
f.pack();
f.setVisible(true); */
TableModel dataMod = new AbstractTableModel() {
	public int getColumnCount() {return columnNames.length;}
	public int getRowCount() {return tableData.length;}
	public Object getValueAt (int row, int col) {
		return tableData[row][col];}
	public String getColumnName (int column) {
		return (String)columnNames[column];}

public Class getColumnClass (int col){
	return getValueAt(0,col).getClass();
}
public boolean isCellEditable(int row, int col) {
	if (col==1|| col==3 || col==4 ||col == 5 ||col==7) return true; else return false;
}
public void setValueAt (Object aValue, int row, int column) {
	tableData[row][column] = aValue;
	if (column ==3 || column == 4 || column == 5) {
		double preis = (Double.valueOf(getValueAt(row,3).toString())).doubleValue();
		double groesse = (Double.valueOf(getValueAt(row,4).toString())).doubleValue();
		double flaschen = (Double.valueOf(getValueAt(row,5).toString())).doubleValue();
		setValueAt(new Double(preis*groesse*flaschen),row,6);
	}
fireTableDataChanged();
}
};

JTable weinTable = new JTable(dataMod);

DecimalFormat eurFormat = new DecimalFormat("#,##0.00 EUR");
DecimalFormat literFormat = new DecimalFormat("0.0 l");
DefaultTableCellRenderer eurRenderer = new DefaultTableCellRenderer() {
	public void setValue(Object value) {
		Double d = (Double)value;
		setText (eurFormat.format(d.doubleValue()));}};
DefaultTableCellRenderer literRenderer = new DefaultTableCellRenderer() {
			public void setValue(Object value) {
				Double d = (Double)value;
				setText (literFormat.format(d.doubleValue()));}};		
eurRenderer.setHorizontalAlignment(JLabel.RIGHT);
literRenderer.setHorizontalAlignment(JLabel.RIGHT);
weinTable.getColumn("Sektpreis/Liter").setCellRenderer(literRenderer);
weinTable.getColumn("Gesamtpreis").setCellRenderer(eurRenderer);

JComboBox weinSorte = new JComboBox();
for (int i=0; i<weinliste.length; i++) {
	weinSorte.addItem(weinliste[i][0]);
}
weinTable.getColumn("Rebsorte").setCellEditor(new DefaultCellEditor(weinSorte));

JComboBox flaschen = new JComboBox();
flaschen.addItem(new Double(0.2));
flaschen.addItem(new Double(0.75));
flaschen.addItem(new Double(1.0));
weinTable.getColumn("Flaschengr.").setCellEditor(new DefaultCellEditor(flaschen));

for (int i = 0;i<dataMod.getColumnCount();i++)
	weinTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);

for (int i = 0;i<dataMod.getRowCount();i++)
dataMod.setValueAt(dataMod.getValueAt(i, 1),i,1);


JScrollPane sc = new JScrollPane(weinTable);
JFrame f = new JFrame("Erichs Weinhandel");
f.add(sc);
f.pack();
f.setVisible(true);



	}}
