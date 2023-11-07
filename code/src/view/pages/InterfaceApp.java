package view.pages;

import view.components.MainPanel;

import javax.swing.*;
import java.io.Serial;

abstract public class InterfaceApp extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
    protected static final int WIDTH = 1000;
    protected static final int HEIGHT = 700;
    ImageIcon logo = new ImageIcon("code/src/images/logo.png");
    protected JPanel mainPanel;

    public InterfaceApp(String title) {
        this.mainPanel = new MainPanel();
        setTitle(title);
        setSize(WIDTH, HEIGHT);
        // Sets window top left corner image
        setIconImage(logo.getImage());
        // Centers the window on the screen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Method that can be called by child classes when they finish adding component to the window
     * For now, it only makes the window scrollable.
     * (To make the window not scrollable, use setContentPane(mainPanel) instead).
     */
    protected void endFrameCreation() {
        // Makes the mainPanel scrollable
        JScrollPane scrollPanel = new JScrollPane(mainPanel);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPanel.getVerticalScrollBar().setUnitIncrement(50);
        scrollPanel.getHorizontalScrollBar().setUnitIncrement(50);
        setContentPane(scrollPanel);
    }

    protected void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur de saisie", JOptionPane.INFORMATION_MESSAGE);
    }
}
