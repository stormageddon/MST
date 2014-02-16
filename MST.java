/**
 * MST.java
 * 
 * This is a program written for CS Algorithms.
 *
 * @author mpc5944: Michael Caputo
 */

public class MST {
    public void DFS() {
	System.out.println("Running depth first search on graph");
    }
    public static void main(String[] args) {
	System.out.println("Testing");
	MST mstObj = new MST();
	mstObj.DFS();
	Graph g = new Graph();
	g.printGraphAsAdjacencyList();
    }
}