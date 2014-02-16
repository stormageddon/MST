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

    public void DFS(Vertex v) {
	ArrayList<Vertex> visited = new ArrayList<Vertex>();
	if (shouldPrint) {
	    System.out.println("Depth-First Search:");
	}
	count = 1;
	DFS_Visit(v, visited);
	
	if (shouldPrint) {
	    System.out.println("Vertices: ");
	    for (int i = 0; i < n; i++) {
		System.out.printf(" %d",g.getVertex(i).getId());
	    }
	    System.out.print("\nPredecessors:\n");
	    for (int k = 0; k < n; k++) {
		System.out.printf("%d ",g.getVertex(k).getPredecessor());
	    }
	    System.out.println("\n");
	}
    }

    public void DFS_Visit(Vertex currVertex, ArrayList<Vertex> visited) {
	//	currVertex.setPredecessor(predecessorVertex.getId());
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

    public void readFile(String fileName) throws IOException {
	BufferedReader reader = null;
	try {
	    reader = new BufferedReader(new FileReader(fileName));
	    n = Integer.parseInt(reader.readLine());
	    seed = Integer.parseInt(reader.readLine());
	    p = Double.parseDouble(reader.readLine());
	} catch( FileNotFoundException e ) {
	    System.out.println("File not found");
	} finally {
	    reader.close();
	    System.out.printf("\nTEST: n=%d, seed=%d, p=%.1f\n",n,seed,p);
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

	int attempt = 1;
	boolean validGraph = false;
	while (!validGraph) {
	    System.out.println("Attempt " + attempt + ": ");
	    graph.generateGraph();
	    mstObj.setGraph(graph);
	    if (mstObj.shouldPrint()) {
		graph.printGraphAsMatrix();
		graph.printGraphAsAdjacencyList();
	    }
	    mstObj.DFS(graph.getVertex(0));

	    if( mstObj.getNumberVertices() == mstObj.getCount() ) {
		validGraph = true;
	    }
	    else {
		graph.clear();
		//mstObj.setCount(0);
		attempt += 1;
	    }
	}
	long endTime = System.currentTimeMillis();
	System.out.printf("Time to generate the graph: %d milliseconds\n\n", endTime - startTime);
    }
}