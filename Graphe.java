package graphealgo;

import java.util.ArrayList;

public class Graphe {

    private ArrayList<ArrayList<Integer>> successeurs;
    private ArrayList<String> informationsSommets; // On en aura besoin plus tard
    private boolean oriente;
    
    public Graphe(boolean _oriente) {
        successeurs = new ArrayList<ArrayList<Integer>>();
        successeurs.add(null);
        oriente = _oriente;
    }

    public Graphe() {
        this(true);
    }

    public Graphe(Graphe G) {
        successeurs = new ArrayList<ArrayList<Integer>>(G.successeurs.size());
        for (ArrayList<Integer> S : G.successeurs) {
            successeurs.add((ArrayList<Integer>) S.clone());
        }
        oriente = G.oriente;
    }

    public Graphe(int nbSommets, ArrayList<Lien> liens, boolean _oriente) {
        oriente = _oriente;
        successeurs = new ArrayList<ArrayList<Integer>>(nbSommets + 1);
        int[] taillesSuccesseurs = new int[nbSommets + 1];
        for (Lien a : liens) {
            taillesSuccesseurs[a.depart]++;
        }
        taillesSuccesseurs[0] = 0;
        for (int i : taillesSuccesseurs) {
            successeurs.add(new ArrayList<Integer>(i));
        }
        for (Lien a : liens) {
            successeurs.get(a.depart).add(a.destination);
        }
    }

    public Graphe(int nbSommets, ArrayList<Lien> liens) {
        this(nbSommets, liens, true);
    }

    public Graphe(int[] fileSuccesseurs, boolean _oriente) {
        oriente = _oriente;
        successeurs = new ArrayList<ArrayList<Integer>>();
        successeurs.add(null);
        for (int i = 1; i < fileSuccesseurs.length; i++) {
            ArrayList<Integer> liste = new ArrayList<Integer>();
            while (fileSuccesseurs[i] > 0) {
                liste.add(fileSuccesseurs[i]);
                i++;
            }
            successeurs.add(liste);
        }
    }

    public Graphe(int[] fileSuccesseurs) {
        this(fileSuccesseurs, true);
    }

    public Graphe(int[][] matriceAdjacente, boolean oriente) {
        this(matriceAdjacente.length - 1, MatriceVersListeLiens(matriceAdjacente, oriente), oriente);
    }

    public Graphe(int[][] matriceAdjacente) {
        this(matriceAdjacente, true);
    }

    public static ArrayList<Lien> MatriceVersListeLiens(int[][] MatriceAdjacence, boolean oriente) {
        ArrayList<Lien> aL = new ArrayList<Lien>();
        if (oriente) {
            for (int i = 1; i < MatriceAdjacence.length; i++) {
                for (int j = 1; j < MatriceAdjacence[i].length; j++) {
                    if (MatriceAdjacence[i][j] != 0) {
                        aL.add(new Lien(i, j));
                    }
                }
            }
        } else {
            for (int i = 1; i < MatriceAdjacence.length; i++) {
                for (int j = 1; j <= i; j++) {
                    if (MatriceAdjacence[i][j] != 0) {
                        aL.add(new Lien(i, j));
                    }
                }
            }
        }

        return aL;
    }

    public int[] getFileSuccesseurs() {
        int tailleFs = successeurs.size();
        for (ArrayList<Integer> listeSommet : successeurs) {
            tailleFs += listeSommet.size();
        }
        int[] fs = new int[tailleFs];
        fs[0] = tailleFs - 1;

        int i = 1;
        for (int j = 1; j < successeurs.size(); j++) {
            for (int sommet : successeurs.get(j)) {
                fs[i++] = sommet;
            }
            fs[i++] = 0;
        }

        return fs;
    }

    public int[] getAdressesPremierSuccesseur() {
        int tailleAps = successeurs.size();
        int[] aps = new int[tailleAps];
        aps[0] = tailleAps - 1;

        int indiceFs = 1, idSommet = 1;
        for (int j = 1; j < successeurs.size(); j++) {
            aps[idSommet] = indiceFs;
            indiceFs += successeurs.get(idSommet).size() + 1;
            idSommet++;
        }

        return aps;
    }

    public int[][] getMatriceAdjacente() {
        int[][] matrice = new int[successeurs.size()][successeurs.size()];
        for (int depart = 1; depart < successeurs.size(); depart++) {
            for (int i : successeurs.get(depart)) {
                matrice[depart][i] = 1;
                if (!oriente) {
                    matrice[i][depart] = 1;
                }
            }
        }

        return matrice;
    }

    public boolean isOriente() {
        return oriente;
    }

    // Les parties suivantes sont nécessaires pour modifier dynamiquement le graphe :
    public void addSommet() {
        successeurs.add(new ArrayList<Integer>());
    }

    public boolean removeSommet(int id) {
        if (id >= successeurs.size()) {
            return false;
        }
        successeurs.remove(id);
        for (int j = 1; j < successeurs.size(); j++) {
            for (int i = 0; i < successeurs.get(j).size(); i++) {
                if (successeurs.get(j).get(i) >= id) {
                    if (successeurs.get(j).get(i) == id) {
                        successeurs.get(j).remove(i);
                    } else {
                        successeurs.get(j).set(i, successeurs.get(j).get(i) - 1);
                    }
                }
            }
        }

        return true;
    }

    public boolean addLien(Lien A) {
        if (successeurs.size() <= A.depart || successeurs.size() <= A.destination) {
            return false;
        }
        successeurs.get(A.depart).add(A.destination);

        return true;
        // renvoie faux si le départ ou la destination de A ne correspondent pas à un sommet du graphe
    }

    public boolean removeLien(Lien A) {
        if (successeurs.size() <= A.depart || successeurs.size() <= A.destination) {
            return false;
        }
        if (oriente) {

            return successeurs.get(A.depart).remove(new Integer(A.destination));
        } else {
            boolean b1 = successeurs.get(A.depart).remove(new Integer(A.destination));
            boolean b2 = successeurs.get(A.destination).remove(new Integer(A.depart));

            return b1 || b2;
        }
        // renvoie faux si un lien equivalent à A n'est pas trouvé
    }

    public boolean changeSensLien(Lien A) {
        if (successeurs.size() <= A.depart || successeurs.size() <= A.destination || !oriente) {
            return false;
        }
        successeurs.get(A.depart).remove(new Integer(A.destination));
        successeurs.get(A.destination).add(new Integer(A.depart));

        return true;
        // renvoie faux si un lien equivalent à A n'est pas trouvé
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
