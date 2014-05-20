package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class DijkstraShortestPath<V> {

    public class Bend<V> {

        boolean Candidate = false;
        double d = Integer.MAX_VALUE;
        V p = null;

        public Bend(boolean Kandidat2, double d2, V p2) {
            Candidate = Kandidat2;
            d = d2;
            p = p2;
        }
    }

    HashMap<V, Bend<V>> dijkstra = new HashMap<V, Bend<V>>();
    Graph<V> g;
    boolean searchShortestPath = false;
    V beginning;
    V end;

    public DijkstraShortestPath(Graph<V> graph) {
        g = graph;
    }

    public boolean searchShortestPath(V startknoten, V zielknoten) {
        beginning = startknoten;
        end = zielknoten;
        V bend_for_end = startknoten;

        //Add all Vertexes
        int number_of_candidates = 0;
        for (V k : g.getVertexList()) {
            dijkstra.put(k, new Bend<V>(false, Double.MAX_VALUE, null));
        }

        //Initiate Starting point
        dijkstra.get(startknoten).d = 0;
        dijkstra.get(startknoten).Candidate = true;
        number_of_candidates++;

        while (number_of_candidates > 0 && !bend_for_end.equals(zielknoten)) {
            //Get Vertex with minimum d[]
            V t = null;
            double distance_max = Double.MAX_VALUE;
            for (Entry<V, Bend<V>> k : dijkstra.entrySet()) {
                if (k.getValue().Candidate) {
                    if (k.getValue().d < distance_max) {
                        t = k.getKey();
                        distance_max = k.getValue().d;
                    }
                }
            }
            dijkstra.get(t).Candidate = false;
            number_of_candidates--;
            bend_for_end = t;

            //Add all adjazent Vertexes
            List<V> adjazent = new ArrayList<V>();
            adjazent.addAll(g.getAdjacentVertexList(t));
            for (int i = 0; i < adjazent.size(); i++) {
                V name = adjazent.get(i);
                if (dijkstra.get(name).d == Double.MAX_VALUE) {
                    dijkstra.get(name).Candidate = true;
                    number_of_candidates++;
                }
            }

            //refresh Edges
            for (Entry<V, Bend<V>> k : dijkstra.entrySet()) {
                if (k.getValue().Candidate) {
                    if (g.getWeight(t, k.getKey()) > 0) {
                        if ((dijkstra.get(t).d + g.getWeight(t, k.getKey())) < k.getValue().d) {
                            k.getValue().d = dijkstra.get(t).d + g.getWeight(t, k.getKey());
                            k.getValue().p = t;
                        }
                    }
                }
            }
        }
        if (dijkstra.get(zielknoten).d == Double.MAX_VALUE) {
            return false;
        } else {
            searchShortestPath = true;
            return true;
        }
    }

    //Setzt eine erfolgreiche Suche mit searchShortestPath voraus
    public List<V> getShortestPath() {
        if (!searchShortestPath) {
            throw new IllegalArgumentException("You have to execute searchShortestPath first");
        }
        List<V> path = new ArrayList<V>();
        V Bend = end;
        path.add(end);
        while (dijkstra.get(Bend).p != null) {
            path.add(dijkstra.get(Bend).p);
            Bend = dijkstra.get(Bend).p;
        }
        return path;
    }

    //Setzt eine erfolgreiche Suche mit searchShortestPath voraus
    public double getDistance() {
        if (!searchShortestPath) {
            throw new IllegalArgumentException("You have to execute searchShortestPath first");
        }
        return dijkstra.get(end).d;
    }

    public boolean searchAllShortestPaths(V startknoten) {
        beginning = startknoten;
        //Add all Vertexes
        int number_of_candidates = 0;
        for (V k : g.getVertexList()) {
            dijkstra.put(k, new Bend<V>(false, Double.MAX_VALUE, null));
        }

        //Initiate Starting point
        dijkstra.get(startknoten).d = 0;
        dijkstra.get(startknoten).Candidate = true;
        number_of_candidates++;

        while (number_of_candidates > 0) {
            //Get Vertex with minimum d[]
            V t = null;
            double distance_max = Double.MAX_VALUE;
            for (Entry<V, Bend<V>> k : dijkstra.entrySet()) {
                if (k.getValue().Candidate) {
                    if (k.getValue().d < distance_max) {
                        t = k.getKey();
                        distance_max = k.getValue().d;
                    }
                }
            }
            dijkstra.get(t).Candidate = false;
            number_of_candidates--;

            //Add all adjazent Vertexes
            List<V> adjazent = new ArrayList<V>();
            adjazent.addAll(g.getAdjacentVertexList(t));
            for (int i = 0; i < adjazent.size(); i++) {
                V name = adjazent.get(i);
                if (dijkstra.get(name).d == Double.MAX_VALUE) {
                    dijkstra.get(name).Candidate = true;
                    number_of_candidates++;
                }
            }

            //refresh Edges
            for (Entry<V, Bend<V>> k : dijkstra.entrySet()) {
                if (k.getValue().Candidate) {
                    if (g.getWeight(t, k.getKey()) > 0) {
                        if ((dijkstra.get(t).d + g.getWeight(t, k.getKey())) < k.getValue().d) {
                            k.getValue().d = dijkstra.get(t).d + g.getWeight(t, k.getKey());
                            k.getValue().p = t;
                        }
                    }
                }
            }
        }
        searchShortestPath = true;
        return true;
    }

    public List<V> getShortestPathTo(V zielknoten) {
        if (!searchShortestPath) {
            throw new IllegalArgumentException("You have to execute searchAllShortestPath first");
        }
        List<V> path = new ArrayList<V>();
        V Bend = zielknoten;
        path.add(zielknoten);
        while (dijkstra.get(Bend).p != null) {
            path.add(dijkstra.get(Bend).p);
            Bend = dijkstra.get(Bend).p;
        }
        return path;
    }

    public double getDistanceTo(V zielknoten) {
        if (!searchShortestPath) {
            throw new IllegalArgumentException("You have to execute searchAllShortestPath first");
        }
        return dijkstra.get(zielknoten).d;
    }
}
