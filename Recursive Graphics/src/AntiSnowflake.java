public class AntiSnowflake {

    public static void draw(int n, double x, double y, double length)
    {

        if(n == 1)
        {
            emptyTriangle(n, x, y, length);
        }

        emptyTriangle(n, x, y, length);

        if(n > 1)
        {
            double third = length/3;
            draw(n -1, x/3, y, third);
            draw(n - 1, x - third, y + height(third)*2, third);
            draw(n - 1, x + third, y + height(third)*2, third);
        }

    }

    private static void line()
    {

    }

    public static void triangle(int n, double[] x, double[] y, double length)
    {
        StdDraw.polygon(x, y);
    }

    public static void emptyTriangle(int n, double x, double y, double length)
    {

        if(n%2 == 0)
        {
            StdDraw.setPenColor(255, 255, 255);
        }else{
            StdDraw.setPenColor(0, 204, 204);
        }
        double[] xVal = new double[3];
        double[] yVal = new double[3];
        xVal[0] = x + length;
        yVal[0] = y;
        xVal[1] = x - length;
        yVal[1] = y;
        xVal[2] = x;
        yVal[2] = y + (height(length)* 2);

        StdDraw.filledPolygon(xVal, yVal);
    }

    public static double height(double length)
    {
        double height = Math.sqrt(Math.pow(length, 2) - Math.pow(length/2.0, 2));
        return height;
    }


    public static void main(String[] args)
    {
        StdDraw.setXscale(-1, 1);
        StdDraw.setYscale(-1, 1);
        //double[] x = {-.5, 0, .5};
        //double[] y = {-.5, height(.5), -.5};
        draw(7, 0, -.5, .5);

    }
}
