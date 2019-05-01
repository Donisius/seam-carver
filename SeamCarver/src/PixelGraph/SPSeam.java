package PixelGraph;

import edu.princeton.cs.algs4.Stack;

/**
 * Class to find the shortest seam in a given graph.
 * 
 * @author Donisius Wigie
 *
 */
public class SPSeam {

	private pixelEdge[] edgeTo;
	private double[] distTo;
	
	/**
	 * Constructor for SPSeam.
	 * 
	 * @param G pictureGraph to find shortest seam of.
	 * @param s Source vertex.
	 */
	public SPSeam(pictureGraph G, int s) {
		edgeTo = new pixelEdge[G.V()];
		distTo = new double[G.V()];
		
		for(int v = 0; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		
		pixelOrder postOrder = new pixelOrder(G);
		
		for(int v : postOrder.reversePostOrder()) {
			relax(G, v);
		}
	}
	
	/*
	 * Standard relax method obtained from page 648 of Algorithms Fourth Edition
	 * by Robert Sedgewick and Kevin Wayne.
	 */
	private void relax(pictureGraph G, int v) {
		for(pixelEdge e : G.adj(v)) {
			int w = e.to();
			if(distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
			}
		}
	}
	
	/**
	 * Queries whether there is a path to given vertex v.
	 * 
	 * @param v Vertex to see if a path from the source exists to.
	 * @return distTo[v] < Double.POSITIVE_INFINITY.
	 */
	public boolean hasPathTo(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	
	/**
	 * Gives the distance from the source vertex to given vertex v.
	 * 
	 * @param v v Vertex to obtain distance to from the source vertex.
	 * @return distTo[v] Distance to vertex v from the source vertex.
	 */
	public double distTo(int v) {
		return distTo[v];
	}
	
	/**
	 * Getter for the path from the source vertex to given vertex v.
	 * 
	 * @param v Vertex to obtain the path from the source to.
	 * @return path Iterable type containing the path from the source vertex to 
	 * given vertex v.
	 */
	public Iterable<pixelEdge> pathTo(int v){
		if(!hasPathTo(v)) return null;
		Stack<pixelEdge> path = new Stack<pixelEdge>();
		for(pixelEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
			path.push(e);
		}
		return path;
	}
}
