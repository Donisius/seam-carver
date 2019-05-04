# SeamCarver

Using the dual-gradient energy function, the program calculates the energy of each pixel in the picture relative to its position to other pixels. It then removes a path of pixels containing the least amount of energy either vertically (from the top to the bottom with one pixel in each row) or horizontally (from left to right with one pixel in each row). 

### Original Image

![jetski](https://user-images.githubusercontent.com/45505172/57173833-ea843180-6e03-11e9-9235-d7cbdd13c36e.png)

### Image with lowest energy vertical seams removed

![jetski_carved](https://user-images.githubusercontent.com/45505172/57173821-b27cee80-6e03-11e9-9c5c-1dc318a373d1.png)

### Image with lowest energy horizontal seams removed

![jetski_carved2](https://user-images.githubusercontent.com/45505172/57173836-f112a900-6e03-11e9-92bb-8ab3ccd35bba.png)

### Image with both lowest energy vertical and horizontal seams removed

![jetski_both](https://user-images.githubusercontent.com/45505172/57173999-9417f280-6e05-11e9-8a91-7b3fb9dd252e.png)
