package GeneralUtility;

public class IndexConverter {

	public IndexConverter() {
		
	}
	/*
	 * Method to convert a 1D array index into a 2D array row index.
	 */
	public static int indexToRow(int index, int width) {
		return index/width;
	}
	
	/*
	 * Method to convert a 1D array index into a 2D array col index.
	 */
	public static int indexToCol(int index, int width) {
		return index % width;
	}
	
	/*
	 * Method to convert a 2D array index into a 1D array index.
	 */
	public static int toIndex(int col, int row, int width) {
		return width * row + col;
	}
	
}
