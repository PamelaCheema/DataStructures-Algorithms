public class Transform2D {

    public static double[] copy(double[] array)
    {
        double[] copiedArray = new double[array.length];
        for(int i = 0; i < array.length; i++)
        {
            copiedArray[i] = array[i];
        }
       return copiedArray;
    }

    public static void scale(double[] x, double[] y, double alpha)
    {
        for(int i = 0; i < x.length; i++)
        {
            x[i] = x[i] * alpha;
            y[i] = y[i] * alpha;
        }
    }

    public static double[] scale(double[] x, double[] y, double alpha, char z)
    {
        for(int i = 0; i < x.length; i++)
        {
            x[i] = x[i] * alpha;
            y[i] = y[i] * alpha;
        }

        if( z == 'x')
        {
            return x;
        }

        return y;
    }

    public static void translate(double[] x, double[] y, double dx, double dy)
    {
        for(int i = 0; i < x.length; i++)
        {
            x[i] = x[i] + dx;
            y[i] = y[i] + dy;
        }
    }

    public static double translate(double x, double y, double dx, double dy, char z)
    {

            x = x + dx;
            y = y + dy;

        if( z == 'x')
        {
            return x;
        }

        return y;

    }

    public static double[] translate(double[] x, double[] y, double dx, double dy, char z)
    {
        for(int i = 0; i < x.length; i++)
        {
            x[i] = x[i] + dx;
            y[i] = y[i] + dy;
        }

        if( z == 'x')
        {
            return x;
        }

        return y;
    }

    public static void rotate(double[] x, double[] y, double theta )
    {
        for(int i = 0; i < x.length; i++)
        {
            double temp = x[i];
            x[i] = (x[i] * Math.cos(Math.toRadians(theta))) - (y[i] * Math.sin(Math.toRadians(theta)));
            y[i] = (y[i] * Math.cos(Math.toRadians(theta))) + (x[i] * Math.sin(Math.toRadians(theta)));
        }
    }

    public static double[] rotate(double[] x, double[] y, double theta, char z)
    {
        for(int i = 0; i < x.length; i++)
        {
            double temp = x[i];
            x[i] = (x[i] * Math.cos(Math.toRadians(theta))) - (y[i] * Math.sin(Math.toRadians(theta)));
            y[i] = (y[i] * Math.cos(Math.toRadians(theta))) + (temp * Math.sin(Math.toRadians(theta)));
        }

        if( z == 'x')
        {
            return x;
        }

        return y;
    }

    public static void main(String[] args)
    {

    }
}
