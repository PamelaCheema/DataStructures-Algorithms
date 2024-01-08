import java.awt.*;

public class SeamCarver {

    //energy for all pixels on the boarder of the image
    final int BOARDER_ENERGY = 1000;

    //declares picture as current image
    private Picture picture;

    //declares edgeTo for pixels
    private int edgeTo[][];

    //declares distTo for pixels
    private double distTo[][];

    //declares colors values for pixels
    private int[][] colors;

    //declares height for pic
    private int height;

    //declares width for pic
    private int width;


    public SeamCarver(Picture picture)  {
        //sets picture
        this.picture = picture;

        //initializes edgeTo with width and height of pic as the size
        edgeTo = new int[width()][height()];

        //initializes distTo with width and height of pic as the size
        distTo = new double[width()][height()];

        //intitalizes height
        height = height();

        //initializes width
        width = width();

        //initializes colors
        colors = getColors();

    }

    //returns the current picture with the current energy seams and height and width
    public Picture picture() {
        // Your code here
        Picture currentPic = new Picture(width(), height());

        //creates a new picture and updates the picture with new energy values and height and
        //width that have been modified
        for(int i = 0; i < width(); i++)
        {
            for(int j = 0; j < height(); j++)
            {
                //sets the current pic to the current color values after and modifications or seam removals
                currentPic.set(i, j , new Color(getColors()[i][j]));
            }
        }
        return null;

    }

    //returns the width of the picture
    public int width() {

        return picture.width();
    }

    //returns the height of the picture
    public int height() {

        return picture.height();
    }

    public double energy(int x, int y) {
        //checks that the x coordinate of the pixel is valid in the current pic
        if(x < 0 || x >= width())
        {
            throw new IndexOutOfBoundsException();
        }
        //checks that the y coordinate of the pixel is valid in the current pic
        if(y < 0 || y >= height())
        {
            throw new IndexOutOfBoundsException();
        }
        //return BOARDER_ENERGY val if the coordinate of the pixels falls on the border
        if(x == 0 || x == width() - 1 || y == 0 || y == height() - 1)
        {
            return BOARDER_ENERGY;
        }else {

            //calculate the square root of the x and y energies of the pixel colors as current pixel energy
            double energy = Math.sqrt(xEnergyCalculation(x, y, picture) + yEnergyCalculation(x, y, picture));

            return roundEnergyDecimals(energy, 2);
        }
    }

    //calculate the energy of the x values of the current pixel
    private double xEnergyCalculation(int x, int y, Picture picture)
    {
        //calculate Redx energy
        int red =  Math.abs(picture.get(x - 1, y).getRed() - picture.get(x + 1, y).getRed());
        //calculate Greenx energy
        int green = Math.abs(picture.get(x - 1, y).getGreen() - picture.get(x + 1, y).getGreen());
        //calculate Bluex enery
        int blue = Math.abs(picture.get(x - 1, y).getBlue() - picture.get(x + 1, y).getBlue());


        return (Math.pow(red, 2) + Math.pow(green, 2) + Math.pow(blue, 2));
    }

    //calculate the energy of teh y values of the current pixel
    private double yEnergyCalculation(int x, int y, Picture picture)
    {
        //calculate Redy energy
        int red =  Math.abs(picture.get(x, y - 1).getRed() - picture.get(x, y + 1).getRed());
        //calculate Greeny energy
        int green = Math.abs(picture.get(x, y - 1).getGreen() - picture.get(x, y + 1).getGreen());
        //calculate Bluey energy
        int blue = Math.abs(picture.get(x, y - 1).getBlue() - picture.get(x, y + 1).getBlue());

        return (Math.pow(red, 2) + Math.pow(green, 2) + Math.pow(blue, 2));
    }

    //round double energies to two decimal places
    public static double roundEnergyDecimals(double dec, int decPlace)
    {
        int temp = (int)(dec * Math.pow(10, decPlace));
        return ((double)temp)/Math.pow(10, decPlace);
    }


    public int[] findVerticalSeam() {

        minSearch();

        for (short c = 0; c < height() - 1; c++) {
            for (short r = 1; r < width() - 1; r++) {
                //relax pixel below and right
                relax(r, c, r - 1, c + 1, r);
                //relax pixel right
                relax(r, c, r, c + 1, r);
                //relac pixels right and above
                relax(r, c, r + 1, c + 1, r);
            }
        }

        //sets min and MAX to compare with distances between pixels
        double min = Double.MAX_VALUE;

        //index of file pos
        int endOfFile = 0;

        for (int i = 1; i < width() - 1; i++) {
            //compares distance between pixels to min val
            //if less then replace as min
            if (distTo[i][height() - 1] < min) {
                min = distTo[i][height() - 1];
                endOfFile = i;
            }
        }

        int[] seam = new int[height()];
        for (int j = height() - 1; j >= 0; j--) {
            seam[j] = endOfFile;
            endOfFile = edgeTo[endOfFile][j];
        }

        return seam;
    }

