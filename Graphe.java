package graphealgo;

import java.util.ArrayList;
import java.util.Collections;

public class Graphe {

    private ArrayList<ArrayList<Integer>> successeurs;
    private ArrayList<ArrayList<Double>> poidsLiens;
    private ArrayList<String> informationsSommets;
    private boolean oriente;

    public Graphe(boolean _oriente) {
        successeurs = new ArrayList<>();
        successeurs.add(null);
        poidsLiens = new ArrayList<>();
        poidsLiens.add(null);
        informationsSommets = new ArrayList<>();
        informationsSommets.add(null);
        oriente = _oriente;
    }

    public Graphe() {
        this(true);
    }

    public Graphe(Graphe G) {
        successeurs = new ArrayList<>(G.successeurs.size());
        poidsLiens = new ArrayList<>(G.poidsLiens.size());
        for (ArrayList<Integer> S : G.successeurs) {
            successeurs.add((ArrayList<Integer>) S.clone());
        }
        for (ArrayList<Double> P : G.poidsLiens) {
            poidsLiens.add((ArrayList<Double>) P.clone());
        }
        informationsSommets = (ArrayList<String>) G.informationsSommets.clone();
        oriente = G.oriente;
    }

    public Graphe(int nbSommets, ArrayList<Lien> liens, boolean _oriente) {
        oriente = _oriente;
        successeurs = new ArrayList<>(nbSommets + 1);
        poidsLiens = new ArrayList<>(nbSommets + 1);
        informationsSommets = new ArrayList<>(Collections.nCopies(nbSommets + 1, ""));
        int[] taillesSuccesseurs = new int[nbSommets + 1];
        for (Lien a : liens) {
            taillesSuccesseurs[a.depart]++;
        }
        taillesSuccesseurs[0] = 0;
        for (int i : taillesSuccesseurs) {
            successeurs.add(new ArrayList<>(i));
            poidsLiens.add(new ArrayList<>(i));
        }
        for (Lien a : liens) {
            successeurs.get(a.depart).add(a.destination);
            poidsLiens.get(a.depart).add(a.poids);
        }
    }

    public Graphe(int nbSommets, ArrayList<Lien> liens) {
        this(nbSommets, liens, true);
    }

    public Graphe(int[] fileSuccesseurs, boolean _oriente) {
        this(_oriente);
        for (int i = 1; i < fileSuccesseurs.length; i++) {
            ArrayList<Integer> liste = new ArrayList<>();
            while (fileSuccesseurs[i] > 0) {
                liste.add(fileSuccesseurs[i]);
                i++;
            }
            ArrayList<Double> poids = new ArrayList<>(Collections.nCopies(liste.size(), 1.0));
            successeurs.add(liste);
            poidsLiens.add(poids);
        }
        informationsSommets = new ArrayList<>(Collections.nCopies(successeurs.size(), ""));

    }

    public Graphe(int[] fileSuccesseurs) {
        this(fileSuccesseurs, true);
    }

    public Graphe(int[][] matriceAdjacente, boolean oriente) {
        this(matriceAdjacente.length - 1, matriceAdjacenteVersListeLiens(matriceAdjacente, oriente), oriente);
    }

    public Graphe(int[][] matriceAdjacente) {
        this(matriceAdjacente, true);
    }

    public static ArrayList<Lien> matriceAdjacenteVersListeLiens(int[][] matriceAdjacente, boolean oriente) {
        ArrayList<Lien> aL = new ArrayList<>();
        if (oriente) {
            for (int i = 1; i < matriceAdjacente.length; i++) {
                for (int j = 1; j < matriceAdjacente[i].length; j++) {
                    if (matriceAdjacente[i][j] != 0) {
                        aL.add(new Lien(i, j));
                    }
                }
            }
        } else {
            for (int i = 1; i < matriceAdjacente.length; i++) {
                for (int j = 1; j <= i; j++) {
                    if (matriceAdjacente[i][j] != 0) {
                        aL.add(new Lien(i, j));
                    }
                }
            }
        }

        return aL;
    }

