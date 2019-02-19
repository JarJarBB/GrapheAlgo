package graphealgo;

import java.util.ArrayList;

public class Graphe {

    private ArrayList<ArrayList<Integer>> successeurs;
    private ArrayList<String> informationsSommets; // On en aura besoin plus tard
    private boolean oriente;

    public Graphe(Graphe G) {
        successeurs = new ArrayList<ArrayList<Integer>>(G.successeurs.size());
        for (ArrayList<Integer> S : G.successeurs) {
            successeurs.add((ArrayList<Integer>) S.clone());
        }
        oriente = G.oriente;
    }

    public Graphe(int nbSommets, ArrayList<Arc> arcs, boolean _oriente) {
        oriente = _oriente;
        successeurs = new ArrayList<ArrayList<Integer>>(nbSommets + 1);
        int[] taillesSuccesseurs = new int[nbSommets + 1];
        for (Arc a : arcs) {
            taillesSuccesseurs[a.depart]++;
        }
        taillesSuccesseurs[0] = 0;
        for (int i : taillesSuccesseurs) {
            successeurs.add(new ArrayList<Integer>(i));
        }
        for (Arc a : arcs) {
            successeurs.get(a.depart).add(a.destination);
        }
    }

    public Graphe(int nbSommets, ArrayList<Arc> arcs) {
        this(nbSommets, arcs, true);
    }

    public Graphe(int[] fileSuccesseurs, int[] adressesPremierSuccesseur, boolean oriente) {
        throw new UnsupportedOperationException();
    }

    public Graphe(int[] fileSuccesseurs, int[] adressesPremierSuccesseur) {
        this(fileSuccesseurs, adressesPremierSuccesseur, true);
    }

    public Graphe(int[][] matriceAdjacente, boolean oriente) {
        throw new UnsupportedOperationException();
    }

    public Graphe(int[][] matriceAdjacente) {
        this(matriceAdjacente, true);
    }

    public int[] getFileSuccesseurs() {
        throw new UnsupportedOperationException();
    }

    public int[] getAdressesPremierSuccesseur() {
        throw new UnsupportedOperationException();
    }

    public int[][] getMatriceAdjacente() {
        throw new UnsupportedOperationException();
    }

    public boolean isOriente() {
        return oriente;
    }

    // Les parties suivantes sont nécessaires pour modifier dynamiquement le graphe :
    public void addSommet() {
        throw new UnsupportedOperationException();
    }

    public boolean removeSommet(int id) {
        throw new UnsupportedOperationException();
        // doit également supprimer toutes les arcs reliées au sommet id
        // renvoie faux si id ne correspond à aucun sommet
    }

    public boolean addArc(Arc A) {
        throw new UnsupportedOperationException();
        // renvoie faux si le départ ou la destination de A ne correspondent pas à un sommet du graphe
    }

    public boolean removeArc(Arc A) {
        throw new UnsupportedOperationException();
        // renvoie faux si un arc equivalent à A n'est pas trouvé
    }

    public boolean changeSensArc(Arc A) {
        throw new UnsupportedOperationException();
        // renvoie faux si un arc equivalent à A n'est pas trouvé
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (oriente) {
            sb.append("Orienté ");
        } else {
            sb.append("Non orienté ");
        }
        for (int i = 1; i < successeurs.size(); i++) {
            sb.append(" #").append(i).append(": ");
            for (Integer I : successeurs.get(i)) {
                sb.append(I).append(" ");
            }
        }
        return sb.toString();
    }

}
