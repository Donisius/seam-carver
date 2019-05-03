package SeamCarver;

import edu.princeton.cs.algs4.Picture;

public class Main {

	public static void main(String[] args) {
		
		Picture picture = new Picture("Pictures/jetski.png");
		
		picture.show();
		
		for(int i = 0; i < 100; i++) {
			picture = SeamCarver.getNextVerticalPicture(picture);
		}
		
		picture.show();
		
	}
}