    public int getNombreSommets() {
        return successeurs.size() - 1;
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

    public double getPoids(int depart, int destination) {
        double valeurSiNonPresent = -1.0;
        int index = successeurs.get(depart).indexOf(destination);
        if (index == -1) {
            if (!oriente) {
                index = successeurs.get(destination).indexOf(depart);
                if (index == -1) {
                    return valeurSiNonPresent;
                } else {
                    return poidsLiens.get(destination).get(index);
                }
            } else {
                return valeurSiNonPresent;
            }
        } else {
            return poidsLiens.get(depart).get(index);
        }
    }

    public double getPoids(Lien L) {
        return getPoids(L.depart, L.destination);
    }

    public String getInformation(int id) {
        return informationsSommets.get(id);
    }

    public void setInformation(int id, String info) {
        informationsSommets.set(id, info);
    }

    public boolean setPoids(Lien L) {
        int index = successeurs.get(L.depart).indexOf(L.destination);
        if (index == -1) {
            if (!oriente) {
                index = successeurs.get(L.destination).indexOf(L.depart);
                if (index == -1) {
                    return false;
                } else {
                    poidsLiens.get(L.destination).set(index, L.poids);
                    return true;
                }
            } else {
                return false;
            }
        } else {
            poidsLiens.get(L.depart).set(index, L.poids);
            return true;
        }
    }

    // Les parties suivantes sont necessaires pour modifier dynamiquement le graphe :
    public void addSommet(String info) {
        successeurs.add(new ArrayList<>());
        poidsLiens.add(new ArrayList<>());
        informationsSommets.add(info);
    }

    public void addSommet() {
        addSommet("");
    }

    public boolean removeSommet(int id) {
        if (id >= successeurs.size()) {
            return false;
        }
        successeurs.remove(id);
        poidsLiens.remove(id);
        informationsSommets.remove(id);
        for (int j = 1; j < successeurs.size(); j++) {
            for (int i = 0; i < successeurs.get(j).size(); i++) {
                if (successeurs.get(j).get(i) >= id) {
                    if (successeurs.get(j).get(i) == id) {
                        successeurs.get(j).remove(i);
                        poidsLiens.get(j).remove(i);
                    } else {
                        successeurs.get(j).set(i, successeurs.get(j).get(i) - 1);
                    }
                }
            }
        }

        return true;
    }

    public boolean addLien(Lien L) {
        if (successeurs.size() <= L.depart || successeurs.size() <= L.destination) {
            return false;
        }
        successeurs.get(L.depart).add(L.destination);
        poidsLiens.get(L.depart).add(L.poids);

        return true;
        // renvoie faux si le depart ou la destination de L ne correspondent pas a un sommet du graphe
    }

    public boolean removeLien(Lien L) {
        if (successeurs.size() <= L.depart || successeurs.size() <= L.destination) {
            return false;
        }
        int index = successeurs.get(L.depart).indexOf(L.destination);
        boolean b1 = index != -1, b2 = false;
        if (b1) {
            successeurs.get(L.depart).remove(index);
            poidsLiens.get(L.depart).remove(index);
        }
        if (!oriente) {
            index = successeurs.get(L.destination).indexOf(L.depart);
            b2 = index != -1;
            if (b2) {
                successeurs.get(L.destination).remove(index);
                poidsLiens.get(L.destination).remove(index);
            }
        }

        return b1 || b2;  // renvoie faux si un lien equivalent a L n'est pas trouve
    }

    public boolean changeSensLien(Lien L) {
        if (successeurs.size() <= L.depart || successeurs.size() <= L.destination || !oriente) {
            return false;
        }
        int index = successeurs.get(L.depart).indexOf(L.destination);
        if (index != -1) {
            successeurs.get(L.depart).remove(index);
            double poids = poidsLiens.get(L.depart).remove(index);
            successeurs.get(L.destination).add(L.depart);
            poidsLiens.get(L.destination).add(poids);

            return true;
        } else {
            return false;
        }
        // renvoie faux si un lien equivalent a L n'est pas trouve
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
