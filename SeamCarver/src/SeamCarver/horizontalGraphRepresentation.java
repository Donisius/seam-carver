package SeamCarver;

import GeneralUtility.IndexConverter;
import PixelGraph.pictureGraph;
import PixelGraph.pixelEdge;

/**
 * Creates a horizontal Directed Acyclic Graph given the energy in a picture's 
 * pixels.
 * 
 * @author Donisius Wigie
 *
 */
public class horizontalGraphRepresentation {

	private double[][] pixelEnergy;
	private pictureGraph graphRepresentation;
	
	/**
	 * Constructor for the horizontalGraphRepresentation class an creates a graph
	 * representation with edges going towards a horizontal sink.
	 * 
	 * @param pixelEnergy Array containing the energy of all the pixels of the graph.
	 * @param width Width of the picture.
	 * @param height Height of the picture.
	 */
	public horizontalGraphRepresentation(double[][] pixelEnergy, int width, int height) {
		
		graphRepresentation = new pictureGraph(width, height);
		this.pixelEnergy = pixelEnergy;
		
		for(int i = 0; i < width - 1; i++) {
			
			for(int j = 0; j < height; j++) {
				
				/*
				 * When the pixel being checked is not in the bottom-most row.
				 */
				if(j + 1 < height) {
					
					/*
					 * When the pixel being checked is not in the top-most row or bottom-most row.
					 */
					if(j - 1 >= 0) {
						
						/*
						 * Create edges pointing from the current pixel to the pixel to the top right,
						 * right, and bottom right of the current pixel using weights of the pixels it points
						 * towards.
						 */
						pixelEdge newEdgeUR = new pixelEdge(IndexConverter.toIndex(i, j, width), 
								IndexConverter.toIndex(i + 1, j - 1, width), pixelEnergy[i + 1][j - 1]);
						
						pixelEdge newEdgeRight = new pixelEdge(IndexConverter.toIndex(i, j, width),
								IndexConverter.toIndex(i + 1, j, width), pixelEnergy[i + 1][j]);
						
						pixelEdge newEdgeDR = new pixelEdge(IndexConverter.toIndex(i, j, width),
								IndexConverter.toIndex(i + 1, j + 1, width), pixelEnergy[i + 1][j + 1]);
						
						graphRepresentation.addEdge(newEdgeUR);
						graphRepresentation.addEdge(newEdgeRight);
						graphRepresentation.addEdge(newEdgeDR);
						
					}
					
					/*
					 * When the pixel being checked is not in the bottom-most row.
					 * but the pixel is in the top-most row.
					 */
					else {
						
						/*
						 * Create edges pointing from the current pixel to the pixel to the right
						 * and bottom right of the current pixel using weights of the pixels it points
						 * towards.
						 */
						pixelEdge newEdgeRight = new pixelEdge(IndexConverter.toIndex(i, j, width),
								IndexConverter.toIndex(i + 1, j, width), pixelEnergy[i + 1][j]);
						
						pixelEdge newEdgeDR = new pixelEdge(IndexConverter.toIndex(i, j, width),
								IndexConverter.toIndex(i + 1, j + 1, width), pixelEnergy[i + 1][j + 1]);
						
						graphRepresentation.addEdge(newEdgeRight);
						graphRepresentation.addEdge(newEdgeDR);
						
					}
				}
				
				/*
				 * When the pixel being checked is in the bottom-most row.
				 */
				else {
					
					/*
					 * Create edges pointing from the current pixel to the pixel to the top right
					 * and right of the current pixel using weights of the pixels it points towards.
					 */
					pixelEdge newEdgeUR = new pixelEdge(IndexConverter.toIndex(i, j, width), 
							IndexConverter.toIndex(i + 1, j - 1, width), pixelEnergy[i + 1][j - 1]);
					
					pixelEdge newEdgeRight = new pixelEdge(IndexConverter.toIndex(i, j, width),
							IndexConverter.toIndex(i + 1, j, width), pixelEnergy[i + 1][j]);
					
					graphRepresentation.addEdge(newEdgeUR);
					graphRepresentation.addEdge(newEdgeRight);
				}
			}
		}
		
		for(int i = 0; i < height; i++) {
			
			/*
			 * Create the source vertex.
			 */
			graphRepresentation.addEdge(new pixelEdge(width*height, IndexConverter.toIndex(0, i, width), 
					pixelEnergy[0][i]));
			
			/*
			 * Create the sink vertex.
			 */
			graphRepresentation.addEdge(new pixelEdge(IndexConverter.toIndex(width - 1, i, width), 
					width*height + 1, 0));
		}
	}
	
	/**
	 * Getter for the graph representation containing edges going horizontally 
	 * to the right the 3 pixels below the current pixel.
	 * 
	 * @return graphRepresentation horizontal Directed Acyclic Graph representation created of the picture.
	 */
	public pictureGraph horizontalEdgePictureGraph() {
		return this.graphRepresentation;
	}
}
