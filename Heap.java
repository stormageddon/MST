import java.util.ArrayList;

public class Heap {

    ArrayList<Vertex> pq;
    int index = 0;

    public Heap() {
	pq = new ArrayList<Vertex>();
	pq.add(null);
	//	index++;
    }

    private void swim( int k ) {
	while( k > 1 && less( k/2, k ) ) {
	    exchange(k, k/2);
	    k = k/2;
	}
    }

    private void sink( int k ) {
	while( 2*k <= index ) {
	    int j = 2*k;
	    if (j < index && less(j, j+1)) j++;
	    if (!less(k, j)) break;
	    exchange(k, j);
	    k = j;
	}
    }

    public void insert(Vertex v) {
	index++;
	
	pq.add(index, v);
	swim(index);
    }

    public Vertex delMax() {
	Vertex max = pq.get(1);
	exchange( 1, index-- );
	sink( 1 );

	pq.set(index+1, null);
	return max;
    }

    private void exchange( int first, int second ) {
	Vertex temp = pq.get(first);
	pq.set( first, pq.get(second) );
	pq.set( second, temp );
    }

    private boolean less(int first, int second) {
	return (pq.get(first).compareTo(pq.get(second))) > 0;

    }

    public boolean isEmpty() {
	return pq.size() <= 0;
    }

    public boolean contains(Vertex v) {
	return pq.contains(v);
    }

    public void print() {
	System.out.println("Printing heap: ");
	for( Vertex v : pq ) {
	    if( v != null ) {
		System.out.println("\t" + v.getId());
	    }
	}
	System.out.println("End of heap");
    }
}
