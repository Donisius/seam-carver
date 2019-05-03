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
	
	
	private Picture nextPicture;
	private double[][] nextPixelEnergy;
	
	public SeamCarver(String type, Picture picture, double[][] pixelEnergy) {
		
		if(type == "vertical" || type == "Vertical") {
			
			getNextVerticalPicture(picture, pixelEnergy);
			
		}
		
		else if(type == "horizontal" || type == "Horizontal") {
			
			getNextHorizontalPicture(picture, pixelEnergy);
			
		}
		
	}
	
	public Picture getNewPic() {
		
		return this.nextPicture;
		
	}
	
	public double[][] getPixelEnergy(){
		
		return this.nextPixelEnergy;
		
	}
	
	/**
	 * Creates a new picture with a vertical seam removed. Note that the source index is 
	 * picture.width()*picture.height() and the sink index is picture.width()*picture.height() + 1.
	 * 
	 * @param Picture to remove vertical seam from.
	 */
	private void getNextVerticalPicture(Picture picture, double[][] pixelEnergy) {
		
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
		
		SeamRemover placeholder = new SeamRemover("vertical", picture, shortestSeam, pixelEnergy);
		
		this.nextPicture = placeholder.getNewPic();
		this.nextPixelEnergy = placeholder.getPixelEnergy();
		
	}
	
	private void getNextHorizontalPicture(Picture picture, double[][] pixelEnergy) {
		
		/*
		 * Create a horizontal Directed Acyclic Graph using the given picture.	
		 */
		horizontalGraphRepresentation graphRepresentation = 
				new horizontalGraphRepresentation(pixelEnergy, picture.width(), picture.height());
		
		/*
		 * Topologically sort the vertical Directed Acyclic Graph of the given picture.
		 */
		SPSeam shortestPath = new SPSeam(graphRepresentation.horizontalEdgePictureGraph(), 
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
		
		SeamRemover placeholder = new SeamRemover("horizontal", picture, shortestSeam, pixelEnergy);
		
		this.nextPicture = placeholder.getNewPic();
		this.nextPixelEnergy = placeholder.getPixelEnergy();
	}
	
	
}
