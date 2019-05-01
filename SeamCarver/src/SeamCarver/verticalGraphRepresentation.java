package SeamCarver;

import PixelGraph.pictureGraph;
import PixelGraph.pixelEdge;
import GeneralUtility.IndexConverter;

/**
 * Class to create a vertical Directed Acyclic Graph representation of a Picture.
 * 
 * @author Donisius Wigie
 *
 */
public class verticalGraphRepresentation {

	private double[][] pixelEnergy;
	private pictureGraph graphRepresentation;
	
	/**
	 * Constructor for the verticalGraphRepresentation class and creates a graph
	 * representation of a picture with edges going vertically down.
	 * 
	 * @param pixelEnergy Graph containing the energy of all the pixels of the graph.
	 * @param width Width of the picture.
	 * @param height Height of the picture.
	 */
	public verticalGraphRepresentation(double[][] pixelEnergy, int width, int height) {

		graphRepresentation = new pictureGraph(width, height);
		this.pixelEnergy = pixelEnergy;
	
		for(int i = 0; i < width; i++) {
			
			for(int j = 0; j < height - 1; j++) {
				
				/*
				 * When the pixel being checked is not at the right-most corner of the graph.
				 */
				if(i + 1 < width) {
					
					/*
					 * When the pixel being checked is not at the left-most corner of the graph.
					 */
					if(i - 1 >= 0) {
						
						/*
						 * Create edges pointing from the current pixel to the pixel to the bottom-left,
						 * bottom, and bottom right of the current pixel using weights of the pixels it points
						 * towards.
						 */
						pixelEdge newEdgeLD = new pixelEdge(IndexConverter.toIndex(i, j, width),
								IndexConverter.toIndex(i - 1, j + 1, width), pixelEnergy[i - 1][j + 1]);
						
						pixelEdge newEdgeDown = new pixelEdge(IndexConverter.toIndex(i, j, width),
								IndexConverter.toIndex(i, j + 1, width), pixelEnergy[i][j + 1]);
						
						pixelEdge newEdgeRD = new pixelEdge(IndexConverter.toIndex(i, j, width),
								IndexConverter.toIndex(i + 1, j + 1, width), pixelEnergy[i + 1][j + 1]); 
						
						graphRepresentation.addEdge(newEdgeLD);
						graphRepresentation.addEdge(newEdgeDown);
						graphRepresentation.addEdge(newEdgeRD);
					}
					
					/*
					 * When the pixel being checked is not at the right-most corner but is at the 
					 * left-most corner of the graph.
					 */
					else {
						
						/*
						 * Create edges pointing from the current pixel to the pixel to the bottom, and 
						 * bottom right of the current pixel using weights of the pixels it points
						 * towards.
						 */
						pixelEdge newEdgeDown = new pixelEdge(IndexConverter.toIndex(i, j, width),
								IndexConverter.toIndex(i, j + 1, width), pixelEnergy[i][j + 1]);
						
						pixelEdge newEdgeRD = new pixelEdge(IndexConverter.toIndex(i, j, width),
								IndexConverter.toIndex(i + 1, j + 1, width), pixelEnergy[i + 1][j + 1]);
						
						graphRepresentation.addEdge(newEdgeDown);
						graphRepresentation.addEdge(newEdgeRD);
						
					}
				}
				
				/*
				 * When the pixel being checked is at the right-most corner of the graph.
				 */
				else {
					
					/*
					 * Create edges pointing from the current pixel to the pixel to the bottom-left
					 *  and bottom of the current pixel using weights of the pixels it points
					 * towards.
					 */
					pixelEdge newEdgeLD = new pixelEdge(IndexConverter.toIndex(i, j, width),
							IndexConverter.toIndex(i - 1, j + 1, width), pixelEnergy[i - 1][j + 1]);
					
					pixelEdge newEdgeDown = new pixelEdge(IndexConverter.toIndex(i, j, width),
							IndexConverter.toIndex(i, j + 1, width), pixelEnergy[i][j + 1]);
					
					graphRepresentation.addEdge(newEdgeLD);
					graphRepresentation.addEdge(newEdgeDown);
					
				}
				
			}
		}
		
		/*
		 * Create the source of the graph; a vertex pointing to all vertices in the first row of the 
		 * graph representation of the Picture.
		 */
		for(int i = 0; i < width; i++) {
			
			/*
			 * Create the source vertex.
			 */
			graphRepresentation.addEdge(new pixelEdge(width*height, IndexConverter.toIndex(i, 0, width), 
					pixelEnergy[i][0]));
			
			/*
			 * Create the sink vertex.
			 */
			graphRepresentation.addEdge(new pixelEdge(IndexConverter.toIndex(i, height - 1, width), 
					width*height + 1, 0));
			
		}
	}
	
	/**
	 * Getter for the graph representation containing edges going vertically below the 3 pixels
	 * below the current pixel.
	 * 
	 * @return graphRepresentation Vertical Directed Acyclic Graph representation created of the picture.
	 */
	public pictureGraph verticalEdgePictureGraph() {
		return this.graphRepresentation;
	}
	
}
