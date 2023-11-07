package view.pages;
import model.search.ISearch;
import model.search.FilterBehavior;
import model.search.SearchBehavior;
import view.components.*;
import model.Internship;
import javax.swing.*;
import java.util.ArrayList;

public class SearchInternshipGUI extends InterfaceApp {
    private final JTextField searchStringField = new DefaultTextField();
    private final InternshipTableModel results = new InternshipTableModel();
    private final JComboBox<String> specialityComboBox;
    private final JComboBox<String> siteComboBox;
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
        String[] options = {"", "PeiP1", "IDU3", "IDU4", "SNI3", "SNI4"};
        specialityComboBox = new JComboBox<>(options);
        siteComboBox = new JComboBox<>(new String[]{"", "Annecy", "Chambéry"});
        searchPanel.add(new TitleLabel("Recherche de stage"));
        searchPanel.add(searchStringField);
        searchPanel.add(new DefaultLabel("Spécialité"));
        searchPanel.add(specialityComboBox);
        searchPanel.add(new DefaultLabel("Site"));
        searchPanel.add(siteComboBox);
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
        String formation = specialityComboBox.getSelectedItem().toString();
        String site = siteComboBox.getSelectedItem().toString();
        results.clear();

        ArrayList<ISearch> temp = new ArrayList<>(internships);
        temp.addAll(internships);
        ISearch searchBehavior = new SearchBehavior(temp);

        if(!search.isEmpty())
            searchBehavior = new FilterBehavior(searchBehavior, "title", search);
        if(!formation.isEmpty())
            searchBehavior = new FilterBehavior(searchBehavior, "speciality", formation);
        if(!site.isEmpty())
            searchBehavior = new FilterBehavior(searchBehavior, "site", site);

        ArrayList<ISearch> shownInternships = searchBehavior.apply();

        for (ISearch internship : shownInternships) {
            results.add((Internship)internship);
        }
    }
}


