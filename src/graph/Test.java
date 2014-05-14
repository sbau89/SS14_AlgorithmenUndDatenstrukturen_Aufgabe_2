package graph;

import graph.GraphTraversion;

/**
 *
 * @author sbau8_000
 */
public class Test extends AdjacencyListUndirectedGraph<Integer> {

    public static void main(String[] args) {

        //Tiefensuche
        AdjacencyListUndirectedGraph<Integer> tiefe = new AdjacencyListUndirectedGraph<Integer>();
        tiefe.addVertex(0);
        tiefe.addVertex(1);
        tiefe.addVertex(2);
        tiefe.addVertex(3);
        tiefe.addVertex(4);
        tiefe.addVertex(5);
        tiefe.addEdge(1, 2);
        tiefe.addEdge(4, 3);
        tiefe.addEdge(1, 4);
        tiefe.addEdge(2, 3);
        tiefe.addEdge(4, 5);
        tiefe.addEdge(1, 0);
        tiefe.addEdge(4, 0);
        tiefe.addEdge(4, 2);
        System.out.println("Tiefensuche soll: [1, 0, 4, 2, 3, 5]");
        System.out.println("Tiefensuche ist: " + GraphTraversion.depthFirstSearch(tiefe, 1));
        System.out.println("");

        //Breitensuche
        AdjacencyListUndirectedGraph<Integer> breite = new AdjacencyListUndirectedGraph<Integer>();
        breite.addVertex(0);
        breite.addVertex(1);
        breite.addVertex(2);
        breite.addVertex(3);
        breite.addVertex(4);
        breite.addVertex(5);
        breite.addEdge(1, 2);
        breite.addEdge(4, 3);
        breite.addEdge(2, 3);
        breite.addEdge(1, 4);
        breite.addEdge(4, 5);
        breite.addEdge(1, 0);
        breite.addEdge(4, 0);
        breite.addEdge(4, 2);
        System.out.println("Breitensuche soll: [1, 0, 2, 4, 3, 5]");
        System.out.println("Breitensuche ist: " + GraphTraversion.breadthFirstSearch(breite, 1));
        System.out.println("");
    }
}
