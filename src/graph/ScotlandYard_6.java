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

public class ScotlandYard_6 {

	public static void main(String[] args) {
		AdjacencyListUndirectedGraph<Integer> plan = new AdjacencyListUndirectedGraph<Integer>();

		for(int i = 0; i <= 199; i++) {
			plan.addVertex(i);
		}
		
		try {
			FileReader fr = new FileReader("ScotlandYard.txt");
			BufferedReader br = new BufferedReader(fr);
			
			String zeile = null;
			while((zeile = br.readLine()) != null) {
				if(zeile.contains("Taxi")) {
					StringTokenizer tokenizer = new StringTokenizer(zeile);
					int i = Integer.parseInt(tokenizer.nextToken());
					int j = Integer.parseInt(tokenizer.nextToken());
					plan.addEdge(i, j);
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
		
		//Tiefensuche
		sim.startSequence("Tiefensuche");
		List<Integer> t = new ArrayList<Integer>();
		t = GraphTraversion.depthFirstSearch(plan, 114);
		int vorgaenger1 = 114;
		int nachfolger1;
		for(int i = 0; i < t.size(); i++) {
			nachfolger1 = t.get(i);
			sim.visitStation(nachfolger1);
			sim.drive(vorgaenger1, nachfolger1, Color.BLUE);
			vorgaenger1 = nachfolger1;
		}
		sim.stopSequence();
		
		//Breitensuche
		sim.startSequence("Breitensuche");
		List<Integer> b = new ArrayList<Integer>();
		b = GraphTraversion.breadthFirstSearch(plan, 1);
		int vorgaenger2 = 1;
		int nachfolger2;
		for(int i = 0; i < b.size(); i++) {
			nachfolger2 = b.get(i);
			sim.visitStation(nachfolger2);
			sim.drive(vorgaenger2, nachfolger2, Color.ORANGE);
			vorgaenger2 = nachfolger2;
		}
		sim.stopSequence();
	}
}