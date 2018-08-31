Data Structures Assignment 1 - Aerial Sheeps Recognition

Fritz Gerald Santos - 20071968 - Computer Forensics and Security (Y4 - 2018/2019)


Outstanding work:
Removing outliers -- see Problem below.

Find boundaries of sheeps and clusters 
- When a cluster/sheep is picked, get the root node.
- By looping through all the nodes in the set, if the pixel of the node has an adjacent black pixel, i would change the colour of this.
- if the estimated amount of sheep in this set == 1, colour would be blue; if > 1, colour would be red.


Estimate sheep cluster numbers 
- I planned on using onMouseClick to get the pixel in the image and find the root node
- Each node would have a property of how many sheeps are in that set judging from the size.



Accomplished work:
Node class:
- Each node represents a disjoint set of point/pixel in the image.
- It contains pixel co-ordination, if it's a parent, the size of the set, if it is a white pixel, the estimated amount of sheep in the set

UnionFind:
- Used to join disjointed sets of pixels
- contains method of finding the root of a node recursively
- contains a simple union of two nodes method
- contains a union-by-size method

ImageProcessor:
- contains making disjoint set node
- method for resizing a BufferedImage
- method for converting an image into black and white
- method for joining white pixels sets

Main:
- Contains a simple JavaFX GUI that opens an upload window where the user can select an image in a file chooser (only shows JPG and PNG)
- The image is then opened in the window with options of finding total estimated sheeps and black and white version.



Problem:
I tried to find a solution of removing outliers and looked into possibly doing Chauvenet's Criterion but I didn't have time.
I used a mean to remove any outliers below it instead. 
From looking at the size of each sets in the array of sets. I noticed that they were all around *3 of the mean, so I used that to find the average size of the sheep.
