package PixelGraph;

import edu.princeton.cs.algs4.Bag;

/**
 * Class to represent a graph representation of a picture.
 * 
 * @author Donisius Wigie.
 *
 */
public class pictureGraph {

	private final int V;
	private final int numCol;
	private final int numRow;
	private int E;
	private Bag<pixelEdge>[] adj;
	
	/**
	 * Constructor for the pictureGraph.
	 * 
	 * @param numCol Number of columns the picture contains.
	 * @param numRow Number of rows the picture contains.
	 */
	public pictureGraph(int numCol, int numRow) {
		
		this.V = numCol * numRow;
		this.numCol = numCol;
		this.numRow = numRow;
		this.E = 0;
		adj = (Bag<pixelEdge>[]) new Bag[V];
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<pixelEdge>();
		}
	}
	
	/**
	 * Getter for number of vertices present in the graph.
	 * 
	 * @return V The number of vertices present in the graph.
	 */
	public int V() {
		return V;
	}
	
	/**
	 * Getter for the number of edges present in the graph.
	 * 
	 * @return E The number of edges present in the graph.
	 */
	public int E() {
		return E;
	}
	
	/**
	 * Adds an edge on the graph.
	 * 
	 * @param e pixelEdge to add to the graph.
	 */
	public void addEdge(pixelEdge e) {
		adj[e.from()].add(e);
	}
	
	/**
	 * Getter for all the edges present in the graph.
	 * 
	 * @return bag Iterable type containing all edges present in the graph.
	 */
	public Iterable<pixelEdge> edges(){
		Bag<pixelEdge> bag = new Bag<pixelEdge>();
		for(int v = 0; v < V; v++) {
			for(pixelEdge e : adj[v]) {
				bag.add(e);
			}
		}
		return bag;
	}
	
	/**
	 * Getter for all the edges adjacent to a given value.
	 * 
	 * @param v The vertex to obtain all adjacent edges associated to.
	 * @return adj[v] Iterable type containing all adjacent edges to v.
	 */ 
	public Iterable<pixelEdge> adj(int v){
		return adj[v];
	}
}
