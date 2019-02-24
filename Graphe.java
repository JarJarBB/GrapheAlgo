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
        this(matriceAdjacente.length - 1, MatriceVersListeArcs(matriceAdjacente, oriente), oriente);
    }

    public Graphe(int[][] matriceAdjacente) {
        this(matriceAdjacente, true);
    }
    
    public static ArrayList<Arc> MatriceVersListeArcs(int[][] MatriceAdjacence, boolean oriente) {
        ArrayList<Arc> aL = new ArrayList<Arc>();
        if (oriente) {
            for (int i = 1; i < MatriceAdjacence.length; i++) {
                for (int j = 1; j < MatriceAdjacence[i].length; j++) {
                    if (MatriceAdjacence[i][j] != 0) {
                        aL.add(new Arc(i, j));
                    }
                }
            }
        } else {
            for (int i = 1; i < MatriceAdjacence.length; i++) {
                for (int j = 1; j <= i; j++) {
                    if (MatriceAdjacence[i][j] != 0) {
                        aL.add(new Arc(i, j));
                    }
                }
            }
        }

        return aL;
    }

    public int[] getFileSuccesseurs() {
        int i=0;
        for(ArrayList<Integer> liste:successeurs){
            for(int entier:liste)
                i++;
            i++;
        }
        int[] fs=new int[i+1];
        fs[0]=i;
        i=1;
        for(ArrayList<Integer> liste:successeurs){
            for(int entier:liste){
                fs[i]=entier
                i++;
            }
            fs[i]=0;
            i++;
        }

        return fs;
    }

    public int[] getAdressesPremierSuccesseur() {
        int i=0;
        for(ArrayList<Integer> liste:successeurs)
            i++;
        int[] aps=new int[i+1];
        aps[0]=i;
        i=1;
        int indice=1;
        for(ArrayList<Integer> liste:successeurs){
            aps[indice]=i;
            indice++;
            for(int entier:liste)
                i++;
            i++;
        }

        return aps;

    }

    public int[][] getMatriceAdjacente() {
        int[][] matrice =new int[successeurs.size()][successeurs.size()];

        for (int i=0;i<successeurs.size() ;i++ ) {
            for (int j=0;j<successeurs.size() ;j++ ) {
                matrice[i][j]=0;
            }
        }
        int depart=1;
        for(ArrayList<Integer> liste : successeurs){
            for(int i:liste)
                matrice[depart][i]=1;
            depart++;
        }
        return matrice;
    }

    public boolean isOriente() {
        return oriente;
    }

    // Les parties suivantes sont nécessaires pour modifier dynamiquement le graphe :
    public void addSommet() {
        successeurs.add(new ArrayList<Integer>);
    }

    public boolean removeSommet(int id) {
        if(successeurs.size()<id)
            return false;
        successeurs.remove(id-1);
        for(ArrayList<Integer> liste : successeurs){
            for(int i=0;i<liste.size();i++){
                if(liste.get(i) == id)
                    liste.remove(i);
            }
        }
        return true;
    }

    public boolean addArc(Arc A) {
        if(successeurs.size() < A.depart || successeurs.size() < A.destination)
            return false;
        if(isOriente){
            successeurs.get(A.depart-1).add(A.destination);
        }else{
            successeurs.get(A.depart-1).add(A.destination);
            successeurs.get(A.destination-1).add(A.depart);
        }
        return true;
        // renvoie faux si le départ ou la destination de A ne correspondent pas à un sommet du graphe
    }

    public boolean removeArc(Arc A) {
        //pas entiermenet fini manque c=des return false
        if(successeurs.size() < A.depart || successeurs.size() < A.destination)
            return false;
        boolean trouve=false;
        if(isOriente){
            for(int i=0;i<successeurs.get(A.depart-1).size();i++){
                if(successeurs.get(A.depart-1).get(i) == A.destination){
                    successeurs.get(A.depart-1).remove(i);        
                    trouve=true;
                }
            }
            if(!trouve)
                return false;
            
        }else{
            for(int i=0;i<successeurs.get(A.depart).size();i++){
                if(successeurs.get(A.depart-1).get(i) == A.destination){
                    successeurs.get(A.depart-1).remove(i);        
                    trouve=true;
                }
            }
           for(int i=0;i<successeurs.get(A.destination-1).size();i++){
                if(successeurs.get(A.destination-1).get(i) == A.depart){
                    trouve=true;
                    successeurs.get(A.destinations).remove(i);        
                }
            }
            if(!trouve)
                return false;
        }
        return true;
        // renvoie faux si un arc equivalent à A n'est pas trouvé
    }

    public boolean changeSensArc(Arc A) {
        if(successeurs.size() < A.depart || successeurs.size() < A.destination || !isOriente())
            return false;
        for(int i=0;i<successeurs.get(A.depart-1).size();i++){
            if(successeurs.get(A.depart-1).get(i)==A.destination){
                trouve=true;
                successeurs.get(A.depart-1).remove(i);
            }
        }
        if(!trouve)
            return false;
        successeurs.get(A.destination-1).add(A.depart);
        return true;
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
