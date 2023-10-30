package view.pages;
import view.components.*;
import model.Internship;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SearchInternshipGUI extends InterfaceApp {
    private final JTextField searchStringField = new DefaultTextField();
    private final InternshipTableModel results = new InternshipTableModel();
    private ArrayList<Internship> internships;

    public SearchInternshipGUI() {
        super("Stages");

        // TODO : Get internships from db
        internships = new ArrayList<>();
        internships.add(new Internship(
                "Gestion et conduite d'un chantier d'eau potable",
                "Etablissement et gestion planning de chantier, gestion financière de chantier - appel d'offre, organisation des moyens matériels du chantier (machine et humains, relation client et compte rendu de chantier, visite de chantier et journée terrain",
                "VIENNE",
                "22 Rue de la Sévenne",
                "PeiP1",
                2023,
                "ALBERTAZZI",
                "Chambéry",
                "France"
        ));
        internships.add(new Internship(
                "Production de lames d'escrimes pour la compétition",
                "Meulage, Polissage, Dressage",
                "LE CHAMBON FEUGEUROLLES",
                "Rue Antoine Lavoisier",
                "PeiP1",
                2023,
                "BLAISE FRERES",
                "Chambéry",
                "France"
        ));

        JButton searchButton = new PrimaryButton("Rechercher");

        searchButton.addActionListener(e -> updateResultPanel());

        JPanel searchPanel = new DefaultPanel(50);
        searchPanel.add(searchStringField);
        searchPanel.add(searchButton);
        mainPanel.add(searchPanel);

        DefaultPanel resultsPanel = new DefaultPanel(50);
        resultsPanel.add(new TitleLabel("Résultats"));
        resultsPanel.add(new JTable(results));
        mainPanel.add(resultsPanel);

        // Add internship to the table
        updateResultPanel();
        // Makes the window scrollable
        endFrameCreation();
    }

    // TODO : Finish this
    public void updateResultPanel() {
        String search = searchStringField.getText();
//        if(internships.isEmpty())
//            results.add(new DefaultLabel("Pas de résultats"));

        for (Internship internship : internships) {
            results.add(internship);
        }
    }
}


