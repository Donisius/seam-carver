package SeamCarver;

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
	
	public SeamCarver(String fileName) {
		
		Picture pic = new Picture(fileName);
		
		
	}
	
	/*
	 * Method to convert a 1D array index into a 2D array row index.
	 */
	private int indexToRow(int index, int width) {
		return index/width;
	}
	
	/*
	 * Method to convert a 1D array index into a 2D array col index.
	 */
	private int indexToCol(int index, int width) {
		return index % width;
	}
	
	/*
	 * Method to convert a 2D array index into a 1D array index.
	 */
	private int toIndex(int col, int row, int width) {
		return width * row + col;
	}
	
}