    public int[] findHorizontalSeam() {
        minSearch();

        for (short r = 0; r < width() - 1; r++) {
            for (short c = 1; c < height() - 1; c++) {
                //relaxes pixel above and left
                relax(r, c, r + 1, c - 1, c);
                //relaxes pixels above
                relax(r, c, r + 1, c, c);
                //relaxes pixel above and right
                relax(r, c, r + 1, c + 1, c);
            }
        }

        //sets the min value in the array as MAX to compare
        double min = Double.MAX_VALUE;

        //index to keep track of the file
        int endOfFile = 0;

        for (int j = 1; j < height() - 1; j++) {
            //compares the distance between pixels to min
            //if less then replace as min
            if (distTo[width() - 1][j] < min) {
                min = distTo[width() - 1][j];
                //move through file
                endOfFile = j;
            }
        }

        int[] seam = new int[width()];
        for (int i = width() - 1; i >= 0; i--) {
            seam[i] = endOfFile;
            endOfFile = edgeTo[i][endOfFile];
        }

        for(int i = 0; i < seam.length; i++)
        {
            System.out.println(seam[i]);
        }

        return seam;
    }

    //relaxes the edges to the current pixels
    //does not relax edges to border pixels because they all have BOARDER_ENERGY and not pixels on all sides
    private void relax(int oi, int oj, int i, int j, short edge) {
        double dis = distTo[oi][oj] + energy(i, j);
        if (dis < distTo[i][j]) {
            distTo[i][j] = dis;
            edgeTo[i][j] = edge;
        }
    }

    /**
     * sets min distances and edges between the pixels
     */
    private void minSearch() {
        for (int i = 0; i < width(); i++) {
            //sets the distances of boarder pixels to boarder energy
            distTo[i][0] = BOARDER_ENERGY;
            //edges for boarders are 0
            edgeTo[i][0] = 0;
        }
        for (int j = 0; j < height(); j++) {
            //sets the distances of boarder pixels to boarder energy
            distTo[0][j] = BOARDER_ENERGY;
            //edges for boarders are 0a
            edgeTo[0][j] = 0;
        }
        for (int j = 1; j < height(); j++) {
            for (int i = 1; i < width(); i++) {
                distTo[i][j] = Double.MAX_VALUE;
                edgeTo[i][j] = -1;
            }
        }
    }

    /**
     *
     * @return 2D array of the color values from the current pic
     */
    private int[][] getColors()
    {
        int[][] colors = new int[width()][height()];

        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                colors[i][j] = picture.get(i, j).getRGB();
            }
        }

        return colors;
    }


    /**
     *
     * @param seam horizontal seam to be removed
     */
    public void removeHorizontalSeam(int[] seam) {

        checkSeamValidity(seam);

        //stores the color values of the original/current pic
        int[][] tempColorVals = colors;

        //creates a new 2D array to store the color values of the new pic
        colors = new int[width()][height() - 1];

        //sets the colors for the new pic by copying the original colors excluding the values at the indexes from the seam
        for(int i = 0; i < width(); i++)
        {
            System.arraycopy(tempColorVals[i], 0, colors[i], 0, seam[i]);
            System.arraycopy(tempColorVals[i], seam[i] + 1, colors[i], seam[i], height() - seam[i] - 1);
        }

        height--;
    }

    /**
     *
     * @param seam vertical seam to be removed
     */
    public void removeVerticalSeam(int[] seam) {

        checkSeamValidity(seam);

        //stores the color values of the original (current pic)
        int[][] tempColorVals = colors;

        //creates a new 2D int color array for the colors of the new pic with a width-1
        colors= new int[width - 1][height];

        //index
        int currentPos = 0;

        //sets the new colors 2D array by not adding the original color values at the indexes in the seam
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width - 1; j++)
            {
                if(j >= seam[i])
                {
                    //increases index
                    currentPos = j + 1;
                }
                //sets the color values for the new pic
                colors[j][i] = tempColorVals[currentPos][i];
            }
        }
        //decreases the width, which is the new width of the new pic
        width--;

    }

    /**
     * Checks that the seam to be removed is a valid seam
     * checks that seam is correct length
     * checks that the seam doesn't have any values adjacent of more than 2 values
     * @param seam
     */
    private void checkSeamValidity(int[] seam) {
        //checks to see that the width and height is greater than 1
        if (width() <= 1 || height() <= 1) {
            throw new IllegalArgumentException();
        }
        //checks that the seam to be removed is valid by check it is not less than 1
        if (seam.length <= 1) {
            throw new IllegalArgumentException();
        }
        //checks to see if the adjacent values are at a difference no greater than 1
        for (int i = 0; i < seam.length - 1; i++) {
            if (Math.abs(seam[i] - seam[i + 1]) > 1) {
                throw new IllegalArgumentException();
            }
        }
    }

}
