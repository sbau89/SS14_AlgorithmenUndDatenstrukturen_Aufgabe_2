package graph;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import sim.SYSimulation;

public class ScotlandYard_8 {

    public static void main(String[] args) {
        AdjacencyListUndirectedGraph<Integer> plan = new AdjacencyListUndirectedGraph<Integer>();

        for (int i = 0; i <= 199; i++) {
            plan.addVertex(i);
        }

        try {
            FileReader fr = new FileReader("ScotlandYard.txt");
            BufferedReader br = new BufferedReader(fr);

            String zeile = null;

            while ((zeile = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(zeile);
                if (zeile.contains("Taxi")) {
                    int i = Integer.parseInt(tokenizer.nextToken());
                    int j = Integer.parseInt(tokenizer.nextToken());
                    plan.addEdge(i, j, 3);
                } else if (zeile.contains("Bus")) {
                    int i = Integer.parseInt(tokenizer.nextToken());
                    int j = Integer.parseInt(tokenizer.nextToken());
                    plan.addEdge(i, j, 2);
                } else if (zeile.contains("UBahn")) {
                    int i = Integer.parseInt(tokenizer.nextToken());
                    int j = Integer.parseInt(tokenizer.nextToken());
                    plan.addEdge(i, j, 5);
                }
            }
            br.close();
        } catch (FileNotFoundException b) {
            System.out.println("file not found");
        } catch (IOException a) {
            System.out.println("Error while reading from ScotlandYard.txt");
        }

        SYSimulation sim;
        try {
            sim = new SYSimulation();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        DijkstraShortestPath<Integer> graph2 = new DijkstraShortestPath<Integer>(plan);
        System.out.println(graph2.searchAllShortestPaths(175));
        System.out.println(graph2.getShortestPathTo(1));

        //AllShortest Path
        sim.startSequence("Shortest Path_8");
        List<Integer> t = new ArrayList<Integer>();
        t = graph2.getShortestPathTo(1);
        int predecessor = t.get(0);
        sim.visitStation(predecessor);
        int successor = 0;
        for (int i = 1; i < t.size(); i++) {
            successor = t.get(i);
            sim.visitStation(successor);
            sim.drive(predecessor, successor, Color.GREEN);
            predecessor = successor;
        }
        sim.stopSequence();

        System.out.println(graph2.searchShortestPath(175, 1));
        System.out.println(graph2.getShortestPath());

        //Shortest Path
        sim.startSequence("Shortest Path_9");
        List<Integer> g = new ArrayList<Integer>();
        g = graph2.getShortestPath();
        int predecessor2 = g.get(0);
        sim.visitStation(predecessor2);
        int successor2 = 0;
        for (int i = 1; i < g.size(); i++) {
            successor2 = g.get(i);
            sim.visitStation(successor2);
            sim.drive(predecessor2, successor2, Color.BLUE);
            predecessor2 = successor2;
        }
        sim.stopSequence();
    }
}
