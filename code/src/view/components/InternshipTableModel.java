package view.components;

import model.Internship;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class InternshipTableModel extends AbstractTableModel {
    private List<Internship> internships = new ArrayList<>();
    private String[] columnNames = {"Name", "Class", "Grade", "Age"};

    @Override
    public int getRowCount() {
        return internships.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Internship internship = internships.get(row);
        return switch (column) {
            case 0 -> internship.getTitle();
            case 1 -> internship.getSpeciality();
            case 2 -> internship.getTown();
            case 3 -> internship.getDescription();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void add(Internship internship) {
        internships.add(internship);
        fireTableRowsInserted(internships.size() - 1, internships.size() - 1);
    }
}