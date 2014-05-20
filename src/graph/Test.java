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

        //topologische Suche
        AdjacencyListDirectedGraph<String> graph = new AdjacencyListDirectedGraph<String>();
        graph.addVertex("Strümpfe");
        graph.addVertex("Schuhe");
        graph.addVertex("Hose");
        graph.addVertex("Unterhose");
        graph.addVertex("Unterhemd");
        graph.addVertex("Hemd");
        graph.addVertex("Gürtel");
        graph.addVertex("Pullover");
        graph.addVertex("Mantel");
        graph.addVertex("Schal");
        graph.addVertex("Handschuhe");
        graph.addVertex("Mütze");

        graph.addEdge("Strümpfe", "Hose");
        graph.addEdge("Unterhose", "Hose");
        graph.addEdge("Hose", "Gürtel");
        graph.addEdge("Hose", "Schuhe");
        graph.addEdge("Unterhemd", "Hemd");
        graph.addEdge("Hemd", "Pullover");
        graph.addEdge("Pullover", "Mantel");
        graph.addEdge("Schuhe", "Mantel");
        graph.addEdge("Gürtel", "Mantel");
        graph.addEdge("Mantel", "Schal");
        graph.addEdge("Mantel", "Mütze");
        graph.addEdge("Schal", "Handschuhe");
        graph.addEdge("Mütze", "Handschuhe");

        System.out.println("topologische Sortierung des Anziehens: ");
        System.out.println(GraphTraversion.topologicalSort(graph));

        // Shortest Path
        AdjacencyListUndirectedGraph<Integer> graph3 = new AdjacencyListUndirectedGraph<Integer>();

        graph3.addVertex(0);
        graph3.addVertex(1);
        graph3.addVertex(2);
        graph3.addVertex(3);
        graph3.addVertex(4);
        graph3.addVertex(5);
        graph3.addVertex(6);
        graph3.addVertex(7);
        graph3.addVertex(8);
        graph3.addVertex(9);
        graph3.addEdge(0, 1, 1);
        graph3.addEdge(0, 2, 6);
        graph3.addEdge(0, 3, 2);
        graph3.addEdge(0, 4, 2);
        graph3.addEdge(1, 2, 4);
        graph3.addEdge(1, 5, 5);
        graph3.addEdge(2, 3, 3);
        graph3.addEdge(2, 7, 8);
        graph3.addEdge(4, 6, 1);
        graph3.addEdge(4, 5, 6);
        graph3.addEdge(5, 8, 7);
        graph3.addEdge(7, 8, 1);
        graph3.addEdge(6, 7, 1);

        DijkstraShortestPath<Integer> graph2 = new DijkstraShortestPath<Integer>(graph3);
        System.out.println();
        System.out.println("Shortest Paths");
        System.out.println("Suche Pfad mit nicht verbundenem Knoten: " + graph2.searchShortestPath(0, 9));
        System.out.println("Suche Pfad mit vorhandenem Knoten: " + graph2.searchShortestPath(0, 4));
        System.out.println("Suche Weg sollte [4,0] sein: " + graph2.getShortestPath());
        System.out.println("Suche Entfernung sollte (2): " + graph2.getDistance());

        System.out.println();
        System.out.println("All Shortest Paths");
        System.out.println("Suche Pfad: " + graph2.searchAllShortestPaths(0));
        System.out.println("Suche Weg sollte [7,6,4,0] sein: " + graph2.getShortestPathTo(7));
        System.out.println("Suche Entfernung sollte (4): " + graph2.getDistanceTo(7));
    }
}
