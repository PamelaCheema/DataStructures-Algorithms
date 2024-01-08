import java.security.cert.CertificateNotYetValidException;

public class Sierpinski {

    public static double height(double length)
    {
        double height = Math.sqrt(Math.pow(length, 2) - Math.pow(length/2.0, 2));
        return height;
    }

    public static void emptyTriangle(double x, double y, double length)
    {
        StdDraw.setPenColor(StdDraw.RED);
        double[] xVal = new double[3];
        double[] yVal = new double[3];
        xVal[0] = x + length;
        yVal[0] = y;
        xVal[1] = x - length;
        yVal[1] = y;
        xVal[2] = x;
        yVal[2] = y + (height(length)* 2);

        StdDraw.polygon(xVal, yVal);
    }

    public static void emptyTriangle(double[] x, double[] y, double length)
    {
        StdDraw.polygon(x, y);
    }

    public static void filledTriangle(int n, double x, double y, double length)
    {
        //StdDraw.setPenColor(StdDraw.RED);
        double[] xVal = new double[3];
        double[] yVal = new double[3];
        xVal[0] = x;
        yVal[0] = y;
        xVal[1] = x - (length/2.0);
        yVal[1] = y + height(length);
        xVal[2] = xVal[1] + length;
        yVal[2] = yVal[1];

        if(n%2 == 0)
        {
            StdDraw.setPenColor(154,205,50);
        }else{
            StdDraw.setPenColor(StdDraw.RED);
        }

        StdDraw.filledPolygon(xVal, yVal);
    }

    public static void sierpinski(int n, double x, double y, double length)
    {
        if(n == 1)
        {
            filledTriangle(n, x, y, length);
        }

        filledTriangle(n, x, y, length);
        if(n > 1)
        {
            double half = length/2;
            sierpinski(n - 1, x + half, y, half);
            sierpinski(n - 1, x - half, y, half);
            sierpinski(n - 1, x, y + height(length), half);
        }
    }

    public static void main(String[] args)
    {

        double[] x = {0, .5, 1};
        double[] y = {0, height(.5)*2, 0};

        emptyTriangle(x, y, 1);
        sierpinski(10, .5,0, .5);
    }
}
