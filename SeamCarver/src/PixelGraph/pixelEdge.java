package PixelGraph;

/**
 * Class to represent an edge joining two pixels.
 * 
 * @author Donisius Wigie
 *
 */
public class pixelEdge {
	
	private final int v;
	private final int w;	
	private final double weight;
	
	/**
	 * Constructor for the pixelEdge class.
	 * 
	 * @param v The pixel which the edge points away from.
	 * @param w The pixel which the edge points towards.
	 * @param weight The energy of the pixel in which the edge points towards.
	 */
	public pixelEdge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	/**
	 * Getter for the energy of the edge.
	 * 
	 * @return weight The energy of the pixel in which the edge points towards.
	 */
	public double weight() {
		return weight;
	}
	
	/**
	 * Getter for the "from" pixel.
	 * 
	 * @return v The pixel which the edge points away from.
	 */
	public int from() {
		return v;
	}
	
	/**
	 * Getter for the "to" pixel.
	 * 
	 * @return w The pixel which the edge points towards.
	 */
	public int to() {
		return w;
	}
}
