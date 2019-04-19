package graphealgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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

//--------------------------------------------
    private static int[] demiDegresInterieurs(int[] fs, int[] aps) {
        int[] ddi = new int[aps[0] + 1];
        ddi[0] = aps[0];
        for (int i = 1; i <= fs[0]; i++) {
            if (fs[i] != 0) {
                ddi[fs[i]]++;
            }
        }

        return ddi;
    }

    private static void empiler(int x, int[] pilch) {
        pilch[x] = pilch[0];
        pilch[0] = x;
    }

    public static int[] rangDesSommets(Graphe G) {
        int[] fs = G.getFileSuccesseurs();
        int[] aps = G.getAdressesPremierSuccesseur();
        int n = aps[0], taillefs = fs[0], s, k, h, t;
        int[] rang = new int[n + 1];
        int[] ddi = demiDegresInterieurs(fs, aps);
        int[] pilch = new int[n + 1];
        int[] prem = new int[n + 1];

        pilch[0] = 0;
        for (s = 1; s <= n; s++) {
            rang[s] = -1;
            if (ddi[s] == 0) {
                empiler(s, pilch);
            }
        }

        k = -1;
        s = pilch[0];
        prem[0] = s;
        while (pilch[0] > 0) {
            k++;
            pilch[0] = 0;
            while (s > 0) {
                rang[s] = k;
                h = aps[s];
                t = fs[h];
                while (t > 0) {
                    ddi[t]--;
                    if (ddi[t] == 0) {
                        empiler(t, pilch);
                    }
                    h++;
                    t = fs[h];
                }
                s = pilch[s];
            }
            s = pilch[0];
            prem[k + 1] = s;
        }
        return rang;
    }

//--------------------------------------------
    public static Graphe grapheReduitSelonTarjan(Graphe G) {
        throw new UnsupportedOperationException();
    }

    public static ArrayList<int[]> basesDuGrapheSelonTarjan(Graphe G) {
        throw new UnsupportedOperationException();
    }

    public static ArrayList<String[]> cheminsCritiques(Graphe G) {
        throw new UnsupportedOperationException(); // voir cours L3
    }

    public static double[] datesAuPlusTard(Graphe G) {
        throw new UnsupportedOperationException();
    }

    public static ArrayList<Integer> cheminLePlusCourtSelonDjikstra(Graphe G, int depart, int destination) {

        int v, j = 0, max;
        int n = G.getNombreSommets();
        int[] fs = G.getFileSuccesseurs();
        int[] aps = G.getAdressesPremierSuccesseur();
        int[] d = new int[n + 1];
        int[] pred = new int[n + 1];
        boolean[] S = new boolean[n + 1];

        for (int i = 1; i <= n; ++i) {
            if (G.getPoids(depart, i) != -1.0) {
                d[i] = (int) G.getPoids(depart, i);
                pred[i] = depart;
            } else {
                d[i] = Integer.MAX_VALUE;
                pred[i] = 0;
            }
            S[i] = true;
        }

        S[depart] = false;
        d[depart] = 0;

        for (int cpt = 0; cpt < n; ++cpt) {
            max = Integer.MAX_VALUE;
            for (int i = 1; i <= n; ++i) {
                if (S[i] && d[i] < max) {
                    max = d[i];
                    j = i;
                }
            }
            if (d[j] != Integer.MAX_VALUE) {
                S[j] = false;
                for (int l = aps[j]; fs[l] != 0; ++l) {
                    int k = fs[l];
                    if (S[k]) {
                        v = d[j] + ((int) G.getPoids(j, k));
                        if (v < d[k]) {
                            d[k] = v;
                            pred[k] = j;
                        }
                    }
                }
            } else {
                break;
            }
        }
        ArrayList<Integer> chemin = new ArrayList<>();
        int etape = destination;
        while (etape != depart) {
            chemin.add(etape);
            etape = pred[etape];
        }
        chemin.add(depart);
        Collections.reverse(chemin);

        return chemin;
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
