/**
 * MST.java
 * 
 * This is a program written for CS Algorithms.
 *
 * @author mpc5944: Michael Caputo
 */

import java.io.*;
import java.util.ArrayList;

public class MST {
    private int n, seed;
    private Double p;
    private boolean shouldPrint = true;
    private Graph g;
    private int count = 1;
    //    private ArrayList<Integer> dfs_results = new ArrayList<Integer>();
    private ArrayList<MSTException> errors = new ArrayList<MSTException>();

    public void DFS(Vertex v) {
	ArrayList<Vertex> visited = new ArrayList<Vertex>();
	count = 1;
	DFS_Visit(v, visited);
    }

    public void DFS_Visit(Vertex currVertex, ArrayList<Vertex> visited) {
	visited.add(currVertex);
	for (int i = 0; i < currVertex.getNeighbors().size(); i++) {
	    Vertex currNeighbor = currVertex.getNeighbors().get(i).vertex;
	    if( !visited.contains(currNeighbor) ) {
		currNeighbor.setPredecessor(currVertex.getId());
		count += 1;
		DFS_Visit(currNeighbor, visited);
	    }
	}
    }

    public void printPredecessors() {
	if (!shouldPrint) { return; }
	System.out.println("Depth-First Search:");
	System.out.println("Vertices:");
	for (int i = 0; i < n; i++) {
	    System.out.printf(" %d", g.getVertex(i).getId());
	}
	System.out.printf("\nPredecessors:\n");
	for (int k = 0; k < n; k++) {
	    System.out.printf("%d ", g.getVertex(k).getPredecessor());
	}
	System.out.println("\n");
    }

    public void readFile(String fileName) throws IOException {
	BufferedReader reader = null;
	try {
	    reader = new BufferedReader(new FileReader(fileName));
	    try {
		n = Integer.parseInt(reader.readLine());
		seed = Integer.parseInt(reader.readLine());
		if (n < 2) {
		    errors.add(new MSTException("n must be greater than 1"));
		}
	    } catch (NumberFormatException e) {
		errors.add(new MSTException("n and seed must be integers"));
	    }

	    try {
		p = Double.parseDouble(reader.readLine());
		if (p < 0 || p > 1) {
		    errors.add(new MSTException("p must be between 0 and 1"));
		}
	    } catch (NumberFormatException e) {
		errors.add(new MSTException("p must be a real number"));
	    }
	} catch( IOException e ) {
	    errors.add(new MSTException("Input file not found"));
	}
	finally {
	    reader.close();
	    
	    if( errors.size() > 0 ) {
		System.out.println(errors.get(0));
		System.exit(0);
	    }
	}
	
    }

    public int getNumberVertices() {
	return n;
    }

    public int getSeed() {
	return seed;
    }

    public double getProbability() {
	return p;
    }

    public void setGraph(Graph g) {
	this.g = g;
    }

    public void setShouldPrint(boolean shouldPrint) {
	this.shouldPrint = shouldPrint;
    }

    public boolean shouldPrint() {
	return shouldPrint;
    }

    public int getCount() {
	return this.count;
    }

    public static void main(String[] args) {
	MST mstObj = new MST();
	if (args.length < 1) {
	    System.out.println("Input file not found");
	    System.exit(0);
	}
	try {
	    mstObj.readFile(args[0]);
	} catch( IOException e ) {
	    System.out.println("IO Exception");
	}
	if (mstObj.getNumberVertices() >= 10) {
	    mstObj.setShouldPrint(false);
	}
	
	long startTime = System.currentTimeMillis();
	Graph graph = new Graph(mstObj.getNumberVertices(), mstObj.getSeed(), mstObj.getProbability());
	mstObj.setGraph(graph);
	boolean validGraph = false;
	while (!validGraph) {
	    graph.generateGraph();
	    mstObj.setGraph(graph);
	    mstObj.DFS(graph.getVertex(0));

	    if( mstObj.getNumberVertices() == mstObj.getCount() ) {
		validGraph = true;
	    }
	    else {
		graph.clear();
	    }
	}
	
	graph.generateGraphAsMatrix();
	long endTime = System.currentTimeMillis();
	System.out.printf("\nTEST: n=%d, seed=%d, p=%.1f\n",mstObj.getNumberVertices(), mstObj.getSeed(), mstObj.getProbability());
	System.out.printf("Time to generate the graph: %d milliseconds", endTime - startTime);
	if (mstObj.shouldPrint()) {
	    graph.printGraphAsMatrix();
	    graph.printGraphAsAdjacencyList();
	}

	mstObj.printPredecessors();
    }
}