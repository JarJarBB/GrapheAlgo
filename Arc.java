package graphealgo;

public class Arc {

    public final int depart, destination;

    public Arc(int _depart, int _destination) {
        depart = _depart;
        destination = _destination;
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
        final Arc other = (Arc) obj;
        if (this.depart != other.depart) {
            return false;
        }
        return this.destination == other.destination;
    }

}
