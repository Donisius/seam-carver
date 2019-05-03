package EnergyCalculator;

import java.util.ArrayList;

import GeneralUtility.IndexConverter;
import PixelGraph.pixelEdge;
import edu.princeton.cs.algs4.Picture;

public class SeamRemover {
	
	private Picture newPic;
	private double[][] newPixelEnergy;
	
	/**
	 * Constructor for the SeamRemover Class.
	 * 
	 * @param type "vertical" or "Vertical" to remove a vertical seam and "horizontal" or "Horizontal"
	 * to remove a horizontal seam.
	 * @param picture Original picture to remove seam from.
	 * @param shortestSeam Calculated path of the shortest (lowest energy) vertical seam.
	 * @param pixelEnergy Array of calculated energies of each pixel in the picture.
	 */
	public SeamRemover(String type, Picture picture, ArrayList shortestSeam, double[][] pixelEnergy) {
		
		if(type == "vertical" || type == "Vertical") {
			
			removeVerticalSeam(picture, shortestSeam, pixelEnergy);
			
		}
		
		else if(type == "horizontal" || type == "Horizontal") {
			
			removeHorizontalSeam(picture, shortestSeam, pixelEnergy);
			
		}
		
	}
	
	public Picture getNewPic() {
		
		return this.newPic;
		
	}
	
	public double[][] getPixelEnergy(){
		
		return this.newPixelEnergy;
		
	}
	
	/**
	 * Create a new picture by removing a vertical seam calculated to have the least amount of energy.
	 * 
	 * @param picture Original picture to remove seam from.
	 * @param shortestSeam Calculated path of the shortest (lowest energy) vertical seam.
	 * @param pixelEnergy Array of calculated energies of each pixel in the picture.
	 * @return newPic A new picture with the shortest (lowest energy) vertical seam removed.
	 */
	private void removeVerticalSeam(Picture picture, ArrayList shortestSeam, double[][] pixelEnergy) {
		
		/*
		 * Initialize a picture with 1 less column of pixels.
		 */
		this.newPic = new Picture(picture.width() - 1, picture.height());
		
		/*
		 * Initialize a new pixelEnergy array with adjusted positions.
		 */
		this.newPixelEnergy = new double[picture.width() - 1][picture.height()];
		
		/*
		 * Iterate through the picture searching for pixels to remove.
		 */
		for(int i = 0; i < picture.height() - 1; i++) {
			
			int offset = 0;
			pixelEdge delPixel = (pixelEdge) shortestSeam.get(i);
			
			for(int j = 0; j < picture.width() - 1; j++) {
				
				/*
				 * When at the beginning of the list, remove the coordinates of the "from" pixel
				 * and increment the offset for the current row by one to accommodate.
				 */
				if(i == 0) {
					if(j == IndexConverter.indexToCol(delPixel.from(), picture.width())) {
						offset++;
					}
				}
				
				/*
				 * When not at the beginning of the list, remove the coordinates of the "to" pixel
				 * and increment the offset for the current row by one to accommodate.
				 */
				else if(j == IndexConverter.indexToCol(delPixel.to(), picture.width())) {
					offset++;
				}
				
				/*
				 * Set the new coordinates of the picture using the offset as accommodation for the current position
				 * on the current row of the picture.
				 */
				this.newPic.set(j, i, picture.get(j + offset, i));
				
				/*
				 * Set the new coordinates of the pixelEnergy array using the offset as accommodation for the current position
				 * on the current row of the picture.
				 */
				this.newPixelEnergy[j][i] = pixelEnergy[j + offset][i];
			}
		}
	}
	
	/**
	 * Create a new picture by removing a horizontal seam calculated to have the least amount of energy.
	 * 
	 * @param picture Original picture to remove seam from.
	 * @param shortestSeam Calculated path of the shortest (lowest energy) horizontal seam.
	 * @param pixelEnergy Array of calculated energies of each pixel in the picture.
	 * @return newPic A new picture with the shortest (lowest energy) horizontal seam removed.
	 */
	private void removeHorizontalSeam(Picture picture, ArrayList shortestSeam, double[][] pixelEnergy) {
		
		/*
		 * Initialize a picture with 1 less row of pixels.
		 */
		this.newPic = new Picture(picture.width(), picture.height() - 1);
		
		/*
		 * Initialize a new pixelEnergy array with adjusted positions.
		 */
		this.newPixelEnergy = new double[picture.width()][picture.height() - 1];
		
		/*
		 * Iterate through the picture searching for pixels to remove.
		 */
		for(int i = 0; i < picture.width() - 1; i++) {
			
			int offset = 0;
			pixelEdge delPixel = (pixelEdge) shortestSeam.get(i);
			
			for(int j = 0; j < picture.height() - 1; j++) {
				
				/*
				 * When at the beginning of the list, remove the coordinates of the "from" pixel
				 * and increment the offset for the current col by one to accommodate.
				 */
				if(i == 0) {
					if(j == IndexConverter.indexToRow(delPixel.from(), picture.width())) {
						offset++;
					}
				}
				
				/*
				 * When not at the beginning of the list, remove the coordinates of the "to" pixel
				 * and increment the offset for the current col by one to accommodate.
				 */
				else if(j == IndexConverter.indexToRow(delPixel.to(), picture.width())) {
					offset++;
				}
				
				/*
				 * Set the new coordinates of the picture using the offset as accommodation for the current position
				 * on the current column of the picture.
				 */
				this.newPic.set(i, j, picture.get(i, j + offset));
				
				/*
				 * Set the new coordinates of the pixelEnergy array using the offset as accommodation for the current position
				 * on the current row of the picture.
				 */
				this.newPixelEnergy[i][j] = pixelEnergy[i][j + offset];
			}
		}
	}
}
