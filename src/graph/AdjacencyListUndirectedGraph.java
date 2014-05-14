package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author sbau8_000
 * @param <V>
 */
public class AdjacencyListUndirectedGraph<V> implements UndirectedGraph<V> {

    HashMap<V, HashMap<V, Double>> adjacencyList = new HashMap<V, HashMap<V, Double>>();

    @Override
    public int getDegree(Object v) {
        return adjacencyList.get(v).size();
    }

    @Override
    public List<Edge<V>> getEdgeList() {
        List<Edge<V>> edge_list = new ArrayList<Edge<V>>();
        for (Entry<V, HashMap<V, Double>> k : adjacencyList.entrySet()) {
            for (Entry<V, Double> j : k.getValue().entrySet()) {
                Edge<V> Edge = new Edge<V>(k.getKey(), j.getKey(), j.getValue());
                edge_list.add(Edge);
            }
        }
        return edge_list;
    }

    @Override
    public boolean addVertex(V v) {
        if (!adjacencyList.containsKey(v)) {
            adjacencyList.put(v, new HashMap<V, Double>());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addEdge(V v, V w) {
        if (!adjacencyList.containsKey(v) || !adjacencyList.containsKey(w) || v.equals(w)) {
            throw new IllegalArgumentException("Error in: addEdge");
        }
        boolean return_value = adjacencyList.get(v).containsKey(w);
        adjacencyList.get(v).put(w, 1.0);
        adjacencyList.get(w).put(v, 1.0);
        return return_value;
    }

    @Override
    public boolean addEdge(V v, V w, double weight) {
        if (!adjacencyList.containsKey(v) || !adjacencyList.containsKey(w) || v.equals(w)) {
            throw new IllegalArgumentException("Error in: addEdge");
        }
        boolean return_value = adjacencyList.get(v).containsKey(w);
        adjacencyList.get(v).put(w, weight);
        adjacencyList.get(w).put(v, weight);
        return return_value;
    }

    @Override
    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }

    @Override
    public boolean containsEdge(V v, V w) {
        if (!adjacencyList.containsKey(v) || !adjacencyList.containsKey(w)) {
            throw new IllegalArgumentException("Error in: containsEdge");
        }
        return adjacencyList.get(v).containsKey(w);
    }

    @Override
    public double getWeight(V v, V w) {
        if (!adjacencyList.containsKey(v) || !adjacencyList.containsKey(w)) {
            throw new IllegalArgumentException("Error in: getWeight");
        }
        if (adjacencyList.get(v).containsKey(w)) {
            return adjacencyList.get(v).get(w);
        } else {
            return 0;
        }
    }

    @Override
    public int getNumberOfVertexes() {
        return adjacencyList.size();
    }

    @Override
    public int getNumberOfEdges() {
        int number_of_edges = 0;
        for (Entry<V, HashMap<V, Double>> k : adjacencyList.entrySet()) {
            number_of_edges += k.getValue().size();
        }
        //divide by two because every edge is added twice (undirected)
        return number_of_edges / 2;
    }

    @Override
    public List<V> getVertexList() {
        List<V> vertexList = new ArrayList<V>();
        vertexList.addAll(adjacencyList.keySet());
        return vertexList;
    }

    @Override
    public List<V> getAdjacentVertexList(Object v) {
        if (!adjacencyList.containsKey(v)) {
            throw new IllegalArgumentException("Error in: getAdjacentVertexList");
        }
        ArrayList<V> adjacent_vertex_list = new ArrayList<V>();
        for (Entry<V, HashMap<V, Double>> k : adjacencyList.entrySet()) {
            if (k.getValue().containsKey(v)) {
                adjacent_vertex_list.add(k.getKey());
            }
        }
        return adjacent_vertex_list;
    }

    @Override
    public List<Edge<V>> getIncidentEdgeList(V v) {
        if (!adjacencyList.containsKey(v)) {
            throw new IllegalArgumentException("Error in: getIncidentEdgeList");
        }
        List<Edge<V>> incident_edge_list = new ArrayList<Edge<V>>();
        for (Entry<V, Double> k : adjacencyList.get(v).entrySet()) {
            incident_edge_list.add(new Edge<V>(v, k.getKey(), k.getValue()));
        }
        return incident_edge_list;
    }

}
