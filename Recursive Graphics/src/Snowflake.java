public class Snowflake {

    public static void draw(int n, double[] x, double[] y, double theta)
    {
        double distance = distance(Transform2D.copy(x), Transform2D.copy(y));
        if(n == 1)
        {
            baseStructure(Transform2D.copy(x), Transform2D.copy(y), theta, 0);
        }else {

            baseStructure(Transform2D.copy(x), Transform2D.copy(y), theta, distance);
        }
        if(n > 1)
        {

            double[] tempX = Transform2D.scale(Transform2D.translate(x, y, -.6, -.2, 'x'), y, .5, 'x');
            double[] tempY = Transform2D.scale(tempX, Transform2D.translate(x, y, -.6, -.2, 'y'), .5, 'y');
            draw(n - 1, tempX, tempY, theta);
            draw(n - 1, Transform2D.translate(tempX, tempY, distance* -2, -.2, 'x'),
                    Transform2D.translate(tempX, tempY, distance* -2, -.2, 'y'), theta);
            //draw(n-1, );

        }
    }

    private static void flake(double[] x, double[] y, double theta)
    {
        double[] copiedX = Transform2D.rotate(Transform2D.copy(x),Transform2D.copy(y), theta, 'x');
        double[] copiedY = Transform2D.rotate(Transform2D.copy(x),Transform2D.copy(y), theta, 'y');

        //double[][] rotated = Transform2D.rotate(Transform2D.copy(x), Transform2D.copy(y), theta);

        for(int i = 0; i < 5; i++)
        {
            double[] rotatedX = copiedX;
            double[] rotatedY = copiedY;

            StdDraw.line(rotatedX[0], rotatedY[0], rotatedX[1], rotatedY[1]);
        }
    }

    private static double distance(double[] x, double[] y)
    {
        return Math.sqrt(Math.pow(x[1] - x[0], 2) + Math.pow(y[1] - y[0], 2));
    }

    public static void baseStructure(double[] x, double[] y, double theta, double len)
    {
        double[][] rotated = rotate(Transform2D.copy(x), Transform2D.copy(y), theta);

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

    public static void main(String[] args)
    {
        StdDraw.setXscale(-1, 1);
        StdDraw.setYscale(-1, 1);
        double theta = 72;
        double[] x = {0, 0};
        double[] y = {-.6, 0};
        baseStructure(x, y, theta, 0);
        draw(5, x, y, theta);
    }
}
