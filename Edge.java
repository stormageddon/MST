// Edge.java
// Data structure that stores two vertices and the 
// weight between them.
//
// @author Michael Caputo <mpc5944>

import java.util.Comparator;

public class Edge implements Comparator {
    public int v1; // left vertex
    public int v2; // right vertex
    public int weight;

    public Edge(int v1, int v2, int weight) {
	this.v1 = v1;
	this.v2 = v2;
	this.weight = weight;
    }

    public int[] getVertices() {
	int[] vertices = {v1, v2};
	return vertices;
    }

    public int getWeight() {
	return weight;
    }

    @Override
    public int compare(Object e1, Object e2) {
	int toReturn = 0;
	Edge edge1 = ((Edge) e1);
	Edge edge2 = ((Edge) e2);

	if (edge1.weight < edge2.weight) {
	    toReturn = 1;
	}
	else if (edge1.weight > edge2.weight) {
	    toReturn = -1;
	}
	else {
	    if( edge1.v1 < edge2.v2 ) {
		toReturn = 1;
	    }
	    else if( edge1.v2 < edge2.v2 ) {
		toReturn = -1;
	    }
	    else {
		toReturn = 0;
	    }
	}

	return toReturn;
    }

    @Override
    public boolean equals(Object otherEdge) {
	if (otherEdge == null) {
	    //	    System.out.println("Null");
	    return false;
	}
	Edge oEdge = ((Edge)otherEdge);
	//	System.out.println("Comparing edges");
	return ( ( this.v1 == oEdge.v1 || this.v1 == oEdge.v2) && (this.v2 == oEdge.v1 || this.v2 == oEdge.v2) && (this.weight == oEdge.weight) );
    }

    @Override
    public String toString() {
	return v1 + " " + v2 + " weight = " + weight;
    }
}
