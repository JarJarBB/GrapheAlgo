package graphealgo;

import java.util.ArrayList;

public class GraphAlgo {

    public static void main(String[] args) {
        testArc();
        testConstruteurGrapheListeEtParDefaut();
    }

    public static void testArc() {
        int dep = 1, arr = 2;
        Arc a = new Arc(dep, arr);
        System.out.println(a);
        Arc b = new Arc(dep, arr);
        System.out.println(a.equals(b));
    }

    public static void testConstruteurGrapheListeEtParDefaut() {
        ArrayList<Arc> aL = new ArrayList<Arc>(3);
        aL.add(new Arc(1, 2));
        aL.add(new Arc(1, 3));
        aL.add(new Arc(2, 4));

        Graphe G1 = new Graphe(4, aL);
        Graphe G2 = new Graphe(G1);
        System.out.println(G2);
    }

}
