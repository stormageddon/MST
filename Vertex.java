/**
 * Vertex for graph
 *
 */
import java.util.ArrayList;

public class Vertex {
    private int id, predecessorId;
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
}