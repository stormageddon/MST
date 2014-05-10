/**
 * Vertex for graph
 *
 */
import java.util.ArrayList;
import java.util.Comparator;

public class Vertex implements Comparator, Comparable {
    private int id, predecessorId;
    public int matrixRow, matrixCol;

    public Vertex parent;
    public int priority = 999999;
    private ArrayList<Tuple<Vertex,Integer>> neighbors;
    public Vertex(Integer id) {
	this(id, new ArrayList<Tuple<Vertex, Integer>>());
    }

    public Vertex(int id, ArrayList<Tuple<Vertex,Integer>> neighbors) {
	this.id = id;
	predecessorId = -1;
	this.neighbors = neighbors;
    }

    public void addNeighbor(Vertex neighbor, Integer weight) {
	Tuple edge = new Tuple(neighbor, weight);
	neighbors.add(edge);
    }

    public ArrayList<Tuple<Vertex,Integer>> getNeighbors() {
	return neighbors;
    }

    public Vertex getNeighborById(int id) {
	Vertex toReturn = null;
	for( Tuple neighbor : neighbors ) {
	    if( ((Vertex)neighbor.vertex).getId() == id ) {
		toReturn = ((Vertex)neighbor.vertex);
	    }
	}
	return toReturn;
    }

    public int getId() {
	return id;
    }

    public int getPredecessor() {
	return this.predecessorId;
    }

    public void setPredecessor(int id) {
	this.predecessorId = id;
    }

    public boolean contains(int cId) {
	for( int i = 0; i < neighbors.size(); i++ ) {
	    if (neighbors.get(i).vertex.getId() == cId) {
		return true;
	    }
	}
	return false;
    }
    
    public int getNeighborWeight(Vertex neighbor) {
	for(int i = 0; i < neighbors.size(); i++) {
	    if (neighbors.get(i).vertex.equals(neighbor)) {
		return neighbors.get(i).weight;
	    }
	}
	return 0;
    }

    public int compare(Object o1, Object o2) {
	return 0;
    }

    public int compareTo(Object other) {
	int toReturn = 0;

	if( this.priority < ((Vertex)other).priority ) {
	    toReturn = 1;
	}
	else if( this.priority > ((Vertex)other).priority ) {
	    toReturn = -1;
	}

	return toReturn;
    }

    public int getPriority() {
	return this.priority;
    }

    
}
