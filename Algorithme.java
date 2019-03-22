package graphealgo;

import java.util.ArrayList;
import java.util.Arrays;

public final class Algorithme {

    private Algorithme() {
    }

    public static int[][] matriceDesDistances(Graphe G) {
        int[][] distances = new int[G.getNombreSommets() + 1][];
        distances[0] = new int[G.getNombreSommets() + 1];
        Arrays.fill(distances[0], -1);
        for (int i = 1; i <= G.getNombreSommets(); i++) {
            distances[i] = distancesUnSommet(G, i);
        }

        return distances;
    }

    public static int[] distancesUnSommet(Graphe G, int idSommet) {
        int[] distances = new int[G.getNombreSommets() + 1];
        Arrays.fill(distances, -1);
        int[] fileAttente = new int[G.getNombreSommets()];
        fileAttente[0] = idSommet;
        int[] fileSuccesseurs = G.getFileSuccesseurs();
        int[] adressesPremierSuccesseur = G.getAdressesPremierSuccesseur();
        int tete = 0, queue = 0, pDernier = 0, distance = 0;
        distances[idSommet] = distance++;

        while (tete <= pDernier) {
            while (tete <= queue) {
                for (int i = adressesPremierSuccesseur[fileAttente[tete]]; fileSuccesseurs[i] != 0; ++i) {
                    if (distances[fileSuccesseurs[i]] == -1) {
                        distances[fileSuccesseurs[i]] = distance;
                        fileAttente[++pDernier] = fileSuccesseurs[i];
                    }
                }
                ++tete;
            }
            queue = pDernier;
            ++distance;
        }

        return distances;
    }

    public static int[] rangDesSommets(Graphe G) {
        throw new UnsupportedOperationException();
    }

    public static Graphe grapheReduitSelonTarjan(Graphe G) {
        throw new UnsupportedOperationException();
    }

    public static ArrayList<int[]> basesDuGrapheSelonTarjan(Graphe G) {
        throw new UnsupportedOperationException();
    }

    public static ArrayList<String[]> cheminsCritiques(Graphe G) {
        throw new UnsupportedOperationException();
    }

    public static double[] datesAuPlusTard(Graphe G) {
        throw new UnsupportedOperationException();
    }

    public static int[] cheminLePlusCourtSelonDjikstra(Graphe G, int depart, int destination) {
        throw new UnsupportedOperationException();
    }

    public static Graphe grapheMinimalSelonKruskal(Graphe G) {
        throw new UnsupportedOperationException();
    }

    public static Graphe codageDePruferVersGraphe(int[] codeDePrufer) {
        throw new UnsupportedOperationException();
    }

    public static int[] grapheVersCodageDePrufer(Graphe G) {
        throw new UnsupportedOperationException();
    }

}
