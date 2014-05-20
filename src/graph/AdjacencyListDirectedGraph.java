package graph;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author sbau8_000
 * @param <V>
 */
public class AdjacencyListDirectedGraph<V> implements DirectedGraph<V> {

    HashMap<V, HashMap<V, Double>> predecessorList = new HashMap<V, HashMap<V, Double>>();
    HashMap<V, HashMap<V, Double>> successorList = new HashMap<V, HashMap<V, Double>>();

    @Override
    public boolean addVertex(V v) {
        if (!predecessorList.containsKey(v)) {
            predecessorList.put(v, new HashMap<V, Double>());
            successorList.put(v, new HashMap<V, Double>());
            return true;
        }
        return false;
    }

    @Override
    public boolean addEdge(V v, V w) {
        if (!predecessorList.containsKey(v) || !predecessorList.containsKey(w) || v.equals(w)) {
            throw new IllegalArgumentException("Error in: addEdge");
        }
        boolean return_value = successorList.containsKey(v);
        successorList.get(v).put(w, 1.0);
        predecessorList.get(w).put(v, 1.0);
        return return_value;
    }

    @Override
    public boolean addEdge(V v, V w, double weight) {
        if (!predecessorList.containsKey(v) || !predecessorList.containsKey(w) || v.equals(w)) {
            throw new IllegalArgumentException("Error in: addEdge");
        }
        boolean return_value = successorList.containsKey(v);
        successorList.get(v).put(w, weight);
        predecessorList.get(w).put(v, weight);
        return return_value;
    }

    @Override
    public boolean containsVertex(V v) {
        return predecessorList.containsKey(v);
    }

    @Override
    public boolean containsEdge(V v, V w) {
        if (!predecessorList.containsKey(v)) {
            throw new IllegalArgumentException("Error in: containsEdge");
        }
        return predecessorList.get(w).containsKey(v);
    }

    @Override
    public double getWeight(V v, V w) {
        if (!predecessorList.containsKey(v) || !predecessorList.containsKey(w) || v.equals(w)) {
            throw new IllegalArgumentException("Error in: getWeight");
        }
        if (predecessorList.containsKey(v)) {
            return successorList.get(v).get(w);
        }
        return 0;
    }

    @Override
    public int getNumberOfVertexes() {
        return predecessorList.size();
    }

    @Override
    public int getNumberOfEdges() {
        int number_of_edges = 0;
        for (Entry<V, HashMap<V, Double>> k : predecessorList.entrySet()) {
            number_of_edges += k.getValue().size();
        }
        return number_of_edges;
    }

    @Override
    public List<V> getVertexList() {
        List<V> vertex_list = new ArrayList<V>();
        vertex_list.addAll(predecessorList.keySet());
        return vertex_list;
    }

    @Override
    public List getEdgeList() {
        List<String> edge_list = new ArrayList<String>();
        for (Entry<V, HashMap<V, Double>> k : predecessorList.entrySet()) {
            for (Entry<V, Double> j : k.getValue().entrySet()) {
                double weight = j.getValue();
                if (weight == 1.0) {
                    edge_list.add(j.getKey() + " -> " + k.getKey());
                } else {
                    edge_list.add(j.getKey() + " -> " + k.getKey() + "(" + weight + ")");
                }
            }
        }
        return edge_list;
    }

    @Override
    public List getAdjacentVertexList(V v) {
        List<V> adjacent_vertex_list = new ArrayList<V>();
        adjacent_vertex_list.addAll(successorList.get(v).keySet());
        return adjacent_vertex_list;
    }

    @Override
    public List getIncidentEdgeList(V v) {
        List<String> incident_edge_list = new ArrayList<String>();
        for (Entry<V, Double> k : successorList.get(v).entrySet()) {
            double weight = k.getValue();
            if (weight == 1.0) {
                incident_edge_list.add(v + " -> " + k.getKey());
            } else {
                incident_edge_list.add(v + " -> " + k.getKey() + "(" + weight + ")");
            }
        }
        return incident_edge_list;
    }

    @Override
    public int getOutDegree(V v) {
        return successorList.get(v).size();
    }

    @Override
    public int getInDegree(V v) {
        return predecessorList.get(v).size();
    }

    @Override
    public List getPredecessorVertexList(V v) {
        List<V> predecessor_vertex_list = new ArrayList<V>();
        predecessor_vertex_list.addAll(predecessorList.get(v).keySet());
        return predecessor_vertex_list;
    }

    @Override
    public List getSuccessorVertexList(V v) {
        List<V> successor_vertex_list = new ArrayList<V>();
        successor_vertex_list.addAll(successorList.get(v).keySet());
        return successor_vertex_list;
    }

    @Override
    public List getOutgoingEdgeList(V v) {
        List<String> outgoint_edges_list = new ArrayList<String>();
        for (Entry<V, Double> k : successorList.get(v).entrySet()) {
            outgoint_edges_list.add(v + " -> " + k.getKey() + "(" + k.getValue() + ")");
        }
        return outgoint_edges_list;
    }

    @Override
    public List getIncomingEdgeList(V v) {
        List<String> incoming_edges_list = new ArrayList<String>();
        for (Entry<V, Double> k : predecessorList.get(v).entrySet()) {
            incoming_edges_list.add(k.getKey() + " -> " + v + "(" + k.getValue() + ")");
        }
        return incoming_edges_list;
    }
}
