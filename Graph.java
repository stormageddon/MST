/**
 * Graph.java
 * This class represents an undirected, connected, weighted graph.
 *
 */
import java.util.*;

public class Graph {
    private Map<Integer, Vertex> adjacencyList;
    private Random vertexGenerator = new Random();
    private Random weightGenerator = new Random(200000);
    private Random connectionGenerator = new Random(100000);
    private List<Integer> remainingVertices = new ArrayList<Integer>();

    public Graph() {
	System.out.println("Constructing a graph");
	adjacencyList = new HashMap<Integer, Vertex>();
	generateGraph(7);
    }

    /*
     * n := number of vertices in graph
     */
    public Graph generateGraph(int n) {

	for (int i = 0; i < n; i++ ) {
	    addVertex(i);
	    //	    remainingVertices.add(new Integer(i));
	}
	for (int i = 0; i < adjacencyList.size(); i++) {
	    addNeighbors(i);
	}
	return this;
    }

    public void addVertex(int vertexId) {
	adjacencyList.put(vertexId, new Vertex(vertexId));
    }

    public void addNeighbors(int vertexId) {
	for( int element = vertexId; element < adjacencyList.size(); element++ ) {
	    if( element != vertexId ) {
		if (connectionGenerator.nextDouble() <= 0.5) {
		    //		    adjacencyList.get(vertexId).add(element);
		    int weight = weightGenerator.nextInt(7) + 1;
		    adjacencyList.get(vertexId).addNeighbor(adjacencyList.get(element),new Integer(weight));
		    adjacencyList.get(element).addNeighbor(adjacencyList.get(vertexId),new Integer(weight));
		}
	    }
	}
    }

    public void printGraphAsAdjacencyList() {
	System.out.println("The graph as an adjacency list:");
	for(int i = 0; i < adjacencyList.size(); i++) {
	    Vertex currVertex = adjacencyList.get(i);
	    System.out.printf("\n%d->",currVertex.getId());
	    for(int k = 0; k < currVertex.getNeighbors().size(); k++) {
		Tuple currNeighbor = currVertex.getNeighbors().get(k);
		System.out.printf(" %d(%d)",((Vertex)currNeighbor.vertex).getId(), currNeighbor.weight);
	    }
	}
	System.out.println("");
    }
}