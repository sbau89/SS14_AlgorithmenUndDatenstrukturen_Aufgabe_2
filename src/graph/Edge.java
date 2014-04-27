package graph;

/**
 * Klasse fÃ¼r Kanten.
 * @author Oliver Bittel
 * @since 21.10.2011
 * @param <V> Knotentyp.
 */

public class Edge<V> {

    /**
     * Startknoten.
     */
    final protected V source;
    /**
     * Zielknoten
     */
    final protected V target;
    /**
     * Gewicht.
     */
    final protected double weight;

    /**
     * Erzeugt neue Kante mit Gewicht 1.
     * @param source Startknoten.
     * @param target Zielknoten.
     */
    public Edge(V source, V target) {
        this.source = source;
        this.target = target;
        this.weight = 1.0;
    }

    /**
     * Erzeugt neue Kante mit Gewicht weight.
     * @param source
     * @param target Startknoten.
     * @param weight Zielknoten.
     */
    public Edge(V source, V target, double weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    /**
     * Liefert Startknoten zurÃ¼ck.
     * @return Startknoten.
     */
    public V getSource() {
        return this.source;
    }

    /**
     * Liefert Zielknoten zurÃ¼ck.
     * @return Zielknoten.
     */
    public V getTarget() {
        return this.target;
    }

    /**
     * Liefert Gewicht der Kante zurÃ¼ck.
     * @return Gewicht.
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * Liefert String-Darstellung der Kante zurÃ¼ck.
     * @return String-Darstellung.
     */
    @Override public String toString() {
        if (weight == 1)
            return source + " -- " + target;
        else
            return source + " -- " + target + " (" + weight + ")";
    }
}