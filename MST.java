/**
 * MST.java
 * 
 * This is a program written for CS Algorithms.
 *
 * @author mpc5944: Michael Caputo
 */

import java.io.*;

public class MST {
    private int n, seed;
    private Double p;

    public void DFS() {
	System.out.println("Running depth first search on graph");
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

    public static void main(String[] args) {
	MST mstObj = new MST();
	try {
	    mstObj.readFile(args[0]);
	} catch( IOException e ) {
	    System.out.println("IO Exception");
	}
	//	mstObj.DFS();
	Graph g = new Graph(mstObj.getNumberVertices(), mstObj.getSeed(), mstObj.getProbability());
	g.printGraphAsMatrix();
	g.printGraphAsAdjacencyList();
    }
}