package SeamCarver;

import edu.princeton.cs.algs4.Picture;
import EnergyCalculator.EnergyCalculator;

public class Main {

	public static void main(String[] args) {
	
		Picture picture = new Picture("Pictures/jetski.png");
		picture.show();
		
		double[][] pixelEnergy = new EnergyCalculator(picture).getPixelEnergy();
		
		for(int i = 0; i < 50; i++) {
			
			SeamCarver carver = new SeamCarver("vertical", picture, pixelEnergy);
			picture = carver.getNewPic();
			pixelEnergy = carver.getPixelEnergy();
		}
		
		picture.show();
	
	}
}
