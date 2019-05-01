package SeamCarver;

import EnergyCalculator.EnergyCalculator;
import GeneralUtility.IndexConverter;
import PixelGraph.SPSeam;
import PixelGraph.pixelEdge;
import edu.princeton.cs.algs4.Picture;

/**
 * Class that acts as a controller for the program.
 * 
 * @author Donisius Wigie
 *
 */
public class SeamCarver {
	
	/**
	 * Constructor for SeamCarver controller class. Note that the source index is width*height and 
	 * the sink index is width*height + 1.
	 * 
	 * @param fileName Name of the file containing the picture jpeg image.
	 */
	public SeamCarver(String fileName) {
		
		/*
		 * Picture representation of the given jpeg image.
		 */
		Picture picture = new Picture(fileName);
		
		/*
		 * Array containing the energy of each pixel in the picture.
		 */
		double[][] pixelEnergy = new EnergyCalculator(picture).getPixelEnergy();
		
		/*
		 * Create a vertical Directed Acyclic Graph using the given picture.	
		 */
		verticalGraphRepresentation graphRepresentation = 
				new verticalGraphRepresentation(pixelEnergy, picture.width(), picture.height());
		
		/*
		 * Topologically sort the vertical Directed Acyclic Graph of the given picture.
		 */
		SPSeam shortestPath = new SPSeam(graphRepresentation.verticalEdgePictureGraph(), 
				picture.width()*picture.height());
		
		/*
		 * Print out the Shortest path from source to sink of the graph.
		 */
		System.out.println("The shortest path from source to sink:");
		for(pixelEdge index : shortestPath.pathTo(picture.width()*picture.height() + 1)) {
			System.out.println(IndexConverter.indexToRow(index.from(), picture.width()) + "," 
					+ IndexConverter.indexToCol(index.from(), picture.width()) + " -> " +
					IndexConverter.indexToRow(index.to(), picture.width()) + "," + 
					IndexConverter.indexToCol(index.to(), picture.width()));
		}
		
		for(pixelEdge index : shortestPath.pathTo(picture.width()*picture.height() + 1)) {
			if(index.from() == picture.width()*picture.height() || index.to() == 
					picture.width()*picture.height() + 1) {
				continue;
			}
			picture.setRGB(IndexConverter.indexToCol(index.to(), picture.width()), 
					IndexConverter.indexToRow(index.to(), picture.width()), -1);
		}
		picture.show();
	}
	
	public static void main(String[] args) {
		
		new SeamCarver("Pictures/rocks.jpg");
		
	}
}
