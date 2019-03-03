package graphealgo;

public class Lien {

    public final int depart, destination;
    public final double valeur;

    public Lien(int _depart, int _destination, double _valeur) {
        depart = _depart;
        destination = _destination;
        valeur = _valeur;
    }
    
    public Lien(int _depart, int _destination) {
        this(_depart, _destination, 1.0);
    }

    @Override
    public String toString() {
        return "(" + depart + ", " + destination + ')';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.depart;
        hash = 89 * hash + this.destination;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lien other = (Lien) obj;
        if (this.depart != other.depart) {
            return false;
        }
        return this.destination == other.destination;
    }

}
