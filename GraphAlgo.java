package graphealgo;

import java.util.ArrayList;
import java.util.Arrays;

public class GraphAlgo {

    public static void main(String[] args) {
        testDistancesUnSommet();
        //new UIGrapheAlgo();
    }

    public static void testDistancesUnSommet() {

        ArrayList<Lien> aL = new ArrayList<>(3);
        aL.add(new Lien(1, 2));
        aL.add(new Lien(1, 3));
        aL.add(new Lien(2, 4, 3.14159));

        Graphe G = new Graphe(4, aL);
        System.out.println(G);

        int[] distances = Algorithme.distancesUnSommet(G, 1);
        int[] fs = G.getFileSuccesseurs();
        int[] aps = G.getAdressesPremierSuccesseur();
        for (int i = 0; i < distances.length; ++i) {
            System.out.println(i + ":" + distances[i]);
        }
    }

    public static void testLien() {
        int dep = 1, arr = 2;
        Lien a = new Lien(dep, arr);
        System.out.println(a);
        Lien b = new Lien(dep, arr);
        System.out.println(a.equals(b));
    }

    public static void testConstructeurParDefaut() {
        Graphe G = new Graphe();
        System.out.println(G);
    }

    public static void testConstructeurListe() {
        ArrayList<Lien> aL = new ArrayList<>(3);
        aL.add(new Lien(1, 2));
        aL.add(new Lien(1, 3));
        aL.add(new Lien(2, 4, 3.14159));

        Graphe G = new Graphe(4, aL);
        System.out.println(G);
        System.out.println(G.getPoids(new Lien(2, 4)));
        System.out.println("a" + G.getInformation(2) + "z");

    }

    public static void testConstructeurParRecopie() {
        ArrayList<Lien> aL = new ArrayList<>(3);
        aL.add(new Lien(1, 2));
        aL.add(new Lien(1, 3));
        aL.add(new Lien(2, 4, 2.718));

        Graphe G1 = new Graphe(4, aL, false);
        G1.setPoids(new Lien(2, 1, 1.41));
        Graphe G2 = new Graphe(G1);
        System.out.println(G2);
        System.out.println(G2.getPoids(1, 2));
        System.out.println("a" + G2.getInformation(2) + "z");
    }

    public static void testConstructeurFileSuccesseurs() {
        int[] fs = new int[8];
        fs[0] = 7;
        fs[1] = 2;
        fs[2] = 3;
        fs[3] = 0;
        fs[4] = 4;
        fs[5] = 0;
        fs[6] = 0;
        fs[7] = 0;
        Graphe G = new Graphe(fs);
        System.out.println(G);
        System.out.println(G.getPoids(1, 3));
        System.out.println("a" + G.getInformation(2) + "z");
    }

    public static void testConstructeurMatriceAdjacente() {
        int[][] M = new int[5][5];
        M[1][2] = 1;
        M[2][1] = 1;
        M[1][3] = 1;
        M[3][1] = 1;
        M[2][4] = 1;
        M[4][2] = 1;

        Graphe G = new Graphe(M);
        System.out.println(G);
        System.out.println(G.getPoids(1, 3));
        G.setInformation(2, "exemple de num deux");
        System.out.println("a" + G.getInformation(2) + "z");
    }

    public static void testGetFileSuccesseurs() {
        ArrayList<Lien> aL = new ArrayList<>(3);
        aL.add(new Lien(1, 2));
        aL.add(new Lien(1, 3));
        aL.add(new Lien(2, 4));

        Graphe G = new Graphe(4, aL);
        System.out.println(G);
        for (int i : G.getFileSuccesseurs()) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }

    public static void testGetAdressesPremierSuccesseur() {
        ArrayList<Lien> aL = new ArrayList<>(3);
        aL.add(new Lien(1, 2));
        aL.add(new Lien(1, 3));
        aL.add(new Lien(2, 4));

        Graphe G = new Graphe(4, aL);
        System.out.println(G);

        for (int i : G.getFileSuccesseurs()) {
            System.out.print(i + " ");
        }
        System.out.println("");

        for (int i : G.getAdressesPremierSuccesseur()) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }

    public static void testGetMatriceAdjacente() {
        ArrayList<Lien> aL = new ArrayList<>(3);
        aL.add(new Lien(1, 2));
        aL.add(new Lien(1, 3));
        aL.add(new Lien(2, 4));

        Graphe G = new Graphe(4, aL);
        System.out.println(G);
        for (int[] ligne : G.getMatriceAdjacente()) {
            System.out.println(Arrays.toString(ligne));
        }
    }

    public static void testAddSommet() {
        Graphe G = new Graphe();
        System.out.println(G);
        G.addSommet("Premier sommet ajoute");
        System.out.println(G);
        System.out.println(G.getPoids(new Lien(1, 1)));
        System.out.println(G.getInformation(1));
    }

    public static void testRemoveSommet() {
        ArrayList<Lien> aL = new ArrayList<>(3);
        aL.add(new Lien(1, 2));
        aL.add(new Lien(1, 3));
        aL.add(new Lien(2, 4));
        aL.add(new Lien(3, 4));
        aL.add(new Lien(4, 1, -2.3));

        Graphe G = new Graphe(4, aL);
        G.setInformation(3, "Nom du Trois");
        System.out.println(G);
        System.out.println(G.getPoids(4, 1));
        System.out.println("a" + G.getInformation(3) + "z");
        G.removeSommet(3);
        System.out.println(G);
        System.out.println(G.getPoids(3, 1));
        System.out.println("a" + G.getInformation(3) + "z");
    }

    public static void testAddLien() {
        ArrayList<Lien> aL = new ArrayList<>(3);
        aL.add(new Lien(1, 2));
        aL.add(new Lien(1, 3));
        aL.add(new Lien(2, 4));

        Graphe G = new Graphe(4, aL);
        System.out.println(G);
        G.addLien(new Lien(3, 4, 4.5));
        System.out.println(G);
        System.out.println(G.getPoids(3, 4));
    }

    public static void testRemoveLien() {
        ArrayList<Lien> aL = new ArrayList<>(3);
        aL.add(new Lien(1, 2));
        aL.add(new Lien(1, 3));
        aL.add(new Lien(2, 4, 3.14159));
        aL.add(new Lien(3, 4));

        Graphe G = new Graphe(4, aL, false);
        G.addLien(new Lien(4, 2, 2.718));
        System.out.println(G);
        System.out.println(G.getPoids(4, 2));
        G.removeLien(new Lien(2, 4));
        System.out.println(G);
        System.out.println(G.getPoids(4, 2));
    }

    public static void testChangeSensLien() {
        ArrayList<Lien> aL = new ArrayList<>(3);
        aL.add(new Lien(1, 2, 2.718));
        aL.add(new Lien(1, 3));
        aL.add(new Lien(2, 4));
        aL.add(new Lien(3, 4));

        Graphe G = new Graphe(4, aL);
        System.out.println(G);
        System.out.println(G.getPoids(1, 2));
        G.changeSensLien(new Lien(1, 2));
        System.out.println(G);
        System.out.println(G.getPoids(2, 1));
    }

}
