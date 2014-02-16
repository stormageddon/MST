public class Tuple<Vertex,Integer> {
    public final Vertex vertex;
    public final Integer weight;
    public Tuple (Vertex x, Integer y) {
	this.vertex = x;
	this.weight = y;
    }
}