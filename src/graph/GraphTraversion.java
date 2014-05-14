package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author sbau8_000
 */
public class GraphTraversion<V> {

    public static <V> List<V> depthFirstSearch(Graph<V> g, V s) {
        List<V> visited = new ArrayList<V>();
        return depthFirstSearch(g, s, visited);
    }

    private static <V> List<V> depthFirstSearch(Graph<V> g, V s, List<V> visited) {
        Deque<V> stack = new ArrayDeque<V>();
        List<V> ausgabe = new ArrayList<V>();
        stack.push(s);

        while (!stack.isEmpty()) {
            V t = stack.pop();
            if (visited.contains(t)) {
                continue;
            }

            visited.add(t);
            ausgabe.add(t);
            List<V> adjacent_list = new ArrayList<V>();
            adjacent_list.addAll(g.getAdjacentVertexList(t));
            for (int i = adjacent_list.size() - 1; i >= 0; i--) {
                if (!visited.contains(adjacent_list.get(i))) {
                    stack.push(adjacent_list.get(i));
                }
            }
        }
        return ausgabe;
    }

    public static <V> List<V> breadthFirstSearch(Graph<V> g, V s) {
        List<V> visited = new ArrayList<V>();
        return breadthFirstSearch(g, s, visited);
    }

    private static <V> List<V> breadthFirstSearch(Graph<V> g, V s, List<V> visited) {
        Deque<V> queue = new ArrayDeque<V>();
        List<V> ausgabe = new ArrayList<V>();
        queue.add(s);

        while (!queue.isEmpty()) {
            V t = queue.pop();
            if (visited.contains(t)) {
                continue;
            }
            visited.add(t);
            ausgabe.add(t);
            List<V> adjacent_list = new ArrayList<V>();
            adjacent_list.addAll(g.getAdjacentVertexList(t));
            for (int i = 0; i < adjacent_list.size(); i++) {
                V knoten = adjacent_list.get(i);
                if (!visited.contains(knoten)) {
                    queue.add(knoten);
                }
            }
        }
        return ausgabe;
    }

    public static <V> List<V> topologicalSort(DirectedGraph<V> g) {
        List<V> ausgabe = new ArrayList<V>();
        HashMap<V, Integer> inDegree = new HashMap<V, Integer>();
        Deque<V> queue = new ArrayDeque<V>();

        for (V v : g.getVertexList()) {
            inDegree.put(v, g.getInDegree(v));
            if (g.getInDegree(v) == 0) {
                queue.add(v);
            }
        }
        V v;
        int k = 0;
        while (!queue.isEmpty()) {
            v = queue.pop();
            ausgabe.add(v);
            k++;
            for (V w : g.getSuccessorVertexList(v)) {
                int value = inDegree.get(w);
                value--;
                inDegree.put(w, value);
                if (value == 0) {
                    queue.add(w);
                }
            }
        }
        if (k != ausgabe.size()) {
            return null;
        } else {
            return ausgabe;
        }
    }
}
