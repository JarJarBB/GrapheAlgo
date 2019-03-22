package graphealgo;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UIGrapheAlgo extends JFrame {

    private JPanel ajoutLien;
    private JButton ajoutSommet, boutonAjoutLien;
    private JLabel texteGraphe;
    private JTextField departLien, destinationLien;
    private Graphe G;

    public UIGrapheAlgo() {
        super("Graphe");

        G = new Graphe();

        miseEnPlaceComposants();
        miseEnPlaceEcouteurs();
        miseEnPlaceUI();
    }

    private void miseEnPlaceComposants() {
        //Bouton pour "Ajouter sommet" :
        ajoutSommet = new JButton("Ajouter sommet");

        //Affichage du graphe :
        texteGraphe = new JLabel(G.toString());
        texteGraphe.setPreferredSize(new Dimension(200, 100));

        // Bouton et champs pour "Ajouter lien" :
        ajoutLien = new JPanel();
        ajoutLien.setLayout(new GridLayout(1, 3));
        departLien = new JTextField();
        destinationLien = new JTextField();
        boutonAjoutLien = new JButton("Ajouter lien");
        ajoutLien.add("depart", departLien);
        ajoutLien.add("destination", destinationLien);
        ajoutLien.add("boutonAjoutLien", boutonAjoutLien);

        // Ajout des composants à la fenêtre principale
        getContentPane().add(texteGraphe, "North");
        getContentPane().add(ajoutLien, "South");
        getContentPane().add(ajoutSommet, "Center");
    }

    private void miseEnPlaceEcouteurs() {
        ActionListener actionAjoutSommet = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                G.addSommet();
                UIGrapheAlgo.this.texteGraphe.setText(G.toString());
            }
        };
        ajoutSommet.addActionListener(actionAjoutSommet);

        ActionListener actionAjoutLien = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                G.addLien(new Lien(Integer.parseInt(departLien.getText()), Integer.parseInt(destinationLien.getText())));
                UIGrapheAlgo.this.texteGraphe.setText(G.toString());
            }
        };
        boutonAjoutLien.addActionListener(actionAjoutLien);

    }

    private void miseEnPlaceUI() {
        setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
