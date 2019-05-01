package SeamCarver;

import java.util.ArrayList;

import EnergyCalculator.EnergyCalculator;
import EnergyCalculator.SeamRemover;
import GeneralUtility.IndexConverter;
import PixelGraph.SPSeam;
import PixelGraph.pixelEdge;
import edu.princeton.cs.algs4.Picture;

/**
 * Abstract data type that acts a the controller for the program.
 * 
 * @author Donisius Wigie
 *
 */
public class SeamCarver {
	
	/**
	 * Creates a new picture with a vertical seam removed. Note that the source index is 
	 * picture.width()*picture.height() and the sink index is picture.width()*picture.height() + 1.
	 * 
	 * @param fileName Name of the file containing the picture jpeg image.
	 */
	public static Picture getNextVerticalPicture(Picture picture) {
		
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
		System.out.println(shortestPath.distTo(picture.width()*picture.height() + 1));
		
		/*
		 * Put the shortest seam into an array list.
		 */
		ArrayList<pixelEdge> shortestSeam = new ArrayList<>();
		
		for(pixelEdge index : shortestPath.pathTo(picture.width()*picture.height() + 1)) {
			if(index.from() == picture.width()*picture.height() || index.to() == 
					picture.width()*picture.height() + 1) {
				continue;
			}
			
			shortestSeam.add(index);
		}	
		
		/*
		 * Create the next picture by removing the calculated vertical seam with the lowest energy.
		 */
		Picture nextPicture = SeamRemover.removeVerticalSeam(picture, shortestSeam);
		return nextPicture;
	}
}
