package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
public class modeltabeldatamv extends AbstractTableModel {

    List<datamv> dp;

    public modeltabeldatamv(List<datamv> dp) {
        this.dp = dp;
    }

    @Override
    public int getRowCount() {
        return dp.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        // Mengembalikan nama kolom berdasarkan indeks kolom
        switch (column) {
            case 0:
                return "Judul";
            case 1:
                return "Alur";
            case 2:
                return "Penokohan";
            case 3:
                return "Akting";
            case 4:
                return "Nilai";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        // Mengembalikan nilai dari sel yang ditentukan dalam tabel
        datamv mv = dp.get(row);
        switch (column) {
            case 0:
                return mv.getJudul();
            case 1:
                return mv.getAlur();
            case 2:
                return mv.getPenokohan();
            case 3:
                return mv.getAkting();
            case 4:
                return (mv.getAlur() + mv.getPenokohan() + mv.getAkting()) / 3;
            default:
                return null;
        }
    }
}
