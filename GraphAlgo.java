package graphealgo;

import java.util.ArrayList;
import java.util.Arrays;

public class GraphAlgo {

    public static void main(String[] args) {
        testRemoveSommet();
        new UIGrapheAlgo();
    }

    public static void testLien() {
        int dep = 1, arr = 2;
        Lien a = new Lien(dep, arr);
        System.out.println(a);
        Lien b = new Lien(dep, arr);
        System.out.println(a.equals(b));
    }

    public static void testConstruteurParDefaut() {
        Graphe G = new Graphe();
        System.out.println(G);
    }

    public static void testConstruteurListe() {
        ArrayList<Lien> aL = new ArrayList<Lien>(3);
        aL.add(new Lien(1, 2));
        aL.add(new Lien(1, 3));
        aL.add(new Lien(2, 4));

        Graphe G = new Graphe(4, aL);
        System.out.println(G);
    }

    public static void testConstruteurParRecopie() {
        ArrayList<Lien> aL = new ArrayList<Lien>(3);
        aL.add(new Lien(1, 2));
        aL.add(new Lien(1, 3));
        aL.add(new Lien(2, 4));

        Graphe G1 = new Graphe(4, aL);
        Graphe G2 = new Graphe(G1);
        System.out.println(G2);
    }

    public static void testConstruteurFileSuccesseurs() {
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
    }

    public static void testConstruteurMatriceAdjacente() {
        int[][] M = new int[5][5];
        M[1][2] = 1;
        M[2][1] = 1;
        M[1][3] = 1;
        M[3][1] = 1;
        M[2][4] = 1;
        M[4][2] = 1;

        Graphe G = new Graphe(M, false);
        System.out.println(G);
    }

    public static void testGetFileSuccesseurs() {
        ArrayList<Lien> aL = new ArrayList<Lien>(3);
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
        ArrayList<Lien> aL = new ArrayList<Lien>(3);
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
        ArrayList<Lien> aL = new ArrayList<Lien>(3);
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
        G.addSommet();
        System.out.println(G);
    }

    public static void testRemoveSommet() {
        ArrayList<Lien> aL = new ArrayList<Lien>(3);
        aL.add(new Lien(1, 2));
        aL.add(new Lien(1, 3));
        aL.add(new Lien(2, 4));
        aL.add(new Lien(3, 4));
        aL.add(new Lien(4, 1));

        Graphe G = new Graphe(4, aL);
        System.out.println(G);
        G.removeSommet(3);
        System.out.println(G);
    }

    public static void testAddLien() {
        ArrayList<Lien> aL = new ArrayList<Lien>(3);
        aL.add(new Lien(1, 2));
        aL.add(new Lien(1, 3));
        aL.add(new Lien(2, 4));

        Graphe G = new Graphe(4, aL);
        System.out.println(G);
        G.addLien(new Lien(3, 4));
        System.out.println(G);
    }

    public static void testRemoveLien() {
        ArrayList<Lien> aL = new ArrayList<Lien>(3);
        aL.add(new Lien(1, 2));
        aL.add(new Lien(1, 3));
        aL.add(new Lien(2, 4));
        aL.add(new Lien(3, 4));

        Graphe G = new Graphe(4, aL);
        System.out.println(G);
        G.removeLien(new Lien(2, 4));
        System.out.println(G);
    }

    public static void testChangeSensLien() {
        ArrayList<Lien> aL = new ArrayList<Lien>(3);
        aL.add(new Lien(1, 2));
        aL.add(new Lien(1, 3));
        aL.add(new Lien(2, 4));
        aL.add(new Lien(3, 4));

        Graphe G = new Graphe(4, aL);
        System.out.println(G);
        G.changeSensLien(new Lien(2, 4));
        System.out.println(G);
    }

}
