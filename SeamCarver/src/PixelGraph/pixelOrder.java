package PixelGraph;

import edu.princeton.cs.algs4.Stack;

/**
 * Class to topologically sort the pixels of the picture by
 * computing the reverse post-order of the graph.
 * 
 * @author Donisius Wigie
 *
 */
public class pixelOrder {

	private boolean[] marked;
	private Stack<Integer> reversePost;
	
	/**
	 * Constructor for the pixelOrder class.
	 * 
	 * @param G pictureGraph to compute the reverse post-order of.
	 */
	public pixelOrder(pictureGraph G) {
		reversePost = new Stack<Integer>();
		marked = new boolean[G.V()];
		
		for(int v = 0; v < G.V(); v++) {
			if(!marked[v]) {
				dfs(G, v);
			}
		}
	}
	
	/*
	 * Depth first search algorithm which places a vertex into a stack after 
	 * the recursive calls of dfs are finished.
	 */
	private void dfs(pictureGraph G, int v) {
		
		marked[v] = true;
		for(pixelEdge w : G.adj(v)) {
			if(!marked[w.to()]) {
				dfs(G, w.to());
			}
		}
		reversePost.push(v);
	}
	
	/**
	 * Getter for the reverse post-order of the graph.
	 * 
	 * @return reversePost Iterable type which represents the vertices of the graph in 
	 * reverse post-order.
	 */
	public Iterable<Integer> reversePostOrder(){
		return reversePost;
	}
	
}
