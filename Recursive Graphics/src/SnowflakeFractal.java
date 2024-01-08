public class SnowflakeFractal {

    public static void draw(int n, double[] x, double[] y, double theta)
    {
        if(n == 1)
        {
            baseStructure(x, y, theta, 0);
        }

        baseStructure(x, y, theta, 0);
        double distance = distance(x, y) * 2;

        if(n > 1)
        {
            StdDraw.setPenColor(StdDraw.RED);


            draw(n - 1, Transform2D.scale(x, y, .5, 'x'), Transform2D.scale(x, y, .5, 'y'), theta/2);

        }
    }

    private static double distance(double[] x, double[] y)
    {
        return Math.sqrt(Math.pow(x[1] - x[0], 2) + Math.pow(y[1] - y[0], 2));
    }


    public static void baseStructure(double[] x, double[] y, double theta, double len)
    {
        double[][] rotated = rotate(copy(x), copy(y), theta);

        for(int i = 0; i < 5; i++)
        {
            double[] rotatedX = {rotated[0][0], rotated[1][0]};
            double[] rotatedY = {rotated[0][1], rotated[1][1]};
            rotated = rotate(rotatedX, rotatedY, theta);

            if(len > 0 && len < 1 || len < 0 && len > -1)
            {
               rotatedX = Transform2D.translate(rotatedX, rotatedY, 0, len, 'x');
               rotatedY = Transform2D.translate(rotatedX, rotatedY, 0, len, 'y');
            }

            StdDraw.line(rotatedX[0], rotatedY[0], rotatedX[1], rotatedY[1]);
        }
    }

    public static void scaleSnowflake(double[] x, double[] y, double theta)
    {
        StdDraw.line(x[0], y[0] ,x[1], y[1]);
        double[][] rotated = rotate(copy(x), copy(y), theta);


        for(int i = 0; i < 4; i++)
        {
            StdDraw.line(rotated[0][0], rotated[0][1], rotated[1][0], rotated[1][1]);
            double[] rotatedX = {rotated[0][0], rotated[1][0]};
            double[] rotatedY = {rotated[0][1], rotated[1][1]};
            rotated = rotate(rotatedX, rotatedY, theta);
        }

    }

    public static double[][] rotate(double[] xVal, double[] yVal, double theta )
    {
        for(int i = 0; i < xVal.length; i++)
        {
            double temp = xVal[i];
            xVal[i] = (xVal[i] * Math.cos(Math.toRadians(theta))) - (yVal[i] * Math.sin(Math.toRadians(theta)));
            yVal[i] = (yVal[i] * Math.cos(Math.toRadians(theta))) + (temp * Math.sin(Math.toRadians(theta)));
        }
        double[][] newPoints = new double[xVal.length][2];
        for(int i = 0; i < newPoints.length; i++)
        {
            for(int j = 0; j < newPoints[0].length; j++)
            {
                newPoints[i][0] = xVal[i];
                newPoints[i][1] = yVal[i];
            }
        }

        return newPoints;
    }

    public static double[] copy(double[] array)
    {
        double[] copiedArray = new double[array.length];
        for(int i = 0; i < array.length; i++)
        {
            copiedArray[i] = array[i];
        }
        return copiedArray;
    }

    public static void main(String[] args)
    {
        StdDraw.setXscale(-1, 1);
        StdDraw.setYscale(-1, 1);
        double theta = 72;
        double[] x = {0, 0};
        double[] y = {-.6, 0};
        draw(2, x, y, theta);
    }
}
