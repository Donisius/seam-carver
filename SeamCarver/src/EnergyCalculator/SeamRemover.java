package EnergyCalculator;

import java.util.ArrayList;

import GeneralUtility.IndexConverter;
import PixelGraph.pixelEdge;
import edu.princeton.cs.algs4.Picture;

public class SeamRemover {
	
	/**
	 * Create a new Picture by removing a vertical seam calculated to have the least amount of energy.
	 * 
	 * @param picture Original picture to remove seam from.
	 * @param shortestSeam Calculated path of the shortest (lowest energy) vertical seam.
	 * @return newPic A new picture with the shortest (lowest energy) vertical seam removed.
	 */
	public static Picture removeVerticalSeam(Picture picture, ArrayList shortestSeam) {
		
		/*
		 * Initialize a picture with 1 less column of pixels.
		 */
		Picture newPic = new Picture(picture.width() - 1, picture.height());
		
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
				 * Set the new coordinates of the picture using the offset as accomodation for the current position
				 * on the current row of the picture.
				 */
				newPic.set(j, i, picture.get(j + offset, i));
			}
		}
		return newPic;
	}
}
